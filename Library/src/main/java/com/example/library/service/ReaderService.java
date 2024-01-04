package com.example.library.service;

import com.example.library.domain.entity.Reader;
import com.example.library.exception.EntityNotFoundException;
import com.example.library.exception.InvalidEmailException;
import com.example.library.exception.InvalidFieldException;
import com.example.library.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderService {

    @Autowired
    private final ReaderRepository readerRepository;

    public List<Reader> getAllReaders() {
        return readerRepository.getAllReaders();
    }

    public Reader getReaderById(String readerId) {
        Reader reader = readerRepository.getReaderById(readerId);
        if (reader == null) {
            throw new EntityNotFoundException("The reader was not found!");
        }
        return reader;
    }

    public String createReader(Reader reader) {
        validateReader(reader);

        if (!readerRepository.isEmailUnique(reader.getEmail())) {
            throw new InvalidEmailException("Email " + reader.getEmail() + " is not unique!");
        }

        readerRepository.createReader(reader);
        return "SUCCESS";
    }

    public String updateReader(String readerId, Reader updatedReader) {
        validateReader(updatedReader);

        if (!readerRepository.isReaderExists(readerId)) {
            throw new EntityNotFoundException("The reader was not found!");
        }
        readerRepository.updateReader(readerId, updatedReader);
        return "SUCCESS";
    }

    public String deleteReader(String readerId) {
        if (!readerRepository.isReaderExists(readerId)) {
            throw new EntityNotFoundException("The reader was not found!");
        }
        readerRepository.deleteReader(readerId);
        return "SUCCESS";
    }

    private void validateReader(Reader reader) {
        if (StringUtils.isEmpty(reader.getFirstname()) || StringUtils.isEmpty(reader.getLastname())
                || StringUtils.isEmpty(reader.getEmail()) || StringUtils.isEmpty(reader.getPhone())) {
            throw new InvalidFieldException("Complete all the fields!");
        }

        if (!isValidEmail(reader.getEmail())) {
            throw new InvalidEmailException("The email address: " + reader.getEmail() + " is not valid!");
        }
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }
}

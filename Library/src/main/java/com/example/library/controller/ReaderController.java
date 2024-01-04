package com.example.library.controller;

import com.example.library.domain.Reader;
import com.example.library.exception.EntityNotFoundException;
import com.example.library.service.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ReaderController {

    private final ReaderService readerService;

    @GetMapping("/readers")
    public ResponseEntity<List<Reader>> getAllReaders() {
        List<Reader> readers = readerService.getAllReaders();
        return new ResponseEntity<>(readers, HttpStatus.OK);
    }

    @GetMapping("/readers/{readerId}")
    public ResponseEntity<Object> getReaderById(@PathVariable String readerId) {
        try {
            Reader reader = readerService.getReaderById(readerId);
            return new ResponseEntity<>(reader, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(createErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(createErrorResponse("There was an error when retrieving the reader!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/readers")
    public ResponseEntity<Object> createReader(@RequestBody Reader reader) {
        try {
            String result = readerService.createReader(reader);
            if ("SUCCESS".equals(result)) {
                return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(createErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/readers/{readerId}")
    public ResponseEntity<Object> updateReader(@RequestBody Reader reader, @PathVariable String readerId) {
        try {
            String result = readerService.updateReader(readerId, reader);
            if ("SUCCESS".equals(result)) {
                return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(createErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/readers/{readerId}")
    public ResponseEntity<Object> deleteReader(@PathVariable String readerId) {
        try {
            String result = readerService.deleteReader(readerId);
            if ("SUCCESS".equals(result)) {
                return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(createErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    private Map<String, Object> createErrorResponse(String errorMessage) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("Error message", errorMessage);
        return errorResponse;
    }
}

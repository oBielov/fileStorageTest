package com.cml.test.filestorage.controller;

import com.cml.test.filestorage.documents.File;
import com.cml.test.filestorage.service.FileService;
import com.cml.test.filestorage.service.domain.data.TagsData;
import com.cml.test.filestorage.service.domain.exceptions.TagsNotFoundException;
import com.cml.test.filestorage.service.domain.response.BadResponse;
import com.cml.test.filestorage.service.domain.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.FileNotFoundException;

@RestController
@Validated
@RequestMapping("/file")
public class FileController {

    private final FileService service;

    @Autowired
    public FileController(FileService service){
        this.service = service;
    }

    @PostMapping("/save-file")
    public ResponseEntity<Response> saveFile(@Valid @RequestBody File file){
        return new ResponseEntity<>(service.saveFile(file), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteFile(@PathVariable String id){
        return new ResponseEntity<>(service.deleteFile(id), HttpStatus.OK);
    }

    @PostMapping("/{id}/tags")
    public ResponseEntity<Response> addTags(@RequestBody TagsData data){
        return new ResponseEntity<>(service.addTags(data), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/tags")
    public ResponseEntity<Response> deleteTags(@RequestBody TagsData data){
        return new ResponseEntity<>(service.deleteTags(data), HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleBadRequestException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(new BadResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<Response> handleNotFoundException() {
        return new ResponseEntity<>(new BadResponse(false,"File not found"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TagsNotFoundException.class)
    public ResponseEntity<Response> handleTagsException(TagsNotFoundException e){
        return new ResponseEntity<>(new BadResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}

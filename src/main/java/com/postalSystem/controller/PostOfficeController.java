package com.postalSystem.controller;

import com.postalSystem.dto.PostOfficeDto;
import com.postalSystem.service.PostOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PostOfficeController {

    @Autowired
    private PostOfficeService officeService;

    @PostMapping(path = "registration/postOffices", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerPostOffice(@Valid @RequestBody PostOfficeDto officeDto) {
        officeService.createPostOffice(officeDto);
    }

}

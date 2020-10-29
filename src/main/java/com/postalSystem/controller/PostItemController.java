package com.postalSystem.controller;

import com.postalSystem.dto.PostItemDto;
import com.postalSystem.model.PostItem;
import com.postalSystem.model.PostOffice;
import com.postalSystem.service.PostItemService;
import com.postalSystem.service.PostOfficeService;
import com.postalSystem.service.validationPostItem.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PostItemController {

    @Autowired
    private PostItemService postItemService;

    @Autowired
    private PostOfficeService postOfficeService;

    @Autowired
    private ValidationService validationService;

    @PostMapping(value = "/registration/postItems", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerPostItem(@Valid @RequestBody PostItemDto postItemDto) {
        validationService.doValidation(new CreatedPostItemValidation(postItemDto, postOfficeService));

        postItemService.create(postItemDto);
    }

    @PutMapping(value = "/sending/postItem", produces = "application/json")
    public void sendPostItem(@Valid @RequestBody PostItemDto postItemDto) {
        PostOffice postOffice = postOfficeService.findByIndex(postItemDto.getTargetPostOfficeIndex());
        PostItem postItem = postItemService.findById(postItemDto.getId());

        validationService.doValidation(new SentPostItemValidation(postItem, postOffice));

        postItemService.sendToPostOffice(postItem, postOffice);
    }

    @PutMapping(value = "arrival/postItem", produces = "application/json")
    public void arrivalPostItem(@Valid @RequestBody PostItemDto postItemDto) {
        PostOffice postOffice = postOfficeService.findByIndex(postItemDto.getTargetPostOfficeIndex());
        PostItem postItem = postItemService.findById(postItemDto.getId());

        validationService.doValidation(new ArrivedPostItemValidation(postItem, postOffice));

        postItemService.arrivalAtPostOffice(postItem, postOffice);
    }

    @PutMapping(value = "delivery/postItem", produces = "application/json")
    public void deliveryPostItemToClient(@Valid @RequestBody PostItemDto postItemDto) {
        PostItem postItem = postItemService.findById(postItemDto.getId());

        validationService.doValidation(new DeliveredPostItemValidation(postItem));

        postItemService.deliveryToClient(postItem);
    }


}

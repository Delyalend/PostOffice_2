package com.postalSystem.controller;

import com.postalSystem.dto.PostItemDto;
import com.postalSystem.model.PostItem;
import com.postalSystem.model.PostOffice;
import com.postalSystem.service.PostItemService;
import com.postalSystem.service.PostOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PostItemController {

    @Autowired
    private PostItemService postItemService;

    @Autowired
    private PostOfficeService officeService;

    @PostMapping(value = "/registration/postItems", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerPostItem(@Valid @RequestBody PostItemDto postItemDto) {
        postItemService.create(postItemDto);
    }

    @PutMapping(value = "/sending/postItem", produces = "application/json")
    public void sendPostItem(@Valid @RequestBody PostItemDto postItemDto) {
        PostOffice office = officeService.findByIndex(postItemDto.getTargetPostOfficeIndex());
        PostItem postItem = postItemService.findById(postItemDto.getId());
        postItemService.sendToPostOffice(postItem, office);
    }

    @PutMapping(value = "arrival/postItem", produces = "application/json")
    public void arrivalPostItem(@Valid @RequestBody PostItemDto postItemDto) {
        PostOffice office = officeService.findByIndex(postItemDto.getTargetPostOfficeIndex());
        PostItem postItem = postItemService.findById(postItemDto.getId());
        postItemService.arrival(postItem, office);
    }

    @PutMapping(value = "delivery/postItem", produces = "application/json")
    public void deliveryPostItemToClient(@Valid @RequestBody PostItemDto postItemDto) {
        PostItem postItem = postItemService.findById(postItemDto.getId());
        postItemService.delivery(postItem);
    }


}

package com.postalSystem.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.postalSystem.model.PostItem;
import com.postalSystem.service.PostItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @Autowired
    private PostItemService postItemService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping(path = "postItems/{postItemId}/status", produces = "application/json")
    public @ResponseBody
    String getStatus(@PathVariable Long postItemId) throws JsonProcessingException {
        PostItem postItem = postItemService.findById(postItemId);
        return objectMapper.writeValueAsString(postItem.getStatus());
    }

}

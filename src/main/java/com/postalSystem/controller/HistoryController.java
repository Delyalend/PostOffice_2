package com.postalSystem.controller;

import com.postalSystem.model.History;
import com.postalSystem.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @GetMapping("postItems/{postItemId}/histories")
    public @ResponseBody
    List<History> getHistories(@PathVariable Long postItemId) {
        return historyService.getHistoriesByPostItemId(postItemId);
    }

}

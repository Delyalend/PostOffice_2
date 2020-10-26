package com.postalSystem.service;

import com.postalSystem.model.History;
import com.postalSystem.model.PostItem;

import java.util.List;

public interface HistoryService {
    void createHistory(PostItem postItem, String content);
    List<History> getHistoriesByPostItemId(long postItemId);
}

package com.postalSystem.service;

import com.postalSystem.model.History;
import com.postalSystem.model.PostItem;
import com.postalSystem.service.postItemHistory.HistoryAbstract;

import java.util.List;

public interface HistoryService {
    //void createHistory(PostItem postItem, String content);
    void createHistory(PostItem postItem, HistoryAbstract history);
    List<History> getHistoriesByPostItemId(long postItemId);
}

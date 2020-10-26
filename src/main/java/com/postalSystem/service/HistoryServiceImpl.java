package com.postalSystem.service;

import com.postalSystem.exception.NoSuchPostItemException;
import com.postalSystem.model.History;
import com.postalSystem.model.PostItem;
import com.postalSystem.repository.HistoryRepo;
import com.postalSystem.repository.PostItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private PostItemRepo postItemRepo;

    @Autowired
    private HistoryRepo historyRepo;

    @Override
    public void createHistory(PostItem postItem, String content) {
        History history = History.builder()
                .postItem(postItem)
                .content(content)
                .date(new java.sql.Date(new java.util.Date().getTime()))
                .build();
        historyRepo.save(history);
    }

    @Override
    public List<History> getHistoriesByPostItemId(long postItemId) {
        Optional<PostItem> postItemOptional = postItemRepo.findById(postItemId);
        if (!postItemOptional.isPresent()) {
            throw new NoSuchPostItemException();
        }
        PostItem postItem = postItemOptional.get();
        return postItem.getHistories();
    }
}

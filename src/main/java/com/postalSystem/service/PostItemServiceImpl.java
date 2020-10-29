package com.postalSystem.service;

import com.postalSystem.dto.PostItemDto;
import com.postalSystem.exception.NoSuchPostItemException;
import com.postalSystem.model.PostItem;
import com.postalSystem.model.PostOffice;
import com.postalSystem.model.StatusPostItem;
import com.postalSystem.repository.PostItemRepo;
import com.postalSystem.service.postItemHistory.CreatedPostItemHistory;
import com.postalSystem.service.postItemHistory.DeliveredPostItemHistory;
import com.postalSystem.service.postItemHistory.SentPostItemHistory;
import com.postalSystem.service.postItemHistory.WaitingPostItemHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PostItemServiceImpl implements PostItemService {

    @Autowired
    private PostItemRepo postItemRepo;

    @Autowired
    private PostOfficeService postOfficeService;

    @Autowired
    private HistoryService historyService;


    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void create(PostItemDto postItemDto) {
        PostItem postItem = getPostItemFromDto(postItemDto);
        postItemRepo.save(postItem);
        historyService.createHistory(postItem, new CreatedPostItemHistory());
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void sendToPostOffice(PostItem postItem, PostOffice postOffice) {
        postItem.setNextPostOfficeIndex(postOffice.getIndex());
        postItem.setStatus(StatusPostItem.delivers);
        postItemRepo.save(postItem);

        historyService.createHistory(postItem, new SentPostItemHistory());
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void arrivalAtPostOffice(PostItem postItem, PostOffice postOffice) {
        if (postItem.getTargetPostOffice().getIndex() == postOffice.getIndex()) {
            postItem.setStatus(StatusPostItem.waitingClient);
        } else {
            postItem.setStatus(StatusPostItem.waitingAtIntermediatePostOffice);
        }
        postItem.setCurPostOfficeIndex(postOffice.getIndex());
        postItemRepo.save(postItem);

        historyService.createHistory(postItem, new WaitingPostItemHistory());
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void deliveryToClient(PostItem postItem) {
        postItem.setStatus(StatusPostItem.isDelivered);
        postItem.setNextPostOfficeIndex(0);
        postItem.setCurPostOfficeIndex(0);
        postItemRepo.save(postItem);

        historyService.createHistory(postItem, new DeliveredPostItemHistory());
    }

    @Override
    public PostItem findById(long id) {
        Optional<PostItem> postItem = postItemRepo.findById(id);
        if (!postItem.isPresent()) {
            throw new NoSuchPostItemException();
        }
        return postItem.get();
    }

    private PostItem getPostItemFromDto(PostItemDto postItemDto) {
        PostOffice targetOffice = postOfficeService.findByIndex(postItemDto.getTargetPostOfficeIndex());
        return PostItem.builder()
                .targetPostOffice(targetOffice)
                .status(StatusPostItem.registering)
                .curPostOfficeIndex(postItemDto.getCurrentPostOfficeIndex())
                .nameRecipient(postItemDto.getNameRecipient())
                .addressRecipient(postItemDto.getAddressRecipient())
                .type(postItemDto.getType())
                .build();
    }

}

package com.postalSystem.service;

import com.postalSystem.dto.PostItemDto;
import com.postalSystem.exception.IncorrectIndexPostOfficeException;
import com.postalSystem.exception.NoSuchPostItemException;
import com.postalSystem.exception.NoSuchPostOfficeException;
import com.postalSystem.exception.UnavailableActionAtCurrentStatusException;
import com.postalSystem.model.PostItem;
import com.postalSystem.model.PostOffice;
import com.postalSystem.model.StatusPostItem;
import com.postalSystem.repository.PostItemRepo;
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
    private PostOfficeService officeService;

    @Autowired
    private HistoryService historyService;


    @Override
    public PostItem findById(long id) {
        Optional<PostItem> postItem = postItemRepo.findById(id);
        if (!postItem.isPresent()) {
            throw new NoSuchPostItemException();
        }
        return postItem.get();
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void create(PostItemDto postItemDto) {
        //Регистрировать посылку для отправки в тот же офис нельзя
        if(postItemDto.getTargetPostOfficeIndex() == postItemDto.getCurPostOfficeIndex()) {
            throw new IncorrectIndexPostOfficeException();
        }

        int startOfficeIndex = postItemDto.getCurPostOfficeIndex();

        if(!officeService.hasPostOffice(startOfficeIndex)) {
            throw new NoSuchPostOfficeException();
        }

        PostOffice targetOffice = officeService.findByIndex(postItemDto.getTargetPostOfficeIndex());
        PostItem postItem = PostItem.builder()
                .curPostOfficeIndex(startOfficeIndex)
                .targetPostOffice(targetOffice)
                .status(StatusPostItem.registering)
                .nameRecipient(postItemDto.getNameRecipient())
                .addressRecipient(postItemDto.getAddressRecipient())
                .type(postItemDto.getType())
                .build();
        postItemRepo.save(postItem);
        String contentHistory = "The post item is registered in the post office with index = " + startOfficeIndex +
                                " to post with index = " + targetOffice.getIndex();
        historyService.createHistory(postItem, contentHistory);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void sendToPostOffice(PostItem postItem, PostOffice office) {
        //Отправлять посылку в тот же офис нельзя
        if(postItem.getCurPostOfficeIndex() == office.getIndex()) {
            throw new IncorrectIndexPostOfficeException();
        }

        StatusPostItem status = postItem.getStatus();
        if (status == StatusPostItem.registering || status == StatusPostItem.waitingAtIntermediatePostOffice) {
            postItem.setNextPostOfficeIndex(office.getIndex());
            postItem.setStatus(StatusPostItem.delivers);
            String contentHistory = "The post item was sent to the post office with index = " + office.getIndex();
            historyService.createHistory(postItem, contentHistory);
            postItemRepo.save(postItem);
        } else {
            throw new UnavailableActionAtCurrentStatusException();
        }
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void arrival(PostItem postItem, PostOffice office) {

        if (postItem.getStatus() != StatusPostItem.delivers) {
            throw new UnavailableActionAtCurrentStatusException();
        }

        //Получать посылку не там, куда она была направлена - нельзя
        if (postItem.getNextPostOfficeIndex() != office.getIndex()) {
            throw new IncorrectIndexPostOfficeException();
        }

        if (postItem.getTargetPostOffice().getIndex() == office.getIndex()) {
            postItem.setStatus(StatusPostItem.waitingClient);
        } else {
            postItem.setStatus(StatusPostItem.waitingAtIntermediatePostOffice);
        }

        postItem.setCurPostOfficeIndex(office.getIndex());
        postItemRepo.save(postItem);

        String contentHistory = "The post item arrived at the post office with index  = " + office.getIndex();
        historyService.createHistory(postItem, contentHistory);

    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void delivery(PostItem postItem) {
        if (postItem.getStatus() != StatusPostItem.waitingClient) {
            throw new UnavailableActionAtCurrentStatusException();
        }
        postItem.setStatus(StatusPostItem.isDelivered);
        postItem.setNextPostOfficeIndex(0);
        postItem.setCurPostOfficeIndex(0);
        postItemRepo.save(postItem);

        String contentHistory = "The post item is delivered";
        historyService.createHistory(postItem, contentHistory);
    }
}

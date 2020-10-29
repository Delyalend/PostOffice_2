package com.postalSystem.service.validationPostItem;

import com.postalSystem.exception.UnavailableActionAtCurrentStatusException;
import com.postalSystem.model.PostItem;
import com.postalSystem.model.StatusPostItem;

public class DeliveredPostItemValidation extends ValidationAbstract {

    public DeliveredPostItemValidation(PostItem postItem) {
        this.postItem = postItem;
    }

    @Override
    public void validate() {
        if (postItem.getStatus() != StatusPostItem.waitingClient) {
            throw new UnavailableActionAtCurrentStatusException();
        }
    }
}

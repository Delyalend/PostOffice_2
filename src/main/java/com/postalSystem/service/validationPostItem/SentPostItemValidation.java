package com.postalSystem.service.validationPostItem;

import com.postalSystem.exception.IncorrectIndexPostOfficeException;
import com.postalSystem.exception.UnavailableActionAtCurrentStatusException;
import com.postalSystem.model.PostItem;
import com.postalSystem.model.PostOffice;
import com.postalSystem.model.StatusPostItem;

public class SentPostItemValidation extends ValidationAbstract {

    public SentPostItemValidation(PostItem postItem, PostOffice postOffice) {
        this.postItem = postItem;
        this.postOffice = postOffice;
    }

    @Override
    public void validate() {
        //Отправлять посылку в тот же офис нельзя
        if (postItem.getCurPostOfficeIndex() == postOffice.getIndex()) {
            throw new IncorrectIndexPostOfficeException();
        }
        StatusPostItem status = postItem.getStatus();
        if (status != StatusPostItem.registering && status != StatusPostItem.waitingAtIntermediatePostOffice) {
            throw new UnavailableActionAtCurrentStatusException();
        }
    }
}

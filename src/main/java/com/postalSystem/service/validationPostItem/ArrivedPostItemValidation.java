package com.postalSystem.service.validationPostItem;

import com.postalSystem.exception.IncorrectIndexPostOfficeException;
import com.postalSystem.exception.UnavailableActionAtCurrentStatusException;
import com.postalSystem.model.PostItem;
import com.postalSystem.model.PostOffice;
import com.postalSystem.model.StatusPostItem;

public class ArrivedPostItemValidation extends ValidationAbstract {

    public ArrivedPostItemValidation(PostItem postItem, PostOffice postOffice){
        this.postItem = postItem;
        this.postOffice = postOffice;
    }


    @Override
    public void validate() {
        if (postItem.getStatus() != StatusPostItem.delivers) {
            throw new UnavailableActionAtCurrentStatusException();
        }
        //Получать посылку не там, куда она была направлена - нельзя
        if (postItem.getNextPostOfficeIndex() != postOffice.getIndex()) {
            throw new IncorrectIndexPostOfficeException();
        }
    }
}

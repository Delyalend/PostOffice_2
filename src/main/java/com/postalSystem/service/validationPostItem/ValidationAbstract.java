package com.postalSystem.service.validationPostItem;

import com.postalSystem.dto.PostItemDto;
import com.postalSystem.model.PostItem;
import com.postalSystem.model.PostOffice;

public abstract class ValidationAbstract {
    protected PostItem postItem;
    protected PostItemDto postItemDto;
    protected PostOffice postOffice;

    public abstract void validate();
}

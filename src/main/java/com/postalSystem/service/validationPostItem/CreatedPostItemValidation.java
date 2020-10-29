package com.postalSystem.service.validationPostItem;

import com.postalSystem.dto.PostItemDto;
import com.postalSystem.exception.IncorrectIndexPostOfficeException;
import com.postalSystem.exception.NoSuchPostOfficeException;
import com.postalSystem.service.PostOfficeService;

public class CreatedPostItemValidation extends ValidationAbstract {


    private PostOfficeService postOfficeService;

    public CreatedPostItemValidation(PostItemDto postItemDto, PostOfficeService postOfficeService) {
        this.postItemDto = postItemDto;
        this.postOfficeService = postOfficeService;
    }

    @Override
    public void validate() {
        int startOfficeIndex = postItemDto.getCurrentPostOfficeIndex();
        //Регистрировать посылку для отправки в тот же офис нельзя
        if (postItemDto.getTargetPostOfficeIndex() == startOfficeIndex) {
            throw new IncorrectIndexPostOfficeException();
        }
        if (!postOfficeService.hasPostOffice(startOfficeIndex)) {
            throw new NoSuchPostOfficeException();
        }
    }

}

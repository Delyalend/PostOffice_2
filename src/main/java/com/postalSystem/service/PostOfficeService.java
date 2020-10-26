package com.postalSystem.service;

import com.postalSystem.dto.PostOfficeDto;
import com.postalSystem.model.PostOffice;

public interface PostOfficeService {
    PostOffice findByIndex(int index);
    boolean hasPostOffice(int index);
    void createPostOffice(PostOfficeDto postOfficeDto);
}

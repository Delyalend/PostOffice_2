package com.postalSystem.service;

import com.postalSystem.dto.PostOfficeDto;
import com.postalSystem.exception.NoSuchPostOfficeException;
import com.postalSystem.exception.PostOfficeAlreadyExists;
import com.postalSystem.model.PostOffice;
import com.postalSystem.repository.PostOfficeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostOfficeServiceImpl implements PostOfficeService {

    @Autowired
    private PostOfficeRepo postOfficeRepo;

    @Override
    public PostOffice findByIndex(int index) {
        Optional<PostOffice> postOffice = postOfficeRepo.findByIndex(index);
        if (!postOffice.isPresent()) {
            throw new NoSuchPostOfficeException();
        }
        return postOffice.get();
    }

    @Override
    public boolean hasPostOffice(int index) {
        return postOfficeRepo.findByIndex(index).isPresent();
    }

    @Override
    public void createPostOffice(PostOfficeDto postOfficeDto) {
        if (hasPostOffice(postOfficeDto.getIndex())) {
            throw new PostOfficeAlreadyExists();
        }
        PostOffice office = PostOffice.builder()
                .index(postOfficeDto.getIndex())
                .address(postOfficeDto.getAddress())
                .name(postOfficeDto.getName()).build();
        postOfficeRepo.save(office);
    }

}

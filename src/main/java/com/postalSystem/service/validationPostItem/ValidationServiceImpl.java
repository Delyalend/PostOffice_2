package com.postalSystem.service.validationPostItem;

import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImpl implements ValidationService {
    @Override
    public void doValidation(ValidationAbstract validationAbstract) {
        validationAbstract.validate();
    }
}

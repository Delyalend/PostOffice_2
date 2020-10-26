package com.postalSystem.dto;

import com.postalSystem.model.TypePostItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostItemDto {
    private long id;
    private int curPostOfficeIndex;
    private int targetPostOfficeIndex;
    private TypePostItem type;
    private String nameRecipient;
    private String addressRecipient;
}

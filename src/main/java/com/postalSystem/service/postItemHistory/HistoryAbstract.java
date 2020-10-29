package com.postalSystem.service.postItemHistory;

import com.postalSystem.model.PostItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class HistoryAbstract {

    protected PostItem postItem;

    public abstract String generateContentHistory();

}

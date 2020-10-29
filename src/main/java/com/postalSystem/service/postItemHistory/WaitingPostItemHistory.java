package com.postalSystem.service.postItemHistory;

public class WaitingPostItemHistory extends HistoryAbstract {

    @Override
    public String generateContentHistory() {
        return "The post item arrived at the post office with index  = " + postItem.getCurPostOfficeIndex();
    }

}

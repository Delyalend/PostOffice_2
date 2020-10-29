package com.postalSystem.service.postItemHistory;

public class SentPostItemHistory extends HistoryAbstract {

    @Override
    public String generateContentHistory() {
        return "The post item was sent to the post office with index = " + postItem.getNextPostOfficeIndex();
    }

}

package com.postalSystem.service.postItemHistory;

public class DeliveredPostItemHistory extends HistoryAbstract {

    @Override
    public String generateContentHistory() {
        return "The post item is delivered";
    }

}

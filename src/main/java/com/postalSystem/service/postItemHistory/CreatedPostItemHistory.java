package com.postalSystem.service.postItemHistory;

public class CreatedPostItemHistory extends HistoryAbstract {

    @Override
    public String generateContentHistory() {
        return  "The post item is registered in the post office" +
                " with index = " + postItem.getCurPostOfficeIndex() +
                " to post with index = " + postItem.getTargetPostOffice().getIndex();
    }

}


package com.example.gp.SingleAuctionModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Authentication2 {

    @SerializedName("single_auction")
    @Expose
    private SingleAuction singleAuction;

    public SingleAuction getSingleAuction() {
        return singleAuction;
    }

    public void setSingleAuction(SingleAuction singleAuction) {
        this.singleAuction = singleAuction;
    }

}

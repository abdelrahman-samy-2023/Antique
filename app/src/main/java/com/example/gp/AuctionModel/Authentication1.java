
package com.example.gp.AuctionModel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Authentication1 {

    @SerializedName("auctions_data")
    @Expose
    private AuctionsDatum[] auctionsData;

    public AuctionsDatum[] getAuctionsData() {
        return auctionsData;
    }

    public void setAuctionsData(AuctionsDatum[] auctionsData) {
        this.auctionsData = auctionsData;
    }
}

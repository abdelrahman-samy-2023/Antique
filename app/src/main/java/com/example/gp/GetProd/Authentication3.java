
package com.example.gp.GetProd;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Authentication3 {

    @SerializedName("auction_products")
    @Expose
    private AuctionProduct[] auctionProducts = null;

    public AuctionProduct[] getAuctionProducts() {
        return auctionProducts;
    }

    public void setAuctionProducts(AuctionProduct[] auctionProducts) {
        this.auctionProducts = auctionProducts;
    }
}

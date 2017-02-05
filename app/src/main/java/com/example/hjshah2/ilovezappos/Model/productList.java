package com.example.hjshah2.ilovezappos.Model;

/**
 * Created by hjshah2 on 2/5/2017.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class productList {
    @SerializedName("results")
    private List<ProductDescription> pList;

    @SerializedName("inputTerm")
    private String term;

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        System.out.println("inputTerm : " + term);
        this.term = term;
    }



    public List<ProductDescription> getProductList() {
        return pList;
    }

    public void setProductList(List<ProductDescription> productList) {
        this.pList = productList;
    }
}

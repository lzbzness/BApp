package com.bzyness.bzyness.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by munshama on 1/29/2017.
 */

public class Category {

    @SerializedName("categoryID")
    @Expose
    private int categoryID;
    @SerializedName("categoryName")
    @Expose
    private Object categoryName;

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public Object getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(Object categoryName) {
        this.categoryName = categoryName;
    }

}
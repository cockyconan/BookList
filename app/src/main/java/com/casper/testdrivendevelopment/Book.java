package com.casper.testdrivendevelopment;

/**
 * Created by jszx on 2019/9/24.
 */

public class Book {
    private String Title;
    private int CoverResourceId;
    public int getCoverResourceId() {
        return CoverResourceId;
    }
    public void setCoverResourceId(int coverResourceId) {
        CoverResourceId = coverResourceId;
    }
    public String getTitle() {
        return Title;
    }
    public void setTitle(String title) {
        Title = title;
    }
    public Book( String title,int coverResourceId) {
        CoverResourceId = coverResourceId;
        Title = title;
    }



}

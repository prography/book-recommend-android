package org.techtown.a1006_bibly;

import android.provider.BaseColumns;

import java.io.Serializable;

public class DataModel implements Serializable {

    public String post_title;
    public String post_content;
    //public String cate;

    public DataModel() {
        setPost_title("null~");
        setPost_content("null~");
        //setCate("null~");
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public String getPost_content() {
        return post_content;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
    }

//    public String getCate() {
//        return cate;
//    }
//
//    public void setCate(String cate) {
//        this.cate = cate;
//    }
}

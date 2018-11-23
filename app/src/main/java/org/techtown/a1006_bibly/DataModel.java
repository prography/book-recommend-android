package org.techtown.a1006_bibly;

import android.provider.BaseColumns;

public class DataModel {
    public String post_title;
    public String post_content;

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
}

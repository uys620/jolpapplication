package com.example.myapplication.model;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class comment {

    private String nickname;
    private String contents;
    @ServerTimestamp
    private Date date;

    public comment(String nickname, String contents) {
        this.nickname = nickname;
        this.contents = contents;
    }

    public String getNickname() {
        return nickname;
    }

    public String getContents() {
        return contents;
    }

    public Date getDate() {
        return date;
    }
}

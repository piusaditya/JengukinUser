package com.example.affereaflaw.uxdatabase;

/**
 * Created by Affe Reaflaw on 10/23/2017.
 */
public class MessagesGetSet {
    private String pesan, from;

    public MessagesGetSet(String pesan, String from) {
        this.pesan = pesan;
        this.from = from;
    }

    public MessagesGetSet(){

    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}

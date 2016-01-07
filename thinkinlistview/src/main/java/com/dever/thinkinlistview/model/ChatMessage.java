package com.dever.thinkinlistview.model;

/**
 * Created by admin on 2015/12/26.
 */
public class ChatMessage {
    private String message;
    private long time;
    private String address;//发信人或者收信人
    private boolean isSelf;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isSelf() {
        return isSelf;
    }

    public void setIsSelf(boolean isSelf) {
        this.isSelf = isSelf;
    }
}

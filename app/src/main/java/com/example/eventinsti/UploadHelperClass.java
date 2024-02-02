package com.example.eventinsti;

public class UploadHelperClass {
    private String username;
    private String verifyStatus;



    private String eventTitle;
    private String eventDsc;
    private String eventLink;
    private String eventImg;
    private String profileURL;

    public String getProfileURL() {
        return profileURL;
    }

    public void setProfileURL(String profileURL) {
        this.profileURL = profileURL;
    }

    public String getUsername() {
        return username;
    }

    public String getVerifyStatus() {
        return verifyStatus;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public String getEventDsc() {
        return eventDsc;
    }

    public String getEventLink() {
        return eventLink;
    }

    public String getEventImg() {
        return eventImg;
    }

    public UploadHelperClass(String username, String verifyStatus, String eventTitle, String eventDsc, String eventLink, String eventImg, String profileURL) {
        this.username = username;
        this.verifyStatus = verifyStatus;
        this.eventTitle = eventTitle;
        this.eventDsc = eventDsc;
        this.eventLink = eventLink;
        this.eventImg = eventImg;
        this.profileURL = profileURL;
    }
    public UploadHelperClass() {
    }
}

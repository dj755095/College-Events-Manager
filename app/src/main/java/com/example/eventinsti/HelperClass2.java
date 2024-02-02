package com.example.eventinsti;

public class HelperClass2 {
    String username ,verifyStats;
    String profileURL;

    public String getProfileURL() { return profileURL; }

    public void setProfileURL(String profileURL) { this.profileURL = profileURL; }


    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setVerifyStats(String verifyStats) {
        this.verifyStats = verifyStats;
    }
    public String getVerifyStats() {
        return verifyStats;
    }
    public HelperClass2(String username, String verifyStats, String profileURL) {
        this.username = username;
        this.verifyStats = verifyStats;
        this.profileURL = profileURL;
    }
    public HelperClass2() {
    }
}

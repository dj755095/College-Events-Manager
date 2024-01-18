package com.example.eventinsti;

public class HelperClass2 {
    String username ,verifyStats;
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
    public HelperClass2(String username, String verifyStats) {
        this.username = username;
        this.verifyStats = verifyStats;
    }
    public HelperClass2() {
    }
}

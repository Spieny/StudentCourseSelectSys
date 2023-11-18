package com.ziahh.beans;

public class Admin {

    private String adminName;
    private String adminID;
    private String password;

    public Admin(String adminName, String adminID, String password) {
        this.adminName = adminName;
        this.adminID = adminID;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }
}

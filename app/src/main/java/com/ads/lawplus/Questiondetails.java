package com.ads.lawplus;


public class Questiondetails {

    private String area;
    private String title;
    private String body;
    private String contactOption;
    private String username;
    private String answer;

    public Questiondetails() {
    }

    public Questiondetails(String area, String title, String body, String contactOption, String username, String answer) {
        this.area = area;
        this.title = title;
        this.body = body;
        this.contactOption = contactOption;
        this.username = username;
        this.answer = answer;
    }

    //getters

    public String getArea() {
        return area;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getContactOption() {
        return contactOption;
    }

    public String getUsername() {
        return username;
    }

    public String getAnswer() {
        return answer;
    }


    //setters


    public void setArea(String area) {
        this.area = area;
    }

    public void setTitle(String title) {this.title = title;}

    public void setBody(String body) {this.body = body;}

    public void setContactOption(String contactOption) {this.contactOption = contactOption;}

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAnswer(String answer) {this.answer = answer;}

}

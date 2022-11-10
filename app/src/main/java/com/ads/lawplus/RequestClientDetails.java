package com.ads.lawplus;

public class RequestClientDetails {

    private String volunteerName;
    private String volunteerEmail;
    private String requestBody;

    public RequestClientDetails(){

    }
    public RequestClientDetails(String volunteerName, String volunteerEmail, String requestBody){
        this.volunteerName = volunteerName;
        this.volunteerEmail = volunteerEmail;
        this.requestBody = requestBody;
    }

    //getters
    public String getVolunteerName() {
        return volunteerName;
    }
    public String getVolunteerEmail() {
        return volunteerEmail;
    }
    public String getRequestBody() {
        return requestBody;
    }


    //setters


    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }

    public void setVolunteerEmail(String volunteerEmail){
        this.volunteerEmail=volunteerEmail;
    }

    public void setRequestBody(String requestBody){
        this.requestBody=requestBody;
    }


}

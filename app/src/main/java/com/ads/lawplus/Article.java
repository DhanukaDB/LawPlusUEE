package com.ads.lawplus;

public class Article {
    String category, title, body ,file,id;

    Article(){}

    public Article(String category, String title, String body, String file, String id ) {
        this.category = category;
        this.title = title;
        this.body = body;
        this.file = file;
        this.id = id;

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
   return id; }
}

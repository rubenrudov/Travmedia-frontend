package com.travmedia.models;

public class Post {
    // Class for post (database document: Posts)
    private String publisher, title, content, uploadedAt;

    public Post() {

    }

    public Post(String publisher, String title, String content, String uploadedAt) {
        this.publisher = publisher;
        this.title = title;
        this.content = content;
        this.uploadedAt = uploadedAt;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(String uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public String toString(){
        return "publisher: " + this.publisher + ", title: " + this.title + ", content: " + this.content + ", upload time and date: " + this.uploadedAt;
    }
}

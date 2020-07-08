package com.hitesh.fileuploading;

public class File_Database {

    String title,url;

    public File_Database(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public File_Database() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

package com.example;

import javax.print.DocFlavor;

/**
 * Created by Praveen Thati on 3/30/17.
 */
public class Comment {
    private String content;
    private String author;

    public String getContent(){
        return this.content;
    }

    public String getAuthor()
    {
        return  this.author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String toString()
    {
        return  String.format("%s said %s",getAuthor(),getContent());
    }
}

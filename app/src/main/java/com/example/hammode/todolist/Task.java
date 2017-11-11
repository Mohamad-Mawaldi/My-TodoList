package com.example.hammode.todolist;

/**
 * Created by Hammode on 10/30/17.
 */

public class Task {
    private String Title;
    private String description;
    private String Id ;

    public Task(String Id, String Title, String description) {
        this.Id = Id;
        this.Title = Title;
        this.description = description;}

    public void setId(String Id) {this.Id = Id;}
    public void setDescription(String Discription) {this.description = description;}
    public void setTitle(String Title) {this.Title = Title;}


    public String getId() {return Id;}
    public String getTitle() {return Title;}
    public String getDescription() {return description;}
}

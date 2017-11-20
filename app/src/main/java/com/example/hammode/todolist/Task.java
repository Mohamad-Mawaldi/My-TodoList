package com.example.hammode.todolist;

//A model for my ArrayList

public class Task {
    private String Title;
    private String Description;
    private String Id;

    public Task(String Id, String Title, String Description) {
        this.Id = Id;
        this.Title = Title;
        this.Description = Description;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }


    public String getId() {
        return Id;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }



}
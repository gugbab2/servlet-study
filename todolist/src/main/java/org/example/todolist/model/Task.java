package org.example.todolist.model;

public class Task {
    private Integer id;
    private String owner;
    private String description;

    public Task() {
    }

    public Task(Integer id, String owner, String description) {
        this.id = id;
        this.owner = owner;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", owner='" + owner + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

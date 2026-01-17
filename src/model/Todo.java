package model;

public class Todo {

    private int id;
    private String task;
    private boolean completed;

    // No-argument constructor
    public Todo() {
    }

    // Constructor with task
    public Todo(String task) {
        this.task = task;
        this.completed = false;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
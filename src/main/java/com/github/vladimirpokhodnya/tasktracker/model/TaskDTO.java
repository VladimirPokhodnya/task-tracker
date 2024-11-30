package com.github.vladimirpokhodnya.tasktracker.model;

public class TaskDTO {
    private Long id;
    private String title;
    private String description;

    public TaskDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", description='" + description + '\'' +
               '}';
    }
}

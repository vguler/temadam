package com.example.myapplication.domain;

import java.util.Date;

public class Task {

    private String taskName;
    private String taskType;

    public Task(String taskName, String taskType, String taskDescription, Date taskDeadline) {
        this.taskName = taskName;
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.taskDeadline = taskDeadline;
    }

    private String taskDescription;

    private Date taskDeadline;



    @Override
    public String toString() {
        return "Task{" +
                "taskName='" + taskName + '\'' +
                ", taskType='" + taskType + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", taskDeadline=" + DateConverter.fromDate(taskDeadline) +
                '}';
    }



    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public Date getTaskDeadline() {
        return taskDeadline;
    }

    public void setTaskDeadline(Date taskDeadline) {
        this.taskDeadline = taskDeadline;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }







}

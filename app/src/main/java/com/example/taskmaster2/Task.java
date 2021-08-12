package com.example.taskmaster2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Task {
  @PrimaryKey(autoGenerate = true)
    private long id;
  @ColumnInfo(name = "Task_Title")
  String title;
  @ColumnInfo(name = "Task_Body")
  String body;
  String state;

  public Task(String title, String body, String state) {
    this.title = title;
    this.body = body;
    this.state = state;
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

  public String getState() {
    return state;
  }

  public long getId() {
    return id;
  }

  public void setState(String state) {
    this.state = state;
  }

  public void setId(long id) {
    this.id = id;
  }
}

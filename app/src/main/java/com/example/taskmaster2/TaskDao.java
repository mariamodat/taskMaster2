package com.example.taskmaster2;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDao  {
  @Insert
  void insert (Task task);

  @Query("select * from Task where Task_Title like :name")
  Task findByTitle(String name);


  @Query("select * from Task")
 List <Task> findAll();

  @Delete
  void remove(Task task);
}

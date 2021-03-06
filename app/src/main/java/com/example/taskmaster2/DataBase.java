package com.example.taskmaster2;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Task.class} ,version = 1)
public abstract class DataBase extends RoomDatabase {
  public abstract TaskDao taskDao();
}

package com.example.taskmaster2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TaskDetails extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Intent i = getIntent();
    setContentView(R.layout.activity_task_details);
    TextView view= (TextView) findViewById(R.id.taskText);
    view.setText(i.getExtras().getString(MainActivity.TASK_NAME));

  }
}

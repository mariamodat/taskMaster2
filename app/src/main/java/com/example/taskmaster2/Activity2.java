package com.example.taskmaster2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {

  private DataBase dataBase;
  private TaskDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        dataBase= Room.databaseBuilder(getApplicationContext(),DataBase.class,"TaskList").allowMainThreadQueries().build();
        dao=dataBase.taskDao();

      Button submitB= (Button) findViewById(R.id.addTask) ;

      submitB.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

          EditText title= (EditText) findViewById(R.id.titleA);
          EditText desc= (EditText) findViewById(R.id.desc);
          String titleName =title.getText().toString();
          String titleDesc =desc.getText().toString();

          Task newTask= new Task(titleName,titleDesc,"ok");
          dao.insert(newTask);

          Intent intent= new Intent(getApplicationContext(),MainActivity.class);
          startActivity(intent);
          Toast.makeText(Activity2.this,"submitted!", Toast.LENGTH_SHORT).show();
        Toast toast= Toast.makeText( getApplicationContext(),"Submitted!" , Toast.LENGTH_SHORT);
        toast.show();
        }
      });
    }


}

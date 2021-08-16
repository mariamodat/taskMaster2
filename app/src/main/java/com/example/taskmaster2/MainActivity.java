package com.example.taskmaster2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  public static final String TASK_NAME = "taskTitle";
  RecycleAdapter recycleAdapter= new RecycleAdapter();
//  private ArrayList<Task>  taskList= recycleAdapter.getTasksList();
  private List<Task> taskList;
  private RecyclerView recyclerView;
  private String TAG = "deleteBtn";
  private DataBase dataBase;
  private TaskDao dao;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
//    System.out.println(findViewById(R.id.taskText).getContext());
    Button addBtn = (Button) findViewById(R.id.addBtn);

      addBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
openNewPage();
        }
      });

      dataBase= Room.databaseBuilder(getApplicationContext(),DataBase.class, "TaskList" ).allowMainThreadQueries().build();
      dao=dataBase.taskDao();


    System.out.println("on create >>>>>>>>>>>>" );

    Button settingsBtn = (Button) findViewById(R.id.imageButton);
    settingsBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent= new Intent(MainActivity.this,Settings.class);
        startActivity(intent);
      }
    });


       recyclerView= findViewById(R.id.recyclerView1);
      taskList=new ArrayList<>();
    // set the adapter here and call the functions : >>>>>>>>>>>>>>>>
//    setTaskDetails();





    /**
     * put text into task text
     */
    Button task1Btn= (Button) findViewById(R.id.details1);
    task1Btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String buttonText = task1Btn.getText().toString();
        Intent intent= new Intent(MainActivity.this,TaskDetails.class);
        intent.putExtra(TASK_NAME, buttonText);
        startActivity(intent);

      }
    });
    /**
     * task2
     */

    Button task2Btn = (Button) findViewById(R.id.details2);
    task2Btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String buttonText = task2Btn.getText().toString();
        Intent intent = new Intent(MainActivity.this,TaskDetails.class);
        intent.putExtra(TASK_NAME,buttonText);
        startActivity(intent);
      }
    });

/**
 * task 3
 */
    Button task3Btn = (Button) findViewById(R.id.details3);
    task3Btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String buttonText = task3Btn.getText().toString();
        Intent intent = new Intent(MainActivity.this,TaskDetails.class);
        intent.putExtra(TASK_NAME,buttonText);
        startActivity(intent);
      }
    });



  }



  private void setAdapter() {
    RecycleAdapter adapter=new RecycleAdapter(taskList, new RecycleAdapter.OnClickListener() {
      @Override
      public void onTaskClicked(int position) {
          Intent intent=new Intent(getApplicationContext(),TaskDetails.class);
         TextView TextTaskTitle= findViewById(R.id.taskText);
         intent.putExtra("taskTitle",taskList.get(position).getTitle());
         startActivity(intent);
      }

      @SuppressLint("NotifyDataSetChanged")
      @Override
      public void onTaskDelete(int position) {
        dao.remove(taskList.get(position));
          taskList.remove(position);
          for (Task tt:taskList){
            System.out.println("task title is :   "+tt.getTitle());
          }

        recycleAdapter.notifyDataSetChanged();
      }
    });
    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.setAdapter(adapter);
  }

  private void setTaskDetails() {
    taskList.add(new Task("Maryam" ,"im cute " ,"ok"));
    taskList.add(new Task("Yousef" ," hello " ,"ok"));
//    taskList.add(new Task("Sara" ," dear " ,"ok"));
//    taskList.add(new Task("Reem" ,"friend " ,"ok"));
//    taskList.add(new Task("Dana" ,"ok " ,"ok"));
//    taskList.add(new Task("Aseel" ,"done" ,"ok"));
//    taskList.add(new Task("Taima" ,"edited" ,"ok"));
//    taskList.add(new Task("Aya" ,"cute" ,"ok"));
//    taskList.add(new Task("Lora" ," cute" ,"ok"));
//    taskList.add(new Task("Reta" ,"darling " ,"ok"));
//    taskList.add(new Task("Farah" ,"oh " ,"ok"));
//    taskList.add(new Task("Memez" ,"sis " ,"ok"));
//    taskList.add(new Task("meeeeeee" ,"me " ,"ok"));
  }

  public void openNewPage(){
    Intent intent = new Intent(MainActivity.this, Activity2.class);
    startActivity(intent);

  }


  @Override
  protected void onStart() {
    super.onStart();
    Toast toast= Toast.makeText( getApplicationContext(),"Welcome" , Toast.LENGTH_SHORT);
    toast.show();
  }

  @Override
  protected void onResume() {
    super.onResume();
    taskList= (ArrayList<Task>) dao.findAll();
    setAdapter();
    SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    TextView username= (TextView) findViewById(R.id.textView);
    username.setText(preferences.getString("username" ,"edited username"));
    System.out.println("on Resume >>>>>>>>>>>>" );
    System.out.println("from Data base >>>>>>>>>>>>>>>>>>" + dao.findAll().toString());

  }

  @Override
  protected void onStop() {
    super.onStop();
    Toast toast= Toast.makeText( getApplicationContext(),"Thanks!" , Toast.LENGTH_SHORT);
    toast.show();
  }

  @Override
  protected void onPause() {
    super.onPause();
    Toast toast= Toast.makeText( getApplicationContext(),"waiting ..." , Toast.LENGTH_SHORT);
    toast.show();
  }
}

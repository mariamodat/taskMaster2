package com.example.taskmaster2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.DataStoreChannelEventName;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.hub.HubChannel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  public static final String TASK_NAME = "taskTitle";
  RecycleAdapter recycleAdapter = new RecycleAdapter();
  //  private ArrayList<Task>  taskList= recycleAdapter.getTasksList();
  private ArrayList<Task> taskList = new ArrayList<>();
  private RecyclerView recyclerView;
  private String TAG = "deleteBtn";
  private DataBase dataBase;

  private List <Task> data = new ArrayList<>();
  private TaskDao dao;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


    ////////////////////////////////////////////////////////////////

    Button addBtn = (Button) findViewById(R.id.addBtn);

    addBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        openNewPage();
      }
    });

    // dataBase= Room.databaseBuilder(getApplicationContext(),DataBase.class, "TaskList" ).allowMainThreadQueries().build();
    // dao=dataBase.taskDao();
      setAmplify();
    getTaskFromAPI();

    // this is for using local data store :
    Amplify.DataStore.query(Task.class,
      todos -> {
        while (todos.hasNext()) {
          Task todo = todos.next();

          Log.i("Tutorial", "==== Todo ====");
          Log.i("Tutorial", "Name: " + todo.getTitle());

          if (todo.getTitle() != null && !this.taskList.contains(todo)) {
            this.taskList.add(todo);
          }

        }
      },
      failure -> Log.e("Tutorial", "Could not query DataStore", failure)
    );



//
//    Amplify.DataStore.query(Task.class, result -> { /* ignore this result */ }, error -> { });
//    Amplify.Hub.subscribe(HubChannel.DATASTORE,
//      hubEvent -> DataStoreChannelEventName.READY.toString().equals(hubEvent.getName()),
//      hubEvent -> Amplify.DataStore.query(Task.class, result -> {
//        while (result.hasNext()){
//          Task taskToDo = result.next();
//          Log.i("Tutorial", "==== Todo ====");
//          Log.i("Tutorial", "Name: " + taskToDo.getTitle());
//
//          if (taskToDo.getTitle() != null && !this.taskList.contains(taskToDo)) {
//            this.taskList.add(taskToDo);
//          }
//        }
//        /* expect the actual results here */ },
//
//        error -> { })
//    );


    Button settingsBtn = (Button) findViewById(R.id.imageButton);
    settingsBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, Settings.class);
        startActivity(intent);
      }
    });

    recyclerView = findViewById(R.id.recyclerView1);



    /**
     * put text into task text
     */
    Button task1Btn = (Button) findViewById(R.id.details1);
    task1Btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String buttonText = task1Btn.getText().toString();
        Intent intent = new Intent(MainActivity.this, TaskDetails.class);
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
        Intent intent = new Intent(MainActivity.this, TaskDetails.class);
        intent.putExtra(TASK_NAME, buttonText);
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
        Intent intent = new Intent(MainActivity.this, TaskDetails.class);
        intent.putExtra(TASK_NAME, buttonText);
        startActivity(intent);
      }
    });

  }


  private void setAmplify(){
    // add amplify things :
    try {

      Amplify.addPlugin(new AWSDataStorePlugin());
      Amplify.addPlugin(new AWSApiPlugin());
      Amplify.configure(getApplicationContext());

      Log.i("Tutorial", "Initialized Amplify");
    } catch (AmplifyException e) {
      Log.e("Tutorial", "Could not initialize Amplify", e);
    }
  }

  private Handler handler = new Handler(Looper.getMainLooper(),
                new Handler.Callback() {
                  @Override
                  public boolean handleMessage(@NonNull Message msg) {
                    recyclerView.getAdapter().notifyDataSetChanged();

                    return false;

                  }
          });


  private void getTaskFromAPI(){
    Amplify.API.query(ModelQuery.list(Task.class),response ->
      {
        for (Task t : response.getData()){
          data.add(t);
          Log.i(TAG, "onCreate: the expenses are => " + t.getTitle());
        }
        handler.sendEmptyMessage(1);
      },
      error -> Log.e(TAG, "onCreate: Failed to get expenses => " + error.toString())

      );

  }


  private void setAdapter() {
    RecycleAdapter adapter = new RecycleAdapter(taskList, new RecycleAdapter.OnClickListener() {
      @Override
      public void onTaskClicked(int position) {
        Intent intent = new Intent(getApplicationContext(), TaskDetails.class);
        TextView TextTaskTitle = findViewById(R.id.taskText);
        intent.putExtra("taskTitle", taskList.get(position).getTitle());
        startActivity(intent);
      }

      @SuppressLint("NotifyDataSetChanged")
      @Override
      public void onTaskDelete(int position) {
//        dao.remove(taskList.get(position));
        taskList.remove(position);
        for (Task tt : taskList) {
          System.out.println("task title is :   " + tt.getTitle());
        }

        recycleAdapter.notifyDataSetChanged();
      }
    });
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.setAdapter(adapter);
  }


  public void openNewPage() {
    Intent intent = new Intent(MainActivity.this, Activity2.class);
    startActivity(intent);

  }


  @Override
  protected void onStart() {
    super.onStart();
    Toast toast = Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT);
    toast.show();
  }

  @Override
  protected void onResume() {
    super.onResume();
  // taskList= (ArrayList<Task>) dao.findAll();

    setAdapter();
    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    TextView username = (TextView) findViewById(R.id.textView);
    username.setText(preferences.getString("username", "edited username"));
    System.out.println("on Resume >>>>>>>>>>>>");


  }

  @Override
  protected void onStop() {
    super.onStop();
    Toast toast = Toast.makeText(getApplicationContext(), "Thanks!", Toast.LENGTH_SHORT);
    toast.show();
  }

  @Override
  protected void onPause() {
    super.onPause();
    Toast toast = Toast.makeText(getApplicationContext(), "waiting ...", Toast.LENGTH_SHORT);
    toast.show();
  }
}

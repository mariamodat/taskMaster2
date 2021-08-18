package com.example.taskmaster2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;

public class Activity2 extends AppCompatActivity {

  private static final String TAG = "connectivity";
  private DataBase dataBase;
  private TaskDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        // Room things:
//        dataBase= Room.databaseBuilder(getApplicationContext(),DataBase.class,"TaskList").allowMainThreadQueries().build();
//        dao=dataBase.taskDao();


      Button submitB= (Button) findViewById(R.id.addTask) ;

      submitB.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

          EditText title= (EditText) findViewById(R.id.titleA);
          EditText status= findViewById(R.id.status);
          EditText desc= (EditText) findViewById(R.id.desc);
          String titleName =title.getText().toString();
          String titleDesc =desc.getText().toString();
          String titleStatus= status.getText().toString();
          Task task2=Task.builder()
            .title(titleName)
            .body(titleDesc).
              state(titleStatus)
            .build();
              if (isNetworkAvailable(getApplicationContext()))
              {
                Log.i(TAG, "onClick: the network is available");
              } else {
                Log.i(TAG, "onClick: net down");
              }


          Amplify.DataStore.save(task2,
            success -> Log.i("Tutorial", "Saved item: " + success.item().getTitle()),
            error -> Log.e("Tutorial", "Could not save item to DataStore", error)
          );
            // save the task to the api :
          saveTasksToApi(task2);


      //  Task newTask= new Task(titleName,titleDesc,"ok");
      //    dao.insert(newTask);

          Intent intent= new Intent(getApplicationContext(),MainActivity.class);
          startActivity(intent);
          Toast.makeText(Activity2.this,"submitted!", Toast.LENGTH_SHORT).show();
        Toast toast= Toast.makeText( getApplicationContext(),"Submitted!" , Toast.LENGTH_SHORT);
        toast.show();
        }
      });
    }

  private boolean isNetworkAvailable(Context applicationContext) {
    ConnectivityManager connectivityManager= ((ConnectivityManager) applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE));
    return connectivityManager.getActiveNetworkInfo()!=null && connectivityManager
      .getActiveNetworkInfo().isConnected();
  }

  private void saveTasksToApi(Task task){
      Amplify.API.mutate(ModelMutation.create(task),
        success -> Log.i(TAG, "Saved item: from API " + success.getData().getTitle()),
        error -> Log.e(TAG, "Could not save item to API/dynamodb", error));

  }


}

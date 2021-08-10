package com.example.taskmaster2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  public static final String TASK_NAME = "taskTitle";


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button addBtn = (Button) findViewById(R.id.addBtn);

      addBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
openNewPage();
        }
      });

    Button settingsBtn = (Button) findViewById(R.id.settingBtn);
    settingsBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent= new Intent(MainActivity.this,Settings.class);
        startActivity(intent);
      }
    });


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

    SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    TextView username= (TextView) findViewById(R.id.textView);
    username.setText(preferences.getString("username" ,"edited username"));

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

package com.example.taskmaster2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button addBtn = (Button) findViewById(R.id.addBtn);
//    Button submitB= (Button) findViewById(R.id.submitBtn) ;
//    submitB.setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View v) {
//        submitTask();
//      }
//    });
      addBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
openNewPage();
        }
      });
  }
  public void openNewPage(){
    Intent intent = new Intent(MainActivity.this, Activity2.class);
    startActivity(intent);
//    Toast.makeText(MainActivity.this,"Submitted" , Toast.LENGTH_LONG).show();
  }

//public void submitTask(){
//  Toast.makeText(MainActivity.this,"Submitted" , Toast.LENGTH_LONG).show();
//}


}

package com.example.taskmaster2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

      Button submitB= (Button) findViewById(R.id.addTask) ;

      submitB.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Toast.makeText(Activity2.this,"submitted!", Toast.LENGTH_SHORT).show();
        Toast toast= Toast.makeText( getApplicationContext(),"Submitted!" , Toast.LENGTH_SHORT);
        toast.show();
        }
      });
    }


}

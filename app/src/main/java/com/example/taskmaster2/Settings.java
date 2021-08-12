package com.example.taskmaster2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.settings);

    SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    SharedPreferences.Editor prefEditor= preferences.edit();
    Button saveBtn= (Button) findViewById(R.id.usernameBtn);
    saveBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        EditText userName= findViewById(R.id.usernameText);
        prefEditor.putString("username",userName.getText().toString());
        prefEditor.apply();
        Toast toast= Toast.makeText(Settings.this,"edited username", Toast.LENGTH_SHORT);
        toast.show();
        Intent intent = new Intent(Settings.this,MainActivity.class);
        startActivity(intent);
      }
    });
  }
}

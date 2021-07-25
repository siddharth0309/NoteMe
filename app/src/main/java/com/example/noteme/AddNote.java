package com.example.noteme;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.Calendar;

public class AddNote extends AppCompatActivity {

    //Toolbar toolbar;
    //ActionBar actionBar;
    EditText NoteTitle,NoteDetails;
    Calendar c;
    String todaysDate;
    String currentTime;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
       // toolbar = findViewById(R.id.toolbar);
        //setActionBar(toolbar);
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        NoteTitle=findViewById(R.id.NoteTitle);
        NoteDetails=findViewById(R.id.NoteDetails);



        NoteTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() != 0){
                    getSupportActionBar().setTitle(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // set current date and time
        c = Calendar.getInstance();
        todaysDate = c.get(Calendar.DAY_OF_MONTH)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.YEAR);
        Log.d("DATE", "Date: "+todaysDate);
        currentTime = pad(c.get(Calendar.HOUR))+":"+pad(c.get(Calendar.MINUTE));
        Log.d("TIME", "Time: "+currentTime);

    }

    private String pad(int time) {
        if(time < 10)
            return "0"+time;
        return String.valueOf(time);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete){
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();

        }
        if(item.getItemId() == R.id.save){
            Note note = new Note(NoteTitle.getText().toString(),NoteDetails.getText().toString(),todaysDate,currentTime);
            NoteDatabase db= new NoteDatabase(this);
            //adding the note to database
            db.addNote(note);
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
            goToMain();
        }
        return super.onOptionsItemSelected(item);
    }
    private void goToMain(){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
    public void onBackPressed() {
        super.onBackPressed();
    }
}

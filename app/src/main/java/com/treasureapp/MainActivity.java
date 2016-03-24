package com.treasureapp;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


    public class MainActivity extends Activity {

    Button writebtn;
    Button readbtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Buttons
        writebtn = (Button) findViewById(R.id.writebtn);
        readbtn = (Button) findViewById (R.id.readbtn);

        // open write activity
        writebtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), WriteActivity.class);
                startActivity(i);

            }
        });

        // open read activity
        readbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), ReadActivity.class);
                startActivity(i);

            }
        });

    }
}



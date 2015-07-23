package com.mojokarma.mojokarma;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class apploud extends Activity {

    Button signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apploud);

        signin = (Button) findViewById(R.id.signin);

        final EditText email = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.password);

        final SharedPreferences.Editor editor = getSharedPreferences("login anme", MODE_PRIVATE).edit();



        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = email.getText().toString();
                String upass = password.getText().toString();

                if (uname.equals("kundan") && upass.equals("1234")) {

                    editor.putString("name", "kundan");
                    editor.commit();

                    Toast.makeText(getApplicationContext(), "Successfully Logined", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(apploud.this, TimeLine.class);
                    startActivity(in);
                    finish();

                } else if (uname.equals("suman") && upass.equals("1234")) {

                    editor.putString("name", "suman");
                    editor.commit();

                    Toast.makeText(getApplicationContext(), "Successfully Logined", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(apploud.this, TimeLine.class);
                    startActivity(in);
                    finish();

                } else if (uname.equals("vjaysh") && upass.equals("1234")) {

                    editor.putString("name", "vjaysh");
                    editor.commit();

                    Toast.makeText(getApplicationContext(), "Successfully Logined", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(apploud.this, TimeLine.class);
                    startActivity(in);
                    finish();

                } else if (uname.equals("imran") && upass.equals("1234")) {


                    editor.putString("name", "imran");
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Successfully Logined", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(apploud.this, TimeLine.class);
                    startActivity(in);
                    finish();

                } else {
                    Toast.makeText(getApplicationContext(), "Error in Login... PLZ ENTER CORRECT CREDENTIALS", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_apploud, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }
}

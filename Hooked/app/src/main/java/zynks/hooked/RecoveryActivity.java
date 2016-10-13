package zynks.hooked;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class RecoveryActivity extends AppCompatActivity {
    Button b1;
    EditText et1,et2;
    public static String st,st2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery);
        b1=(Button)findViewById(R.id.button1);
        et1=(EditText)findViewById(R.id.editText1);
        et2=(EditText)findViewById(R.id.editText2);
        b1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                st=et1.getText().toString();
                st2=et2.getText().toString();
                FileOutputStream fos;
                FileOutputStream fo;
                try {
                    fos = openFileOutput("abc.txt", Context.MODE_PRIVATE);
                    fo = openFileOutput("abcd.txt", Context.MODE_PRIVATE);
                    if(st.length()<10 && st2.length()<5){
                        Toast.makeText(getApplicationContext(), "Enter a valid Question or Answer", Toast.LENGTH_SHORT).show();
                    }
                    else
                        et2.setText("");
                    //default mode is PRIVATE, can be APPEND etc.
                    fos.write(st.getBytes());
                    fo.write(st2.getBytes());
                    fos.close();
                    fo.close();
                } catch (FileNotFoundException e) {e.printStackTrace();}
                catch (IOException e) {e.printStackTrace();}
                Toast.makeText(getApplicationContext(), "Question Saved", Toast.LENGTH_SHORT).show();
                et1.setText("");
                et2.setText("");


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.Hooked:
                Intent in1=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(in1);
                break;
            case R.id.lock_settings:
                Intent in=new Intent(getApplicationContext(),LockSelActivity.class);
                startActivity(in);
                break;
            case R.id.Recovery:
                Intent in3=new Intent(getApplicationContext(),RecoveryActivity.class);
                startActivity(in3);
                break;
            case R.id.About:
                Intent in2=new Intent(getApplicationContext(),AboutActivity.class);
                startActivity(in2);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);


    }
}

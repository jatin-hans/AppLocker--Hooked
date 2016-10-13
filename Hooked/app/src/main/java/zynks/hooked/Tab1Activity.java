package zynks.hooked;

import android.support.v7.app.AppCompatActivity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Tab1Activity extends AppCompatActivity {
    EditText e1,e2,p1,p2;
    Button b1;
    String s1,s2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab1);

        p1 = (EditText) findViewById(R.id.paas);
        p2 = (EditText) findViewById(R.id.repaas);
        b1 = (Button) findViewById(R.id.savebutton);

        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if(p1.getText().toString().equals("")&&p2.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Please Enter Pin", Toast.LENGTH_LONG).show();
                }else
                if(p1.getText().toString().equals(p2.getText().toString())){


                    String data = p2.getText().toString();

                    FileOutputStream fos;
                    try{
                        File file = new File("zynks.txt");
                        file.delete();
                        fos= openFileOutput("zynks.txt",Context.MODE_PRIVATE);

                        fos.write(data.getBytes());
                        fos.close();


                    }catch(IOException e){
                        e.printStackTrace();
                    }

                    String dat = "3";
                    FileOutputStream fo;
                    try{

                        fo= openFileOutput("flag.txt",Context.MODE_PRIVATE);

                        fo.write(dat.getBytes());
                        fo.close();


                    }catch(IOException e){
                        e.printStackTrace();
                    }

                    Intent open = new Intent(getApplicationContext(),WelcomeScreen.class);
                    startActivity(open);
                }
                else{
                    Toast.makeText(getApplicationContext()," Password not matching ", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.welcome_screen, menu);
        return true;
    }

}

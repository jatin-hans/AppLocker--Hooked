package zynks.hooked;

import android.support.v7.app.AppCompatActivity;
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

public class Tab3Activity extends AppCompatActivity {
    Button save;
    EditText incrementor;
    public static int x;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab3);
        save =(Button) findViewById(R.id.button1);
        incrementor =(EditText) findViewById(R.id.editText1);
        //x=Integer.parseInt(incrementor.getText().toString());
        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                x=Integer.parseInt(incrementor.getText().toString());
                String dat = "2";

                FileOutputStream fo;
                try{

                    fo= openFileOutput("flag.txt",Context.MODE_PRIVATE);

                    fo.write(dat.getBytes());
                    fo.close();


                }catch(IOException e){
                    e.printStackTrace();
                }
                String data = incrementor.getText().toString();

                FileOutputStream fos;
                try{

                    fos= openFileOutput("zynks2.txt",Context.MODE_PRIVATE);

                    fos.write(data.getBytes());
                    fos.close();


                }catch(IOException e){
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), Integer.toString(WelcomeScreen.b), Toast.LENGTH_LONG).show();
                incrementor.setText("");
                Intent open =new Intent(getApplicationContext(),WelcomeScreen.class);
                startActivity(open);
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

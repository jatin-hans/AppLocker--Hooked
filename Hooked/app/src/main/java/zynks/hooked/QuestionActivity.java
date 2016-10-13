package zynks.hooked;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class QuestionActivity extends AppCompatActivity {
    EditText et1,et2;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        et1=(EditText)findViewById(R.id.editText1);
        et2=(EditText)findViewById(R.id.editText2);
        b1=(Button)findViewById(R.id.button1);

        StringBuffer stringB = new StringBuffer();
        String st;

        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    openFileInput("abc.txt")));


            String i;

            while ((i= input.readLine()) != null) {
                stringB.append(i);
            }
            st = stringB.toString();
            et1.setText(st);
        }
        catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }




        et1.setEnabled(false);
        et2.setText("");
        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String an=et2.getText().toString();
                StringBuffer stringBuffer = new StringBuffer();
                String string;
                try {
                    //Attaching BufferedReader to the FileInputStream by the help of InputStreamReader
                    BufferedReader inputReader = new BufferedReader(new InputStreamReader(
                            openFileInput("abcd.txt")));
                    String inputString;
                    //Reading data line by line and storing it into the stringbuffer
                    while ((inputString = inputReader.readLine()) != null) {
                        stringBuffer.append(inputString );  }
                    string = stringBuffer.toString();

                    if(an.equals(string)){
                        Intent in=new Intent(getApplicationContext(),LockSelActivity.class);
                        startActivity(in);
                        et1.setText("");
                        et2.setText("");


                    }
                    else
                        Toast.makeText(getApplicationContext(),"Wrong Answer" , Toast.LENGTH_LONG).show();




                } catch (IOException e) {
                    e.printStackTrace();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

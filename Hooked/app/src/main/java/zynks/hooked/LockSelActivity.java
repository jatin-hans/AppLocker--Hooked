package zynks.hooked;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LockSelActivity extends AppCompatActivity {
    String Opt[]={"Password","PIN","Time Lock"};
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_sel);
        list=(ListView)findViewById(R.id.listView1);
        ArrayAdapter<String>adap=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Opt);
        list.setAdapter(adap);
        list.setOnItemClickListener(new OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position) {
                    case 0:
                        Intent in=new Intent(getApplicationContext(),Tab1Activity.class);
                        startActivity(in);
                        break;
                    case 1:
                        Intent in1=new Intent(getApplicationContext(),Tab2Activity.class);
                        startActivity(in1);
                        break;
                    case 2:
                        Intent in2=new Intent(getApplicationContext(),Tab3Activity.class);
                        startActivity(in2);
                        break;

                }
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

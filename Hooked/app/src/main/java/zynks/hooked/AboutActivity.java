package zynks.hooked;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class AboutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
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

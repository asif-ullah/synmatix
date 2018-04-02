package au.com.synmatix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent i = new Intent(Splash.this, MainActivity.class);
                i.putExtra("launch","notlaunch");
                startActivity(i);
                //overridePendingTransition(R.anim.unzoom_in,R.anim.unzoom_out);
                finish();
            }
        });
        thread.start();
    }
}

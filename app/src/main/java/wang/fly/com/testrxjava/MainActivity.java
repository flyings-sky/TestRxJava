package wang.fly.com.testrxjava;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    private Button bt1;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        setEvent();
    }

    private void setEvent() {
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this,CreateActivity.class);
                startActivity(intent);
            }
        });
    }

    private void findView() {
        bt1 = (Button) findViewById(R.id.test01);
    }
}

package wang.fly.com.testrxjava;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    private Button bt1,bt2,bt3,bt4,bt5;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        setEvent();
    }

    private void setEvent() {
        bt1.setOnClickListener(view -> {
            intent = new Intent(MainActivity.this,TestMap.class);
            startActivity(intent);
        });

        bt2.setOnClickListener(view -> {
            intent = new Intent(MainActivity.this,TestFlatAndConcatMap.class);
            startActivity(intent);
        });

        bt3.setOnClickListener(view -> {
            intent = new Intent(MainActivity.this,TestFilterAndDistinct.class);
            startActivity(intent);
        });

        bt4.setOnClickListener(view -> {
            intent = new Intent(MainActivity.this,TestMergeAndZip.class);
            startActivity(intent);
        });

        bt5.setOnClickListener(view -> {
            intent = new Intent(MainActivity.this,TestMyOperator.class);
            startActivity(intent);
        });
    }

    private void findView() {
        bt1 = (Button) findViewById(R.id.test01);
        bt2 = (Button) findViewById(R.id.test02);
        bt3 = (Button) findViewById(R.id.test03);
        bt4 = (Button) findViewById(R.id.test04);
        bt5 = (Button) findViewById(R.id.test05);
    }
}

package wang.fly.com.testrxjava;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;


/**
 * Created by 兆鹏 on 2016/11/20.
 */
public class TestMyOperator extends Activity {

    private EditText ed;
    private String s1 = "";
    public static Integer [] data = new Integer[]{6,9,2,11,26,10};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testmyoperator_layout);
        ed = (EditText) findViewById(R.id.id_test_myoperator_ed);
        TestSequenceOperator();
    }

    private void TestSequenceOperator(){
    }

    private void onShow(String s){
        ed.setText(s);
    }
}


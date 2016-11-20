package wang.fly.com.testrxjava;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

public class TestFilterAndDistinct extends Activity{
    private EditText ed;
    private String s1 = "";
    private Integer [] data = new Integer[]{3,12,9,6,22,33,4,2,100,33,12};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testfilter_layout);
        ed = (EditText) findViewById(R.id.id_test_filter_ed);
        Test1();
    }

    private void Test1(){
        Observable.from(data)
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer > 10;
                    }
                })
                .distinct()
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        s1 += "filter->onNext:" + integer + "\n";
                        onShow(s1);
                    }
                });
    }

    private void Test2(){
        Observable.from(data)
                .filter(integer -> integer > 10)
                .distinct()
                .subscribe(integer -> {
                    s1 += "filter->onNext:" + integer + "\n";
                    onShow(s1);
                });
    }

    private void onShow(String str) {
        ed.setText(str);
    }
}

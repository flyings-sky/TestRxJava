package wang.fly.com.testrxjava;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

/**
 * Created by 兆鹏 on 2016/11/20.
 */
public class TestMergeAndZip extends Activity {
    private EditText ed;
    private String s1 = "";
    private String [] data1 = new String[]{"Hi,","Hello,"};
    private String [] data2 = new String[]{"JAVA","C++","C"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testmergezip_layout);
        ed = (EditText) findViewById(R.id.id_test_merge_zip_ed);
        TestMerge1();
        TestZip2();
    }

    private void TestMerge1(){
        Observable
                .merge(Observable.from(data1),Observable.from(data2))
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        s1 += "merge->onNext:" + s + "\n";
                        onShow(s1);
                    }
                });
    }

    private void TestMerge2(){
        Observable
                .merge(Observable.from(data1),Observable.from(data2))
                .subscribe(s -> {
                    s1 += "merge->onNext:" + s + "\n";
                    onShow(s1);
                });
    }

    private void TestZip1(){
        Observable
                .zip(Observable.from(data1), Observable.from(data2), new Func2<String, String, String>() {
                        @Override
                        public String call(String s, String s2) {
                            return s+s2;
                        }
                    })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        s1 += "zip->onNext:" + s + "\n";
                        onShow(s1);
                    }
                });

    }

    private void TestZip2(){
        Observable
                .zip(Observable.from(data1), Observable.from(data2), (s,s2) -> s+s2)
                .subscribe(s -> {
                    s1 += "zip->onNext:" + s + "\n";
                    onShow(s1);
                });

    }


    private void onShow(String str) {
        ed.setText(str);
    }
}

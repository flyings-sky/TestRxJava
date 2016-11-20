package wang.fly.com.testrxjava;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 *
 * Created by 兆鹏 on 2016/11/20.
 */
public class TestFlatAndConcatMap extends Activity{
    private EditText ed;
    private List<List<Integer>> datas;
    private String s1 = "";
    private List<Integer> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testflatconcat_layout);
        ed = (EditText) findViewById(R.id.id_test_flat_concat_ed);
        datas = new ArrayList<>();

        for(int i = 0;i < 20;i++) {
            data = new ArrayList<>();
            for(int j = 1;j < 4;j++){
                data.add(3*i+j);
            }
            datas.add(data);
        }
        TestFlatMap();
        TestConcatMap();
    }

    private void TestFlatMap(){
        Observable
                .from(datas)
                .flatMap(new Func1<List<Integer>, Observable<Integer>>() {
                        @Override
                        public Observable<Integer> call(List<Integer> integers) {
                            return Observable.from(integers);
                        }
                    })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                s1 += "flatmap->onNext:" + integer + "\n";
                onShow(s1);
            }
        });
    }

    private void TestFlatMap1(){
        Observable
                .from(datas)
                .flatMap(integers -> Observable.from(integers))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> {
                    s1 += "flatmap->onNext:" + integer + "\n";
                    onShow(s1);
                });
    }

    private void TestConcatMap(){
        Observable.from(datas)
                .concatMap(integers -> Observable.from(integers))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> {
                    s1 += "Concatmap->onNext:" + integer + "\n";
                    onShow(s1);
                });
    }
    private void onShow(String str) {
        ed.setText(str);
    }
}

package wang.fly.com.testrxjava;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/*
 * Created by 兆鹏 on 2016/11/2.
 */
public class transformActivity extends Activity implements View.OnClickListener{
    private TextView tv;
    private Button bt1,bt2;
    private String s = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transform_layout);
        findView();
        setEvent();
    }

    private void setEvent() {
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
    }

    private void findView() {
        tv = (TextView) findViewById(R.id.test010);
        bt1 = (Button) findViewById(R.id.test011);
        bt2 = (Button) findViewById(R.id.test012);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.test011:
                s = "";
                map();
                break;
            case R.id.test012:
                s = "";
                flatMap();
                break;
            default:break;
        }
    }

    private void flatMap() {
        String str[] = {"JAVA","C","C++"};
        Observable.just(str).flatMap(new Func1<String[], Observable<String>>() {
            @Override
            public Observable<String> call(String[] strings) {
                return Observable.from(strings);
            }
        }).map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return "Hi,"+s;
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                s += "flatMap->onCompleted:run completed";
                onShow(s);
            }

            @Override
            public void onError(Throwable e) {
                s += "flatMap->onError:"+e.getMessage();
                onShow(s);
            }

            @Override
            public void onNext(String string) {
                s +=  "flatMap->onNext:"+string+"\n";
            }
        });
    }

    private void map() {
        Observable.just("java","C","C++").map(new Func1<String, String>() {

            @Override
            public String call(String s) {
                return "Hi," + s;
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                s += "map->onCompleted:run completed";
                onShow(s);
            }

            @Override
            public void onError(Throwable e) {
                s += "map->onError:"+e.getMessage();
                onShow(s);
            }

            @Override
            public void onNext(String string) {
                s +=  "map->onNext:"+string+"\n";
            }
        });
    }

    private void onShow(String str) {
        tv.setText(str);
    }

}

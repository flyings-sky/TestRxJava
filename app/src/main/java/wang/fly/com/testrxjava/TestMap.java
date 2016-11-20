package wang.fly.com.testrxjava;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 *
 * Created by 兆鹏 on 2016/11/20.
 */
public class TestMap extends Activity{
    private EditText ed;
    private String s = "";
    private Subscription subscription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testmap_layout);
        ed = (EditText) findViewById(R.id.id_test_map_ed);
        Test1();
    }

    private void Test1() {
        subscription =
                Observable.just("java", "C", "C++")
                        .map(new Func1<String, String>() {
                            @Override
                            public String call(String s) {
                                return "Hi," + s;
                            }
                        })
                        .subscribe(new Subscriber<String>() {
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

    private void Test2() {
        subscription =
                Observable.just("java", "C", "C++")
                        .map(s1 -> s1 +"Hi,")
                        .subscribe(new Action1<String>() {
                            @Override
                            public void call(String string) {
                                s += "map->onNext:" + string + "\n";
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable e) {
                                s += "map->onError:" + e.getMessage();
                                onShow(s);
                            }
                        }, new Action0() {
                            @Override
                            public void call() {
                                s += "map->onCompleted:run completed";
                                onShow(s);
                            }
                        });
    }

    private void Test3() {
        subscription =
                Observable.just("java", "C", "C++")
                .map(s -> "Hi," + s)
                .subscribe(string -> s += "map->onNext:" + string + "\n",//onNext()方法
                        e -> {                                           //onError()方法
                            s += "map->onError:"+e.getMessage();
                            onShow(s);
                        },
                        () -> {                                         //onCompleted()方法
                            s += "map->onCompleted:run completed";
                            onShow(s);
                        });
    }

    private void Test4() {
        subscription =
                Observable.just("java", "C", "C++")
                        .map(s -> "Hi," + s)
                        .subscribe(string -> {
                            s += "map->onNext:" + string + "\n";
                            onShow(s);
                        });
    }

    private void onShow(String str) {
        ed.setText(str);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(subscription.isUnsubscribed())
            subscription.unsubscribe();
    }
}

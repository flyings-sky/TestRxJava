package wang.fly.com.testrxjava;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import rx.Observable;
import rx.Subscriber;

/*
 * Created by 兆鹏 on 2016/10/20.
 */
public class CreateActivity extends Activity implements View.OnClickListener{
    private String string = "";
    private Button bt1;
    private Button bt2;
    private Button bt3;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_layout);
        findView();
        setEvent();
    }

    private void findView() {
        tv = (TextView) findViewById(R.id.test000);
        bt1 = (Button) findViewById(R.id.test001);
        bt2 = (Button) findViewById(R.id.test002);
        bt3 = (Button) findViewById(R.id.test003);
    }

    private void setEvent() {
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.test001:
                string = "";
                create();
                break;
            case R.id.test002:
                string = "";
                just();
                break;
            case R.id.test003:
                string = "";
                from();
                break;
            default:break;
        }
    }

    //比较from和just的不同
    private void from() {
        Integer [] arr1 = {0,1,2,3};
        Observable.from(arr1).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                string += "from->onCompleted:run completed";
                onShow(string);
            }

            @Override
            public void onError(Throwable e) {
                string += "from->onError:"+e.getMessage();
                onShow(string);
            }

            @Override
            public void onNext(Integer integer) {
                string += "from->onNext:The No." + integer + "Event is running!\n";
            }
        });
    }

    private void just() {
        int [] arr = {0,1,2,3};
        Observable.just(arr).subscribe(new Subscriber<int[]>() {
            @Override
            public void onCompleted() {
                string += "just->onCompleted:run completed";
                onShow(string);
            }

            @Override
            public void onError(Throwable e) {
                string += "just->onError:"+e.getMessage();
                onShow(string);
            }

            @Override
            public void onNext(int[] ints) {
                //检验onError的代码
//                for(int i = 0;i <= ints.length;i++){
//                    string += "just->onNext:The No." + ints[i] + "Event is running!\n";
//                }
                //正常代码
                for (int anInt : ints) {
                    string += "just->onNext:The No." + anInt + "Event is running!\n";
                }
            }
        });
    }

    private void create() {
        Observable.create(new Observable.OnSubscribe<Integer>(){

            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                try {
                    if(!subscriber.isUnsubscribed()){//检测该被观察者是否有观察者订阅
                        for(int i = 0;i < 5;i++){
                            subscriber.onNext(i);//向观察者发送数据
                        }
                        subscriber.onCompleted();//向观察者发送事件结束的标志
                    }
                }catch (Exception e){
                    subscriber.onError(e);//在上面语句产生错误时，在观察者的onError方法里进行处理
                }
            }
        }).subscribe(new Subscriber<Integer>() {//new一个观察者对象，并且通过subscribr操作实现对被观察者的订阅
            @Override
            public void onCompleted() {//在事件队列为空时执行一些收尾操作
                Log.e("test01","run completed");
                string += "create->onCompleted:run completed";
                onShow(string);
            }

            @Override
            public void onError(Throwable e) {//对发送事件过程中产生的各种异常进行处理
                Log.e("test01","run error:"+e.getMessage());
                string += "create->onError:"+e.getMessage();
                onShow(string);
            }

            @Override
            public void onNext(Integer integer) {//响应从被观察者发送过来的事件
                Log.e("test01","The No."+integer+"is running!");
                string += "create->onNext:The No."+integer+"Event is running!\n";
            }
        });
    }

    private void onShow(String str) {
        tv.setText(str);
    }
}

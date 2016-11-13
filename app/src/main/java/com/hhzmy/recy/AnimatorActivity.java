package com.hhzmy.recy;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class AnimatorActivity extends AppCompatActivity {

    private ImageView view;
    private Button toger;
    private Button weiyi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);
        view=(ImageView) findViewById(R.id.iv);
        toger = (Button) findViewById(R.id.toger);
        weiyi = (Button) findViewById(R.id.transcale);


    }
    public void WeiYi(View v){
        // 通过静态方法构建一个ValueAnimator对象
        // 设置数值集合
        ValueAnimator animator = ValueAnimator.ofFloat(0f,0f,400f);
        // 设置作用对象
        animator.setTarget(view);
        // 设置执行时间(1000ms)
        animator.setDuration(2000);
        // 添加动画更新监听
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 获取当前值
                Float mValue = (Float) animation.getAnimatedValue();
                // 设置横向偏移量
                view.setTranslationX(mValue);
                // 设置纵向偏移量
                view.setTranslationY(mValue);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(AnimatorActivity.this, "我点击了", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        // 开始动画
        animator.start();
    }
    public void Toger(View  v){
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(view,"scaleX",
                5.0f, 1f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(view,"scaleY",
                5.0f, 1f);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(view,"alpha", 1f,
                0f, 1f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(2000);
        animSet.setInterpolator(new LinearInterpolator());
        //两个动画同时执行
        animSet.playTogether(anim1,anim2,anim3);
        animSet.start();
    }
}

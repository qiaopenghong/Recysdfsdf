package com.hhzmy.recy;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class AnimationOnectivity extends AppCompatActivity {

    private  ImageView bt;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_onectivity);
        bt = (ImageView) findViewById(R.id.bt);
        // 类型估值 - 抛物线示例
        final TypeEvaluator<PointF> typeEvaluator = new TypeEvaluator<PointF>() {
            @Override
            public PointF evaluate(float fraction, PointF startValue,
                                   PointF endValue) {
                float time = fraction * 3;
                // x方向200px/s ，y方向0.5 * 200 * t * t
                PointF point = new PointF();
                point.x = 120 * time;
                point.y = 0.5f * 200 * time * time;
                return point;
            }
        };
        ValueAnimator valueAnimator = ValueAnimator.ofObject(typeEvaluator,
                new PointF(0, 0));
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(3000);
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF point = (PointF) animation.getAnimatedValue();
                bt.setX(point.x);
                bt.setY(point.y);
            }
        });
    }
}

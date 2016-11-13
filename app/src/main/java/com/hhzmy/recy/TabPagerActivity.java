package com.hhzmy.recy;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import java.util.ArrayList;
import java.util.List;

public class TabPagerActivity extends FragmentActivity{

    private ViewGroup vg;
    private ViewPager vp;
    private final String[] title={"上海","北京","重庆","山西","天津","陕西","南京","湖南"};
    private List<RadioButton> list=new ArrayList<RadioButton>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_pager);
        vg = (ViewGroup) findViewById(R.id.rg);
        vp = (ViewPager) findViewById(R.id.vp);
        vp.setOffscreenPageLimit(8);
        for (int i=0;i<vg.getChildCount();i++){
            RadioButton rb= (RadioButton) vg.getChildAt(i);
            final int num=i;
            rb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   vp.setCurrentItem(num);
                }
            });
            list.add(rb);
        }
        MyAdapter adapter=new MyAdapter(getSupportFragmentManager(),title);
        vp.setAdapter(adapter);
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


}

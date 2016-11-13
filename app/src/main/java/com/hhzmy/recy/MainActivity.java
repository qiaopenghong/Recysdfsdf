package com.hhzmy.recy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhzmy.recy.httputil.BaseCallBack;
import com.hhzmy.recy.httputil.OkHttp;
import com.hhzmy.recy.httputil.OkhttpHelper;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private HomeAdapter mAdapter;
    private String path="http://m.yunifang.com/yunifang/mobile/goods/getall?random=39986&encode=2092d7eb33e8ea0a7a2113f2d9886c90&category_id=17";

    //private List<Person>  list=new ArrayList<Person>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        //设置布局管理器
 //        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//        //设置Item增加、移除动画
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置adapter
        OkhttpHelper.getInstance().get(path, new BaseCallBack<DataBean>() {
            @Override
            public void onRequsetBefore(Request request) {

            }

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onSuccess(Response response, DataBean dataBean) {
                List<Data> list = dataBean.data;
                mRecyclerView.setAdapter(mAdapter = new HomeAdapter(list));
            }


            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });

        OkHttp.getAsync(path,new OkHttp.DataCallBack() {


            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
//                Log.e("qqqqqqqqqqqq",result);
//                JSONObject json=new JSONObject(result);
//                Log.e("111111111111",result);
//                JSONArray jsonArray=json.getJSONArray("data");
//                for (int i=0;i<jsonArray.length();i++) {
//                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
//                    String goods_img = jsonObject.getString("goods_img");
//                    String goods_name = jsonObject.getString("goods_name");
//                    Person p=new Person();
//                    p.setGoods_img(goods_img);
//                    p.setGoods_name(goods_name);
//                    list.add(p);
//                }
//                Gson gson=new Gson();
//                DataBean dataBean = gson.fromJson(result, DataBean.class);
//                List<Data> list =dataBean.data;
//                mRecyclerView.setAdapter(mAdapter = new HomeAdapter(list));
            }
        });

    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
        private List<Data> list;
        public HomeAdapter(List<Data> list) {
            this.list=list;
        }
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    MainActivity.this).inflate(R.layout.itemhome, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv.setText(list.get(position).goods_name);
            ImageLoader.getInstance().displayImage(list.get(position).goods_img,holder.iv);
            ImageLoader.getInstance().clearMemoryCache();
//            DiskCache diskCache =ImageLoader.getInstance().getDiskCache();
//            holder.tv.setText(diskCache.toString());

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv;
            ImageView iv;
            public MyViewHolder(View view) {
                super(view);
                tv = (TextView) view.findViewById(R.id.id_num);
                iv = (ImageView) view.findViewById(R.id.iv);
            }
        }
    }

}

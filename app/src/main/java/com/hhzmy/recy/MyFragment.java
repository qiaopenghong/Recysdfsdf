package com.hhzmy.recy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hhzmy.recy.httputil.BaseCallBack;
import com.hhzmy.recy.httputil.OkhttpHelper;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by qiao on 2016/11/11.
 */
public class MyFragment extends Fragment implements PullBaseView.OnFooterRefreshListener,PullBaseView.OnHeaderRefreshListener{

    private PullRecyclerView mRecyclerView;
    private HomeAdapter mAdapter;
    private String path="http://m.yunifang.com/yunifang/mobile/goods/getall?random=39986&encode=2092d7eb33e8ea0a7a2113f2d9886c90&category_id=17";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return View.inflate(getActivity(),R.layout.item,null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        String title=bundle.getString("title");
        mRecyclerView = (PullRecyclerView) getView().findViewById(R.id.recyclerView1);
        mRecyclerView.setOnFooterRefreshListener(MyFragment.this);
        mRecyclerView.setOnHeaderRefreshListener(MyFragment.this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //mRecyclerView.setItemAnimator(new ());
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
        mRecyclerView.addOnItemTouchListener(new RecyclerViewClickListener(getActivity(), new RecyclerViewClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(),"fddddd",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));


    }

    @Override
    public void onFooterRefresh(PullBaseView view) {

    }

    @Override
    public void onHeaderRefresh(PullBaseView view) {

    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
        private List<Data> list;
        public HomeAdapter(List<Data> list) {
            this.list = list;
        }
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    getActivity()).inflate(R.layout.itemhome, parent,
                    false));
            return holder;
        }
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv.setText(list.get(position).goods_name);
            ImageLoader.getInstance().displayImage(list.get(position).goods_img, holder.iv);
            ImageLoader.getInstance().clearMemoryCache();
//            DiskCache diskCache =ImageLoader.getInstance().getDiskCache();
//            holder.tv.setText(diskCache.toString());

        }

        @Override
        public int getItemCount() {

            return list.size();
        }
        class MyViewHolder extends RecyclerView.ViewHolder{
            TextView tv;
            ImageView iv;
            public MyViewHolder(View view) {
                super(view);
                tv = (TextView) view.findViewById(R.id.id_num);
                iv = (ImageView) view.findViewById(R.id.iv);
            }
        }
    }
//    public static class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
//        public interface OnItemClickListener {
//            void onItemClick(View view, int position);
//
//            void onItemLongClick(View view, int position);
//        }
//
//        private OnItemClickListener mListener;
//
//        private GestureDetector mGestureDetector;

//        public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
//            mListener = listener;
//
//            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
//                @Override
//                public boolean onSingleTapUp(MotionEvent e) {
//                    return true;
//                }
//
//                @Override
//                public void onLongPress(MotionEvent e) {
//                    View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
//
//                    if (childView != null && mListener != null) {
//                        mListener.onItemLongClick(childView, recyclerView.getChildAdapterPosition(childView));
//                    }
//                }
//            });
//        }
//
//        @Override
//        public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
//            View childView = view.findChildViewUnder(e.getX(), e.getY());
//
//            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
//                mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
//            }
//
//            return false;
//        }
//
//        @Override
//        public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
//        }
//
//        @Override
//        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//        }

 //   }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {

        try {
            super.onViewStateRestored(savedInstanceState);

        } catch (Exception e) {
            e.printStackTrace();
        }
        savedInstanceState=null;
    }
}

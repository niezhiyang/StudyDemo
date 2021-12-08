package com.nzy.viewstudy.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nzy.viewstudy.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{
    private LayoutInflater inflater;
    private Context mContext;
    private List<String> mDatas;

    //创建构造参数
    public MyAdapter(Context context , List<String> datas){
        this.mContext = context;
        this.mDatas = datas;
        inflater = LayoutInflater.from(context);
    }

    //创建ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycleview_item , parent , false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    //绑定ViewHolder
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //为textview 赋值
        holder.tv.setText(mDatas.get(position));
    }


    @Override
    public int getItemCount() {
        //Log.i("TAG", "mDatas "+mDatas);

        return mDatas.size();

    }

    //新增item
    public void addData(int pos){
        mDatas.add("新增");
        notifyItemInserted(pos);
    }

    //移除item
    public void deleateData(int pos){
        mDatas.remove(pos);
        notifyItemRemoved(pos);
    }
}
class MyViewHolder extends RecyclerView.ViewHolder{

    TextView tv;

    public MyViewHolder(View itemView) {
        super(itemView);

        tv = (TextView) itemView.findViewById(R.id.recycle_tv);
        tv.setTextSize(20);

    }
}
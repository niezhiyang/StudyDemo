package com.enjoy.leo_recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StarAdapter extends RecyclerView.Adapter<StarAdapter.StarViewHolder> {

    private Context context;
    private List<Star> starList;


    public StarAdapter(Context context, List<Star> starList) {
        this.context = context;
        this.starList = starList;
    }

    /**
     * 是否是组的第一个item
     *
     * @param position
     * @return
     */
    public boolean isGourpHeader(int position) {
        if (position == 0) {
            return true;
        } else {
            String currentGroupName = getGroupName(position);
            String preGroupName = getGroupName(position - 1);
            if (preGroupName.equals(currentGroupName)) {
                return false;
            } else {
                return true;
            }
        }
    }

    public String getGroupName(int position) {
        return starList.get(position).getGroundName();
    }


    @NonNull
    @Override
    public StarAdapter.StarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_star, null);
        return new StarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StarAdapter.StarViewHolder holder, int position) {
        holder.tv.setText(starList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return starList == null ? 0 : starList.size();
    }

    public class StarViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;

        public StarViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_star);
        }
    }
}

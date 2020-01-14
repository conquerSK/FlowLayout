package com.shenkai.flowlayout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shenkai.flowlayout.R;

import java.util.List;

/**
 * Author:shenkai
 * Time:2020/1/13 11:24
 * Description:
 */
public class FlexboxLayoutManagerAdapter extends RecyclerView.Adapter<FlexboxLayoutManagerAdapter.FlexboxViewHolder> {
    private Context mContext;
    private List<String> mDatas;
    private LayoutInflater inflater;

    public FlexboxLayoutManagerAdapter(Context context, List<String> datas) {
        this.mContext = context;
        this.mDatas = datas;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public FlexboxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FlexboxViewHolder(inflater.inflate(R.layout.item_tag, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FlexboxViewHolder holder, int position) {
        holder.tvTag.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    static class FlexboxViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTag;

        public FlexboxViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTag = itemView.findViewById(R.id.tv_tag);
        }
    }
}

package com.shenkai.flowlayout.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author:shenkai
 * Time:2020/1/15 14:25
 * Description:
 */
public abstract class TagAdapter {
    public abstract int getItemCount();

    public abstract View createView(LayoutInflater inflater, ViewGroup parent, int position);

    public abstract void bindView(View view, int position);

    public void onItemViewClick(View v, int position) {

    }

    public void tipForSelectMax(View v, int maxSelectCount) {

    }

    public void onItemSelected(View v, int position) {

    }

    public void onItemUnSelected(View v, int position) {

    }

    public interface OnDataSetChangeListener {
        void onDataChanged();
    }

    private OnDataSetChangeListener mListener;

    public void setOnDataChangeListener(OnDataSetChangeListener listener) {
        mListener = listener;
    }

    public void notifyDataSetChanged() {
        if (mListener != null) {
            mListener.onDataChanged();
        }
    }
}

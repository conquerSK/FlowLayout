package com.shenkai.flowlayout.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:shenkai
 * Time:2020/1/15 14:24
 * Description:
 */
public class TagFlowLayout extends FlowLayout implements TagAdapter.OnDataSetChangeListener {
    private TagAdapter mAdapter;

    private int mMaxSelectCount;

    public void setMaxSelectCount(int count) {
        mMaxSelectCount = count;
    }

    public TagFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setAdapter(TagAdapter adapter) {
        mAdapter = adapter;
        adapter.setOnDataChangeListener(this);
        onDataChanged();
    }

    public void onDataChanged() {
        removeAllViews();
        TagAdapter adapter = mAdapter;
        for (int i = 0; i < adapter.getItemCount(); i++) {
            View view = adapter.createView(LayoutInflater.from(getContext()), this, i);
            adapter.bindView(view, i);
            addView(view);

            if (view.isSelected()) {
                mAdapter.onItemSelected(view, i);
            } else {
                mAdapter.onItemUnSelected(view, i);
            }

            bindViewMethod(i, view);
        }
    }

    /**
     * 单选：可以直接选择一个，当选择下一个，上一个选择效果自动取消
     * 多选：用户需要手动反选
     *
     * @param position
     * @param view
     */
    private void bindViewMethod(final int position, View view) {
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.onItemViewClick(v, position);

                if (mMaxSelectCount <= 0)
                    return;

                //特殊case
                if (!v.isSelected()) {
                    //view没有被选中
                    if (getSelectViewCount() >= mMaxSelectCount) {
                        //已经选择到达最大值
                        if (getSelectViewCount() == 1) {
                            //单选
                            View selectedView = getSelectedView();
                            if (selectedView != null) {
                                selectedView.setSelected(false);
                                mAdapter.onItemSelected(selectedView, getPositionForChild(selectedView));
                            }
                        } else {
                            //多选
                            mAdapter.tipForSelectMax(v, mMaxSelectCount);
                            return;
                        }
                    }
                }

                if (v.isSelected()) {
                    v.setSelected(false);
                    mAdapter.onItemUnSelected(v, position);
                } else {
                    v.setSelected(true);
                    mAdapter.onItemSelected(v, position);
                }
            }
        });
    }

    private int getPositionForChild(View selectedView) {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (selectedView == view) {
                return i;
            }
        }
        return 0;
    }

    private int getSelectViewCount() {
        int result = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view.isSelected()) {
                result++;
            }
        }
        return result;
    }

    private View getSelectedView() {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view.isSelected()) {
                return view;
            }
        }
        return null;
    }

    public List<Integer> getSelectedItemPositionList() {
        List<Integer> selectList = new ArrayList<>();
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view.isSelected()) {
                selectList.add(i);
            }
        }
        return selectList;
    }
}

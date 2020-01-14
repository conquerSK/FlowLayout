package com.shenkai.flowlayout.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:shenkai
 * Time:2020/1/11 9:30
 * Description:
 */
public class FlowLayout extends ViewGroup {
    private List<List<View>> mAllViews = new ArrayList<>();
    private List<Integer> mLineHeight = new ArrayList<>();

    private static final int[] LL = new int[]{android.R.attr.maxLines};
    private int maxLines;

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, LL);
        maxLines = a.getInt(0, Integer.MAX_VALUE);
        a.recycle();

        Log.d("shenkai", "maxLine = " + maxLines);
    }

    /**
     * flowlayout
     * 宽度：一定是确定的；
     * 高度：wrap_content,exactly,unspecified
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mAllViews.clear();
        mLineHeight.clear();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /**
         * widthMeasureSpec
         * 建议宽度+mode
         * 1.300dp+exactly
         * 2.parent width+ at_most
         * 3.0,parent width +unspecified
         */
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);

        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        int lineWidth = 0;
        int lineHeight = 0;
        int height = 0;

        int count = getChildCount();

        //拿到当前所有child需要占据的高度，设置给我们的容器
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }
            //child也要确定宽高
            //child mode+size
            //1.xml里面写30dp,match_parent,wrap_content
            //2.父控件当前的mode
            measureChild(child, widthMeasureSpec, heightMeasureSpec);

            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            List<View> lineViews = new ArrayList<>();

            if (lineWidth + childWidth > sizeWidth - (getPaddingLeft() + getPaddingRight())) {
                //换行处理
                height += lineHeight;

                mLineHeight.add(lineHeight);

                mAllViews.add(lineViews);
                lineViews = new ArrayList<>();
                lineViews.add(child);

                //重置
                lineWidth = childWidth;
                lineHeight = childHeight;
            } else {
                //未换行
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight);

                lineViews.add(child);
            }

            if (i == count - 1) {
                height += lineHeight;
                mLineHeight.add(lineHeight);
                mAllViews.add(lineViews);
            }
        }

        //maxLines 校正,有优化空间
        if (maxLines < mLineHeight.size()) {
            height = getMaxLinesHeight();
        }

        if (modeHeight == MeasureSpec.EXACTLY) {//可以前移优化
            height = sizeHeight;
        } else if (modeHeight == MeasureSpec.AT_MOST) {
            height = Math.min(sizeHeight, height);
            height = height + getPaddingTop() + getPaddingBottom();
        } else if (modeHeight == MeasureSpec.UNSPECIFIED) {
            height = height + getPaddingTop() + getPaddingBottom();
        }

        setMeasuredDimension(sizeWidth, height);
    }

    private int getMaxLinesHeight() {
        int height = 0;
        for (int i = 0; i < maxLines; i++) {
            height += mLineHeight.get(i);
        }
        return height;
    }

    //摆放view
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left = getPaddingLeft();
        int top = getPaddingTop();

        int lineNums = mAllViews.size();
        for (int i = 0; i < lineNums; i++) {
            List<View> lineViews = mAllViews.get(i);
            Integer lineHeight = mLineHeight.get(i);

            for (int j = 0; j < lineViews.size(); j++) {
                View child = lineViews.get(j);

                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

                //l t r b
                int childLeft = left + lp.leftMargin;
                int childTop = top + lp.topMargin;
                int childRight = childLeft + child.getMeasuredWidth();
                int childBottom = childTop + child.getMeasuredHeight();

                child.layout(childLeft, childTop, childRight, childBottom);

                left += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            }

            left = getPaddingLeft();
            top += lineHeight;
        }
    }

    //child没有设置layoutparams
    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    //inflate
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    //addView cast
    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    //addView
    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof MarginLayoutParams;
    }
}

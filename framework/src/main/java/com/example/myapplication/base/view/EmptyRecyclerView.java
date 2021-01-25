package com.example.myapplication.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

/**
 * 空数据提示的RecyclerView
 */
public class EmptyRecyclerView extends RecyclerView {

    private View emptyView;

    private Context mContext;

    private ViewGroup parentView;

    public EmptyRecyclerView(@NonNull Context context) {
        super(context);
        mContext = context;
        emptyView = LayoutInflater.from(mContext).inflate(R.layout.layout_recycleview_empty, null, false);
    }

    public EmptyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        emptyView = LayoutInflater.from(mContext).inflate(R.layout.layout_recycleview_empty, null, false);
    }

    public EmptyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        emptyView = LayoutInflater.from(mContext).inflate(R.layout.layout_recycleview_empty, null, false);
    }

    final private AdapterDataObserver observer = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            Log.i("tag", "onItemRangeInserted" + itemCount);
            checkIfEmpty();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            checkIfEmpty();
        }
    };

    private void checkIfEmpty() {

        if (parentView == null) {
            parentView = (ViewGroup) getParent();
            int index = parentView.indexOfChild(this);
            parentView.addView(emptyView, index);
        }

        if (emptyView != null && getAdapter() != null) {
            final boolean emptyViewVisible = getAdapter().getItemCount() == 0;
            emptyView.setVisibility(emptyViewVisible ? VISIBLE : GONE);
            setVisibility(emptyViewVisible ? GONE : VISIBLE);
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        final Adapter oldAdapter = getAdapter();
        if (oldAdapter != null) {
            oldAdapter.unregisterAdapterDataObserver(observer);
        }
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(observer);
        }

        checkIfEmpty();
    }

//    public void setEmptyView(View emptyView) {
//        this.emptyView = emptyView;
//        checkIfEmpty();
//    }

}

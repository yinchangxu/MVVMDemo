package com.example.myapplication.base.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableList;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class CommonRecycleViewAdapter<T> extends RecyclerView.Adapter {
    private ObservableList<T> observableList;
    private int variableId;
    private @LayoutRes int layoutId;
    private OnRecycleViewItemClickListener onRecycleViewItemClickListener;

    public CommonRecycleViewAdapter(@NonNull ObservableList<T> observableList, int variableId, @LayoutRes int layoutId) {
        this.observableList = observableList;
        this.variableId = variableId;
        this.layoutId = layoutId;
        observableList.addOnListChangedCallback(new BaseOnListChangeCallback());
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //这里的第四个参数必须为 false 这在Google
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), layoutId, viewGroup, false);
        return new BaseViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ViewDataBinding binding = DataBindingUtil.getBinding(viewHolder.itemView);
        binding.setVariable(variableId, observableList.get(i));
        //设置item点击监听
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRecycleViewItemClickListener != null) {
                    onRecycleViewItemClickListener.itemClick(observableList.get(i), i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return observableList != null ? observableList.size() : 0;
    }

    class BaseViewHolder extends RecyclerView.ViewHolder {

        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public OnRecycleViewItemClickListener getOnRecycleViewItemClickListener() {
        return onRecycleViewItemClickListener;
    }

    public void setOnRecycleViewItemClickListener(OnRecycleViewItemClickListener onRecycleViewItemClickListener) {
        this.onRecycleViewItemClickListener = onRecycleViewItemClickListener;
    }

    //数据变化监听
    class BaseOnListChangeCallback extends ObservableList.OnListChangedCallback {

        @Override
        public void onChanged(ObservableList sender) {
        }

        @Override
        public void onItemRangeChanged(ObservableList sender, int positionStart, int itemCount) {
            CommonRecycleViewAdapter.this.notifyItemRangeChanged(positionStart, itemCount);
        }

        @Override
        public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {
            CommonRecycleViewAdapter.this.notifyItemRangeInserted(positionStart, itemCount);
        }

        @Override
        public void onItemRangeMoved(ObservableList sender, int fromPosition, int toPosition, int itemCount) {
            CommonRecycleViewAdapter.this.notifyItemMoved(fromPosition, toPosition);
        }

        @Override
        public void onItemRangeRemoved(ObservableList sender, int positionStart, int itemCount) {
            CommonRecycleViewAdapter.this.notifyItemRangeRemoved(positionStart, itemCount);
        }
    }

    /**
     * RecycleView 的 Item 点击监听
     * @param <T>
     */
    interface OnRecycleViewItemClickListener<T> {
        /**
         *
         * @param item 点击的 Item 对应的数据
         * @param position 点击的 Item 位置
         */
        void itemClick(T item, int position);
    }

}
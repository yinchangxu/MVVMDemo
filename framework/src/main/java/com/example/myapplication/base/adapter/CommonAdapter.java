package com.example.myapplication.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.listener.OnItemEventListener;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public abstract class CommonAdapter<DB extends ViewDataBinding, T> extends RecyclerView.Adapter {

    protected List<T> dataList;

    protected Context context;

    protected OnItemEventListener mOnItemEventListener;

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public CommonAdapter(Context context, List<T> dataList) {
        this.dataList = dataList;
        this.context = context;
    }

    // 抽象函数  获取布局资源id
    public abstract int getLayoutId();

    // 抽象函数  通过databinding为布局设置数据
    public abstract void bindView(CommonViewHolder viewHolder, int position);

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 生成DB对象 (这个方法是不是和View.inflate()很像？)
        DB bindView = DataBindingUtil.inflate(LayoutInflater.from(context), getLayoutId(), parent, false);
        return new CommonViewHolder(bindView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // 调用抽象函数，将holder强转为CommonViewHodler，供子类Adapter使用其成员对象bindView；
        bindView((CommonViewHolder) holder, position);
    }

    /**
     * 设置回调监听器
     *
     * @param listener 回调监听器
     */
    public void setOnItemEventListener(OnItemEventListener listener) {
        this.mOnItemEventListener = listener;
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class CommonViewHolder extends RecyclerView.ViewHolder {

        public DB bindView;
        // 每一个item都必须持有的一个ViewDataBinding子类对象

        public CommonViewHolder(DB bindView) {
            super(bindView.getRoot());
            this.bindView = bindView;
        }

    }


}

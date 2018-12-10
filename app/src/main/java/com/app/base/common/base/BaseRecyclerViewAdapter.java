package com.app.base.common.base;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 10, December, 2018 3:05 PM
 */
public abstract class BaseRecyclerViewAdapter<E> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected List<E> mData;

    public BaseRecyclerViewAdapter() {
        mData = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public E getItem(int position) {
        if (0 <= position && position < mData.size()) {
            return mData.get(position);
        }

        return null;
    }

    public List<E> getData() {
        return mData;
    }

    public void setData(List<E> data) {
        clear();
        addData(data);
    }

    public void addData(List<E> data) {
        if (!data.isEmpty()) {
            int positionStart = mData.size();
            mData.addAll(data);
            notifyItemRangeInserted(positionStart, data.size());
        }
    }

    public void addData(E data) {
        if (data != null) {
            int positionStart = mData.size();
            mData.add(data);
            notifyItemRangeInserted(positionStart, 1);
        }
    }

    public void addData(E data, int position) {
        if (data != null) {
            mData.add(position, data);
            notifyItemRangeInserted(position, 1);
        }
    }

    public void removeItem(int position) {
        if (-1 < position && position < getItemCount()) {
            mData.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, getItemCount());
        }
    }

    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }
}

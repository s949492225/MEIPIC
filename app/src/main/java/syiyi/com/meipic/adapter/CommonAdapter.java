package syiyi.com.meipic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import java.util.List;

/**
 * Created by songlintao on 15/7/10.
 */
public abstract class CommonAdapter<T> extends BaseAdapter{
    protected Context mContext;
    protected List<T> mData;
    protected LayoutInflater mInflate;
    protected int layoutID;

    public CommonAdapter(List<T> data, Context mContext, int layoutID) {
        this.mData = data;
        this.mContext = mContext;
        this.mInflate = LayoutInflater.from(mContext);
        this.layoutID = layoutID;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.get(mContext, convertView, parent, layoutID, position);
        convert(holder, position);
        View v = holder.getContvertView();
        final int mPosition=position;
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(mPosition);
            }
        });
        return v;
    }

    public abstract void convert(ViewHolder holder, int position);

    public abstract void onItemClick(int position);


    public void setData(List<T> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void loadMoreData(List<T> data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }


}

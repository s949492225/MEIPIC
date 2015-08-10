package syiyi.com.meipic.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by songlintao on 15/7/10.
 */
public class ViewHolder {
    private SparseArray<View> mViews;
    public int mPosition;
    private View mContvertView;
    public ViewHolder(Context mContext,ViewGroup parent,int layoutId,int position){
        this.mPosition=position;
        this.mViews=new SparseArray<View>();
        mContvertView= LayoutInflater.from(mContext).inflate(layoutId,parent,false);
        mContvertView.setTag(this);
    }
    public static ViewHolder get(Context context,View convertView,ViewGroup parent,int layoutId,int position){
        if (convertView==null){
            return new ViewHolder(context,parent,layoutId,position);
        }else {
            ViewHolder holder = (ViewHolder) convertView.getTag();
            holder.mPosition=position;
            return holder;
        }
    }
    public <T extends View > T getView(int viewId){
        View view=mViews.get(viewId);
        if (view==null){
            view=mContvertView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T) view;
    }
    public View getContvertView(){
        return mContvertView;
    }
    public void setText(int viewId,String text){
        ((TextView)getView(viewId)).setText(text);
    }
    public void setImage(int viewId,int id){
        ((ImageView)getView(viewId)).setImageResource(id);
    }
    public void setImage(int viewId,Drawable drawable){
        ((ImageView)getView(viewId)).setImageDrawable(drawable);
    }




}

package syiyi.com.meipic.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import java.util.List;
import syiyi.com.meipic.activity.PicListActivity;
import syiyi.com.meipic.R;
import syiyi.com.meipic.bean.DirItem;
import syiyi.com.meipic.util.ImageloaderUtil;

/**
 * Created by songlintao on 15/8/5.
 */
public class PageItemAdapter extends CommonAdapter<DirItem> {
    public PageItemAdapter(List<DirItem> data, Context mContext) {
        super(data, mContext, R.layout.pige_item);
    }

    @Override
    public void convert(ViewHolder holder, final int position) {
        final ImageView iv=holder.getView(R.id.iv);
        iv.post(new Runnable() {
            @Override
            public void run() {

                ImageLoader.getInstance().displayImage(mData.get(position).getImgeUrl(), iv, ImageloaderUtil.getOption(mContext), new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        int viewWidth = view.getWidth();
                        int width = loadedImage.getWidth();
                        int height = loadedImage.getHeight();
                        int viewHeight = (int) (((double) viewWidth / (double) width) * height);
                        ViewGroup.LayoutParams params = view.getLayoutParams();
                        params.width = viewWidth;
                        params.height = viewHeight;
                        view.setLayoutParams(params);
                        ((ImageView) view).setImageBitmap(loadedImage);
                    }
                });
            }
        });
        holder.setText(R.id.tv, mData.get(position).getTitle());
        holder.setText(R.id.tv_count,mData.get(position).getCount());
    }

    @Override
    public void onItemClick(int position) {
        Intent intent=new Intent(mContext, PicListActivity.class);
        intent.putExtra("url",mData.get(position).getItemPageURL());
        mContext.startActivity(intent);
    }
}

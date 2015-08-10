package syiyi.com.meipic.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import java.util.ArrayList;
import java.util.List;
import syiyi.com.meipic.activity.MaxPhotoActivity;
import syiyi.com.meipic.R;
import syiyi.com.meipic.bean.PicItem;
import syiyi.com.meipic.util.ImageloaderUtil;

/**
 * Created by songlintao on 15/8/5.
 */
public class PicItemAdapter extends CommonAdapter<PicItem> {

    public PicItemAdapter(List<PicItem> data, Context mContext) {
        super(data, mContext, R.layout.pic_item);

    }

    @Override
    public void convert(ViewHolder holder,final int position) {
        final  ImageView iv = holder.getView(R.id.iv);

            TextView tv1=holder.getView(R.id.tv_title);
            TextView tv2=holder.getView(R.id.tv_contenet);
        if (position==0) {
            setTitleAndContent(tv1, tv2, mData.get(position));

            tv1.setVisibility(View.VISIBLE);
            tv2.setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams params1= (LinearLayout.LayoutParams) tv1.getLayoutParams();
            params1.topMargin=dip2px(mContext,12);
            tv1.setLayoutParams(params1);
            LinearLayout.LayoutParams params2= (LinearLayout.LayoutParams) tv2.getLayoutParams();
            params2.topMargin=dip2px(mContext,9);
            params2.bottomMargin=dip2px(mContext,9);
            tv2.setLayoutParams(params2);

        }else {

            LinearLayout.LayoutParams params1= (LinearLayout.LayoutParams) tv1.getLayoutParams();
            params1.topMargin=dip2px(mContext,0);
            tv1.setLayoutParams(params1);
            LinearLayout.LayoutParams params2= (LinearLayout.LayoutParams) tv2.getLayoutParams();
            params2.topMargin=dip2px(mContext,0);
            params2.bottomMargin=dip2px(mContext,0);
            tv2.setLayoutParams(params2);
            tv1.setVisibility(View.GONE);
            tv2.setVisibility(View.GONE);
        }
        iv.post(new Runnable() {
            @Override
            public void run() {

                ImageLoader.getInstance().displayImage(mData.get(position).getUrl(), iv, ImageloaderUtil.getOption(mContext), new SimpleImageLoadingListener() {
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
    }
    public void setTitleAndContent(TextView tvTitle,TextView tvContenet, PicItem item) {
        if (item.getTitle()!=null&&!item.equals("")) {
            tvTitle.setText(item.getTitle());
        }
        if (item.getContent()!=null&&!item.getContent().equals("")) {
            tvContenet.setText(Html.fromHtml(item.getContent()));
        }

    }
    @Override
    public void onItemClick(int position) {
        Intent intent=new Intent(mContext, MaxPhotoActivity.class);
        intent.putExtra("index",position);
        intent.putParcelableArrayListExtra("data", (ArrayList) mData);
        mContext.startActivity(intent);

    }
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}

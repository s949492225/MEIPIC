package syiyi.com.meipic.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import java.util.List;
import syiyi.com.meipic.R;
import syiyi.com.meipic.bean.PicItem;
import syiyi.com.meipic.service.DownloadService;
import syiyi.com.meipic.util.ImageloaderUtil;
import syiyi.com.meipic.view.MBDialog;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class MaxPhotoAdapter extends PagerAdapter {
    TextView tv_count;
    Handler handler;
    Context context;


    public MaxPhotoAdapter(List<PicItem> data, TextView tv_count, Handler handler, Context context) {
        this.data = data;
        this.tv_count=tv_count;
        this.handler=handler;
        this.context=context;
    }

    PhotoViewAttacher attacher;
    private List<PicItem> data;


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public View instantiateItem(ViewGroup container, final int position) {
        final PhotoView photoView = new PhotoView(container.getContext());

        container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        photoView.post(new Runnable() {
            @Override
            public void run() {
                ImageLoader.getInstance().displayImage(data.get(position).getUrl(), photoView, ImageloaderUtil.getOption(context), new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                        ((ImageView) view).setImageBitmap(loadedImage);
                        attacher = new PhotoViewAttacher((ImageView)view);
                        attacher.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
                            @Override
                            public void onViewTap(View view, float x, float y) {
                                if (tv_count.getVisibility() == View.GONE) {
                                    tv_count.setVisibility(View.VISIBLE);
                                    handler.sendEmptyMessageDelayed(0, 2000);
                                }
                            }
                        });
                        attacher.setOnLongClickListener(new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View v) {
                                MBDialog dialog = new MBDialog(context, R.layout.dialog_download, R.style.Theme_dialog, 0.4f, 0.1f);
                                dialog.show();
                                dialog.setOnClickListener(R.id.btn_confim, new MBDialog.OnClickListener() {
                                    @Override
                                    public void onClick(MBDialog view) {
                                        Intent intent = new Intent(context, DownloadService.class);
                                        intent.putExtra("url", data.get(position).getUrl());
                                        context.startService(intent);
                                    }
                                }, true);
                                return false;
                            }
                        });

                        attacher.update();
                    }
                });
            }
        });


        return photoView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}

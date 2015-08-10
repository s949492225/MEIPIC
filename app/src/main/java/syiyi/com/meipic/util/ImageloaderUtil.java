package syiyi.com.meipic.util;

import android.content.Context;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

import syiyi.com.meipic.app.MyApp;

/**
 * Created by songlintao on 15/8/8.
 */
public class ImageloaderUtil {
    public static DisplayImageOptions getOption(Context context){
       return  ((MyApp)context.getApplicationContext()).getOption();

    }
}

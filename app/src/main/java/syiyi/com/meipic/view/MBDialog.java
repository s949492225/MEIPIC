/**
 * MolbaseCustomDialog.java
 * com.molbase.mbapp.view
 * <p/>
 * Function： TODO
 * <p/>
 * ver     date      		author
 * ──────────────────────────────────
 * 2015-6-19 		howard
 * <p/>
 * Copyright (c) 2015, inspurworld All Rights Reserved.
 */

package syiyi.com.meipic.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import syiyi.com.meipic.util.ScreenUtil;


/**
 * ClassName:MBDialog Function: TODO ADD FUNCTION Modify：
 *
 * @author syiyi
 * @version fuck you !fuck you !fuck you !fuck you !fuck you !fuck you !fuck you !fuck you !fuck you !
 * @since Ver 1.1
 *
 *
 */
public class MBDialog extends Dialog {

    private int mLayoutId;
    private Context mContext;
    private float mPW, mPH;

    public MBDialog(Context context, int layoutId, int style, float percentWidth, float percentHeight) {
        super(context, style);
        mContext = context;
        mLayoutId = layoutId;
        mPW = percentWidth;
        mPH = percentHeight;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mLayoutId);
        getWindow().setLayout((int) (ScreenUtil.getScreenWidth(mContext) * mPW), (int) (ScreenUtil.getScreenHeight(mContext) * mPH));

    }

    public void setOnClickListener(int viewID, final OnClickListener listener, final boolean isCancle) {

        MBDialog.this.findViewById(viewID).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(MBDialog.this);
                }
                if (isCancle) {
                    dismiss();
                }
            }
        });
    }

    public interface OnClickListener {
        void onClick(MBDialog context);
    }

    public View getViewByID(int viewId) {
        return findViewById(viewId);
    }

}

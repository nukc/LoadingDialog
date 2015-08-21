package me.ckun.library;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;

/**
 * Created by C on 2015/8/21.
 */
public class LoadingDialog extends Dialog {

    private static final String TAG = "LoadingDialog";
    private static LoadingDialog mInstance = null;

    public LoadingDialog(Context context) {
        super(context, R.style.LoadingDialog);
    }

    public LoadingDialog(Context context, int theme) {
        super(context, theme);
    }

    public static LoadingDialog with(Context context){
        if (mInstance == null){
            mInstance = new LoadingDialog(context);
        }

        mInstance.setContentView(R.layout.view_loading);
        mInstance.getWindow().getAttributes().gravity = Gravity.CENTER;
        return mInstance;
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mInstance == null)
            return;

        ImageView imageView = (ImageView) mInstance.findViewById(R.id.ivLoading);
        ((AnimationDrawable) imageView.getBackground()).start();

        Log.d(TAG, "onAttachedToWindow");
    }

    @Override
    public void onDetachedFromWindow() {
        if (mInstance == null)
            return;

        ImageView imageView = (ImageView) mInstance.findViewById(R.id.ivLoading);
        ((AnimationDrawable) imageView.getBackground()).stop();

        Log.d(TAG, "onDetachedFromWindow");
        super.onDetachedFromWindow();
    }


}

package me.ckun.library;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by C on 2015/8/21.
 */
public class LoadingDialog extends Dialog {
    private static final String TAG = "LoadingDialog";

    private AnimationDrawable mAnimationDrawable;
    private Builder mBuilder;

    public LoadingDialog(Context context) {
        super(context, R.style.LoadingDialog);
    }

    public LoadingDialog(Context context, int theme) {
        super(context, theme);
    }

    public LoadingDialog(Context context, Builder builder){
        this(context, builder.mTheme);
        mBuilder = builder;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.view_loading);
        setCanceledOnTouchOutside(false);

        if (mBuilder.mImageId != 0){
            ImageView imageView = (ImageView) findViewById(R.id.ivLoading);
            imageView.setBackgroundResource(mBuilder.mImageId);
            imageView.setVisibility(View.VISIBLE);
            mAnimationDrawable = (AnimationDrawable) imageView.getBackground();
            findViewById(R.id.progressBar).setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(mBuilder.mMessage)){
            TextView textView = (TextView) findViewById(R.id.tvLoading);
            textView.setVisibility(View.VISIBLE);
            textView.setText(mBuilder.mMessage);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAnimationDrawable != null)
            mAnimationDrawable.start();

        Log.d(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mAnimationDrawable != null)
            mAnimationDrawable.stop();

        Log.d(TAG, "onStop");
    }


    public static class Builder {
        private final Context mContext;
        private int mTheme;
        private CharSequence mMessage;
        private int mImageId = 0;

        public Builder(Context context) {
            this(context, R.style.LoadingDialog);
        }

        public Builder(Context context, int theme) {
            mContext = context;
            mTheme = theme;
        }

        public Builder setTitle(){
            mMessage = mContext.getString(R.string.loading);
            return this;
        }

        public Builder setTitle(int messageId) {
            mMessage = mContext.getText(messageId);
            return this;
        }

        public Builder setTitle(CharSequence message) {
            mMessage = message;
            return this;
        }

        public Builder setImage(int imageId){
            mImageId = imageId;
            return this;
        }


        public LoadingDialog show(){
            LoadingDialog dialog = new LoadingDialog(mContext, this);
            dialog.show();
            return dialog;
        }

    }

    //    @Override
//    public void onAttachedToWindow() {
//        super.onAttachedToWindow();
////        if (mInstance == null)
////            return;
//
//        ImageView imageView = (ImageView) findViewById(R.id.ivLoading);
//        ((AnimationDrawable) imageView.getBackground()).start();
//
//        LogHelper.i(TAG, "onAttachedToWindow");
//    }
//
//    @Override
//    public void onDetachedFromWindow() {
////        if (mInstance == null)
////            return;
//
//        ImageView imageView = (ImageView) findViewById(R.id.ivLoading);
//        ((AnimationDrawable) imageView.getBackground()).stop();
//
//        LogHelper.i(TAG, "onDetachedFromWindow");
//        super.onDetachedFromWindow();
//    }

}

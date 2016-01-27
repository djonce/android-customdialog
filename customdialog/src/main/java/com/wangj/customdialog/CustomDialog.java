package com.wangj.customdialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wangj on 16/1/27
 */
public class CustomDialog extends Dialog{

    public CustomDialog(Context context) {
        super(context);
    }

    public CustomDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {
        private Context context;
        private String title;
        private String message;
        private ColorStateList messageColor;
        private float messageSiez;
        private String positiveButtonText;
        private String negativeButtonText;
        private View contentView;                // 自定义的view
        private ViewGroup contentContainer;
        private DialogInterface.OnClickListener positiveButtonClickLisenter;
        private DialogInterface.OnClickListener negativeButtonClickLisenter;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setTitle(int title) {
            setTitle((String)context.getText(title));
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setMessageSiez(float messageSiez) {
            this.messageSiez = messageSiez;
            return this;
        }

        public Builder setMessageColor(ColorStateList messageColor) {
            this.messageColor = messageColor;
            return this;
        }
    }




}

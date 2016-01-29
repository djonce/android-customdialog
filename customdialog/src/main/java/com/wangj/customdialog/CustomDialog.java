package com.wangj.customdialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


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
        private float messageSize;
        private String positiveButtonText;
        private String negativeButtonText;
        private View contentView;                // 自定义的view
        private ViewGroup contentContainer;
        private DialogInterface.OnClickListener positiveButtonClickListener;
        private DialogInterface.OnClickListener negativeButtonClickListener;
        private boolean cancelable;  // 是否可以点击其它区域取消
        private boolean noContentPadding; //设置中心无边距
        private int positiveButtonBackground; // 确定按钮背景色

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

        public Builder setMessageSize(float messageSize) {
            this.messageSize = messageSize;
            return this;
        }

        public Builder setMessageColor(ColorStateList messageColor) {
            this.messageColor = messageColor;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText, OnClickListener positiveButtonClickListener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = positiveButtonClickListener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText, OnClickListener negativeButtonClickListener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = negativeButtonClickListener;
            return this;
        }

        public Builder setPositiveButtonBackground(int positiveButtonBackground) {
            this.positiveButtonBackground = positiveButtonBackground;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setNoContentPadding(boolean noContentPadding) {
            this.noContentPadding = noContentPadding;
            return this;
        }

        public CustomDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final CustomDialog dialog = new CustomDialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

            View layout = inflater.inflate(R.layout.view_custom_dialog, null);

            Window window = dialog.getWindow();
            WindowManager.LayoutParams windowParams = window.getAttributes();
            Rect rect = new Rect();
            View view1 = window.getDecorView();
            view1.getWindowVisibleDisplayFrame(rect);
            windowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setBackgroundDrawableResource(android.R.color.transparent); //设置背景色透明
            window.setAttributes(windowParams);

            /**
             * setting title Text
             */
            if(title != null) {
                ((TextView)layout.findViewById(R.id.title)).setText(title);
            }else {
                layout.findViewById(R.id.title).setVisibility(View.GONE);
            }

            /**
             * 是否设置自定义的布局
             */
            contentContainer = (ViewGroup) layout.findViewById(R.id.contentContainer);
            if(contentView != null) {
                contentContainer.removeAllViews();
                contentContainer.addView(contentView);
                if (noContentPadding) {
                    contentContainer.setPadding(0, 0, 0, 0);
                }
            }else {
                if(message != null) {
                    ((TextView)layout.findViewById(R.id.message)).setText(message);
                }else {
                    layout.findViewById(R.id.message).setVisibility(View.GONE);
                }

                if(messageColor != null) {
                    ((TextView) layout.findViewById(R.id.message)).setTextColor(messageColor);
                }

                if(messageSize != 0.0) {
                    ((TextView) layout.findViewById(R.id.message)).setTextSize(messageSize);
                }
            }

            /**
             * 设置确认按钮
             */
            if(positiveButtonText == null) {
                // 没有确定按钮
                if(negativeButtonText == null) {
                    layout.findViewById(R.id.dialog_bottom).setVisibility(View.GONE);
                    layout.findViewById(R.id.dialog_line_horizon).setVisibility(View.GONE);
                }else{
                    layout.findViewById(R.id.dialog_line).setVisibility(View.GONE);
                    layout.findViewById(R.id.positiveButton).setVisibility(View.GONE);
                    layout.findViewById(R.id.negativeButton).setBackgroundResource(R.drawable.sel_dialog_btn_conner_b);
                }
            }else {
                //有确定按钮
                if(negativeButtonText == null) {
                    layout.findViewById(R.id.dialog_line).setVisibility(View.GONE);
                    layout.findViewById(R.id.negativeButton).setVisibility(View.GONE);
                    if(positiveButtonBackground != 0) {  // 设置背景色
                        layout.findViewById(R.id.positiveButton).setBackgroundResource(positiveButtonBackground);
                    }else {   //设置默认的背景
                        layout.findViewById(R.id.positiveButton).setBackgroundResource(R.drawable.sel_dialog_btn_conner_b);
                    }
                }
            }

            if(positiveButtonText != null) {
                ((Button)layout.findViewById(R.id.positiveButton)).setText(positiveButtonText);
                if(positiveButtonClickListener == null) {
                    positiveButtonClickListener = new OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    };
                }
                (layout.findViewById(R.id.positiveButton))
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                            }
                        });
            }else {
                layout.findViewById(R.id.positiveButton).setVisibility(View.GONE);
            }

            if(negativeButtonText != null) {
                ((Button) layout.findViewById(R.id.negativeButton)).setText(negativeButtonText);
                if(negativeButtonClickListener == null) {
                    negativeButtonClickListener = new OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    };
                }
                (layout.findViewById(R.id.negativeButton)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        negativeButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                    }
                });

            }else {
                layout.findViewById(R.id.negativeButton).setVisibility(View.GONE);
            }

            dialog.setContentView(layout);
            dialog.setCancelable(cancelable);  // 默认不可取消

            return dialog;
        }

    }

}

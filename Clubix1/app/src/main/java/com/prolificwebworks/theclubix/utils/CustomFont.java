package com.prolificwebworks.theclubix.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomFont extends TextView {
    public CustomFont(Context context) {
        super(context);
        setTypeface(context);
    }

    public CustomFont(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(context);
    }

    public CustomFont(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(context);
    }

    public void setTypeface(Context context) {
        Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/" + "ionicons.ttf");
        this.setTypeface(face);
    }
}

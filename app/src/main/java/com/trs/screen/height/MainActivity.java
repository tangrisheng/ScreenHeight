package com.trs.screen.height;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.Rect;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.lang.reflect.Method;

public class MainActivity extends Activity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    Button getHeightButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initCtrl();
    }

    void initCtrl() {
        getHeightButton = (Button) findViewById(R.id.btn_get_height);
        getHeightButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_get_height:
                getHeight();
                break;
        }
    }

    void getHeight() {

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Log.i(TAG, dm.toString());
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics realMetrics = new DisplayMetrics();
        display.getRealMetrics(realMetrics);

        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        Rect realRect = new Rect();
        wm.getDefaultDisplay().getRectSize(realRect);
        Log.i(TAG, realRect.toString());

        Rect displayRect = new Rect();
        getWindowManager().getDefaultDisplay().getRectSize(displayRect);
        Log.i(TAG, displayRect.toString());

        Rect appRect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(appRect);
        Log.i(TAG, appRect.toString());
        Log.i(TAG, appRect.top + "");
        Rect viewRect = new Rect();
        getWindow().findViewById(Window.ID_ANDROID_CONTENT).getDrawingRect(viewRect);
        Log.i(TAG, viewRect.toString());
        Log.i(TAG, "屏幕分辨率 -- " + realMetrics.heightPixels + "*" + realMetrics.widthPixels);
        Log.i(TAG, "状态栏的高度 -- " + appRect.top);
        Log.i(TAG, "默认标题栏高度 -- " + (appRect.height() - viewRect.height()));
        Log.i(TAG, "不包括虚拟按键，标题栏和状态栏的高度 -- " + viewRect.height());
        Log.i(TAG, "虚拟按键的高度 -- " + (realMetrics.heightPixels - appRect.bottom));
        Log.i(TAG, "不包括虚拟按键的高度 -- " + appRect.height());
    }
}

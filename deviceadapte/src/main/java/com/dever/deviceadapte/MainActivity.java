package com.dever.deviceadapte;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.txt_info);
        Resources resources = getResources();
        float width = resources.getDimension(R.dimen.my_width);
        textView.setText(Float.toString(width));

        //通过API需要获取高度，宽度，dp的数值；
        WindowManager windowManager = getWindowManager();
        Display defaultDisplay = windowManager.getDefaultDisplay();
        //获取屏幕的测量信息，包括屏幕的宽高
        DisplayMetrics outMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(outMetrics);

        int desitDpi = outMetrics.densityDpi;
        int widthPixels = outMetrics.widthPixels;
        int heightPixels = outMetrics.heightPixels;

        //px = (dpi/160)*dp,scaledDensity = (dpi/160)
        float scaledDensity = outMetrics.scaledDensity;
        //宽度dp，高度dp
        float wdp = widthPixels / scaledDensity;
        float hdp = heightPixels / scaledDensity;

        Log.d("MainActivity", "DensityDpi: "+desitDpi);
        Log.d("MainActivity", "widthPixels: "+widthPixels);
        Log.d("MainActivity", "heightPixels: "+heightPixels);
        Log.d("MainActivity", "scaledDensity: "+scaledDensity);
        Log.d("MainActivity", "wdp: "+wdp);
        Log.d("MainActivity", "hdp: "+hdp);
    }
}

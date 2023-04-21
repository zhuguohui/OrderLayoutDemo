package com.example.orderlayoutdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

public class MainActivity2 extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{
    private ImageView mImageView;
    private SeekBar mSeekbarHue,mSeekbarHue1,mSeekbarHue2,mSeekbarHue3,mSeekbarSaturation,mSeekbarLum;
    private final static int MAX_VALUE = 255;   //最大值
    private final static int MID_VALUE = 127;   //中间值

    private float mHue0,mHue1,mHue2,mHue3,mSaturation,mLum;

    private Bitmap bitmap;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //获取图片
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test1);

        mImageView = (ImageView)findViewById(R.id.image_view);

        mImageView.setImageBitmap(bitmap);

        mSeekbarHue = (SeekBar)findViewById(R.id.seekbar_hue_0);
        mSeekbarHue1 = (SeekBar)findViewById(R.id.seekbar_hue_1);
        mSeekbarHue2 = (SeekBar)findViewById(R.id.seekbar_hue_2);
        mSeekbarHue3 = (SeekBar)findViewById(R.id.seekbar_hue_3);

        mSeekbarSaturation = (SeekBar)findViewById(R.id.seekbar_saturation);
        mSeekbarLum = (SeekBar)findViewById(R.id.seekbar_lum);

        mSeekbarHue.setOnSeekBarChangeListener(this);
        mSeekbarHue1.setOnSeekBarChangeListener(this);
        mSeekbarHue2.setOnSeekBarChangeListener(this);
        mSeekbarHue3.setOnSeekBarChangeListener(this);



        mSeekbarSaturation.setOnSeekBarChangeListener(this);
        mSeekbarLum.setOnSeekBarChangeListener(this);

        mSeekbarHue.setMax(MAX_VALUE);
        mSeekbarHue1.setMax(MAX_VALUE);
        mSeekbarHue2.setMax(MAX_VALUE);
        mSeekbarHue3.setMax(MAX_VALUE);

        mSeekbarSaturation.setMax(MAX_VALUE);
        mSeekbarLum.setMax(MAX_VALUE);

        mSeekbarHue.setProgress(MID_VALUE);
        mSeekbarHue1.setProgress(MID_VALUE);
        mSeekbarHue2.setProgress(MID_VALUE);
        mSeekbarHue3.setProgress(MID_VALUE);

        mSeekbarSaturation.setProgress(MID_VALUE);
        mSeekbarLum.setProgress(MID_VALUE);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()){
            case R.id.seekbar_hue_0:
                //将0-255的值转换为色调值
                mHue0 = (progress - MID_VALUE)*1.0F/MID_VALUE*180;
                break;
            case R.id.seekbar_hue_1:
                //将0-255的值转换为色调值
                mHue1 = (progress - MID_VALUE)*1.0F/MID_VALUE*180;
                break;
            case R.id.seekbar_hue_2:
                //将0-255的值转换为色调值
                mHue2 = (progress - MID_VALUE)*1.0F/MID_VALUE*180;
                break;

            case R.id.seekbar_hue_3:
                //将0-255的值转换为色调值
                mHue3 = (progress - MID_VALUE)*1.0F/MID_VALUE*180;
                break;
            case R.id.seekbar_saturation:
                //将0-255值转换为饱和度值
                mSaturation = progress*1.0F/MID_VALUE;
                break;
            case R.id.seekbar_lum:
                //将0-255的值转换为亮度值
                mLum = progress*1.0F/MID_VALUE;
                break;
        }
        mImageView.setImageBitmap(ImageUtils.handleImageEffect(bitmap,
                mHue0,mHue1,mHue2,mHue3,mSaturation,mLum));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}

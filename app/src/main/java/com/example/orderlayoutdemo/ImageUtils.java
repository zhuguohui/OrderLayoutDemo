package com.example.orderlayoutdemo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

/**
 * <pre>
 * Created by zhuguohui
 * Date: 2023/4/21
 * Time: 17:24
 * Desc:
 * </pre>
 */
public class ImageUtils {
    public static Bitmap handleImageEffect(Bitmap bitmap, float hue0,float hue1,float hue2,float hue3, float saturation, float lum){
        //由于不能直接在原图上修改，所以创建一个图片，设定宽度高度与原图相同。为32位ARGB图片
        Bitmap currentBitmap = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        //创建一个和原图相同大小的画布
        Canvas canvas = new Canvas(currentBitmap);
        //创建笔刷并设置抗锯齿
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //色相ColorMatrix
        ColorMatrix hueMatrix = new ColorMatrix();
//        Log.i("zzz", "handleImageEffect: hue="+hue);
     //   hue0=getHub(Color.parseColor("#F0FAFF"),Color.parseColor("#FF8000"));
//        hueMatrix.setRGB2YUV();
//        hueMatrix.setRotate(0,hue0);
//        hueMatrix.setYUV2RGB();
      //  hueMatrix.setRotate(1,hue0);
       // hueMatrix.setRotate(2,hue0);
        //饱和度ColorMatrix
      //  ColorMatrix saturationMatrix = new ColorMatrix();
       // saturationMatrix.setSaturation(saturation);
        //亮度ColorMatrix
     //   ColorMatrix lumMatrix = new ColorMatrix();
      //  lumMatrix.setScale(lum,lum,lum,1);
     //   float[] scale = getScale(Color.parseColor("#F0FAFF"), Color.parseColor("#C8161D"));
        float[] scale=getScaleFromHube(hue0,hue1,hue2,hue3);
      //  lumMatrix.setScale(scale[0],scale[1],scale[2],scale[3]);
        //将三种效果融合起来
       // hue0=getHub(Color.parseColor("#F0FAFF"),Color.parseColor("#FF3168"));
        ColorMatrix cm = new ColorMatrix();
        ColorMatrix tmp = new ColorMatrix();

        cm.setRGB2YUV();
        tmp.setRotate(0, hue0);
        cm.postConcat(tmp);
        tmp.setYUV2RGB();
        cm.postConcat(tmp);


//        ColorMatrix imageMatrix = new ColorMatrix();
//        imageMatrix.postConcat(cm);


       // imageMatrix.postConcat(saturationMatrix);
      //  imageMatrix.postConcat(lumMatrix);

        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        canvas.drawBitmap(bitmap,0,0,paint);
        return currentBitmap;
    }

    private static float[] getScaleFromHube(float hue0, float hue1, float hue2,float hue3) {
        return new float[]{hue0/180,hue1/180,hue2/180,hue3/180};
    }


    private static int[] convertRGB2YUV(int color) {
        ColorMatrix cm = new ColorMatrix();
        cm.setRGB2YUV();
        final float[] yuvArray = cm.getArray();

        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
        int[] result = new int[3];
        result[0] = floatToByte(yuvArray[0] * r + yuvArray[1] * g + yuvArray[2] * b);
        result[1] = floatToByte(yuvArray[5] * r + yuvArray[6] * g + yuvArray[7] * b) + 127;
        result[2] = floatToByte(yuvArray[10] * r + yuvArray[11] * g + yuvArray[12] * b) + 127;
        return result;
    }

    private static int floatToByte(float x) {
        int n = java.lang.Math.round(x);
        return n;
    }

    public static float getHub(int fromColor,int targetColor){
    /*    float[] hsv1=new float[3];
        float[] hsv2=new float[3];
        Color.colorToHSV(fromColor,hsv1);
        Color.colorToHSV(targetColor,hsv2);
        return hsv2[0]-hsv1[0];*/
        int[] yuv1 = convertRGB2YUV(fromColor);
        int[] yuv2 = convertRGB2YUV(targetColor);
        return yuv2[0]-yuv1[1];
    }

    private static float[] getScale(int fromColor,int targetColor){
        int r1 = Color.red(fromColor);
        int g1 = Color.green(fromColor);
        int b1 = Color.blue(fromColor);

        int r2 = Color.red(targetColor);
        int g2 = Color.green(targetColor);
        int b2 = Color.blue(targetColor);

        float[] result=new float[3];
//        result[0]=(r2-r1)*1.0f/255;
//        result[1]=(g2-g1)*1.0f/255;
//        result[2]=(b2-b1)*1.0f/255;

        result[0]=r2*1.0f/255;
        result[1]=r2*1.0f/255;
        result[2]=r2*1.0f/255;

        return result;

    }
}

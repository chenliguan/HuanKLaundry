package com.guan.o2o.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;

import com.guan.o2o.common.Contant;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Guan
 * @file com.guan.o2o.utils
 * @date 2015/9/22
 * @Version 1.0
 */
public class BitmapUtil {

    /**
     * 将图片存储到sdcard中
     *
     * @param bitmap
     * @param imageName
     * @param imageFilePath
     */
    public static void storeImageToSDCARD(Bitmap bitmap, String imageName,
                                          String imageFilePath) {
        File file = new File(imageFilePath);
        if (!file.exists()) {
            file.mkdir();
        }
        File imagefile = new File(file, imageName + ".jpg");
        try {
            imagefile.createNewFile();
            FileOutputStream fos = new FileOutputStream(imagefile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * sdcard取图片
     *
     * @param imageFilePath
     * @param picName
     * @return
     */
    public static Bitmap getBitmapBySDCARD(String imageFilePath, String picName) {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return null;
        }
        try {
            File file = new File(imageFilePath, picName + ".jpg");
            FileInputStream inputStream = new FileInputStream(file);
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);
            Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
            return bitmap;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 以最省内存的方式读取本地资源的图片
     *
     * @param context
     * @param drawableId
     * @param screenWidth
     * @param screenHight
     * @return
     */
    public static Bitmap readBitmapById(Context context, int drawableId,
                                        int screenWidth, int screenHight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inInputShareable = true;
        options.inPurgeable = true;
        InputStream stream = context.getResources().openRawResource(drawableId);
        Bitmap bitmap = BitmapFactory.decodeStream(stream, null, options);
        return getBitmap(bitmap, screenWidth, screenHight);
    }

    /**
     * 等比例压缩图片
     *
     * @param bitmap
     * @param screenWidth
     * @param screenHight
     * @return
     */
    public static Bitmap getBitmap(Bitmap bitmap, int screenWidth,
                                   int screenHight) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        Matrix matrix = new Matrix();
        float scale = (float) screenWidth / w;
        float scale2 = (float) screenHight / h;

        //取比例小的值 可以把图片完全缩放在屏幕内
        scale = scale < scale2 ? scale : scale2;

        // 都按照宽度scale 保证图片不变形.根据宽度来确定高度
        matrix.postScale(scale, scale);
        // w,h是原图的属性.
        return Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
    }
}

package com.gxsn.gaodemapdemo.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;

import java.io.InputStream;

/**
 * Created by zkj on 2016/07/07
 * gulangyu
 */
public class BitmapUtil {

    public static Bitmap zoomImage(Bitmap bgimage, double newWidth,
                                   double newHeight) {
    // 获取这个图片的宽和高
        float width = bgimage.getWidth();
        float height = bgimage.getHeight();
    // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
    // 计算宽高缩放率
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
    // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width,
                (int) height, matrix, true);
        return bitmap;
    }


    /**
     * 加载图片进行压缩
     * @param resources
     * @param resID
     * @param resWidth
     * @param resHeight
     * @return
     */
    public static Bitmap decodeSampledBitmapFromResurce(Resources resources, int resID, int resWidth, int resHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        //获取图片采样率
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, resID, options);

        //Caculeate size
        options.inSampleSize = caluclateInSampleSize(options, resWidth, resHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources,resID,options);
    }

    /**
     * 获取图片压缩对应路径
     * @param path
     * @param resWidth
     * @param resHeight
     * @return
     */
    public static Bitmap decodeSampledBitmapFromResurcePath(String path, int resWidth, int resHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        //获取图片采样率
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        //Caculeate size
        options.inSampleSize = caluclateInSampleSize(options, resWidth, resHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }

    /**
     * 图片压缩对应byteArray
     * @param data
     * @param offset
     * @param length
     * @param resWidth
     * @param resHeight
     * @return
     */
    public static Bitmap decodeSampledBitmapFromResurceArray(byte[] data, int offset, int length, int resWidth, int resHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        //获取图片采样率
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, offset, length, options);

        //Caculeate size
        options.inSampleSize = caluclateInSampleSize(options, resWidth, resHeight);
        options.inJustDecodeBounds = false;
        return  BitmapFactory.decodeByteArray(data, offset, length, options);
    }


    /**
     * 基于流读取的图片压缩
     * @param inputStream
     * @param rect
     * @param resWidth
     * @param resHeight
     * @return
     */
    public static Bitmap decodeSampledBitmapFromResurceStream(InputStream inputStream, Rect rect, int resWidth, int resHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        //获取图片采样率
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, rect,options);

        //Caculeate size
        options.inSampleSize = caluclateInSampleSize(options, resWidth, resHeight);
        options.inJustDecodeBounds = false;
        return  BitmapFactory.decodeStream(inputStream, rect, options);
    }


    /**
     * 计算图片大小尺寸与原有尺寸比
     * @param options
     * @param resWidth
     * @param resHeight
     * @return
     */
    private static int caluclateInSampleSize(BitmapFactory.Options options, int resWidth, int resHeight) {
        int simpleSize = 1;
        int outHeight = options.outHeight;
        int outWidth = options.outWidth;
        if (outHeight > resHeight || outWidth > resWidth) {
            int height = outHeight / 2;
            int width = outWidth / 2;
            while (height / simpleSize >= resHeight && width /simpleSize >= resWidth) {
                simpleSize *= 6;
            }
        }
        return simpleSize;
    }
}

/**
 * ClassName: IOHelper.java
 * created on 2012-3-5
 * Copyrights 2011-2012 qjyong All rights reserved.
 * site: http://blog.csdn.net/qjyong
 * email: qjyong@gmail.com
 */
package com.gxsn.gaodemapdemo.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * IO帮助类
 * @author WangJJ
 */
public class IOHelper {

    /**
     * 从网络路径中获取资源名称
     * @param url
     * @return
     */
    public static String getName(String url) {
        String result = null;
        if (null != url) {
            result = url.substring(url.lastIndexOf("/") + 1);
        }
        return result;
    }

    /**
     * 获取指定文件名的扩展名
     * @param name
     * @return
     */
    public static String getExtension(String name) {
        return name.substring(name.lastIndexOf(".") + 1);
    }

    /**
     * 　　* 保存文件（追加写）
     * 　　* @param toSaveString
     * 　　* @param filePath
     *
     */
    public static void saveFile(String toSaveString, String filePath) {
        try {
            File saveFile = new File(filePath);
            if (!saveFile.exists()) {
                File dir = new File(saveFile.getParent());
                dir.mkdirs();
                saveFile.createNewFile();
            }

            FileOutputStream outStream = new FileOutputStream(saveFile, true);
            outStream.write(toSaveString.getBytes());
            outStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 　　保存文件（重新写）
     * 　　* @param toSaveString
     * 　　* @param filePath
     *
     */
    public static void overWriteFile(String toSaveString, String filePath) {
        try {
            File saveFile = new File(filePath);
            if (!saveFile.exists()) {
                File dir = new File(saveFile.getParent());
                dir.mkdirs();
                saveFile.createNewFile();
            }

            FileOutputStream outStream = new FileOutputStream(saveFile, false);
            outStream.write(toSaveString.getBytes());
            outStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 完成IO流的拷贝功能
     * @param is 输入流--->数据来源
     * @param os 输出流 ---> 数据的存储目标
     */
    public static void copy(InputStream is, OutputStream os) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        byte[] b = new byte[2048];
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(os);

            for (int count = 0; (count = bis.read(b)) != -1; ) {
                bos.write(b, 0, count);
            }
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 文件复制功能
     * @param src 源文件
     * @param dest 目标文件
     */
    public static void copy(File src, File dest) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        byte[] b = new byte[2048];

        try {
            bis = new BufferedInputStream(new FileInputStream(src));
            bos = new BufferedOutputStream(new FileOutputStream(dest));

            for (int count = -1; (count = bis.read(b)) != -1; ) {
                bos.write(b, 0, count);
            }
            bos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != bis) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != bos) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 统计指定目录下的子文件的大小（字节数），包括子孙目录下的所有文件
     * @param baseDir
     * @return
     */
    public static long totalFileSize(File baseDir) {
        long size = 0;
        if ((baseDir != null) && (baseDir.isDirectory())) {
            File[] subs = baseDir.listFiles();
            int length = subs == null ? 0 : subs.length;
            for (int i = 0; i < length; i++) {
                File sub = subs[i];
                if (sub.isFile()) {
                    size += sub.length();
                } else {
                    size += totalFileSize(sub);
                }
            }
        }
        return size;
    }

    /**
     * 刪除指定目录下的所有文件，包括子孙目录下的文件，并返回被删除的文件数量
     * @param baseDir
     * @return 被删除的文件数量
     */
    public static int clearFolder(File baseDir) {
        int count = 0;
        if ((baseDir != null) && (baseDir.isDirectory())) {
            File[] subs = baseDir.listFiles();
            int length = subs == null ? 0 : subs.length;
            for (int i = 0; i < length; i++) {
                File sub = subs[i];
                if (sub.isFile()) {
                    if (sub.delete()) {
                        count++;
                    }
                } else {
                    count += clearFolder(sub);
                }
            }
        }
        return count;
    }

    /**
     * 删除文件夹
     * @param dir
     */
    public static void deleteFolder(File dir) {
        if (dir.exists() && dir.isDirectory()) {
            clearFolder(dir);
            dir.delete();
        }
    }


//    // Get方式请求
//    public static String requestVersion() throws Exception {
//        // 新建一个URL对象
//        URL url = new URL(ConstantMobilesurvery.versionUrl);
//        // 打开一个HttpURLConnection连接
//        HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
//        // 设置连接超时时间
//        urlConn.setConnectTimeout(2000);
//        urlConn.setReadTimeout(2000);
//        // 开始连接
//        urlConn.connect();
//        // 判断请求是否成功
//        String s = null;
//        if (urlConn.getResponseCode() == 200) {
//            // 获取返回的数据
//            byte[] data = readStream(urlConn.getInputStream());
//            s = new String(data, "UTF-8");
//
//            System.out.println("====== si=== " + s);
//
//        } else {
//            Log.i("TAG_GET===", "Get方式请求失败");
//        }
//        // 关闭连接
//        urlConn.disconnect();
//        return s;
//    }
//
//    // Get方式请求
//    public static InputStream requestVersionXml() throws Exception {
//        // 新建一个URL对象
//        URL url = new URL(ConstantMobilesurvery.versionUrl);
//        // 打开一个HttpURLConnection连接
//        HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
//        // 设置连接超时时间
//        urlConn.setConnectTimeout(2000);
//        urlConn.setReadTimeout(2000);
//        // 开始连接
//        urlConn.connect();
//        // 判断请求是否成功
//        InputStream inputStream = urlConn.getInputStream();
//
//        // 关闭连接
//        urlConn.disconnect();
//        return inputStream;
//    }

    /**
     * 读取流数据
     *
     * @param inputStream
     * @return
     */
    public static byte[] readStream(InputStream inputStream) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int len = 0;
        try {
            while (-1 != (len = inputStream.read(buffer))) {
                output.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toByteArray();
    }

    /**
     * 移动指定文件或文件夹(包括所有文件和子文件夹) 
     *
     * @param from
     *            要移动的文件或文件夹 
     * @param to
     *            目标文件夹 
     * @throws Exception
     */
    public static void MoveFolderAndFileWithSelf(String from, String to) throws Exception {
        try {
            File dir = new File(from);
            // 目标  
            to += File.separator + dir.getName();
            File moveDir = new File(to);
            if (dir.isDirectory()) {
                if (!moveDir.exists()) {
                    moveDir.mkdirs();
                }
            } else {
                File tofile = new File(to);
                dir.renameTo(tofile);
                return;
            }


            // 文件一览  
            File[] files = dir.listFiles();
            if (files == null)
                return;

            // 文件移动  
            for (int i = 0; i < files.length; i++) {
                System.out.println("文件名：" + files[i].getName());
                if (files[i].isDirectory()) {
                    MoveFolderAndFileWithSelf(files[i].getPath(), to);
                    // 成功，删除原文件  
                    files[i].delete();
                }
                File moveFile = new File(moveDir.getPath() + File.separator + files[i].getName());
                // 目标文件夹下存在的话，删除  
                if (moveFile.exists()) {
                    moveFile.delete();
                }
                files[i].renameTo(moveFile);
            }
            dir.delete();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 复制单个文件(可更名复制) 
     * @param oldPathFile 准备复制的文件源 
     * @param newPathFile 拷贝到新绝对路径带文件名(注：目录路径需带文件名) 
     * @return
     */
    public static void CopySingleFile(String oldPathFile, String newPathFile) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPathFile);
            if (oldfile.exists()) { //文件存在时  
                InputStream inStream = new FileInputStream(oldPathFile); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPathFile);

                byte[] buffer = new byte[1024 * 8];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小  
                    //System.out.println(bytesum);  
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
                fs.flush();
                fs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Compress by quality,  and generate image to the path specified
     *
     * @param image
     * @param outPath
     * @param maxSize target will be compressed to be smaller than this size.(kb)
     * @throws IOException
     */
    public static void compressAndGenImage(Bitmap image, String outPath, int maxSize) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        // scale
        int options = 100;
        // Store the bitmap into output stream(no compress)
        image.compress(Bitmap.CompressFormat.JPEG, options, os);
        // Compress by loop
        while ( os.toByteArray().length / 1024 > maxSize) {
            // Clean up os
            os.reset();
            // interval 10
            options -= 10;
            image.compress(Bitmap.CompressFormat.JPEG, options, os);
        }

        // Generate compressed image file
        FileOutputStream fos = new FileOutputStream(outPath);
        fos.write(os.toByteArray());
        fos.flush();
        fos.close();
    }


    /**
     * 复制单个文件(原名复制) 
     * @param oldPathFile 准备复制的文件源 
     * @param targetPath 拷贝到新绝对路径带文件名(注：目录路径需带文件名)
     * @return
     */
    public static void CopySingleFileTo(String oldPathFile, String targetPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPathFile);
            String targetfile = targetPath + File.separator + oldfile.getName();
            if (oldfile.exists()) { //文件存在时  
                InputStream inStream = new FileInputStream(oldPathFile); //读入原文件
                FileOutputStream fs = new FileOutputStream(targetfile);
                byte[] buffer = new byte[1444];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小  
                    //System.out.println(bytesum);  
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
                fs.flush();
                fs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 复制整个文件夹的内容(含自身) 
     * @param oldPath 准备拷贝的目录 
     * @param newPath 指定绝对路径的新目录 
     * @return
     */
    public static void copyFolderWithSelf(String oldPath, String newPath) {
        try {
            new File(newPath).mkdirs(); //如果文件夹不存在 则建立新文件夹
            File dir = new File(oldPath);
            // 目标  
            newPath += File.separator + dir.getName();
            File moveDir = new File(newPath);
            if (dir.isDirectory()) {
                if (!moveDir.exists()) {
                    moveDir.mkdirs();
                }
            }
            String[] file = dir.list();
            File temp = null;
            for (int i = 0; i < file.length; i++) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file[i]);
                } else {
                    temp = new File(oldPath + File.separator + file[i]);
                }
                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(newPath +
                            "/" +
                            (temp.getName()).toString());
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if (temp.isDirectory()) { //如果是子文件夹  
                    copyFolderWithSelf(oldPath + "/" + file[i], newPath);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将apk中的文件拷贝到目标地址
     * @param myInput   输入流
     * @param targetPath 目标文件路径
     * @throws IOException
     */
    public static void copyBigDataToSD(InputStream myInput, String targetPath) throws IOException {
        OutputStream myOutput = new FileOutputStream(targetPath);
        byte[] buffer = new byte[1024];
        int length = myInput.read(buffer);
        while (length > 0) {
            myOutput.write(buffer, 0, length);
            length = myInput.read(buffer);
        }

        myOutput.flush();
        myInput.close();
        myOutput.close();
    }


    /**
     *
     * @param imgPath
     * @param bitmap
     * @param imgFormat 图片格式
     * @return
     */
    public static String imgToBase64(String imgPath, Bitmap bitmap, String imgFormat) {
        if (imgPath != null && imgPath.length() > 0) {
            bitmap = readBitmap(imgPath);
        }
        if (bitmap == null) {
            //bitmap not found!!
        }
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);

            out.flush();
            out.close();

            byte[] imgBytes = out.toByteArray();
            return Base64.encodeToString(imgBytes, Base64.DEFAULT);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            return null;
        } finally {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    private static Bitmap readBitmap(String imgPath) {
        try {
            return BitmapFactory.decodeFile(imgPath);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            return null;
        }

    }

    /**
     *
     * @param base64Data
     * @param imgName
     * @param imgFormat 图片格式
     */
    public static void base64ToBitmap(String base64Data, String imgName, String imgFormat) {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

        File myCaptureFile = new File("/sdcard/", imgName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(myCaptureFile);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        boolean isTu = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        if (isTu) {
            // fos.notifyAll();
            try {
                fos.flush();
                fos.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            try {
                fos.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    public static void readStreamData(String base64Data) {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

        File myCaptureFile = new File(base64Data);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(myCaptureFile);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        boolean isTu = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        if (isTu) {
            // fos.notifyAll();
            try {
                fos.flush();
                fos.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            try {
                fos.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    /**
     * 读取txt中的文件
     * @param path
     * @return
     */
    public static String getString(String path) {

        File file = new File(path);
        if (!file.exists())
            return null;

        InputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuffer sb = new StringBuffer("");
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


    /**
     * 得到inputStream
     * @param fileInput
     * @return
     */
    public static InputStream getInputStream(FileInputStream fileInput) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n = -1;
        InputStream inputStream = null;
        try {
            while ((n = fileInput.read(buffer)) != -1) {
                baos.write(buffer, 0, n);

            }
            byte[] byteArray = baos.toByteArray();
            inputStream = new ByteArrayInputStream(byteArray);
            return inputStream;


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}

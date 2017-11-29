package com.gxsn.gaodemapdemo.utils;

/**
 * Created by zyz on 2016/12/21.
 */

public class RequestBodyUtils {
//    public static Map<String, RequestBody> getMultipleBody(List<String> paths) {
//        Map<String, RequestBody> bodyMap = new HashMap<>();
//        if (paths.size() > 0) {
//            for (int i = 0; i < paths.size(); i++) {
//                File file = new File(paths.get(i));
//                bodyMap.put("file" + i + "\"; filename=\"" + file.getName(), RequestBody.create(MediaType.parse("image/png"), file));
//            }
//        }
//        return bodyMap;
//    }
//
//
//    public static MultipartBody.Part getRequestBody(String path) {
//        File file = new File(path);
//
//        RequestBody requestFile =
//                RequestBody.create(MediaType.parse("multipart/form-data"), file);
//
//        MultipartBody.Part body =
//                MultipartBody.Part.createFormData("file", file.getName(), requestFile);
//        return body;
//    }
}

package com.gxsn.gaodemapdemo.utils;

/**
 * Created by zkj on 2017/08/14
 * GreatWall
 */

public class SQLiteUtil {

    /**
     * 判断表格是否存在
     * @param tableName
     * @return
//     */
//    public static boolean tableIsExist(String tableName){
//        boolean result = false;
//        if(tableName == null){
//            return false;
//        }
//        Cursor cursor;
//        try {
//            String sql = "select count(*) as c from Sqlite_master where Type ='table' and name ='"+tableName.trim()+"' ";
//            cursor = DatabaseSqlHelper.getBasicDBSQLHelper().getSQLiteDatabase().rawQuery(sql, null);
//            if(cursor.moveToNext()){
//                int count = cursor.getInt(0);
//                if(count>0){
//                    result = true;
//                }
//            }
//            cursor.close();
//        } catch (Exception e) {
//            // TODO: handle exception
//            result = false;
//        }
//        return result;
//    }

    /**
     * 判断表中是否包含某个字段
     * @param tableName
     * @param columnName
     * @return
     */
//    public  boolean columnIsExistsInTable (String tableName, String columnName) {
//        boolean result = false ;
//        Cursor cursor = null ;
//        try{
//            cursor = DatabaseSqlHelper.getBasicDBSQLHelper().getSQLiteDatabase().rawQuery( "select * from sqlite_master where name = ? and sql like ?"
//                    , new String[]{tableName , "%" + columnName + "%"} );
//            result = null != cursor && cursor.moveToFirst() ;
//        }catch (Exception ignored){
//
//        }finally{
//            if(null != cursor && !cursor.isClosed()){
//                cursor.close() ;
//            }
//        }
//        return result ;
//    }

}

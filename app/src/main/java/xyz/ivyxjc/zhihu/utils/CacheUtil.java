package xyz.ivyxjc.zhihu.utils;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import xyz.ivyxjc.zhihu.TAG;

/**
 * Created by ivyxjc on on 2016/11/30.
 * This work is licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License.
 */

public class CacheUtil {
//    private static final String SQL_CREATE_ENTRIES =
//            "CREATE TABLE " + Constant.TABLE_NAME + "IF NOT EXISTS"+" (" +
//                    "id" + " INTEGER PRIMARY KEY," +
//                    "zhuanlan_title"+" String"+
//                    "article_title"+" String"+
//                    "article_content"+" String"+")";


    public static String calFilename(String suffix){
        return suffix;
    }

    public static String calFilename(int a){
        return a+"";
    }



    public  static void save(Context context, String filename,Object list){
        FileOutputStream out=null;
        ObjectOutputStream writer=null;
        try{
            out=context.openFileOutput(filename, Context.MODE_PRIVATE);
            writer=new ObjectOutputStream(out);
            writer.writeObject(list);
            Log.i(TAG.CACHE_UTIL,"cache list");
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.i(TAG.CACHE_UTIL,e.toString());
        }catch (IOException e){
            e.printStackTrace();
            Log.i(TAG.CACHE_UTIL,e.toString());
        }finally {
            try{
                if(writer!=null){
                    writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public static Object load(Context context,String filename){
        FileInputStream in=null;
        ObjectInputStream reader=null;
        Object res=null;
        try{
            in=context.openFileInput(filename);
            reader=new ObjectInputStream(in);
            res=reader.readObject();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(reader!=null){
                try{
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return res;
    }
}

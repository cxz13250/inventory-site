package cn.iselab.inventory.site.util;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Created by ROGK on 2017/9/23.
 */
public class JSONUtil {

    public static boolean isJson(String json){
        if(json==null || json=="")
            return false;
        try{
            new JSONObject(json);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean isJsonArray(String json){
        if(json==null || json=="")
            return false;
        Object object = new JSONTokener(json).nextValue();
        if(object instanceof JSONArray){
            return true;
        }else
            return false;
    }
}

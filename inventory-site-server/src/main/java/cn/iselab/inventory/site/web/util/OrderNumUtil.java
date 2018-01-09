package cn.iselab.inventory.site.web.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午11:21 2018/1/9
 * @Modified By:
 */
public class OrderNumUtil {

    public static String formatNum(Timestamp time,Long id){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String number;
        if(id/10==0){
            number="0000"+id;
        }else if(id/10>0&&id/100==0){
            number="000"+id;
        }else if(id/100>0&&id/1000==0){
            number="00"+id;
        }else if(id/1000>0&&id/10000==0){
            number="0"+id;
        }else {
            number=String.valueOf(id);
        }
        return format.format(time)+"-"+number;
    }
}

package cn.iselab.inventory.site.web.data.wrapper;

import cn.iselab.inventory.site.model.Complimentary;
import cn.iselab.inventory.site.service.ComplimentaryService;
import cn.iselab.inventory.site.service.CustomService;
import cn.iselab.inventory.site.util.JSONUtil;
import cn.iselab.inventory.site.web.data.ComplimentaryItem;
import cn.iselab.inventory.site.web.data.ComplimentaryVO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午12:04 2017/12/8
 * @Modified By:
 */
@Service
public class ComplimentaryVOWrapper extends BaseWrapper<ComplimentaryVO,Complimentary>{

    @Autowired
    CustomService customService;

    @Override
    public ComplimentaryVO wrap(Complimentary complimentary){
        ComplimentaryVO vo=new ComplimentaryVO();
        vo.setId(complimentary.getId());
        vo.setTotal(complimentary.getTotal());
        vo.setCustomId(complimentary.getCustomId());
        vo.setCustomName(customService.getCustom(complimentary.getCustomId()).getName());
        vo.setCreateTime(complimentary.getCreateTime().getTime());
        vo.setStatus(complimentary.getStatus());
        vo.setExtra(complimentary.getExtra());
        if(JSONUtil.isJsonArray(complimentary.getContent())){
            List<ComplimentaryItem> items=new ArrayList<>();
            JSONArray array=new JSONArray(complimentary.getContent());
            for (int i=0;i<array.length();i++){
                JSONObject jsonObject=array.getJSONObject(i);
                ComplimentaryItem item=new ComplimentaryItem();
                if(jsonObject.has("goodName"))
                    item.setGoodName(jsonObject.getString("goodName"));
                if(jsonObject.has("sum"))
                    item.setSum(jsonObject.getLong("sum"));
                if(jsonObject.has("money"))
                    item.setMoney(jsonObject.getDouble("money"));
                items.add(item);
            }
            vo.setItems(items);
        }
        return vo;
    }

    @Override
    public Complimentary unwrap(ComplimentaryVO vo){
        Complimentary complimentary=new Complimentary();
        if(vo.getItems()!=null){
            JSONArray array=new JSONArray(vo.getItems());
            complimentary.setContent(array.toString());
        }
        complimentary.setCustomId(vo.getCustomId());
        complimentary.setTotal(vo.getTotal());
        complimentary.setExtra(vo.getExtra());
        return complimentary;
    }
}

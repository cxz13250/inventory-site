package cn.iselab.inventory.site.web.data.wrapper;

import cn.iselab.inventory.site.model.Receipt;
import cn.iselab.inventory.site.service.CustomService;
import cn.iselab.inventory.site.util.JSONUtil;
import cn.iselab.inventory.site.web.data.ReceiptVO;
import cn.iselab.inventory.site.web.data.TransferVO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午11:50 2017/12/6
 * @Modified By:
 */
@Service
public class ReceiptVOWrapper extends BaseWrapper<ReceiptVO,Receipt> {

    @Autowired
    CustomService customService;

    @Override
    public ReceiptVO wrap(Receipt receipt){
        ReceiptVO vo = new ReceiptVO();
        vo.setNumber(receipt.getNumber());
        vo.setCreateTime(receipt.getCreateTime().getTime());
        vo.setOperator(receipt.getOperator());
        vo.setStatus(receipt.getStatus());
        vo.setTotal(receipt.getTotal());
        vo.setCreateTime(receipt.getCreateTime().getTime());
        vo.setCumstomId(receipt.getCumstomId());
        vo.setCumstomName(customService.getCustom(receipt.getCumstomId()).getName());
        if (JSONUtil.isJsonArray(receipt.getTransferList())) {
            List<TransferVO> transferVOS=new ArrayList<>();
            JSONArray array=new JSONArray(receipt.getTransferList());
            TransferVO vo1=new TransferVO();
            for(int i=0;i<array.length();i++){
                JSONObject object=array.getJSONObject(i);
                if(object.has("accountName"))
                    vo1.setAccountName(object.getString("accountName"));
                if(object.has("money"))
                    vo1.setMoney(object.getDouble("money"));
                if(object.has("extra"))
                    vo1.setExtra(object.getString("extra"));
                transferVOS.add(vo1);
            }
            vo.setTransfers(transferVOS);
        }
        return vo;
    }

    @Override
    public Receipt unwrap(ReceiptVO vo){
        Receipt receipt = new Receipt();
        receipt.setOperator(vo.getOperator());
        receipt.setTotal(vo.getTotal());
        receipt.setCumstomId(vo.getCumstomId());
        if(vo.getTransfers()!=null){
            JSONArray array=new JSONArray(vo.getTransfers());
            receipt.setTransferList(array.toString());
        }
        return receipt;
    }
}

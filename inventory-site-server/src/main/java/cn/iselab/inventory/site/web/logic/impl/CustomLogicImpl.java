package cn.iselab.inventory.site.web.logic.impl;

import cn.iselab.inventory.site.model.Custom;
import cn.iselab.inventory.site.service.CustomService;
import cn.iselab.inventory.site.util.JSONUtil;
import cn.iselab.inventory.site.web.data.CustomVO;
import cn.iselab.inventory.site.web.data.wrapper.CustomVOWrapper;
import cn.iselab.inventory.site.web.exception.HttpBadRequestException;
import cn.iselab.inventory.site.web.logic.CustomLogic;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午11:05 2017/12/4
 * @Modified By:
 */
@Service
public class CustomLogicImpl implements CustomLogic{

    @Autowired
    CustomService customService;

    @Autowired
    CustomVOWrapper customVOWrapper;

    @Override
    public Page<CustomVO> getCustoms(String keyword, Pageable pageable){
        Page<Custom> customs=customService.getCustoms(keyword,pageable);
        return customs.map(new Converter<Custom, CustomVO>() {
            @Override
            public CustomVO convert(Custom custom) {
                return customVOWrapper.wrap(custom);
            }
        });
    }

    @Override
    public List<CustomVO> getCustomsForReceipt(){
        List<Custom> customs=customService.getCustomsForReceipt();
        List<CustomVO> vos=new ArrayList<>();
        customs.forEach(custom -> {
            CustomVO vo=customVOWrapper.wrap(custom);
            vos.add(vo);
        });
        return vos;
    }

    @Override
    public List<CustomVO> getCustomsForSale(){
        List<Custom> customs=customService.getCustomsForSale();
        List<CustomVO> vos=new ArrayList<>();
        customs.forEach(custom -> {
            CustomVO vo=customVOWrapper.wrap(custom);
            vos.add(vo);
        });
        return vos;
    }

    @Override
    public List<CustomVO> getCustomsForPurchase(){
        List<Custom> customs=customService.getCustomsForPurchase();
        List<CustomVO> vos=new ArrayList<>();
        customs.forEach(custom -> {
            CustomVO vo=customVOWrapper.wrap(custom);
            vos.add(vo);
        });
        return vos;
    }

    @Override
    public CustomVO getCustom(Long customId){
        Custom custom=customService.getCustom(customId);
        if(custom==null){
            throw new HttpBadRequestException("custom not exists");
        }
        return customVOWrapper.wrap(custom);
    }

    @Override
    public Long createCustom(CustomVO customVO){
        Custom custom=customVOWrapper.unwrap(customVO);
        return customService.createCustom(custom);
    }

    @Override
    public void updateCustom(CustomVO customVO){
        Custom custom=customService.getCustom(customVO.getId());
        if(custom==null){
            throw new HttpBadRequestException("custom not exists");
        }
        customService.updateCustom(custom,customVO);
    }

    @Override
    public void deleteCustom(Long customId){
        Custom custom=customService.getCustom(customId);
        if(custom==null){
            throw new HttpBadRequestException("custom not exists");
        }
        customService.deletCustom(custom);
    }

    @Override
    public String getBank(String bankNo)throws Exception{
        String url = "https://ccdcapi.alipay.com/validateAndCacheCardInfo.json?_input_charset=utf-8&cardNo=";
        url+=bankNo;
        url+="&cardBinCheck=true";
        StringBuilder sb = new StringBuilder();
        try {
            URL urlObject = new URL(url);
            URLConnection uc = urlObject.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                sb.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(sb.toString());
        System.out.println(JSONUtil.isJson(sb.toString()));
        JSONObject object=new JSONObject(sb.toString());
        if(object.has("bank")) {
            return object.getString("bank");
        }else {
            throw new HttpBadRequestException("bankNo incorrect");
        }
    }
}

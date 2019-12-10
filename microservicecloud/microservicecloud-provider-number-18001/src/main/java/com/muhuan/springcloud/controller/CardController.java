package com.muhuan.springcloud.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.muhuan.api.util.ValidateUtil;
import com.muhuan.common.entity.Card;
import com.muhuan.springcloud.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pager.QueryCardPager;

import java.util.Map;

/**
 * @ClassName CardController
 * @Description TODO
 * @Author geng <yg.db@uniteddata.com>
 * @Date 2019/11/4
 * @Version 1.0.0
 **/

@RestController
@RequestMapping("/number")
//@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class CardController extends BaseController<Card> {

    @Autowired
    public CardController(CardService service) {
        super(service);
    }


//    @ResponseBody
//    @RequestMapping("/list/{operatorId}/{provinceId}/{cityId}")
//    public ResponseResult list(@PathVariable Integer operatorId, @PathVariable Integer provinceId, @PathVariable Integer cityId) {
//        Map<String, Object> columnMap = new HashMap<>();
//        columnMap.put("operator_id", operatorId);
//        columnMap.put("province_id", provinceId);
//        columnMap.put("city_id", cityId);
//        return ResultGeneratorUtil.getResultSuccessWithData(service.getByMap(columnMap));
//    }

    /**
     * 卡号简单分页条件查询
     */
    @RequestMapping(value = "/query")
    @ResponseBody
    public IPage<Card> query(QueryCardPager queryCardPager ) {
        Page page = new Page();
        page.setCurrent(queryCardPager.getOffset());
        page.setSize(queryCardPager.getLimit()<100?queryCardPager.getLimit():100);
        QueryWrapper<Card> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id")
                .like("operator_id", queryCardPager.getParams().get("operatorId"))
                .like("city_id", queryCardPager.getParams().get("cityId"));
        return service.queryByPage(page,wrapper);

    }

    /**
     * 卡号复杂分页条件查询
     */
    @RequestMapping(value = "/queryFlex")
    @ResponseBody
    public IPage<Card> queryFlex(QueryCardPager queryCardPager ) {
        Map<String,Object> params = queryCardPager.getParams();
        Page<Card> page = new Page<>();
        page.setCurrent(queryCardPager.getOffset());
        page.setSize(queryCardPager.getLimit()<100?queryCardPager.getLimit():100);
        QueryWrapper<Card> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        if(!ValidateUtil.isEmpty(params.get("operatorId"))){
            wrapper.eq("operator_id",params.get("operatorId"));
        }
        if(!ValidateUtil.isEmpty(params.get("province"))){
            wrapper.eq("province_id",params.get("province"));
            if(!ValidateUtil.isEmpty(params.get("city"))){
                wrapper.eq("city_id",params.get("city"));
            }
        }

        if(!ValidateUtil.isEmpty(((String)params.get("number")).replace("模糊搜索",""))){
//            System.out.print("模糊搜索");
            String mhss = (String) params.get("number");
            if (!ValidateUtil.isEmpty((params.get("midnum")))){
                mhss = "%" +mhss+"%";
            }


            wrapper.like("i_number", mhss);

        }
        if (!ValidateUtil.isEmpty(params.get("without4"))){
            wrapper.notLike("i_number", "4");
        }
        if (!ValidateUtil.isEmpty((params.get("without7")))){
            wrapper.notLike("i_number", "7");
        }

        if (!ValidateUtil.isEmpty(params.get("price"))){
//            System.out.print("价格区间");
            String priceParam = (String) params.get("price");
            if (priceParam.contains("-")){
                Float a = Float.valueOf(priceParam.split("-")[0]);
                Float b = Float.valueOf(priceParam.split("-")[1]);
                wrapper.between("price",a,b);
            }else if (priceParam.equals("50000以上")){
                wrapper.lt("price", 50000);
            }
        }
//        System.out.print("自定义价格区间");
        if (!ValidateUtil.isEmpty(params.get("zdyjg1"))){
            Float a = Float.valueOf(String.valueOf(params.get("zdyjg1")));
            wrapper.ge("price",a);
        }
        if (!ValidateUtil.isEmpty(params.get("zdyjg2"))){
            Float a = Float.valueOf(String.valueOf(params.get("zdyjg2")));
            wrapper.lt("price",a);
        }
//        System.out.print("排序");
        if (!ValidateUtil.isEmpty(params.get("Xu"))){
            int Xu = Integer.parseInt((String) params.get("Xu"));
            switch (Xu){
                case 1:
                    wrapper.orderByAsc("i_number");
                    break;
                case 2:
                    wrapper.orderByDesc("i_number");
                    break;
                case 3:
                    wrapper.orderByAsc("price");
                    break;
                case 4:
                    wrapper.orderByDesc("start");
                    break;
                case 5:
                    wrapper.orderByDesc("update_time");
                    break;
            }
        }
//        System.out.print("不存在");
        if (!ValidateUtil.isEmpty(params.get("Bshuwei"))){
            String Bshuwei = (String) params.get("Bshuwei");
            if (Bshuwei.contains(",")){
                String [] items = Bshuwei.split(",");
                for(String item : items){
                    wrapper.notLike("i_number", item);
                }
            }
            else {
                wrapper.notLike("i_number", Bshuwei);
            }
        }
//        System.out.print("查找多数");
        if (!ValidateUtil.isEmpty(params.get("shuwei"))){
            String shuwei = (String) params.get("shuwei");
            if (shuwei.contains(",")){
                String [] items = shuwei.split(",");
                for(String item : items){
                    wrapper.like("i_number", "%"+item+"%"+"%"+item+"%"+item+"%"+item+"%");
                }
            }
            else {
                wrapper.like("i_number", "%"+shuwei+"%"+"%"+shuwei+"%"+shuwei+"%"+shuwei+"%");
            }
        }

        String reg = "1";
        for (int i = 2;i<=11;i++){
            if (!ValidateUtil.isEmpty(params.get("n"+i))){
                String item = (String) params.get("n"+i);
                reg += item;
            }else {
                reg += "[0-9]";
            }
        }
        wrapper.inSql("i_number", "select i_number from select_card where i_number REGEXP '"+reg+"'");
        if (!ValidateUtil.isEmpty(params.get("rule"))) {
            int rule = Integer.parseInt((String) params.get("rule"));
            switch (rule){
                case 1:
                    //尾数AAAAA
                    wrapper.inSql("i_number", "select i_number from select_card where i_number REGEXP '([0-9])\\\\1{4}$'");
                    break;
                case 2:
                    // 尾数AAAA
                    wrapper.inSql("i_number", "select i_number from select_card where i_number REGEXP '([0-9])\\\\1{3}$'");
                    break;
                case 3:
                    //尾数AAABBB
                    wrapper.inSql("i_number", "select i_number from select_card where i_number REGEXP '([0-9])\\\\1{2}([0-9])\\\\2{2}$'");
                    break;
                case 4:
                    //尾数AABBB
                    wrapper.inSql("i_number", "select i_number from select_card where i_number REGEXP '([0-9])\\\\1{1}([0-9])\\\\2{2}$'");
                    break;
                case 5:
                    //尾数AAA
                    wrapper.inSql("i_number", "select i_number from select_card where i_number REGEXP '([0-9])\\\\1{2}$'");
                    break;
                case 6:
                    //尾数AABBCC
                    wrapper.inSql("i_number","select i_number from select_card where i_number REGEXP '([0-9])\\\\1{1}([0-9])\\\\2{1}([0-9])\\\\3{1}$'");
                    break;
                case 7:
                    //尾数AABB
                    wrapper.inSql("i_number", "select i_number from select_card where i_number REGEXP '([0-9])\\\\1{1}([0-9])\\\\2{1}$'");
                    break;
                case 9:
                    //尾数ABAB
                    wrapper.inSql("i_number","select i_number from select_card where  SUBSTRING(i_number,8,2)=SUBSTRING(i_number,10,2) and SUBSTRING(i_number,8,1)!=SUBSTRING(i_number,11,1)");
                    break;
                case 10:
                    // 尾数AA
                    wrapper.inSql("i_number", "select i_number from select_card where i_number REGEXP '([0-9])\\\\1{1}$'");
                    break;
                case 11:
                    // 六位顺增
                    wrapper.inSql("i_number", "select i_number from select_card where i_number REGEXP '(?:0(?=1)|1(?=2)|2(?=3)|3(?=4)|4(?=5)|5(?=6)|6(?=7)|7(?=8)|8(?=9)){5}[0-9\n" +
                            "]'");
                    break;
                case 12:
                    //六位顺降
                    wrapper.inSql("i_number", "select i_number from select_card where i_number REGEXP '(?:9(?=8)|8(?=7)|7(?=6)|6(?=5)|5(?=4)|4(?=3)|3(?=2)|2(?=1)|1(?=0)){5}[0-9]'");
                    break;
                case 13:
                    // 六位顺增或降
                    wrapper.inSql("i_number", "select i_number from select_card where i_number REGEXP '(?:(?:0(?=1)|1(?=2)|2(?=3)|3(?=4)|4(?=5)|5(?=6)|6(?=7)|7(?=8)|8(?=9)){5}|(?:9(?=8)|8(?=7)|7(?=6)|6(?=5)|5(?=4)|4(?=3)|3(?=2)|2(?=1)|1(?=0)){5})[0-9]'");
                    break;
                case 14:
                    // 中间AAAA
                    wrapper.inSql("i_number", "select i_number from select_card where i_number REGEXP '.([0-9])\\\\1{3}.'");
                    break;
                case 15:
                    // 中间AAAAA
                    wrapper.inSql("i_number", "select i_number from select_card where i_number REGEXP '.([0-9])\\\\1{4}.'");
                    break;
                case 16:
                    // 中间AAAAAA
                    wrapper.inSql("i_number", "select i_number from select_card where i_number REGEXP '.([0-9])\\\\1{5}.'");
                    break;
                case 17:
                    // 中间AAABB
                    wrapper.inSql("i_number", "select i_number from select_card where i_number REGEXP '.([0-9])\\\\1{2}([0-9])\\\\2{1}.'");
                    break;
                case 18:
                    // 中间AABBCC
                    wrapper.inSql("i_number","select i_number from select_card where i_number REGEXP '.([0-9])\\\\1{1}([0-9])\\\\2{1}([0-9])\\\\3{1}.'");
                    break;
                case 19:
                    // 尾数AAA*BBB*
                    wrapper.inSql("i_number","select i_number from select_card where i_number REGEXP '([0-9])\\\\1{2,}([0-9])\\\\2{2,}'");
                    break;
                case 20:
                    // 尾数AAABBB*
                    wrapper.inSql("i_number","select i_number from select_card where i_number REGEXP '([0-9])\\\\1{2}([0-9])\\\\2{2,}'");
                    break;
                case 21:
                    // 尾数AAA*BBB
                    wrapper.inSql("i_number","select i_number from select_card where i_number REGEXP '([0-9])\\\\1{2,}([0-9])\\\\2{2}'");
                    break;
                case 22:
                    // 四位顺增
                    wrapper.inSql("i_number", "select i_number from select_card where i_number REGEXP '(?:0(?=1)|1(?=2)|2(?=3)|3(?=4)|4(?=5)|5(?=6)|6(?=7)|7(?=8)|8(?=9)){3}[0-9\n" +
                            "]'");
                    break;
                case 23:
                    //四位顺降
                    wrapper.inSql("i_number", "select i_number from select_card where i_number REGEXP '(?:9(?=8)|8(?=7)|7(?=6)|6(?=5)|5(?=4)|4(?=3)|3(?=2)|2(?=1)|1(?=0)){3}[0-9]'");
                    break;
                case 24:
                    // 四位顺增或降
                    wrapper.inSql("i_number", "select i_number from select_card where i_number REGEXP '(?:(?:0(?=1)|1(?=2)|2(?=3)|3(?=4)|4(?=5)|5(?=6)|6(?=7)|7(?=8)|8(?=9)){3}|(?:9(?=8)|8(?=7)|7(?=6)|6(?=5)|5(?=4)|4(?=3)|3(?=2)|2(?=1)|1(?=0)){3})[0-9]'");
                    break;
                case 25:
                    // 中间AAA
                    wrapper.inSql("i_number", "select i_number from select_card where i_number REGEXP '.([0-9])\\\\1{2}.'");
                    break;
                case 26:
                    // 中间AA
                    wrapper.inSql("i_number", "select i_number from select_card where i_number REGEXP '.([0-9])\\\\1{1}.'");
                    break;
                case 27:
                    // 中间AABBB
                    wrapper.inSql("i_number", "select i_number from select_card where i_number REGEXP '.([0-9])\\\\1{1}([0-9])\\\\2{2}.'");
                    break;
                case 28:
                    // 中间AAABBB
                    wrapper.inSql("i_number", "select i_number from select_card where i_number REGEXP '.([0-9])\\\\1{2}([0-9])\\\\2{2}.'");
                    break;
            }
        }
        return service.queryByPage(page, wrapper);
    }



}

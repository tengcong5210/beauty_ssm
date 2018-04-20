package com.yingjun.ssm.web;

import com.yingjun.ssm.entity.Goods;
import com.yingjun.ssm.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 测试RestController和Controller的区别
 * 总结: 1.RestControllder是 @Controller和@ResponseBody注解的结合，使用此注解,视图解析器无法解析jsp、html页面；
 *        但返回json数据时，方法上不要使用@ResponseBody注解
 *       2.使用@Controller注解，视图解析器可以解析jsp、html页面；若要方法返回json格式，需要在方法上添加@ResponseBody注解
 *
 * @author tengcongcong
 * @create 2018-04-20 16:20
 * @Version 1.0
 **/
//@RestController
@Controller
@RequestMapping("/tests")
public class TestController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, Integer offset, Integer limit) {
        LOG.info("invoke----------/goods/list");
        offset = offset == null ? 0 : offset;//默认便宜0
        limit = limit == null ? 50 : limit;//默认展示50条
        List<Goods> list = goodsService.getGoodsList(offset, limit);
        model.addAttribute("goodslist", list);
        return "goodslist";
    }
}

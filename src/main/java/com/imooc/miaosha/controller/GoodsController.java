package com.imooc.miaosha.controller;

import com.imooc.miaosha.domain.MiaoshaUser;
import com.imooc.miaosha.redis.RedisService;
import com.imooc.miaosha.service.GoodsService;
import com.imooc.miaosha.service.MiaoshaUserService;
import com.imooc.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

	@Autowired
	MiaoshaUserService userService;
	
	@Autowired
	RedisService redisService;

    @Autowired
    GoodsService goodsService;
	/*
    @RequestMapping("/to_list")
    public String list(Model model,MiaoshaUser user) {
    	model.addAttribute("user", user);
        return "goods_list";
    }
    */
    //测试代码，把上面的函数名字改一下，试试效果
    @RequestMapping("/to_list")
    public String toLogin(Model model,MiaoshaUser user) {
        model.addAttribute("user", user);
        
        //查询商品列表
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        model.addAttribute("goodsList", goodsList); //做一个页面展示
        
        return "goods_list";
    }
    
}

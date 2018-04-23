package com.imooc.miaosha.service;

import com.imooc.miaosha.dao.MiaoshaUserDao;
import com.imooc.miaosha.domin.MiaoshaUser;
import com.imooc.miaosha.result.CodeMsg;
import com.imooc.miaosha.util.MD5Util;
import com.imooc.miaosha.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hushida on 18-4-22.
 */
@Service
public class MiaoshaUserService {

    @Autowired
    MiaoshaUserDao miaoshaUserDao;

    public MiaoshaUser getById(Long id) {
        return miaoshaUserDao.getById(id);

    }

    public CodeMsg login(LoginVo loginVo) {
        if(loginVo == null) {
            return CodeMsg.SERVER_ERROR;
        }

        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        //判断手机号码是否存在
        MiaoshaUser user = getById(Long.parseLong(mobile));
        if(user == null) {
            return CodeMsg.MOBILE_NOT_EXIST;
        }

        //验证密码
        String DBPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(formPass, saltDB);
        if(!calcPass.equals(DBPass)) {
            return CodeMsg.PASSWORD_ERROR;
        } else {
            return CodeMsg.SUCCESS;
        }
    }
}
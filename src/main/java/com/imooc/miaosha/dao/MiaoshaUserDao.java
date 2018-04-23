package com.imooc.miaosha.dao;

import com.imooc.miaosha.domin.MiaoshaUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by hushida on 18-4-22.
 */
@Mapper
public interface MiaoshaUserDao {

    @Select("select * from maiosha user where id = #{id}")
    public MiaoshaUser getById(@Param("id") long id);
}

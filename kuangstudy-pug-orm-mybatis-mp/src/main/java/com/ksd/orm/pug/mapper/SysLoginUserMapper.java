package com.ksd.orm.pug.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ksd.pug.pojo.SysLoginUser;
import com.ksd.pug.pojo.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2022/1/2 12:42
 */
public interface SysLoginUserMapper extends BaseMapper<SysLoginUser> {

    // 查询用户对应的角色
    List<SysRole> findSysRoleByUserId(@Param("userId")Long userid);
}

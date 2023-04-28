package com.ksd.orm.pug.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ksd.pug.pojo.SysPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yykk on 17/1/20.
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    // 查询用户对应角色的权限列表
    List<SysPermission> findByAdminUserId(@Param("userId") Long userId);
}

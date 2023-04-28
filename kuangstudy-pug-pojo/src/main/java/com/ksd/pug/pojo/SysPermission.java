package com.ksd.pug.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by yykk on 17/1/20.
 */
@TableName("sys_permission")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysPermission implements java.io.Serializable{
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    //权限名称
    private String name;
    //权限描述
    private String descritpion;
    //授权链接
    private String url;
    //父节点id
    private Long pid;
}
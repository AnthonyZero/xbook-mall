package com.xbook.dao.product.entity;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author anthonyzero
 * @since 2019-08-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 父类别id当id=0时说明是根节点,一级类别
     */
    @TableField("parent_id")
    private Integer parentId;

    /**
     * 类别名称
     */
    @TableField("name")
    private String name;

    /**
     * 类别状态1-正常,2-已废弃
     */
    @TableField("status")
    private Boolean status;

    /**
     * 排序编号,同类展示顺序,数值相等则自然排序
     */
    @TableField("sort_order")
    private Integer sortOrder;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;


}

package com.zh.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 卓少武
 * @date 2020/10/20
 */
@Mapper
public interface StorageDao {
    /**
     * 减库存
     * @param productId
     * @param count
     * @return
     */
    int decrease(@Param("productId") Long productId, @Param("count") Integer count);
}

package com.zyt.charging.provider.mapper;

import com.zyt.charging.provider.entity.domain.ReceiveReportDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: zyt
 * @Date: 2020/5/17
 */
@Mapper
public interface ReceiveReportMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ReceiveReportDO record);

    int insertSelective(ReceiveReportDO record);

    ReceiveReportDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReceiveReportDO record);

    int updateByPrimaryKey(ReceiveReportDO record);
}
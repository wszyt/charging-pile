package com.zyt.charging.provider.mapper;

import com.zyt.charging.provider.entity.domain.NodeInfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: zyt
 * @Date: 2020/5/17
 */
@Mapper
public interface NodeInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NodeInfoDO record);

    int insertSelective(NodeInfoDO record);

    NodeInfoDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NodeInfoDO record);

    int updateByPrimaryKey(NodeInfoDO record);

    NodeInfoDO selectByNodeId(@Param("nodeId") String nodeId);
}
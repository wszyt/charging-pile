package com.zyt.charging.provider.mapper;

import com.zyt.charging.provider.entity.domain.ChargeBrandsDO;
import com.zyt.charging.provider.entity.domain.CountCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: zyt
 * @Date: 2020/5/9
 */
@Mapper
public interface ChargeBrandsMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(ChargeBrandsDO record);

    int insertSelective(ChargeBrandsDO record);

    ChargeBrandsDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ChargeBrandsDO record);

    int updateByPrimaryKey(ChargeBrandsDO record);

    ChargeBrandsDO selectByBrandsAndType(ChargeBrandsDO chargeBrandsDO);

    Integer countChargeBrands(CountCondition countCondition);

    /**
     * 查询充电桩的所有品牌
     * @return
     */
    List<ChargeBrandsDO> selectEachChargeBrands();
}
package com.zyt.charging.provider.manager;

import com.zyt.charging.provider.entity.domain.ChargeBrandsDO;
import com.zyt.charging.provider.mapper.ChargeBrandsMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: zyt
 * @Date: 2020/5/10
 */
@Component
public class ChargeBrandsManager {
    @Resource
    ChargeBrandsMapper chargeBrandsMapper;

    public List<ChargeBrandsDO> selectEachChargeBrands() {
        return chargeBrandsMapper.selectEachChargeBrands();
    }

    public ChargeBrandsDO selectByPrimaryKey(Long id) {
        return chargeBrandsMapper.selectByPrimaryKey(id);
    }
}

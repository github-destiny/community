package cn.nero.community.mappers;

import cn.nero.community.domain.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/16
 */
@Mapper
@Repository
public interface CityMapper {

    /**
     * 查询城市信息通过省份与城市的名称,也可查询全部城市
     * @param city
     * @return
     */
    List<City> findCityByCondition(City city);

    /**
     * 查询符合条件的城市总数
     * @param city
     * @return
     */
    int getTotal(City city);

    /**
     * 更新风险地区
     * @param ids
     * @param level
     */
    void updateCityLevel(@Param("ids") List<String> ids, @Param("level") String level);

    /**
     * 根据地区修改风险等级
     * @param province
     */
    void updateCityByProvince(@Param("province") String province, @Param("level") String level);

    /**
     * 通过风险等级查询共有多少个风险城市
     * @param level
     * @return
     */
    int getTotalByLevel(@Param("level") String level);

    /**
     * 查询某个区域的风险城市
     * @param province
     * @param level
     * @return
     */
    List<City> findAreaByLevel(@Param("province") String province, @Param("level") String level);

    /**
     * 获取所有省份信息
     * @return
     */
    List<String> getAllProvince();
}

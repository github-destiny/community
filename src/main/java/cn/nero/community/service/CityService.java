package cn.nero.community.service;

import cn.nero.community.domain.City;

import java.util.List;
import java.util.Map;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/16
 */
public interface CityService {

    /**
     * 通过城市的部分信息查询他的全部信息
     * @param city
     * @return
     */
    List<Map<String, Object>> findCitiesByCondition(City city);

    /**
     * 更新地区的风险等级
     * @param ids
     * @param level
     */
    void updateCityLevel(List<String> ids, String level);

    /**
     * 更新省份的风险等级
     */
    void updateProvinceLevel(String province, String level);

    /**
     * 通过风险查询共有多少个风险城市
     * @param level
     * @return
     */
    int getTotalByLevel(String level);

    /**
     * 查询周围的城市风险情况
     * @param province
     * @param level
     * @return
     */
    List<City> findAreaLevel(String province, String level);

    List<Map<String, Object>> getAll();

    /**
     * 获取所有省份
     * @return
     */
    List<Map<String, Object>> getAllProvince();
}

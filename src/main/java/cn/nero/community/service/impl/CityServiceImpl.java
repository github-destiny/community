package cn.nero.community.service.impl;

import cn.nero.community.domain.City;
import cn.nero.community.mappers.CityMapper;
import cn.nero.community.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/16
 */
@Service
@Transactional
@Slf4j
public class CityServiceImpl implements CityService {

    @Autowired
    private CityMapper cityMapper;

    @Override
    public List<Map<String, Object>> findCitiesByCondition(City city) {
        List<City> list = cityMapper.findCityByCondition(city);
        Map<String, List<City>> collect =
                list.stream().collect(Collectors.groupingBy(City::getProvince));
        List<Map<String, Object>> resList = new ArrayList();
        Set<String> keys = collect.keySet();
        int total = cityMapper.getTotal(city);
        for (String key : keys) {
            List<City> cities = collect.get(key);
            Map<String, Object> m = new HashMap<>();
            m.put("label", key);
            m.put("cities", cities);
            resList.add(m);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        resList.add(map);
        return resList;
    }

    @Override
    public void updateCityLevel(List<String> ids, String level) {
        cityMapper.updateCityLevel(ids, level);
    }

    @Override
    public void updateProvinceLevel(String province, String level) {
        cityMapper.updateCityByProvince(province, level);
    }

    @Override
    public int getTotalByLevel(String level) {
        return cityMapper.getTotalByLevel(level);
    }

    @Override
    public List<City> findAreaLevel(String province, String level) {
        return cityMapper.findAreaByLevel(province, level);
    }

    @Override
    public List<Map<String, Object>> getAll() {
        List<City> dataList = cityMapper.findCityByCondition(null);
        List<Map<String, Object>> resList = new ArrayList<>();
        for (City city : dataList) {
            Map<String, Object> map = new HashMap<>();
            map.put("value", city.getId());
            map.put("label", city.getCity());
            resList.add(map);
        }
        return resList;
    }

    @Override
    public List<Map<String, Object>> getAllProvince() {
        List<String> provinces = cityMapper.getAllProvince();
        List<Map<String, Object>> resList = new ArrayList<>();
        for (String province : provinces) {
            Map<String, Object> map = new HashMap<>();
            map.put("value", province);
            map.put("label", province);
            resList.add(map);
        }
        return resList;
    }


}

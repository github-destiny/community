package cn.nero.community.controller;

import cn.nero.community.domain.City;
import cn.nero.community.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/16
 */
@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/find")
    public List<Map<String, Object>> findCityByCondition(City city){
        return cityService.findCitiesByCondition(city);
    }

    @GetMapping("/update/city/level")
    public void updateCityLevel(@RequestParam("ids") List<String> ids,
                                @RequestParam("level") String level){
        cityService.updateCityLevel(ids, level);
    }

    @GetMapping("/update/province/level")
    public void updateProvinceLevel(@RequestParam("province") String province,
                                    @RequestParam("level") String level){
        cityService.updateProvinceLevel(province, level);
    }

    @GetMapping("/get/all")
    public List<Map<String, Object>> getAllCities(){
        return cityService.getAll();
    }

    @GetMapping("/get/province")
    public List<Map<String, Object>> getAllProvince(){
        return cityService.getAllProvince();
    }



}

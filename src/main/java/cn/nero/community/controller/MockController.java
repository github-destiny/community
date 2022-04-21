package cn.nero.community.controller;

import cn.nero.community.domain.City;
import cn.nero.community.domain.Inoculation;
import cn.nero.community.domain.Resident;
import cn.nero.community.domain.Returnees;
import cn.nero.community.mappers.ResidentMapper;
import cn.nero.community.mappers.ReturneesMapper;
import cn.nero.community.mock.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/11
 */
@Controller
@RequestMapping("/mock")
public class MockController {

    @Autowired
    private ResidentMapper residentMapper;

    @Autowired
    private ReturneesMapper returneesMapper;

    @GetMapping("/resident")
    @ResponseBody
    public String mockResident(){
        for (int i = 0; i < 100; i++) {
            List<Resident> residents = Mock.getManyResident(50);
            residentMapper.saveManyResident(residents);
        }
        return "success";
    }

    @GetMapping("/inoculation")
    @ResponseBody
    public String mockInoculation(){
        for (int i = 0; i < 100; i++) {
            List<Inoculation> inoculations = Mock.getManyInoculation(i * 50 + 1, 50);
            residentMapper.batchSaveInoculationInfo(inoculations);
        }
        return "success";
    }


    @GetMapping("/returnees")
    @ResponseBody
    public String mockReturnees() throws IOException {
        for (int i = 0; i < 10; i++) {
            List<Returnees> list = Mock.getManyReturnees(20);
            returneesMapper.saveManyReturnees(list);
        }
        return "success";
    }

    @GetMapping("/cities")
    @ResponseBody
    public String mockCities() throws IOException {
        List<City> list = Mock.getCityList();
        returneesMapper.saveCities(list);
        return "success";
    }
}

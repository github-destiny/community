package cn.nero.community.service.impl;

import cn.nero.community.domain.Inoculation;
import cn.nero.community.domain.Resident;
import cn.nero.community.domain.User;
import cn.nero.community.mappers.NucleicMapper;
import cn.nero.community.mappers.ResidentMapper;
import cn.nero.community.service.ResidentService;
import cn.nero.community.domain.vo.PaginationVO;
import cn.nero.community.domain.vo.ResidentInoculationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/11
 */
@Service
@Transactional
@Slf4j
public class ResidentServiceImpl implements ResidentService {

    @Autowired
    private ResidentMapper residentMapper;

    @Autowired
    private NucleicMapper nucleicMapper;

    @Override
    public void saveResident(Resident resident) {
        // 创建resident
        residentMapper.saveResident(resident);
        // 在核酸检测登记表中添加对应的数据记录
        nucleicMapper.saveNucleicByResidentIdColumn(resident.getId());
    }

    @Override
    public void editResidentInfo(Resident resident) {
        residentMapper.editResidentInfo(resident);
    }

    @Override
    public Resident findResidentByUserId(String userId) {
        return residentMapper.findResidentByUserId(userId);
    }

    @Override
    public void deleteResident(String residentId) {
        residentMapper.deleteResident(residentId);
    }

    @Override
    public PaginationVO<Resident> findResidentByCondition(Resident resident, Integer skipCount, Integer pageSize) {
        List<Resident> dataList = residentMapper.findResidentsByCondition(resident, skipCount, pageSize);
        int total = residentMapper.getTotal(resident);
        return new PaginationVO<>(dataList, total);
    }

    @Override
    public List<Map<String, Object>> getResidentGroupByAge() {
        Integer teen = residentMapper.getTotalAboutAge("0", "17");
        Map<String, Object> tMap = new HashMap<>();
        tMap.put("name", "0-17岁");
        tMap.put("value", teen);
        Integer young = residentMapper.getTotalAboutAge("18", "45");
        Map<String, Object> yMap = new HashMap<>();
        yMap.put("name", "18-45岁");
        yMap.put("value", young);
        Integer middleAge = residentMapper.getTotalAboutAge("46", "69");
        Map<String, Object> mMap = new HashMap<>();
        mMap.put("name", "46-69岁");
        mMap.put("value", middleAge);
        Integer oldAge = residentMapper.getTotalAboutAge("70", "150");
        Map<String, Object> oMap = new HashMap<>();
        oMap.put("name", "70岁及以上");
        oMap.put("value", oldAge);
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(tMap);
        list.add(yMap);
        list.add(mMap);
        list.add(oMap);
        return list;
    }

    @Override
    public Map<String, Object> findResidentAndInoculationInfo(Map<String, Object> map) {
        Map<String, Object> resMap = new HashMap<>();
        List<ResidentInoculationVO> dataList = residentMapper.findResidentInoculationInfoByCondition(map);
        resMap.put("dataList", dataList);
        String times = (String) map.get("times");
        String lastTime = (String) map.get("lastTime");
        String resident_id = (String) map.get("resident_id");
        String name = (String) map.get("name");
        String address = (String) map.get("address");
        if (null != times || null != lastTime) {
            Inoculation inoculation = new Inoculation();
            inoculation.setTimes(times).setLastTime(lastTime);
            int count = residentMapper.getInoculationCount(inoculation);
            resMap.put("total", count);
        } else {
            Resident resident = new Resident();
            resident.setName(name).setAddress(address).setId(resident_id);
            int count = residentMapper.getTotal(resident);
            resMap.put("total", count);
        }
        return resMap;
    }

    @Override
    public List<Map<String, Object>> getInoculationData() {
        Inoculation ino = new Inoculation();
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            String name = "";
            if (i == 0) {
                name = "未接种";
            } else {
                name = "接种" + i + "针";
            }
            // 设置接种针数
            ino.setTimes(String.valueOf(i));
            // 查询接种人数
            int count = residentMapper.getInoculationCount(ino);
            // 保存结果集
            Map<String, Object> map = new HashMap<>();
            map.put("name", name);
            map.put("value", count);
            // 封装到list中
            list.add(map);
        }
        return list;
    }

    @Override
    public User findUserByResidentId(String residentId) {
        return residentMapper.findUserByResidentId(residentId);
    }
}

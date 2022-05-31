package cn.nero.community.service.impl;

import cn.nero.community.domain.Nucleic;
import cn.nero.community.domain.Resident;
import cn.nero.community.domain.vo.ResidentNucleicVO;
import cn.nero.community.exception.ResidentException;
import cn.nero.community.mappers.NucleicMapper;
import cn.nero.community.mappers.ResidentMapper;
import cn.nero.community.service.NucleicService;
import cn.nero.community.utils.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/18
 */
@Service
@Transactional
@Slf4j
public class NucleicServiceImpl implements NucleicService {

    @Autowired
    private NucleicMapper nucleicMapper;

    @Autowired
    private ResidentMapper residentMapper;

    @Override
    public void updateNucleic(String condition, String result) {
        Resident resident = residentMapper.findResidentByIdCardOrPhone(condition);
        if (ObjectUtils.isEmpty(resident)) {
            throw new ResidentException("不存在该居民!请重新录入数据!");
        }
        Nucleic nucleic = nucleicMapper.findNucleicByResidentId(resident.getId());
        if (ObjectUtils.isEmpty(nucleic)){
            nucleic = new Nucleic();
            nucleic.setResident_id(resident.getId()).setResult(result).setTime(DateTimeUtil.getDate());
            nucleicMapper.saveNucleic(nucleic);
            return;
        }
        // 注入检测时间
        nucleic.setTime(DateTimeUtil.getDate()).setResult(result);
        // 更新状态
        nucleicMapper.editNucleic(nucleic);
    }

    @Override
    public void batchUpdateNucleic(List<String> residentIds, String result, String time) {
        nucleicMapper.batchEditNucleic(residentIds, result, time);
    }

    @Override
    public void batchUpdateReturneesNucleic(List<String> returneesIds, String testTime, String result) {
        nucleicMapper.batchUpdateReturneesNucleic(returneesIds, testTime, result);
    }

    @Override
    public ResidentNucleicVO findNucleicByIdCardOrIDOrPhone(String id, String idCard, String phone) {
        return nucleicMapper.findNucleicByIdCardOrId(id, idCard, phone);
    }

    @Override
    public Map<String, Object> getNucleicResultAll(String startTime, String endTime) {
        Map<String, Object> map = new HashMap<>();
        int count = residentMapper.getResidentCount();
        // 注入总人数
        map.put("count", count);
        // 获取做核酸人数的总数
        int total = nucleicMapper.findNucleicResultAll(null, startTime, endTime);
        // 获取检测阴性的人数
        int negative = nucleicMapper.findNucleicResultAll("阴性", startTime, endTime);
        // 获取阳性人数
        int positive = nucleicMapper.findNucleicResultAll("阳性", startTime, endTime);
        if (0 != positive) {
            // 获取疑似确诊病例的个人资料,并注入返回结果中
            List<ResidentNucleicVO> residents = nucleicMapper.findPositiveResidentNucleicVO();
            map.put("level", "danger");
            map.put("desc", "出现"+ positive +"例疑似患者!");
            map.put("residents", residents);
        }
        map.put("time", startTime + "--" + endTime);
        map.put("total", total);
        map.put("negative", negative);
        map.put("positive", positive);
        return map;
    }

    @Override
    public void updateAll(String result, String time) {
        nucleicMapper.updateAll(result, time);
    }

    @Override
    public Map<String, Object> init(String startTime, String endTime) {
        Map<String, Object> map = new HashMap<>();
        // 获取居民人数
        int count = residentMapper.getResidentCount();
        map.put("count", count);
        // 获取昨日核酸检测人数
        int nucleicTotal = nucleicMapper.getNucleicTotal(startTime, endTime);
        Map<String, Object> echartsMap = new HashMap<>();
        echartsMap.put("value", nucleicTotal);
        echartsMap.put("label", "检测人数");
        map.put("echarts", echartsMap);
        // 获取最近一次的核酸检测时间
        String recentTime = nucleicMapper.getRecentNucleicTime();
        map.put("recent", recentTime);
        return map;
    }

}

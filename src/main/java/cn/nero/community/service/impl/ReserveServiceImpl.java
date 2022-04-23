package cn.nero.community.service.impl;

import cn.nero.community.domain.Reserve;
import cn.nero.community.domain.Resident;
import cn.nero.community.domain.User;
import cn.nero.community.domain.vo.ResidentReserveVO;
import cn.nero.community.exception.ReserveException;
import cn.nero.community.mappers.ReserveMapper;
import cn.nero.community.mappers.ResidentMapper;
import cn.nero.community.service.ReserveService;
import cn.nero.community.utils.DateTimeUtil;
import cn.nero.community.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/22
 */
@Service
@Transactional
@Slf4j
public class ReserveServiceImpl implements ReserveService {

    @Autowired
    private ReserveMapper reserveMapper;

    @Autowired
    private ResidentMapper residentMapper;

    @Override
    public void saveReserve(Reserve reserve) {
        // 自动设置为最近的一次接种日期
        Map<String, Object> appointment = findAppointment();
        String time = (String) appointment.get("time");
        // 检查是否可以预约了
        String current = DateTimeUtil.getDate();
        if (current.compareTo(time) > 0) {
            throw new ReserveException("最近一次的预约已关闭,请等待下次预约开放!");
        }
        // 注入预约时间
        reserve.setTime(time);
        // 检查是否预约过了
        ResidentReserveVO residentReserveVO = reserveMapper.findReserveInfoByIdCard(reserve.getResident_id());
        if (!ObjectUtils.isEmpty(residentReserveVO)){
            throw new ReserveException("您已经预约过了!");
        }
        // 自动生成预约第几针
        if (reserve.getNum() == null) {
            int times = residentMapper.getResidentInoculationNumByResidentId(reserve.getResident_id());
            times = times + 1;
            reserve.setNum(String.valueOf(times));
        }
        reserve.setCreateTime(DateTimeUtil.getTime());

        reserveMapper.saveReserve(reserve);
    }

    @Override
    public Map<String, Object> checkReserve(String idCard) {
        ResidentReserveVO vo = reserveMapper.findReserveInfoByIdCard(idCard);
        Map<String, Object> map = new HashMap<>();
        if (!ObjectUtils.isEmpty(vo)) {
            map.put("info", vo);
            map.put("msg", "您已经预约了接种");
        } else {
            map.put("msg", "您未预约接种");
        }
        return map;
    }

    @Override
    public Map<String, Object> findAppointment() {
        String appointment = reserveMapper.findAppointment();
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "最近的预约日期");
        map.put("time", appointment);
        return map;
    }

    @Override
    public Map<String, Object> statistics(String time) {
        int statistics = reserveMapper.statistics(time);
        Map<String, Object> map = new HashMap<>();
        map.put("time", time);
        map.put("total", statistics);
        return map;
    }

    @Override
    public void revokeReserve(String residentId) {
        reserveMapper.revokeReserve(residentId);
    }
}

package cn.nero.community.service.impl;

import cn.nero.community.domain.Inoculation;
import cn.nero.community.domain.Reserve;
import cn.nero.community.domain.Resident;
import cn.nero.community.domain.User;
import cn.nero.community.domain.vo.ResidentReserveVO;
import cn.nero.community.exception.ReserveException;
import cn.nero.community.exception.ResidentException;
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
import java.util.List;
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
        String resident_id = reserve.getResident_id();
        Resident resident = residentMapper.findResidentById(resident_id);
        if (ObjectUtils.isEmpty(resident)) {
            throw new ResidentException("查无此居民!请添加居民信息后再预约!");
        }
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

    @Override
    public ResidentReserveVO reverse(String idCard, String times) {
        // 查找居民信息
        Resident resident = residentMapper.findResidentByIdCard(idCard);
        if (ObjectUtils.isEmpty(resident)) {
            throw new ResidentException("查无此居民,请先将该居民信息进行注册后再进行预约!");
        }
        // 如果存在居民信息,先查找他是否已经预约过了
        ResidentReserveVO vo = reserveMapper.findReserveInfoByIdCard(idCard);
        // 如果该居民存在预约信息
        if (null != vo) {
            // 如果预约时间和最新时间不同
            String recentTime = reserveMapper.findAppointment();
            if (!vo.getAppointment().equals(recentTime)) {
                // 更新创建时间以及接种针数
                Reserve reserve = new Reserve();
                int reserveNum = Integer.parseInt(vo.getReserveNum());
                reserveNum += 1;
                reserve.setTime(recentTime)
                        .setResident_id(vo.getResidentId())
                        .setNum(String.valueOf(reserveNum))
                        .setCreateTime(DateTimeUtil.getTime());
                reserveMapper.updateReserve(reserve);
                return reserveMapper.findReserveInfoByIdCard(idCard);
            }
        }
        if (!ObjectUtils.isEmpty(vo)) {
            return vo;
        }
        // 查找核酸检测信息
        Inoculation inoculation = residentMapper.findInoculationByResidentId(resident.getId());
        if (ObjectUtils.isEmpty(inoculation)) {
            inoculation = new Inoculation();
            log.info("正在为他创建一个疫苗接种记录...");
            if (times == null) {
                times = "0";
                log.info("注入初始次数:{}", times);
            }
            inoculation.setTimes(times).setResident_id(resident.getId());
            residentMapper.addInoculationInfo(inoculation);
            log.info("创建疫苗接种记录成功!");
        }
        Reserve reserve = new Reserve();
        reserve.setResident_id(resident.getId());
        saveReserve(reserve);
        // 预约成功后再次查询预约情况斤进行返回
        return reserveMapper.findReserveInfoByIdCard(idCard);
    }

    @Override
    public Map<String, Object> getReserveInfo(String time, Integer skipCount, Integer pageSize) {
        if (null == time || time.equals("")) {
            time = reserveMapper.findAppointment();
        }
        // 获取当天接种人的信息列表
        List<ResidentReserveVO> dataList = reserveMapper.findReserveInfo(time, skipCount, pageSize);
        int total = reserveMapper.statistics(time);
        Map<String, Object> map = new HashMap<>();
        map.put("dataList", dataList);
        map.put("total", total);
        return map;
    }

    @Override
    public List<ResidentReserveVO> findAllReserveInfo(String time) {
        if (time == null || "".equals(time)) {
            time = reserveMapper.findAppointment();
        }
        return reserveMapper.findAllReserveInfo(time);
    }

    @Override
    public void saveAppointment(String time) {
        reserveMapper.saveAppointment(time);
    }
}

package cn.nero.community.service;

import cn.nero.community.domain.Reserve;
import cn.nero.community.domain.vo.ResidentReserveVO;

import java.util.List;
import java.util.Map;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/22
 */
public interface ReserveService {

    void saveReserve(Reserve reserve);

    Map<String, Object> checkReserve(String idCard);

    Map<String, Object> findAppointment();

    Map<String, Object> statistics(String time);

    void revokeReserve(String residentId);

    ResidentReserveVO reverse(String idCard, String times);

    Map<String, Object> getReserveInfo(String time, Integer skipCount, Integer pageSize);

    List<ResidentReserveVO> findAllReserveInfo(String time);

    void saveAppointment(String time);
}

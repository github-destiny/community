package cn.nero.community.service;

import cn.nero.community.domain.Reserve;

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

}

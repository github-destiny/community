package cn.nero.community.mappers;

import cn.nero.community.domain.Reserve;
import cn.nero.community.domain.vo.ResidentReserveVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/22
 */
@Mapper
@Repository
public interface ReserveMapper {

    /**
     * 申请接种
     */
    void saveReserve(Reserve reserve);

    /**
     * 通过身份证号检查是否预约了接种
     * @param idCard
     * @return
     */
    int checkReserve(String idCard);

    /**
     * 更换预约时间
     * @param resident_id
     * @param time
     */
    void updateReserveTime(@Param("resident_id") String resident_id, @Param("time") String time);


    /**
     * 通过身份证号查看预约结果
     * @param idCard
     * @return
     */
    ResidentReserveVO findReserveInfoByIdCard(String idCard);

    /**
     * 查询最近的一次预约时间
     * @return
     */
    String findAppointment();

    /**
     * 统计改天的预约接种人数
     * @param time
     * @return
     */
    int statistics(String time);

    /**
     * 撤销预约
     * @param residentId
     */
    void revokeReserve(String residentId);

    /**
     * 获取当天接种人的详细信息
     *
     * @param time
     * @return
     */
    List<ResidentReserveVO> findReserveInfo(@Param("time") String time, @Param("skipCount") Integer skipCount, @Param("pageSize") Integer pageSize);

    List<ResidentReserveVO> findAllReserveInfo(@Param("time") String time);

    void saveAppointment(@Param("time") String time);

    /**
     * 更新预约信息
     * @param reserve
     */
    void updateReserve(Reserve reserve);
}

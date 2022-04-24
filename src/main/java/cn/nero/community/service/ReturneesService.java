package cn.nero.community.service;

import cn.nero.community.domain.City;
import cn.nero.community.domain.Returnees;
import cn.nero.community.domain.vo.Count;
import cn.nero.community.domain.vo.PaginationVO;
import cn.nero.community.domain.vo.ReturneesCityVO;

import java.util.List;
import java.util.Map;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/13
 */
public interface ReturneesService {

    /**
     * 根据条件进行查询,可用作分页
     * @param returnees
     * @param city
     * @param skipCount
     * @param pageSize
     * @return
     */
    PaginationVO<ReturneesCityVO> findReturneesByCondition(Returnees returnees, City city, Integer skipCount, Integer pageSize);

    /**
     * 保存返乡人员信息
     * @param returnees
     */
    Map<String, Object> saveReturnees(Returnees returnees);

    /**
     * 更新反向人员信息
     * @param returnees
     */
    void updateReturnees(Returnees returnees);

    /**
     * 获取返乡人员的核酸检测结果
     * @param startTime
     * @param endTime
     * @return
     */
    Map<String, Object> getReturneesNucleicResult(String startTime, String endTime);

    /**
     * 获取前n天的返回人数
     * @param preDay
     * @return
     */
    List<Map<String, Object>> getCountOneDay(String preDay);

    /**
     * 获取返乡人员的接种情况
     * @return
     */
    List<Count> getReturneesInoculationTimes();





}

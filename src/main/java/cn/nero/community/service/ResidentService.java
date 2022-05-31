package cn.nero.community.service;

import cn.nero.community.domain.Resident;
import cn.nero.community.domain.User;
import cn.nero.community.domain.vo.PaginationVO;

import java.util.List;
import java.util.Map;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/11
 */
public interface ResidentService {

    /**
     * 创建居民信息
     * @param resident
     */
    Map<String, Object> saveResident(Resident resident);

    /**
     * 修改居民信息
     * @param resident
     */
    void editResidentInfo(Resident resident);

    /**
     * 通过用户id查找对应的居民身份信息
     * @param userId
     * @return
     */
    Resident findResidentByUserId(String userId);

    /**
     * 删除对应的居民信息
     * @param residentId
     */
    void deleteResident(String residentId);

    /**
     * 分页查询,包含基本查询
     * @param resident
     * @param skipCount
     * @param pageSize
     * @return
     */
    PaginationVO<Resident> findResidentByCondition(Resident resident, Integer skipCount, Integer pageSize);

    /**
     * echarts图表,展示社区居民人员年龄组成
     * @return
     */
    List<Map<String, Object>> getResidentGroupByAge();

    /**
     * 通过条件查询居民以及他的接种信息
     * @param map
     * @return
     */
    Map<String, Object> findResidentAndInoculationInfo(Map<String, Object> map);

    /**
     * 获取社区居民接种情况的数据,用来作为echarts的数据源
     * @return
     */
    List<Map<String, Object>> getInoculationData();

    /**
     * 通过居民Id查询他使用的账号信息
     * @param residentId
     * @return
     */
    User findUserByResidentId(String residentId);

    /**
     * 通过身份证号查找居民的部分信息
     * @param idCard
     * @return
     */
    Resident findResidentByIdCard(String idCard);

    /**
     * 查询所有的居民
     * @return
     */
    List<Resident> findAllResident();

    /**
     * 通过居民的id查询居民的所有基础信息
     * @param residentId
     * @return
     */
    Map<String, Object> findAllResidentInfo(String residentId);

}

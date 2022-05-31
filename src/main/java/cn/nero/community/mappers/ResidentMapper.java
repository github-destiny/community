package cn.nero.community.mappers;

import cn.nero.community.domain.Inoculation;
import cn.nero.community.domain.Resident;
import cn.nero.community.domain.User;
import cn.nero.community.domain.vo.ResidentInoculationVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/7
 */
@Mapper
@Repository
public interface ResidentMapper {

    /**
     * 创建居民信息
     * @param resident
     */
    void saveResident(Resident resident);

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
     * 通过条件查找对应的居民信息
     * @param resident
     * @param skipCount
     * @param pageSize
     * @return
     */
    List<Resident> findResidentsByCondition(@Param("resident") Resident resident, @Param("skipCount") int skipCount, @Param("pageSize") int pageSize);

    /**
     * 获取符合条件的记录总数
     * @param resident
     * @return
     */
    int getTotal(Resident resident);

    /**
     * 批量插入多条数据
     * @param residents
     */
    void saveManyResident(@Param("residents") List<Resident> residents);

    /**
     * 确定一定年龄范围内的人员个数
     * @param lowAge
     * @param highAge
     * @return
     */
    Integer getTotalAboutAge(@Param("lowAge") String lowAge, @Param("highAge") String highAge);

    /**
     * 批量保存接种信息
     */
    void batchSaveInoculationInfo(@Param("inoculations") List<Inoculation> inoculations);

    /**
     * 通过某些条件查找居民以及他的接种信息
     * @Param("resident_id") String resident_id,
       @Param("times") String times,
       @Param("lastTime") String lastTime,
       @Param("skipCount") Integer skipCount,
       @Param("pageSize") Integer pageSize,
       @Param("name") String name,
       @Param("address") String address
     * @return
     */
    List<ResidentInoculationVO> findResidentInoculationInfoByCondition(Map<String, Object> map);

    /**
     * 查找符合条件的接种记录总数
     * @param inoculation
     * @return
     */
    int getInoculationCount(Inoculation inoculation);

    /**
     * 根据居民Id查询他的用户账号信息
     * @param residentId
     * @return
     */
    User findUserByResidentId(@Param("residentId") String residentId);

    /**
     * 通过身份证号查询用户部分信息,结合家庭关联时使用
     * @param idCard
     * @return
     */
    Resident findResidentByIdCard(String idCard);

    /**
     * 通过居民id查询他接种了几针
     * @return
     */
    int getResidentInoculationNumByResidentId(String residentId);

    /**
     * 查询所有的居民
     * @return
     */
    List<Resident> findAllResident();

    /**
     * 通过id查找resident
     * @param residentId
     * @return
     */
    Resident findResidentById(@Param("residentId") String residentId);

    /**
     * 通过居民id查询他的疫苗接种情况
     * @param residentId
     * @return
     */
    Inoculation findInoculationByResidentId(@Param("residentId") String residentId);

    /**
     * 为某个居民添加预约接种记录
     * @param inoculation
     */
    void addInoculationInfo(Inoculation inoculation);

    /**
     * 获取社区居民总人数
     * @return
     */
    int getResidentCount();

    /**
     * 通过手机号或者身份证号查找一个居民信息
     * @param condition
     * @return
     */
    Resident findResidentByIdCardOrPhone(@Param("condition") String condition);

}

package cn.nero.community.mappers;

import cn.nero.community.domain.City;
import cn.nero.community.domain.Returnees;
import cn.nero.community.domain.vo.ReturneesCityVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/13
 */
@Mapper
@Repository
public interface ReturneesMapper {

    /**
     * 批量保存返乡人员
     * @param persons
     */
    void saveManyReturnees(@Param("persons") List<Returnees> persons);

    /**
     * 保存返乡人员
     * @param returnees
     */
    void saveReturnees(Returnees returnees);

    /**
     * 保存城市信息
     * @param cities
     */
    void saveCities(@Param("cities") List<City> cities);

    /**
     * 分页查询,也可用作条件查询
     * @param returnees
     * @param skipCount
     * @param pageSize
     * @return
     */
    List<ReturneesCityVO> findReturneesByCondition(@Param("returnees") Returnees returnees,
                                                   @Param("city") City city,
                                                   @Param("skipCount") Integer skipCount,
                                                   @Param("pageSize") Integer pageSize);

    /**
     * 获取总记录数
     * @param returnees
     * @return
     */
    int getTotal(@Param("returnees") Returnees returnees,
                 @Param("city") City city);


    /**
     * 调用mysql的DATE_SUB函数计算时间
     *
     * @return
     */
    String getDate(@Param("methodName") String methodName,
                   @Param("date") String date,
                   @Param("num") String num,
                   @Param("timeType") String timeType);

    /**
     * 更新返乡人员信息,通常是更新核酸结果以及核算时间
     * @param returnees
     */
    void updateReturnees(Returnees returnees);

    /**
     * 获取返乡人员的核酸检测结果
     *
     * @return
     */
    int getReturneesNucleicAll(@Param("result") String result, @Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 获取疑似感染者的信息
     * @return
     */
    List<Returnees> getPositiveReturnees();



}

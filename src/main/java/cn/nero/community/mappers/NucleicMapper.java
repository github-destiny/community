package cn.nero.community.mappers;

import cn.nero.community.domain.Nucleic;
import cn.nero.community.domain.vo.ResidentNucleicVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/18
 */
@Mapper
@Repository
public interface NucleicMapper {

    /**
     * 保存核酸检测结果
     * @param nucleic
     */
    void saveNucleic(Nucleic nucleic);

    /**
     * 通过residentId列进行保存,通常是用于简化保存
     */
    void saveNucleicByResidentIdColumn(Nucleic nucleic);

    /**
     * 更新核酸检测结果
     * @param nucleic
     */
    void editNucleic(Nucleic nucleic);

    /**
     * 批量更新结果
     *
     * @param residentIds
     * @param time
     * @param result
     */
    void batchEditNucleic(@Param("residentIds") List<String> residentIds, @Param("time") String time, @Param("result") String result);

    /**
     * 批量修改返乡人员的核酸检测结果
     *
     * @param returneesIds
     * @param testTime
     * @param result
     */
    void batchUpdateReturneesNucleic(@Param("returneesIds") List<String> returneesIds, @Param("testTime") String testTime, @Param("result") String result);

    /**
     * 通过身份证号查询自己的核酸检测结果
     *
     * @return
     */
    ResidentNucleicVO findNucleicByIdCardOrId(@Param("id") String id, @Param("idCard") String idCard, @Param("phone") String phone);

    /**
     * 查询全员的核酸检测结果,返回一个
     * @param result
     * @param startTime
     * @param endTime
     * @return
     */
    int findNucleicResultAll(@Param("result") String result,
                             @Param("startTime") String startTime,
                             @Param("endTime") String endTime);


    /**
     * 查询阳性感染者的信息
     * @return
     */
    List<ResidentNucleicVO> findPositiveResidentNucleicVO();

    /**
     * 通过居民id查询核酸检测结果
     * @param residentId
     * @return
     */
    Nucleic findNucleicByResidentId(@Param("residentId") String residentId);

    /**
     * 更新全员信息
     * @param result
     * @param time
     */
    void updateAll(@Param("result") String result, @Param("time") String time);

    /**
     * 获取某个日期的做核酸人数
     * @param startTime
     * @param endTime
     * @return
     */
    int getNucleicTotal(@Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 获取最近一次的核酸检测时间
     * @return
     */
    String getRecentNucleicTime();

    
}

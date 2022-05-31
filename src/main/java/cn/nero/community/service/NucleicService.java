package cn.nero.community.service;

import cn.nero.community.domain.Nucleic;
import cn.nero.community.domain.vo.ResidentNucleicVO;

import java.util.List;
import java.util.Map;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/18
 */
public interface NucleicService {

    /**
     * 更新核酸检测结果
     */
    void updateNucleic(String condition, String result);

    /**
     * 批量修改检测结果
     * @param residentIds
     */
    void batchUpdateNucleic(List<String> residentIds, String result, String time);

    /**
     * 批量修改返乡人员的核酸检测结果
     * @param returneesIds
     * @param testTime
     * @param result
     */
    void batchUpdateReturneesNucleic(List<String> returneesIds, String testTime, String result);

    /**
     * 通过手机号,身份证号或id查询他的核酸检测信息
     * @param id
     * @param idCard
     * @param phone
     * @return
     */
    ResidentNucleicVO findNucleicByIdCardOrIDOrPhone(String id, String idCard, String phone);

    /**
     * 获取全员的核酸检测结果
     * @param startTime
     * @param endTime
     * @return
     */
    Map<String, Object> getNucleicResultAll(String startTime, String endTime);

    /**
     * 更新全员核酸检测结果
     */
    void updateAll(String result, String time);

    /**
     * 页面初始化调用
     * @return
     */
    Map<String, Object> init(String startTime, String endTime);
}

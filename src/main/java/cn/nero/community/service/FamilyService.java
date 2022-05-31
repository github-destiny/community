package cn.nero.community.service;

import cn.nero.community.domain.Resident;
import cn.nero.community.domain.vo.FamilyVO;

import java.util.List;
import java.util.Map;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/21
 */
public interface FamilyService {

    Map<String, Object> createFamily(String createBy);

    void addMemberToFamily(String familyId, String residentId);

    void addMembersToFamily(List<String> residentIds, String familyId);

    FamilyVO findFamilyResident(String familyId);

    void deleteFamilyMemberById(String familyId, String residentId);

    /**
     * 根据居民id查询居民是否存在家庭
     * @param residentId
     * @return
     */
    FamilyVO findFamilyByResidentId(String residentId);

}

package cn.nero.community.service;

import cn.nero.community.domain.vo.FamilyVO;

import java.util.List;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/21
 */
public interface FamilyService {

    void createFamily(String createBy);

    void addMemberToFamily(String familyId, String residentId);

    void addMembersToFamily(List<String> residentIds, String familyId);

    FamilyVO findFamilyResident(String familyId);

    void deleteFamilyMemberById(String familyId, String residentId);

}

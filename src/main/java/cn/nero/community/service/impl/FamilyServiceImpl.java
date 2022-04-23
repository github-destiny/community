package cn.nero.community.service.impl;

import cn.nero.community.domain.Family;
import cn.nero.community.domain.vo.FamilyVO;
import cn.nero.community.exception.FamilyException;
import cn.nero.community.mappers.FamilyMapper;
import cn.nero.community.service.FamilyService;
import cn.nero.community.utils.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/21
 */
@Service
@Transactional
@Slf4j
public class FamilyServiceImpl implements FamilyService {

    @Autowired
    private FamilyMapper familyMapper;

    @Override
    public void createFamily(String createBy) {
        // 创建一个家庭,注入创建时间以及创建人属性
        Family family = new Family();
        family.setCreateTime(DateTimeUtil.getTime()).setCreateBy(createBy);
        familyMapper.saveFamily(family);
        // 自动将创建人添加到家庭中
        familyMapper.addMemberToFamily(family.getId(), createBy);
    }

    @Override
    public void addMemberToFamily(String familyId, String residentId) {
        int isExist = familyMapper.isExistsMemberInFamily(familyId, residentId);
        if (isExist != 0) {
            throw new FamilyException("该居民已经在您的家庭中了!");
        }
        familyMapper.addMemberToFamily(familyId, residentId);
    }

    @Override
    public void addMembersToFamily(List<String> residentIds, String familyId) {
        familyMapper.addMembersToFamily(residentIds, familyId);
    }

    @Override
    public FamilyVO findFamilyResident(String familyId) {
        FamilyVO familyVO = familyMapper.findFamilyVOByFamilyId(familyId);
        String owner = familyMapper.findFamilyOwner(familyId);
        familyVO.setCreateBy(owner);
        return familyVO;
    }

    @Override
    public void deleteFamilyMemberById(String familyId, String residentId) {
        familyMapper.deleteMemberFromFamily(familyId, residentId);
    }





}

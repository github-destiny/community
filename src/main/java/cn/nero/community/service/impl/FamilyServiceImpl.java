package cn.nero.community.service.impl;

import cn.nero.community.domain.Family;
import cn.nero.community.domain.Resident;
import cn.nero.community.domain.vo.FamilyVO;
import cn.nero.community.exception.FamilyException;
import cn.nero.community.mappers.FamilyMapper;
import cn.nero.community.mappers.ResidentMapper;
import cn.nero.community.service.FamilyService;
import cn.nero.community.utils.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private ResidentMapper residentMapper;

    @Override
    public Map<String, Object> createFamily(String createBy) {
        // 创建一个家庭,注入创建时间以及创建人属性
        Family family = new Family();
        family.setCreateTime(DateTimeUtil.getTime()).setCreateBy(createBy);
        familyMapper.saveFamily(family);
        // 自动将创建人添加到家庭中
        familyMapper.addMemberToFamily(family.getId(), createBy);
        Map<String, Object> map = new HashMap<>();
        map.put("id", family.getId());
        return map;
    }

    @Override
    public void addMemberToFamily(String familyId, String residentId) {
        // 判断居民是否存在
        Resident resident = residentMapper.findResidentById(residentId);
        if (ObjectUtils.isEmpty(resident)) {
            throw new FamilyException("该居民不存在!请先创建该居民再进行添加操作!");
        }
        // 判断该成员是否已经加入了其他家庭
        String fId = familyMapper.findFamilyByResidentId(residentId);
        if (null != fId) {
            throw new FamilyException("该居民已经加入了其他家庭!");
        }
        // 判断该居民是否已经存在再你的家庭中
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
        if (ObjectUtils.isEmpty(familyVO)){
            throw new FamilyException("查无此家庭");
        }
        String owner = familyMapper.findFamilyOwner(familyId);
        familyVO.setCreateBy(owner);
        return familyVO;
    }

    @Override
    public void deleteFamilyMemberById(String familyId, String residentId) {
        familyMapper.deleteMemberFromFamily(familyId, residentId);
    }

    @Override
    public FamilyVO findFamilyByResidentId(String residentId) {
        // 查询是否存在家庭
        String familyId = familyMapper.findFamilyByResidentId(residentId);
        if (null == familyId) {
            throw new FamilyException("您现在并没有加入任何一个家庭!");
        }
        // 如果存在家庭,直接将家庭的所有成员返回回去
        return findFamilyResident(familyId);
    }


}

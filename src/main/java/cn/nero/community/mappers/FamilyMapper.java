package cn.nero.community.mappers;

import cn.nero.community.domain.Family;
import cn.nero.community.domain.Resident;
import cn.nero.community.domain.vo.FamilyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/21
 */
@Mapper
@Repository
public interface FamilyMapper {

    /**
     * 生成一个家庭信息
     * @param family
     */
    void saveFamily(Family family);

    /**
     * 将成员添加到家庭中
     * @param familyId
     * @param residentId
     */
    void addMemberToFamily(@Param("familyId") String familyId, @Param("residentId") String residentId);

    /**
     * 批量添加成员到家庭中
     * @param residentIds
     * @param familyId
     */
    void addMembersToFamily(@Param("residentIds") List<String> residentIds, @Param("familyId") String familyId);

    /**
     * 查询家庭成员
     * @param familyId
     * @return
     */
    List<Resident> findFamilyMembers(String familyId);

    /**
     * 通过familyId查询家庭信息
     * @param familyId
     * @return
     */
    FamilyVO findFamilyVOByFamilyId(String familyId);

    /**
     * 通过家庭id查询出创建者
     * @param familyId
     * @return
     */
    String findFamilyOwner(String familyId);

    /**
     * 从家庭列表中删除该成员
     *
     * @param familyId
     * @param residentId
     */
    void deleteMemberFromFamily(@Param("familyId") String familyId, @Param("residentId") String residentId);

    /**
     * 判断是否存在该成员
     * @param familyId
     * @param residentId
     * @return
     */
    int isExistsMemberInFamily(@Param("familyId") String familyId, @Param("residentId") String residentId);

    /**
     * 通过居民id查询是否存在家庭
     * @param residentId
     * @return
     */
    String findFamilyByResidentId(@Param("residentId") String residentId);



}

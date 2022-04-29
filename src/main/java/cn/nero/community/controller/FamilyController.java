package cn.nero.community.controller;

import cn.nero.community.domain.Family;
import cn.nero.community.domain.vo.FamilyVO;
import cn.nero.community.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/21
 */
@RestController
@RequestMapping("/family")
public class FamilyController {

    @Autowired
    private FamilyService familyService;

    @PostMapping("/save")
    public Map<String, Object> saveFamily(String createBy){
        return familyService.createFamily(createBy);
    }

    @PostMapping("/add/member")
    public void addMemberToFamily(@RequestParam("familyId")   String familyId,
                                  @RequestParam("residentId") String residentId){
        familyService.addMemberToFamily(familyId, residentId);
    }

    @GetMapping("/find/{familyId}")
    public FamilyVO findFamilyMembers(@PathVariable("familyId") String familyId){
        return familyService.findFamilyResident(familyId);
    }

    @PostMapping("/delete")
    public void deleteFamilyMembers(@RequestParam("familyId")   String familyId,
                                    @RequestParam("residentId") String residentId){
        familyService.deleteFamilyMemberById(familyId, residentId);
    }



}

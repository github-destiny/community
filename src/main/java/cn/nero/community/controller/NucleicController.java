package cn.nero.community.controller;

import cn.nero.community.domain.Nucleic;
import cn.nero.community.domain.vo.ResidentNucleicVO;
import cn.nero.community.exception.ResidentException;
import cn.nero.community.service.NucleicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/18
 */
@RestController
@RequestMapping("/nucleic")
public class NucleicController {

    @Autowired
    private NucleicService nucleicService;

    @GetMapping("/update/personal")
    public void updateNucleic(Nucleic nucleic){
        nucleicService.updateNucleic(nucleic);
    }

    @GetMapping("/update/batch/resident")
    public void batchUpdateNucleic(@RequestParam("ids") List<String> ids,
                                   @RequestParam("result") String result,
                                   @RequestParam("time") String time){
        nucleicService.batchUpdateNucleic(ids, result, time);
    }

    @GetMapping("/update/batch/returnees")
    public void batchUpdateReturneesNucleic(@RequestParam("ids") List<String> ids,
                                            @RequestParam("time") String testTime,
                                            @RequestParam("result") String result){
        nucleicService.batchUpdateReturneesNucleic(ids, testTime, result);
    }

    @GetMapping("/find")
    public ResidentNucleicVO findNucleicResultByResidentInfo(@RequestParam(value = "id", required = false) String id,
                                                             @RequestParam(value = "idCard", required = false) String idCard,
                                                             @RequestParam(value = "phone", required = false) String phone){
        if (null == id && null == idCard && null == phone) {
            throw new IllegalArgumentException();
        }
        ResidentNucleicVO vo = nucleicService.findNucleicByIdCardOrIDOrPhone(id, idCard, phone);
        if (ObjectUtils.isEmpty(vo)) {
            throw new ResidentException("查无此居民,请仔细检查信息是否填写正确");
        }
        return vo;
    }

    @GetMapping("/get/result")
    public Map<String, Object> getNucleicResult(@RequestParam(value = "startTime") String startTime,
                                                @RequestParam(value = "endTime") String endTime){
        return nucleicService.getNucleicResultAll(startTime, endTime);
    }

}

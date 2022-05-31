package cn.nero.community.controller;

import cn.nero.community.domain.vo.ResidentNucleicVO;
import cn.nero.community.exception.ResidentException;
import cn.nero.community.service.NucleicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
    public void updateNucleic(@RequestParam("condition") String condition,@RequestParam("result") String result){
        nucleicService.updateNucleic(condition, result);
    }

    @GetMapping("/update/batch/resident")
    public void batchUpdateNucleic(@RequestParam(value = "ids", required = false) List<String> ids,
                                   @RequestParam("result") String result,
                                   @RequestParam("time") String time){
        nucleicService.batchUpdateNucleic(ids, result, time);
    }

    @GetMapping("/update/batch/returnees")
    public void batchUpdateReturneesNucleic(@RequestParam(value = "ids", required = false) List<String> ids,
                                            @RequestParam("time") String testTime,
                                            @RequestParam("result") String result){
        nucleicService.batchUpdateReturneesNucleic(ids, testTime, result);
    }

    @GetMapping("/find")
    public Map<String, Object> findNucleicResultByResidentInfo(@RequestParam(value = "id", required = false) String id,
                                                             @RequestParam(value = "idCard", required = false) String idCard,
                                                             @RequestParam(value = "phone", required = false) String phone){
        if (null == id && null == idCard && null == phone) {
            throw new IllegalArgumentException();
        }
        ResidentNucleicVO vo = nucleicService.findNucleicByIdCardOrIDOrPhone(id, idCard, phone);
        if (ObjectUtils.isEmpty(vo)) {
            throw new ResidentException("查无此居民,请仔细检查信息是否填写正确");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("name", vo.getName());
        map.put("age", vo.getAge());
        map.put("gender", vo.getGender());
        map.put("phone", vo.getPhone());
        map.put("address", vo.getAddress());
        map.put("result", vo.getResult());
        map.put("time", vo.getTime());
        return map;
    }

    @GetMapping("/get/result")
    public Map<String, Object> getNucleicResult(@RequestParam(value = "startTime") String startTime,
                                                @RequestParam(value = "endTime") String endTime){
        return nucleicService.getNucleicResultAll(startTime, endTime);
    }

    @GetMapping("/update/all")
    public String updateAll(@RequestParam("result") String result,
                            @RequestParam("time") String time){
        nucleicService.updateAll(result, time);
        return "更新成功";
    }

    @GetMapping("/get/init")
    public Map<String, Object> init(@RequestParam("startTime") String startTime,
                                    @RequestParam("endTime") String endTime){
        return nucleicService.init(startTime, endTime);
    }

}

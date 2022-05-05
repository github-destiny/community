package cn.nero.community.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.nero.community.domain.Resident;
import cn.nero.community.domain.User;
import cn.nero.community.domain.vo.PaginationVO;
import cn.nero.community.service.ResidentService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/11
 */
@RestController
@RequestMapping("/resident")
public class ResidentController {

    @Autowired
    private ResidentService residentService;

    @PostMapping("/save")
    public Map<String, Object> saveResident(Resident resident){
        return residentService.saveResident(resident);
    }

    @PostMapping("/update")
    public String editResident(Resident resident){
        residentService.editResidentInfo(resident);
        return "success";
    }

    @GetMapping("/find/user/{userId}")
    public Resident findResidentByUserId(@PathVariable("userId") String userId){
        return residentService.findResidentByUserId(userId);
    }

    @GetMapping("/find")
    public PaginationVO<Resident> findResidentByCondition(Resident resident,
                                                          @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                                          @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        int skipCount = (pageNo - 1) * pageSize;
        return residentService.findResidentByCondition(resident, skipCount, pageSize);
    }

    @GetMapping("/get/age")
    public List<Map<String,Object>> getAgeGroup(){
        return residentService.getResidentGroupByAge();
    }

    @GetMapping("/find/inoculation")
    public Map<String, Object> findResidentAndInoculationInfo(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                                              @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                              @RequestParam(value = "resident_id", required = false) String resident_id,
                                                              @RequestParam(value = "times", required = false) String times,
                                                              @RequestParam(value = "lastTime", required = false) String lastTime,
                                                              @RequestParam(value = "name", required = false) String name,
                                                              @RequestParam(value = "address", required = false) String address){
        int skipCount = (pageNo - 1) * pageSize;
        Map<String, Object> map = new HashMap<>();
        map.put("skipCount", skipCount);
        map.put("pageSize", pageSize);
        map.put("resident_id", resident_id);
        map.put("times", times);
        map.put("lastTime", lastTime);
        map.put("name", name);
        map.put("address", address);
        return residentService.findResidentAndInoculationInfo(map);
    }

    @GetMapping("/get/times")
    public List<Map<String, Object>> getEchartsDataAboutInoculationTimes(){
        return residentService.getInoculationData();
    }

    @GetMapping("/find/resident/{residentId}")
    public User findUserByResidentId(@PathVariable("residentId") String residentId){
        return residentService.findUserByResidentId(residentId);
    }

    @PostMapping("/find/idCard")
    public Resident findResidentByIdCard(@RequestParam("idCard") String idCard){
        return residentService.findResidentByIdCard(idCard);
    }

    @GetMapping("/export")
    public void exportExcel(HttpServletResponse response) throws IOException {
        response.setHeader("content-disposition", "attachment;fileName=" + URLEncoder.encode("居民信息.xls", StandardCharsets.UTF_8));
        Workbook workbook = null;
        ServletOutputStream oos = null;
        try {
            List<Resident> residents = residentService.findAllResident();
            workbook = ExcelExportUtil.exportExcel(new ExportParams("社区居民信息", "居民信息"), Resident.class, residents);
            oos = response.getOutputStream();
            workbook.write(oos);
        } finally {
            if (workbook != null) {
                workbook.close();
            }
            if (oos != null) {
                oos.close();
            }
        }
    }



}

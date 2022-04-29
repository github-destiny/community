package cn.nero.community.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.nero.community.domain.City;
import cn.nero.community.domain.Returnees;
import cn.nero.community.domain.vo.Count;
import cn.nero.community.domain.vo.PaginationVO;
import cn.nero.community.domain.vo.ReturneesCityVO;
import cn.nero.community.service.ReturneesService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/13
 */
@RestController
@RequestMapping("/returnees")
public class ReturneesController {

    @Autowired
    private ReturneesService returneesService;

    @GetMapping("/find")
    public PaginationVO<ReturneesCityVO> findReturneesByCondition(Returnees returnees,
                                                                  City city,
                                                                  @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                                                  @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize){
        int skipCount = (pageNo - 1) * pageSize;
        return returneesService.findReturneesByCondition(returnees, city, skipCount, pageSize);
    }

    @PostMapping("/save")
    public Map<String, Object> saveReturnees(Returnees returnees){
        return returneesService.saveReturnees(returnees);
    }

    @GetMapping("/update/nucleic")
    public void updateReturnees(@RequestParam("returneesId") String returneesId,
                                @RequestParam("result") String result,
                                @RequestParam("testTime") String testTime){
        Returnees returnees = new Returnees();
        returnees.setResult(result).setId(returneesId).setTestTime(testTime);
        returneesService.updateReturnees(returnees);
    }

    @GetMapping("/get/result")
    public Map<String, Object> getReturneesNucleic(@RequestParam("startTime") String startTime,
                                                   @RequestParam("endTime") String endTime) {
        return returneesService.getReturneesNucleicResult(startTime, endTime);
    }

    @GetMapping("/get/count")
    public List<Map<String, Object>> getCountOneDay(String preDay){
        return returneesService.getCountOneDay(preDay);
    }

    @GetMapping("/get/inoculation/times")
    public List<Count> getReturneesInoculationTimes(){
        return returneesService.getReturneesInoculationTimes();
    }

    @GetMapping("/export")
    public void exportExcel(HttpServletResponse response) throws IOException {
        response.setHeader("content-disposition", "attachment;fileName=" + URLEncoder.encode("外来人员.xls", StandardCharsets.UTF_8));
        List<ReturneesCityVO> dataList = returneesService.findAllReturnees();
        ServletOutputStream oos = null;
        Workbook workbook = null;
        try {
            workbook = ExcelExportUtil.exportExcel(new ExportParams("外来人员列表", "外来人员"), ReturneesCityVO.class, dataList);
            oos = response.getOutputStream();
            workbook.write(oos);
            workbook.close();
            oos.close();
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

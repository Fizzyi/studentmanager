package com.study.sutdentmanager.controller;

import com.study.sutdentmanager.pojo.Clazz;
import com.study.sutdentmanager.service.ClazzService;
import com.study.sutdentmanager.service.StudentService;
import com.study.sutdentmanager.util.AjaxResult;
import com.study.sutdentmanager.util.Data;
import com.study.sutdentmanager.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/clazz")
public class ClazzController {

    @Autowired
    public ClazzService clazzService;

    @Autowired
    public StudentService studentService;

    /**
     * 跳转班级页面
     *
     * @return
     */
    @GetMapping("/clazz_list")
    public String clazzList() {
        return "/clazz/clazzList";
    }


    /**
     * 获取所有的班级列表
     *
     * @param page
     * @param rows
     * @param clazzName
     * @param form
     * @return
     */
    @PostMapping("/getClazzList")
    @ResponseBody
    public Object getClazzList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                               @RequestParam(value = "rows", defaultValue = "100") Integer rows,
                               String clazzName, String form) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageno", page);
        paramMap.put("pagesize", rows);
        if (!StringUtils.isEmpty(clazzName)) paramMap.put("name", clazzName);
        PageBean<Clazz> pageBean = clazzService.queryPage(paramMap);
        if (!StringUtils.isEmpty(form) && (form.equals("combox"))) {
            return pageBean.getDatas();
        } else {
            Map<String, Object> result = new HashMap<>();
            result.put("total", pageBean.getTotalsize());
            result.put("rows", pageBean.getDatas());
            return result;
        }
    }

    /**
     * 新增班级
     *
     * @param clazz
     * @return
     */
    @PostMapping("/addClazz")
    @ResponseBody
    public AjaxResult addClazz(Clazz clazz) {
        AjaxResult ajaxResult = new AjaxResult();
        try {
            int count = clazzService.addClazz(clazz);
            if (count > 0) {
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("新增成功");
            } else {
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("新增失败");
            }
        } catch (Exception e) {
            ajaxResult.setMessage("新增失败");
            ajaxResult.setSuccess(false);
        }
        return ajaxResult;
    }

    @PostMapping("deleteClazz")
    @ResponseBody
    public AjaxResult deleteClazz(Data data) {
        AjaxResult ajaxResult = new AjaxResult();
        try {
            List<Integer> ids = data.getIds();
            //判断是否存在学生
            for (Integer id : ids) {
                if (studentService.isStudentByClazzId(id)) {
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("无法删除，班级下存在学生");
                    return ajaxResult;
                }
            }
            // todo:判断是否存在老师
            
            int count = clazzService.deleteClazz(ids);
            if (count > 0) {
                ajaxResult.setMessage("删除成功");
                ajaxResult.setSuccess(true);
            } else {
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("删除失败");
            }
        } catch (Exception e) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("删除失败");
        }
    }
}

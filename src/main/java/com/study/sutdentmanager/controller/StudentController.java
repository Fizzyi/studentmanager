package com.study.sutdentmanager.controller;

import com.study.sutdentmanager.pojo.Student;
import com.study.sutdentmanager.service.SelectedCourseService;
import com.study.sutdentmanager.service.StudentService;
import com.study.sutdentmanager.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    public StudentService studentService;

    @Autowired
    public SelectedCourseService selectedCourseService;

    /**
     * 跳转学生界面
     *
     * @return
     */
    @GetMapping("/student_list")
    public String StudentList() {
        return "/student/studentList";
    }

    /**
     * 获取学生列表
     *
     * @param page
     * @param rows
     * @param studentName
     * @param clazzid
     * @param from
     * @param session
     * @return
     */
    @RequestMapping("/getStudentList")
    @ResponseBody
    public Object getStudentList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "100") Integer rows,
            String studentName,
            @RequestParam(value = "clazzid", defaultValue = "0") String clazzid, String from, HttpSession session) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageno", page);
        paramMap.put("pagesize", rows);
        // 判断是否有用户名称这个参数
        if (!StringUtils.isEmpty(studentName)) paramMap.put("username", studentName);
        // 判断是否有班级参数
        if (!clazzid.equals("0")) paramMap.put("clazzid", clazzid);
        // 判断是否是学生，如果是学生只能查询自己的信息
        Student student = (Student) session.getAttribute(Const.STUDENT);
        if (!StringUtils.isEmpty(student)) {
            paramMap.put("studentid", student.getId());
        }
        PageBean<Student> pageBean = studentService.queryPage(paramMap);
        if (!StringUtils.isEmpty(from) && from.equals("combox")) {
            return pageBean.getDatas();
        } else {
            Map<String, Object> result = new HashMap<>();
            result.put("total", pageBean.getTotalsize());
            result.put("rows", pageBean.getDatas());
            return result;
        }
    }


    /**
     * 删除学生
     * 首先判断学生是否存在关联的课程,然后删除学生的文件，最后删除学生的信息
     * 知识点： 迭代器，resultMap，foreach
     *
     * @param data
     * @return
     */
    @PostMapping("/deleteStudent")
    @ResponseBody
    public AjaxResult deleteStudent(Data data) {
        AjaxResult ajaxResult = new AjaxResult();
        try {
            List<Integer> ids = data.getIds();
            // 判断是否存在课程关联学生，如果存在，则无法进行删除
            for (Integer integer : ids) {
                if (!selectedCourseService.isStudentId(integer)) {
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("无法删除，存在课程关联学生");
                    return ajaxResult;
                }
            }

            //删除学生的文件
            File fileDir = UploadUtil.getImgDirFile();
            for (Integer id : ids) {
                Student byId = studentService.findById(id);
                if (!byId.getPhoto().isEmpty()) {
                    File file = new File(fileDir.getAbsolutePath() + File.separator + byId.getPhoto());
                    boolean status = file.delete();
                }
            }
            int count = studentService.deleteStudent(ids);
            if (count > 0) {
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("全部删除成功");
            } else {
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("删除失败");
            }
        } catch (Exception e) {
            ajaxResult.setMessage("删除失败");
            ajaxResult.setSuccess(false);
        }
        return ajaxResult;
    }


    /**
     * 添加学生信息
     *
     * @param files
     * @param student
     * @return
     * @throws IOException
     */
    @PostMapping("/addStudent")
    @ResponseBody
    public AjaxResult addStudent(@RequestParam("file") MultipartFile[] files, Student student) throws IOException {
        AjaxResult ajaxResult = new AjaxResult();
        student.setSn(SnGenerateUtil.generateSn(student.getClazzId()));

        //存放上传图片的文件夹
        File fileDir = UploadUtil.getImgDirFile();
        for (MultipartFile fileImg : files) {
            //拿到文件名
            String extName = fileImg.getOriginalFilename().substring(fileImg.getOriginalFilename().lastIndexOf("."));
            String uuidName = UUID.randomUUID().toString();

            try {
                //构建真实的文件路径
                File newFile = new File(fileDir.getAbsolutePath() + File.separator + uuidName + extName);
                //上传文件到 绝对路径
                fileImg.transferTo(newFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            student.setPhoto(uuidName + extName);
        }
        //保存学生信息到数据库
        try {
            int count = studentService.addStudent(student);
            if (count > 0) {
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("保存成功");
            } else {
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("保存失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("保存失败");
        }
        return ajaxResult;

    }

    /**
     * 修改学生信息
     * @param files
     * @param student
     * @return
     */
    @PostMapping("/editStudent")
    @ResponseBody
    public AjaxResult editStudent(@RequestParam("file") MultipartFile[] files, Student student) {
        AjaxResult ajaxResult = new AjaxResult();
        try {
            int count = studentService.editStudent(student);
            if (count > 0) {
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("修改成功");
            } else {
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("修改失败");
            }
        } catch (Exception e) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("修改失败");
        }

        return ajaxResult;
    }
}

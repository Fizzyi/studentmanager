package com.study.sutdentmanager.controller;

import com.study.sutdentmanager.pojo.Admin;
import com.study.sutdentmanager.pojo.Student;
import com.study.sutdentmanager.pojo.Teacher;
import com.study.sutdentmanager.service.AdminService;
import com.study.sutdentmanager.service.StudentService;
import com.study.sutdentmanager.service.TeacherService;
import com.study.sutdentmanager.util.AjaxResult;
import com.study.sutdentmanager.util.Const;
import com.study.sutdentmanager.util.CpachaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;


@Controller
@RequestMapping("/system")
public class SystemController {

    @Autowired
    AdminService adminService;

    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    /**
     * 跳转登陆界面
     */
    @GetMapping("/login")
    public String login_base() {
        return "/login";
    }

    /**
     * 登陆表达提交 校验
     */
    @PostMapping("/login")
    @ResponseBody
    public AjaxResult login(String username, String password, String code, String type, HttpSession session) {
        AjaxResult ajaxResult = new AjaxResult();
        if (StringUtils.isEmpty(username)) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("请填写用户名");
            return ajaxResult;
        }
        if (StringUtils.isEmpty(password)) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("请填写密码");
            return ajaxResult;
        }
//        if (StringUtils.isEmpty(code)) {
//            ajaxResult.setSuccess(false);
//            ajaxResult.setMessage("请填写验证码");
//            return ajaxResult;
//        }
        if (ObjectUtils.isEmpty(session.getAttribute(Const.CODE))) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("会话时间过长，请刷新");
            return ajaxResult;
        }
//        } else {
//            if (code.equalsIgnoreCase((String) session.getAttribute(Const.CODE))) {
//                ajaxResult.setSuccess(false);
//                ajaxResult.setMessage("验证码错误");
//                return ajaxResult;
//            }
//        }
        //数据库校验
        switch (type) {
            case "1": {
                //管理员
                Admin admin = new Admin();
                admin.setUsername(username);
                admin.setPassword(password);
                Admin ad = adminService.findByAdmin(admin);
                if (StringUtils.isEmpty(ad)) {
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("用户名或者密码错误");
                    return ajaxResult;
                }
                ajaxResult.setSuccess(true);
                session.setAttribute(Const.ADMIN, ad);
                session.setAttribute(Const.USERTYPE, "1");
                break;
            }
            case "2": {
                //学生
                Student student = new Student();
                student.setUsername(username);
                student.setPassword(password);
                Student student1 = studentService.findByStudent(student);
                if (StringUtils.isEmpty(student1)) {
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("用户名或者密码错误");
                    return ajaxResult;
                }
                ajaxResult.setSuccess(true);
                session.setAttribute(Const.ADMIN, student1);
                session.setAttribute(Const.USERTYPE, "2");
                break;
            }
            case "3": {
                //老师
                Teacher teacher = new Teacher();
                teacher.setUsername(username);
                teacher.setPassword(password);
                Teacher teacher1 = teacherService.findByTeacher(teacher);
                if (StringUtils.isEmpty(teacher1)) {
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("用户名或者密码错误");
                    return ajaxResult;
                }
                ajaxResult.setSuccess(true);
                session.setAttribute(Const.ADMIN, teacher1);
                session.setAttribute(Const.USERTYPE, "3");
                break;
            }
        }
        return ajaxResult;
    }

    //显示验证码
    @GetMapping("/checkCode")
    public void generateCpacha(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam(value = "vl", defaultValue = "4", required = false) Integer vl,
                               @RequestParam(value = "w", defaultValue = "110", required = false) Integer w,
                               @RequestParam(value = "h", defaultValue = "39", required = false) Integer h) {
        CpachaUtil cpachaUtil = new CpachaUtil(vl, w, h);
        String generatorVCode = cpachaUtil.generatorVCode();
        request.getSession().setAttribute(Const.CODE, generatorVCode);
        BufferedImage generatorRotateVCodeImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
        try {
            ImageIO.write(generatorRotateVCodeImage, "gif", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 跳转后台主页
     *
     * @return
     */
    @GetMapping("/index")
    public String index() {
        return "/system/index";
    }

    /**
     * 注销
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "/login";
    }
}

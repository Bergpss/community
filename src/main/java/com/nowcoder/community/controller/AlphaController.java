package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


/**
 * @author pssBerg
 * @version 1.0
 * @date 2022 06 04, 9:16
 */

@Controller // SpringMVC的注解
@RequestMapping("/alpha") // 取一个访问名，SpringMVC的注解
public class AlphaController {

    // 注入AlphaService
    @Autowired
    private AlphaService alphaService;

    // 能被浏览器访问的前提是要有注解
    @RequestMapping("/hello") // 返回的是个网页
    @ResponseBody // 返回字符串
    public String sayHello() {
        return "Hello SpringBoot!";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getDate() {
        return alphaService.find();
    }

    // 演示：在Spring 下怎么相应请求
    // 更底层
    @RequestMapping("/http") // 访问路径
    // 没有返回值是因为response对象可以输出数据
    public void http(HttpServletRequest request, HttpServletResponse response) { // 常用接口
        // 利用request对象处理请求
        // 获取请求数据
        System.out.println(request.getMethod()); // 获取请求方式，Get
        System.out.println(request.getServletPath()); // 获取请求的路径
        // 循环输出请求消息头
        Enumeration<String> enumeration = request.getHeaderNames(); // request是k-v结构，得到所有请求行的key
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement(); // key
            String value = request.getHeader(name); // value
            System.out.println(name + ": " + value);
        }
        System.out.println(request.getParameter("code")); // 请求

        // 返回响应数据
        response.setContentType("text/html;charset=utf-8"); // 返回一个网页
        try(
                PrintWriter writer = response.getWriter(); // Java7 加入的新语法
                // 不用写finally了，前提是writer必须要有close方法
        ) {
            writer.write("<h1>nowcoder</h1>"); // 仅供演示，本来应输出完成的网页
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 处理浏览器的请求：
    //   1.接受请求的数据，基于request
    //   2.返回响应的数据，基于response

    // Get请求，获取某些数据，默认发送的请求就是Get
    // 查询所有的学生，分页处理，当前第几页，最多显示多少条数据
    // 路径：/students?current=1&limit=20
    @RequestMapping(path = "/students", method = RequestMethod.GET) // 限制只有Get才能请求到
    @ResponseBody
    public String getStudents( // 有时参数是没有的，required=false意思就是可以不传此参数，不传的话取默认值
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit) {
            // DispatcherServlet类检测到后，会自动匹配request
        System.out.println("current = " + current);
        System.out.println("limit = " + limit);
        return "some students";
    }

    // 查询一个学生
    // 路径：/student/123，参数作为路径的一部分
    // id是变量，需要用{ }括起来
    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id) {
        System.out.println("id = " + id);
        return "a student";
    }

    // Post请求，浏览器向服务器提交数据
    // templates 动态网页
    // static 静态网页
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age) {
        System.out.println("student_name：" + name);
        System.out.println("student_age：" + age);
        return "success!";
    }

    // 响应动态HTML数据
    // 假设，浏览器查询一个老师，服务器就返回信息
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    // 不加注解，默认返回html
    public ModelAndView getTeacher() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("name", "马保国");
        mav.addObject("age", "60");
        mav.setViewName("/demo/view"); // 默认是在templates目录，只写次级目录就行，view其实是view.html
        return mav;
    }

    // 查询学校
    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model model) {
        model.addAttribute("name", "PKU");
        model.addAttribute("age", "124");
        return "/demo/view";
    }

    // 向浏览器响应JSON数据（异步请求：网页不刷新的情况下，从服务器得到数据）
    // Java对象 和 JS对象不兼容，但是有了JSON字符串
    // Java对象 -> JSON字符串 -> JS对象
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp() {
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "Berg");
        emp.put("age", 23);
        emp.put("salary", 8000.00);
        return emp;
    }

    // 查询所有的员工
    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmps() {
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "Berg");
        emp.put("age", 23);
        emp.put("salary", 8000.00);

        Map<String, Object> emp2 = new HashMap<>();
        emp2.put("name", "Handsome");
        emp2.put("age", 70);
        emp2.put("salary", 90000.00);

        list.add(emp);
        list.add(emp2);

        return list;
    }

}

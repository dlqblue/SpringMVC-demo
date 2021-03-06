package com.dlqblue.controller;

import com.dlqblue.model.UserEntity;
import com.dlqblue.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Administrator on 2017/2/23.
 */

@Controller
public class MainController {

    //自动装配数据库接口，不需要在写原始的Connection来操作数据库
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String login() {
//        return "index";
//    }

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String getUsers(ModelMap modelMap) {

        //查询表中所有记录
        List<UserEntity> userEntityList = userRepository.findAll();

        //将所有记录传递给要返回的jsp页面，放在userEntityList当中
        modelMap.addAttribute("userList", userEntityList);

        //返回pages目录下的admin/users.jsp页面
        return "admin/users";
    }

    // get请求，访问添加用户界面
    @RequestMapping(value = "/admin/users/add", method = RequestMethod.GET)
    public String addUser() {
        //转到addUser界面
        return "admin/addUser";
    }

    //post请求，处理添加用户请求，并重定向到用户管理界面
    @RequestMapping(value = "/admin/users/addP", method = RequestMethod.POST)
    public String addUserPost(@ModelAttribute("user") UserEntity userEntity) {
        //post请求传递过来的是一个UserEntity对象，里面包含了该用户的信息
        //@ModelAttribut()获取传递过来的'user'，并创建这个对象

        //刷新缓存
        userRepository.saveAndFlush(userEntity);

        //重定向
        return "redirect:/admin/users";
    }

    // 更新用户信息
    @RequestMapping(value = "/admin/users/update/{id}", method = RequestMethod.GET)
    public String updateUser(@PathVariable("id") Integer userId, ModelMap modelMap) {

        // 找到userId表示的用户
        UserEntity userEntity = userRepository.findOne(userId);

        // 传递给请求页面
        modelMap.addAttribute("user", userEntity);
        return "admin/updateUser";
    }

    // 更新用户信息，操作
    @RequestMapping(value = "/admin/users/updateP", method = RequestMethod.POST)
    public String updateUserPost(@ModelAttribute("userP") UserEntity user) {

        userRepository.updateUser(user.getNickname(), user.getPassword(), user.getEmail(), user.getId());
        userRepository.flush();
        return "redirect:/admin/users";
    }

    // 删除用户
    @RequestMapping(value = "/admin/users/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") Integer userId) {

        // 删除用户
        userRepository.delete(userId);
        // 立即刷新
        userRepository.flush();
        return "redirect:/admin/users";
    }

    // 查看用户详情
    // @PathVariable可以收集url中的变量，需匹配的变量用{}括起来
    // 例如：访问 localhost:8080/admin/users/show/1 ，将匹配 id = 1
    @RequestMapping(value = "/admin/users/show/{id}", method = RequestMethod.GET)
    public String showUser(@PathVariable("id") Integer userId, ModelMap modelMap) {

        // 找到userId所表示的用户
        UserEntity userEntity = userRepository.findOne(userId);

        // 传递给请求页面
        modelMap.addAttribute("user", userEntity);
        return "admin/userDetail";
    }

}

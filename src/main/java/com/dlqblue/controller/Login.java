package com.dlqblue.controller;

import com.dlqblue.model.UserEntity;
import com.dlqblue.repository.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/3.
 */
@Controller
public class Login {

    //自动装配数据库接口，不需要在写原始的Connection来操作数据库
    @Autowired
    UserRepository userRepository;

    /**
     * @param nickname 用户名，一定要对应着表单的name才行
     * @param password 用户密码，也应该对应表单的数据项
     * @param model    一个域对象，可用于存储数据值
     * @return
     * @RequestParam注解的作用是：根据参数名从URL中取得参数值
     */
//    @RequestMapping(value = "admin/login", method = RequestMethod.POST) // @RequestMapping 注解可以用指定的URL路径访问本控制层
//    public String login(@RequestParam("nickname") String nickname, @RequestParam("password") String password,
//                        Model model) {
//
//        List<UserEntity> userEntityList = userRepository.findAll();
//
//        return "redirect:users";
//
//    }
//    public JSONObject login(HttpSession session, ) {
//
////        Member member = memberService.findByMid(mid);
//
//        if (member != null && member.getPassword().equals(json.getString("password"))) {
//            session.setAttribute("mid", mid);
//            json.put("result", "success");
//            return json;
//        }
//
//        return json;
//    }

    @RequestMapping(value="/admin/login.do", method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Map<String,Object> map = new HashMap<String,Object>();
        List<UserEntity> userEntityList = userRepository.findAll();
        for (UserEntity userEntity : userEntityList) {
            if (userEntity.getNickname().equals(username) && userEntity.getPassword().equals(password)) {
                map.put("username",username);
                map.put("pwd",password);
                map.put("msg", "成功");
                break;
            } else if (username.equals("admin") && password.equals("admin")) {
                map.put("username",username);
                map.put("pwd",password);
                map.put("msg", "成功管理员");
                break;
            } else {
                map.put("msg", "失败");
            }
        }
        return map;
    }

//    @RequestMapping(value= "/admin/login.do", method = RequestMethod.POST)
//    @ResponseBody
//    public String toSearch(@RequestBody JSONObject json, Model model) {
//        String username = json.getString("username");
//        String password = json.getString("password");
//        List<UserEntity> userEntityList = userRepository.findAll();

//    }

}

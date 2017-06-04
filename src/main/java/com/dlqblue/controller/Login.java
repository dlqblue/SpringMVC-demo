package com.dlqblue.controller;

import com.dlqblue.json.JsonMapper;
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

    @RequestMapping(value= "/admin/login.do", method = RequestMethod.POST)
    @ResponseBody
    public String login (@RequestBody Map map) {
        String username = map.get("username").toString();
        String password = map.get("password").toString();
        UserEntity userEntity = userRepository.findByNickname(username);
        Map<String,Object> hashMap = new HashMap<>();
        if (userEntity != null) {
            if (userEntity.getNickname().equals(username) && userEntity.getPassword().equals(password))
            hashMap.put("username",username);
            hashMap.put("pwd",password);
            hashMap.put("msg", "success");
        } else if (username.equals("admin") && password.equals("admin")) {
            hashMap.put("username",username);
            hashMap.put("pwd",password);
            hashMap.put("msg", "successAdmin");
        } else {
            hashMap.put("msg", "false");
        }
        return JsonMapper.nonDefaultMapper().toJson(hashMap);
    }

}

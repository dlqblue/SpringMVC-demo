package com.dlqblue.controller;

import com.dlqblue.json.JsonMapper;
import com.dlqblue.model.NewsEntity;
import com.dlqblue.repository.NewsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by Administrator on 2017/2/28.
 */
@RestController
public class MobileController {

    @Autowired
    NewsRepository newsRepository;

//    @ResponseBody
//    @RequestMapping(value = "/admin/news/get/{id}", method = RequestMethod.GET)
//    public String getNews(@PathVariable("id") int id) throws IOException {
//        NewsEntity newsEntity = newsRepository.findOne(id);
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(newsEntity);
//        return json;
//    }

    // 根据id获取news
    @ResponseBody
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/admin/news/get/{id}", method = RequestMethod.GET)
    public String getNewsById(@PathVariable("id") int id) throws IOException {
        NewsEntity newsEntity = newsRepository.findOne(id);
        // 去掉密码
        newsEntity.getUserByUserId().setPassword("******");
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("code","01");
        hashMap.put("msg","success");
        hashMap.put("data",newsEntity);
        return JsonMapper
                .nonDefaultMapper()
                .toJson(hashMap);
    }

    // 根据id获取news
    @ResponseBody
    @RequestMapping(value = "/admin/news/get", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getNews() throws IOException {
        List<NewsEntity> list = newsRepository.findAll();
        // 去掉密码
        for (int i = 0; i < list.size(); i++) {
            list.get(i).getUserByUserId().setPassword("******");
        }
        LinkedHashMap<String,Object> hashMap = new LinkedHashMap<>();
        hashMap.put("code","02");
        hashMap.put("msg","success");
        hashMap.put("data",list);
        return JsonMapper
                .nonDefaultMapper()
                .toJson(hashMap);
    }
}

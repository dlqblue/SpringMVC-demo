package com.dlqblue.controller;

import com.dlqblue.model.NewsEntity;
import com.dlqblue.model.UserEntity;
import com.dlqblue.repository.NewsRepository;
import com.dlqblue.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2017/2/25.
 */
@Controller
public class NewsController {

    @Autowired
    NewsRepository newsRepository;

    @Autowired
    UserRepository userRepository;

    //查看所有新闻
    @RequestMapping(value = "/admin/news", method = RequestMethod.GET)
    public String showNews(ModelMap modelMap) {

        List<NewsEntity> newsEntityList = newsRepository.findAll();
        modelMap.addAttribute("newsList",newsEntityList);
        return "admin/news";
    }

    //添加新闻
    @RequestMapping(value = "/admin/news/add", method = RequestMethod.GET)
    public String addNews(ModelMap modelMap) {
        List<UserEntity> userEntityList = userRepository.findAll();
        modelMap.addAttribute("userList",userEntityList);
        return "admin/addNews";
    }

    //添加新闻，发送POST请求，重定向查看新闻
    @RequestMapping(value = "/admin/news/addP", method = RequestMethod.POST)
    public String addNewsPost(@ModelAttribute("news") NewsEntity newsEntity) {

        //打印标题，昵称
        System.out.println(newsEntity.getTitle());
        System.out.println(newsEntity.getUserByUserId().getNickname());

        //保存，刷新缓存
        newsRepository.saveAndFlush(newsEntity);

        return "redirect:/admin/news";
    }

    //查看新闻详情，默认使用GET，method可以缺省
    @RequestMapping(value = "/admin/news/show/{id}")
    public String showNews(@PathVariable("id") int id, ModelMap modelMap) {
        NewsEntity newsEntity = newsRepository.findOne(id);
        modelMap.addAttribute("news",newsEntity);
        return "admin/newsDetail";
    }

    //修改新闻内容，页面
    @RequestMapping(value = "/admin/news/update/{id}")
    public String updateNews(@PathVariable("id") int id, ModelMap modelMap) {

        NewsEntity newsEntity = newsRepository.findOne(id);
        List<UserEntity> userEntityList = userRepository.findAll();
        modelMap.addAttribute("news",newsEntity);
        modelMap.addAttribute("userList",userEntityList);
        return "admin/updateNews";
    }

    //修改新闻内容，POST请求
    @RequestMapping(value = "/admin/news/updateP", method = RequestMethod.POST)
    public String updateNewsP(@ModelAttribute("newsP") NewsEntity newsEntity) {
        newsRepository.updateNews(newsEntity.getTitle(), newsEntity.getUserByUserId().getId(),
                newsEntity.getContent(), newsEntity.getPushDate(), newsEntity.getId());
        newsRepository.flush();
        return "redirect:/admin/news";
    }

    //删除博客文章
    @RequestMapping("/admin/news/delete/{id}")
    public String deleteBlog(@PathVariable("id") int id) {
        newsRepository.delete(id);
        newsRepository.flush();
        return "redirect:/admin/news";
    }

}

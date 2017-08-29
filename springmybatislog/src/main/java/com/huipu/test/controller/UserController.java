package com.huipu.test.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huipu.test.entity.User;
import com.huipu.test.mapper.UserMapper;

@Controller
@RequestMapping(value = "/gl")
public class UserController {

	private static int nextSerialNum = 0;

	private static ThreadLocal serialNum = new ThreadLocal() {
		protected synchronized Object initialValue() {
			return new Integer(nextSerialNum++);
		}
	};

	public static int get() {
		return ((Integer) (serialNum.get())).intValue();
	}

	private UserMapper userMapper = null;

	@Autowired
	public void setMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showForm(ModelMap model) {
		List<User> users = userMapper.getAllUser();
		model.addAttribute("users", users);
		return "hello";
	}

	@RequestMapping(value = "/add")
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("hello");
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setUserName(userName);
		user.setPassword(password);
		userMapper.addUser(user);
		List<User> list = userMapper.getAllUser();
		mv.addObject("users", list);
		return mv;
	}

	@RequestMapping(value = "/detail")
	public String detail(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		Logger log = LogManager.getLogger(UserController.class.getName());
		System.out.println(UserController.class.getName());
		log.debug("debug message");
		log.info("info message");
		log.error("error message");

		String id = request.getParameter("id");
		User user = userMapper.getUserById(id);
		model.addAttribute("user", user);
		return "detail";
	}

	@RequestMapping(value = "/delete")
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		userMapper.deleteById(id);
		ModelAndView mv = new ModelAndView("hello");
		List<User> list = userMapper.getAllUser();
		mv.addObject("users", list);
		return mv;
	}

	@RequestMapping(value = "/toupdate")
	public String toUpdate(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		User user = userMapper.getUserById(id);
		model.addAttribute("user", user);
		return "update";
	}

	@RequestMapping(value = "/update")
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("sid");
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		User user = new User();
		user.setId(id);
		user.setUserName(userName);
		user.setPassword(password);
		userMapper.update(user);
		ModelAndView mv = new ModelAndView("hello");
		List<User> list = userMapper.getAllUser();
		mv.addObject("users", list);
		return mv;
	}
	/**
	 * 测试threadlocal变量的使用
	 */
	@RequestMapping(value = "/view")
	@ResponseBody
	public void testLocalThread() {
		System.out.print(get());

	}

	@RequestMapping(value = "/view1")
	@ResponseBody
	public void testLocalThread1() {
		System.out.print(get());

	}
}

package com.learning.Admin.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.learning.Admin.Service.*;
import com.learning.DTO.userDTO;

@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;

	@GetMapping(value = "/admin")
	public String admin(HttpSession session) {
		if((int)session.getAttribute("u_authority") ==7) {
			return "admin";						
		}else {
			return "redirect:/404";
		}
	}
	//학생리스트 불러오기
	@GetMapping(value = "/admin_student")
	public ModelAndView admin_student(HttpSession session) {
		if((int)session.getAttribute("u_authority") ==7) {
			ModelAndView mv = new ModelAndView("admin_student");
			List<userDTO> list = adminService.userList();
			mv.addObject("list",list);
			
			return mv;
		}else {
			ModelAndView mv = new ModelAndView("404");
			return mv;
		}
	}
	
	//학생검색불러오기
	@GetMapping(value = "/admin_studentSearch" , produces="text/plain;charset=utf-8")
	public ModelAndView admin_studentSearch(HttpServletRequest request, HttpSession session) {	
		if((int)session.getAttribute("u_authority") ==7) {
			String u_name = request.getParameter("u_name"); 
			List<userDTO> list = adminService.userSearch(u_name);	
			
			ModelAndView mv = new ModelAndView("admin_student");
			mv.addObject("list",list);
			return mv; 
		}else {
			ModelAndView mv = new ModelAndView("404");
			return mv;
		}
	}
	//선택된 학생의 강의 불러오기
	@GetMapping(value = "/admin_studentLecture")
	public ModelAndView admin_studentLecture(HttpServletRequest request, HttpSession session) {
		if((int)session.getAttribute("u_authority") ==7) {
			ModelAndView mv = new ModelAndView("admin_studentLecture");
			String u_id = request.getParameter("u_id");
			List<String> list = adminService.studentLecture(u_id);
			List<String> report = adminService.studentReport(u_id);
			
			mv.addObject("list",list);
			mv.addObject("report",report);
						
			return mv;
		}else {
			ModelAndView mv = new ModelAndView("404");
			return mv;
		}	
	}
	
	//신고리스트 불러오기
	@GetMapping(value = "admin_student_report")
	public ModelAndView admin_student_report(HttpServletRequest request , HttpSession session) {
		if((int)session.getAttribute("u_authority") ==7) { 
			ModelAndView mv = new ModelAndView("admin_student_report");
			String u_id = null;
			List<String> list = adminService.studentReport(u_id);
			mv.addObject("list",list);
			return mv;
		}else {
			ModelAndView mv = new ModelAndView("404");
			return mv;
		}	
	}
	//학생 신고처리하기.
	@PostMapping(value = "admin_student_report")
	public String admin_student_report(HttpServletRequest request, HttpSession session ,HttpServletResponse response) throws Exception {
		if((int)session.getAttribute("u_authority") ==7) { 
			String u_id = request.getParameter("u_id");
			PrintWriter pw = response.getWriter();
	//		pw.print(list);
			return "admin_student_report";
		}else {
			return "404";
		}	
	}
	
	
	
}

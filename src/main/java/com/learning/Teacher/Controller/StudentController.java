package com.learning.Teacher.Controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.learning.DTO.LectureDTO;
import com.learning.DTO.PageDTO;
import com.learning.Teacher.Service.StudentService;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	//수강생관리 첫페이지 (강의리스트 불러오기)
	@RequestMapping(value = "/student")
	public ModelAndView student(HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		if((int)session.getAttribute("u_authority") > 3) {
			ModelAndView mv = new ModelAndView("student");
			String u_id = (String)session.getAttribute("u_id");
			int pageNo = 1;
			if (request.getParameter("pageNo")!=null) {
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			}
			String name = "lecture";
			// recordCountPageNo 한 페이지당 게시되는 게시물 수 yes
			int listScale = 10;
			// pageSize = 페이지 리스트에 게시되는 페이지 수 yes
			int pageScale = 10;			
			// totalRecordCount 전체 게시물 건수				
			int totalCount = studentService.totalCount(name);
			
			// 전자정부페이징 호출
			PaginationInfo paginationInfo = new PaginationInfo();
			// 값 대입
			paginationInfo.setCurrentPageNo(pageNo);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			paginationInfo.setTotalRecordCount(totalCount);
			// 전자정부 계산하기
			int startPage = paginationInfo.getFirstRecordIndex();
			int lastpage = paginationInfo.getRecordCountPerPage();

			// 서버로 보내기
			PageDTO page = new PageDTO();
			page.setStartPage(startPage);
			page.setLastPage(lastpage);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("u_id", u_id);
			map.put("page", page);
			
			List<LectureDTO> lectureList = studentService.lectureList(map);
			for(int i=0;i<lectureList.size();i++) {
				lectureList.get(i).setL_curriculum(lectureList.get(i).getL_curriculum().replaceAll("<(.*?)>", ""));
			}
			mv.addObject("lectureList", lectureList);
			mv.addObject("paginationInfo", paginationInfo);
			return mv;
	}else {
		ModelAndView mv = new ModelAndView("404");
		return mv;
	}
}
	
	//수강생 리스트 불러오는 페이지	
	@RequestMapping(value = "/student_list")
	public ModelAndView student_list(HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		if((int)session.getAttribute("u_authority") > 3) {
			ModelAndView mv = new ModelAndView("student_list");
			String t_id = (String)session.getAttribute("u_id");
			String l_code = request.getParameter("l_code");
			String name="user_lecture_view";
			int pageNo = 1;
			if (request.getParameter("pageNo")!=null) {
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			}
			
			// recordCountPageNo 한 페이지당 게시되는 게시물 수 yes
						int listScale = 10;
						// pageSize = 페이지 리스트에 게시되는 페이지 수 yes
						int pageScale = 10;			
						// totalRecordCount 전체 게시물 건수				
						int totalCount = studentService.totalCount(name);
						
						// 전자정부페이징 호출
						PaginationInfo paginationInfo = new PaginationInfo();
						// 값 대입
						paginationInfo.setCurrentPageNo(pageNo);
						paginationInfo.setRecordCountPerPage(listScale);
						paginationInfo.setPageSize(pageScale);
						paginationInfo.setTotalRecordCount(totalCount);
						// 전자정부 계산하기
						int startPage = paginationInfo.getFirstRecordIndex();
						int lastpage = paginationInfo.getRecordCountPerPage();

						// 서버로 보내기
						PageDTO page = new PageDTO();
						page.setStartPage(startPage);
						page.setLastPage(lastpage);
						
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("l_code", l_code);
			map.put("t_id", t_id);
			map.put("page", page);
			List<String> studentList = studentService.studentList(map);
			
			System.out.println(studentList);
			mv.addObject("studentList", studentList);
			mv.addObject("paginationInfo", paginationInfo);
		
			return mv;
		}else {
			ModelAndView mv = new ModelAndView("404");
			return mv;
		}
	}
	
	//검색
		@GetMapping(value = "/student_studentName" , produces="text/plain;charset=utf-8")
		public ModelAndView student_studentName(HttpServletRequest request, HttpSession session) {	
			if((int)session.getAttribute("u_authority") > 3) {
				String l_name = request.getParameter("l_name"); 
				List<LectureDTO> searchlist = studentService.lectureNameSearch(l_name);	
				ModelAndView mv = new ModelAndView("student2");				
				mv.addObject("searchlist",searchlist);
				return mv; 
			}else {
				ModelAndView mv = new ModelAndView("404");
				return mv;
			}
		}
	
	//수강생 신고 리스트	
	@RequestMapping(value = "/student_ban")
	public ModelAndView student_ban(HttpServletRequest request, HttpSession session) {
		if((int)session.getAttribute("u_authority") > 3) {
			
			ModelAndView mv = new ModelAndView("student_ban");
			String u_id = (String)session.getAttribute("u_id");
			String name = "user_banned";
			
			int pageNo = 1;
			if (request.getParameter("pageNo")!=null) {
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			}
		// recordCountPageNo 한 페이지당 게시되는 게시물 수 yes
		int listScale = 10;
		// pageSize = 페이지 리스트에 게시되는 페이지 수 yes
		int pageScale = 10;			
		// totalRecordCount 전체 게시물 건수				
		int totalCount = studentService.totalCount(name);
		
		// 전자정부페이징 호출
		PaginationInfo paginationInfo = new PaginationInfo();
		// 값 대입
		paginationInfo.setCurrentPageNo(pageNo);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		paginationInfo.setTotalRecordCount(totalCount);
		// 전자정부 계산하기
		int startPage = paginationInfo.getFirstRecordIndex();
		int lastpage = paginationInfo.getRecordCountPerPage();
		
		// 서버로 보내기
		PageDTO page = new PageDTO();
		page.setStartPage(startPage);
		page.setLastPage(lastpage);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("u_id", u_id);
		map.put("page", page);
		
		List<String> banList = studentService.banList(map);
		System.out.println(banList);
		mv.addObject("banList", banList);
		mv.addObject("paginationInfo", paginationInfo);
	
		return mv;
		} else {
			ModelAndView mv = new ModelAndView("404");
			return mv;
		}
		
	}
	
	//수강생 신고 사유 작성페이지
	@RequestMapping(value = "/lecture_student_report")
	public String lecture_student_report(HttpServletRequest request, HttpSession session) {
		if ((int) session.getAttribute("u_authority") > 3) {
			
			
			LectureDTO dto = new LectureDTO();
			dto.setU_id(request.getParameter("u_id"));
			dto.setT_id((String)session.getAttribute("u_id"));
			dto.setL_code(request.getParameter("l_code"));
			dto.setUr_reason(request.getParameter("ur_reason"));
			System.out.println(dto);
			studentService.ban_reason(dto);
			
			return "redirect:/student_list?l_code=" +request.getParameter("l_code"); 
		} else {
			return "redirect:/404";
		}
	}
	
}

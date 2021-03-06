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
import com.learning.Teacher.Service.Video_QnaService;
import com.learning.utill.Util;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class VideoQnaController {
	
	@Autowired
	private Video_QnaService video_qnaService;
	//비디오 Q&A리스트
	@RequestMapping(value = "/video_qna")
	public ModelAndView video_qna(HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		if ((int) session.getAttribute("u_authority") > 3) {
			ModelAndView mv = new ModelAndView("video_qna");
			String vq_no=null;
			int pageNo = 1;
			if (request.getParameter("pageNo")!=null) {
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			}
			// recordCountPageNo 한 페이지당 게시되는 게시물 수 yes
			int listScale = 10;
			// pageSize = 페이지 리스트에 게시되는 페이지 수 yes
			int pageScale = 10;			
			// totalRecordCount 전체 게시물 건수				
			int totalCount = video_qnaService.totalCount();
			
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
			map.put("vq_no", vq_no);
			map.put("page", page);
			List<LectureDTO> video_qnaList = video_qnaService.video_qnaList(map);
			mv.addObject("video_qnaList", video_qnaList);
			mv.addObject("paginationInfo", paginationInfo);
			return mv;
		} else {
			ModelAndView mv = new ModelAndView("404");
			return mv;
		}
	}
	//비디오 Q&A 답글 페이지
		@RequestMapping(value = "/video_qna_reply")
		public String qna_reply(HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException {
			request.setCharacterEncoding("UTF-8");
			int vq_no = Integer.parseInt(request.getParameter("vq_no"));
			if ((int) session.getAttribute("u_authority") > 3) {
				
				LectureDTO video_qnaDetail = new LectureDTO();
				video_qnaDetail.setVq_no(vq_no);
				
				request.setAttribute("dto", video_qnaService.video_qnaDetail(video_qnaDetail));
				return "video_qna_reply";
			} else {
				return "redirect:/404";
			}
		}

	//글쓰기
	@RequestMapping(value = "/video_qna_reply.do")
	public String write(HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		if ((int) session.getAttribute("u_authority") > 3) {
			String u_id = (String) session.getAttribute("u_id");

			LectureDTO video_qna = new LectureDTO();
			video_qna.setT_id(u_id);
			video_qna.setVqr_title(request.getParameter("title"));
			video_qna.setVq_no(Integer.parseInt(request.getParameter("vq_no")));
			video_qna.setVqr_content(request.getParameter("content"));
			int result = video_qnaService.video_qna(video_qna);
			System.out.println(result);
			if(result ==1) {	
				return "redirect:/video_qna";//상세페이지로 변경해야됨
			}else {
				return "redirect:/404";
				
			}
		} else {
			return "redirect:/404";

		}
	}
	
	//비디오 완료 Q&A 답글 완료 페이지
	@RequestMapping(value = "/video_qna_detail")
	public String video_qna_detail(HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		int vq_no = Integer.parseInt(request.getParameter("vq_no"));
		if ((int) session.getAttribute("u_authority") > 3) {
			
			LectureDTO qna_answer_detail = new LectureDTO();
			qna_answer_detail.setVq_no(vq_no);
			
			request.setAttribute("dto", video_qnaService.qna_answer_detail(qna_answer_detail));
			return "video_qna_detail";
		} else {
			return "redirect:/404";
		}
	}
	
	//비디오 완료 Q&A리스트
	@RequestMapping(value = "/video_qna_answer")
	public ModelAndView video_answer(HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		if ((int) session.getAttribute("u_authority") > 3) {
			ModelAndView mv = new ModelAndView("video_qna_answer");
			String vq_no=null;
			int pageNo = 1;
			if (request.getParameter("pageNo")!=null) {
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			}
			// recordCountPageNo 한 페이지당 게시되는 게시물 수 yes
			int listScale = 10;
			// pageSize = 페이지 리스트에 게시되는 페이지 수 yes
			int pageScale = 10;			
			// totalRecordCount 전체 게시물 건수				
			int totalCount = video_qnaService.totalCount();
			
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
			map.put("vq_no", vq_no);
			map.put("page", page);
			List<LectureDTO> video_answer = video_qnaService.video_answer(map);
			mv.addObject("video_answer", video_answer);
			mv.addObject("paginationInfo", paginationInfo);
			return mv;
		} else {
			ModelAndView mv = new ModelAndView("404");
			return mv;
		}
	}	
		//글 수정
		@RequestMapping(value = "/v_qna_update")
		public ModelAndView v_qna_update(HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException {
			request.setCharacterEncoding("UTF-8");
			int vq_no = Integer.parseInt(request.getParameter("vq_no"));
			if ((int) session.getAttribute("u_authority") > 3) {
				ModelAndView mv = new ModelAndView("v_qna_update");
				LectureDTO v_qna_update = new LectureDTO();
				v_qna_update.setVq_no(vq_no);
				request.setAttribute("dto", video_qnaService.v_qna_update(v_qna_update));
				
				return mv;
			} else {
				ModelAndView mv = new ModelAndView("404");
				return mv;
			}
		}
		//수정 글 쓰기
		@RequestMapping(value = "/v_qna_update.do")
		public String update(HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException {
			request.setCharacterEncoding("UTF-8");
			if ((int) session.getAttribute("u_authority") > 3) {
				String u_id = (String) session.getAttribute("u_id");

				LectureDTO v_qna_write = new LectureDTO();
				v_qna_write.setT_id(u_id);
				v_qna_write.setVqr_title(request.getParameter("title"));
				v_qna_write.setVqr_content(request.getParameter("content"));
				v_qna_write.setVq_no(Integer.parseInt(request.getParameter("vq_no")));
				int result = video_qnaService.v_qna_write(v_qna_write);
				System.out.println(result);
				if(result ==1) {	
					return "redirect:/video_qna_answer";
				}else {
					return "redirect:/404";
					
				}
			} else {
				return "redirect:/404";

			}
		}
	
	
	

}
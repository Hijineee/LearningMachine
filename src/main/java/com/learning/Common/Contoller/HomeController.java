package com.learning.Common.Contoller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Session;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.learning.Admin.Service.AdminService;
import com.learning.Common.Service.CommunityService;
import com.learning.Common.Service.MyService;
import com.learning.DTO.BoardDTO;
import com.learning.DTO.LectureDTO;
import com.learning.DTO.PageDTO;
import com.learning.DTO.PaymentDTO;
import com.learning.DTO.userDTO;
import com.learning.utill.Util;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import retrofit2.http.GET;
import retrofit2.http.HTTP;

@Controller
public class HomeController {

	@Autowired
	private CommunityService communityService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private MyService Myservice;
	
	private String name = null;
	
	@GetMapping(value = "/404")
	public String error404() {
		return "404";
	}
	@GetMapping(value = "/admin_category")
	public ModelAndView category() {
		ModelAndView mv = new ModelAndView("admin_category");
		List<String> cate = adminService.categoryList() ;
		
		mv.addObject("cate",cate);
		return mv;
	}
	
	@GetMapping(value = "/boardWrite")
	public String boardWrtie() {
		return "boardWrite";
	}
	
	@GetMapping(value = "boardModify")
	public ModelAndView boardModify(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("boardModify");
		int b_no = Integer.parseInt(request.getParameter("b_no"));
		BoardDTO board = communityService.boardDetail(b_no);
		
		mv.addObject("board",board);
		
		return mv;
	}
	
	@PostMapping(value = "boardModify")
	public String boardModify(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int b_no = Integer.parseInt(request.getParameter("b_no"));
		BoardDTO dto = new BoardDTO();
		dto.setB_no(b_no);
		dto.setB_title(request.getParameter("b_title"));
		dto.setB_content(request.getParameter("b_content"));
		
		communityService.boardModify(dto);
		
		return "redirect:/boardDetail?b_no="+b_no;
	}
	
	@RequestMapping(value = "/commentUpdate.do")
	public String commentUpdate(HttpServletRequest request) {
		int b_no = Integer.parseInt(request.getParameter("b_no"));
		
		BoardDTO dto = new BoardDTO();
		dto.setB_no(b_no);
		dto.setBr_content((String)request.getParameter("br_content"));
		dto.setBr_no(Integer.parseInt(request.getParameter("br_no")));
	
		communityService.commentUpdate(dto);
		
		return "redirect:/boardDetail?b_no="+b_no;
	}
	
	
	//??????????????? ???????????????
	@PostMapping(value = "boardWrite.do")
	public String boardWrite(HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		BoardDTO dto = new BoardDTO();
		dto.setU_id((String)session.getAttribute("u_id"));
		dto.setB_title(request.getParameter("b_title"));
		dto.setB_content(request.getParameter("b_content"));
		
		int result = communityService.boardWrite(dto);
	
		
		return "redirect:/community";
	}
	
	@RequestMapping(value = "/boardDelete.do")
	public String boardDelete(HttpServletRequest request) {
		communityService.boardDelete(request.getParameter("b_no"));
		
		return "redirect:/community";
	}
	

	@GetMapping(value = "/community")
	public ModelAndView community(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		ModelAndView mv = new ModelAndView("community");
		name ="lecture_qna";
		int pageNo = 1;
		if (request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		// recordCountPageNo ??? ???????????? ???????????? ????????? ??? yes
		int listScale = 20;
		// pageSize = ????????? ???????????? ???????????? ????????? ??? yes
		int pageScale = 5;
		// totalRecordCount ?????? ????????? ??????
		int totalCount = communityService.totalCount(name);
		// ????????????????????? ??????
		PaginationInfo paginationInfo = new PaginationInfo();
		// ??? ??????
		paginationInfo.setCurrentPageNo(pageNo);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		paginationInfo.setTotalRecordCount(totalCount);
		// ???????????? ????????????
		int startPage = paginationInfo.getFirstRecordIndex();
		int lastpage = paginationInfo.getRecordCountPerPage();

		// ????????? ?????????
		PageDTO page = new PageDTO();
		page.setStartPage(startPage);
		page.setLastPage(lastpage);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(request.getParameter("c_name") != null) {			
			map.put("c_name", request.getParameter("c_name"));
		}
		map.put("page", page);
		List<String> list = communityService.QnAList(map);
		List<BoardDTO> board = communityService.boardList();
		
		List<String> category = adminService.categoryList();
		
		List<String> commentTotal = communityService.commentTotal();
		
	
	
		mv.addObject("list",list);
		mv.addObject("commentTotal",commentTotal);
		mv.addObject("board",board);
		mv.addObject("category",category);
		mv.addObject("paginationInfo", paginationInfo);
		mv.addObject("pageNo", pageNo);
		return mv;
	}
	
	//??????????????? ????????????
	@GetMapping(value = "/boardDetail")
	public ModelAndView boardDetail(HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		ModelAndView mv = new ModelAndView("boardDetail");
		int b_no = Integer.parseInt(request.getParameter("b_no"));

		Cookie[] cookies = request.getCookies();
		Cookie viewCookie = null;
		
		if(cookies != null && cookies.length > 0) {
			for(int i=0; i<cookies.length ; i++) {
				if(cookies[i].getName().equals("cookie"+b_no)) {
					viewCookie = cookies[i];
				}
			}
		}
		
		BoardDTO board = communityService.boardDetail(b_no);
		if(board !=null ) {
			if(viewCookie == null) {
				Cookie newCookie = new Cookie("cookie"+b_no , "|" + b_no + "|" );
				response.addCookie(newCookie);
				int result = communityService.boardCountUp(b_no);
				if(result > 0) {
					System.out.println("????????? ??????");
				}else {
					System.out.println("????????? ????????????");
				}
			}else {
				String value= viewCookie.getValue();
			}
		}
		
		List<BoardDTO> comment = communityService.commentList(b_no);
		mv.addObject("comment",comment);
		mv.addObject("boardDetail",board);
		return mv;
	}
	
	
	
	@ResponseBody
	@GetMapping(value = "community_category")
	public List<String> community_category(HttpServletRequest request){		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(request.getParameter("c_name") != null) {			
			map.put("c_name", request.getParameter("c_name"));
		
		}else {
			map.put("c_name", null);
		
		}
		List<String> list1 = communityService.QnAList(map);
		return list1; 
	}	

	//??????????????????
	@GetMapping(value = "/community_communityName" , produces="text/plain;charset=utf-8")
	public ModelAndView community_communityName(HttpServletRequest request, HttpSession session) {	
			String lqa_title = request.getParameter("lqa_title"); 
		
			List<String> category = adminService.categoryList();
		
			List<LectureDTO> list = communityService.communitySearch(lqa_title);	
			ModelAndView mv = new ModelAndView("community2");				
			mv.addObject("category",category);
			mv.addObject("list",list);
			return mv; 
	}
	
	//????????????
	@PostMapping(value = "/boardCommentwrite")
	public @ResponseBody void boardCommentwirte(HttpServletRequest request , HttpSession session , HttpServletResponse response) throws IOException {
		BoardDTO dto = new BoardDTO();
		dto.setU_id((String)session.getAttribute("u_id"));
		dto.setB_no(Integer.parseInt(request.getParameter("b_no")));
		dto.setBr_content((String)request.getParameter("br_content"));
		
		int result = communityService.commentWrite(dto);
		PrintWriter pw = response.getWriter();
		pw.print(result);
		
	}
	
	@RequestMapping(value = "/commentDelete")
	public String commentDelete(HttpServletRequest request) {
		int b_no = Integer.parseInt(request.getParameter("b_no"));
		int br_no = Integer.parseInt(request.getParameter("br_no"));
			
		communityService.commentDelete(br_no);

		return "redirect:/boardDetail?b_no="+b_no;
	}
	
	

//==========================================================================================================================================================================================================
//==========================================================================================================================================================================================================
//==========================================================================================================================================================================================================
	//????????????????????? ?????? ????????????

	@RequestMapping(value = "/myInfo")
	public ModelAndView myInfo(HttpServletRequest rq) {
		ModelAndView mv = new ModelAndView("myInfo");
		String u_id = rq.getSession().getAttribute("u_id").toString();
		List<String> myinfo = Myservice.myInfo(u_id);
		mv.addObject("myinfo",myinfo);
		return mv;
	}
	
	
	//??? ?????? ????????????
	@RequestMapping(value = "/myInfoUpdate")
	public void myInfoUpdate(HttpServletRequest rq , HttpServletResponse rs) {
		String u_nickname = rq.getParameter("u_nickname"); 
		String u_pw = rq.getParameter("u_pw");
		String u_id = rq.getSession().getAttribute("u_id").toString();
		if(u_nickname.equals("")) {
			u_nickname = null;
		}
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("u_id", u_id);
		map.put("u_pw", u_pw);
		map.put("u_nickname", u_nickname);
		
	
		int result = Myservice.myInfoUpdate(map);
	}
	
	
	@RequestMapping(value = "/myLecture")
	public ModelAndView myLecture(HttpServletRequest rq) {
		ModelAndView mv = new ModelAndView("myLecture");
		String u_id = rq.getSession().getAttribute("u_id").toString();
		List<String> list = Myservice.myLectureList(u_id);
		mv.addObject("list",list);
		return mv;
	}
	
	//?????? ????????????
	
	@PostMapping(value = "/myPayment")
	public String myPayment(HttpServletRequest rq, HttpServletResponse rs) {
		int p_no = Integer.parseInt(rq.getParameter("p_no"));
		String u_id = rq.getSession().getAttribute("u_id").toString();
		
		PaymentDTO dto = new PaymentDTO();
		dto.setP_no(p_no);
		dto.setU_id(u_id);
		
		int result = Myservice.myPaymentRefund(dto);
		return "redirect:/myPayment";
	}
	
	
	@RequestMapping(value = "/myPayment")
	public ModelAndView myPayment(HttpServletRequest rq) throws UnsupportedEncodingException {
		rq.setCharacterEncoding("UTF-8");

		ModelAndView mv = new ModelAndView("myPayment");
		String u_id = rq.getSession().getAttribute("u_id").toString();
		
		int pageNo = 1;
		if (rq.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt(rq.getParameter("pageNo"));
		}
		// recordCountPageNo ??? ???????????? ???????????? ????????? ??? yes
		int listScale = 7;
		// pageSize = ????????? ???????????? ???????????? ????????? ??? yes
		int pageScale = 5;
		// totalRecordCount ?????? ????????? ??????
		int totalCount = Myservice.totalCount();
		// ????????????????????? ??????
		PaginationInfo paginationInfo = new PaginationInfo();
		// ??? ??????
		paginationInfo.setCurrentPageNo(pageNo);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		paginationInfo.setTotalRecordCount(totalCount);
		// ???????????? ????????????
		int startPage = paginationInfo.getFirstRecordIndex();
		int lastpage = paginationInfo.getRecordCountPerPage();

		// ????????? ?????????
		PageDTO page = new PageDTO();
		page.setStartPage(startPage);
		page.setLastPage(lastpage);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("u_id",u_id);
		map.put("page", page);
		List<Map<String, String>> myPay = Myservice.myPayment(map);
		
		mv.addObject("paginationInfo", paginationInfo);
		mv.addObject("myPay",myPay);
		return mv;
	}
//==========================================================================================================================================================================================================
//==========================================================================================================================================================================================================
//==========================================================================================================================================================================================================
	
	
	//??????
	@GetMapping(value = "/index_search", produces = "text/plain;charset=utf-8")
		public ModelAndView index_search(HttpServletRequest request, HttpSession session,HttpServletResponse response) throws IOException {
		String l_name = request.getParameter("l_name");
		List<LectureDTO> indexsearch = communityService.index_search(l_name);
		for(int i=0;i<indexsearch.size();i++) {
			indexsearch.get(i).setL_curriculum(indexsearch.get(i).getL_curriculum().replaceAll("<(.*?)>", ""));
		}
		if (indexsearch == null) {
			response.getWriter().println("<script>alert('???????????? ????????? ???????????? ????????????.'); window.history.back();"+"</script>");     
		}else {
			ModelAndView mv = new ModelAndView("index_search");
			mv.addObject("indexsearch",indexsearch);		
			mv.addObject("l_name",l_name);
			return mv;
		}
		return null;
	}
	
	
}
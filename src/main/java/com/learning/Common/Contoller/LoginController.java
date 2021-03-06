package com.learning.Common.Contoller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.learning.Common.Service.LoginService;
import com.learning.DTO.TeacherDTO;
import com.learning.DTO.userDTO;
@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	
	
	//로그인처리.
	@PostMapping(value = "/login")
	public String login(HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8"); 
		  userDTO dto = new userDTO();
	      dto.setU_id(request.getParameter("id"));
	      dto.setU_pw(request.getParameter("pw"));
	      
	      dto = loginService.login(dto);
	      if(dto != null) {
	         HttpSession session = request.getSession();
	         session.setAttribute("u_id",dto.getU_id());
	         session.setAttribute("u_authority", dto.getU_authority());
	         session.setAttribute("u_nickname", dto.getU_nickname());
	         if(dto.getU_authority() > 6) {
	            return "redirect:/admin";            
	         }else if(dto.getU_authority() > 3) {            
	            return "redirect:/lecture";                        
	         }
	         return "redirect:/";
	      }else {
	    	  response.getWriter().println("<script type='text/javascript' charset='utf-8' >alert('아이디, 비밀번호 확인해주세요\\n로그인 창으로 이동 합니다.'); window.location.href = './login'; "+ "</script>");
	    	  return null;
	      }
	      
	   }
	  
	//아이디찾기 jsp
	@RequestMapping(value = "/find_id")
	public String find_id() {
		return "find_id";
	}
	
	//화면에 찾은 ID 띄우기
	@RequestMapping(value = "/find_id", method = RequestMethod.POST)
	public String find_id(HttpServletResponse response, @RequestParam("u_email") String u_email, Model md) throws Exception{
		md.addAttribute("id", loginService.find_id(response, u_email)); //response 으로 화면에 아이디 띄우기
		return "find_id";
	}
	
	//비밀번호 재설정 화면
	@RequestMapping(value = "/resetPw", method = RequestMethod.GET )
	public String resetPw() {
		return "resetPw";
	}
	
	//비밀번호 
	@PostMapping(value = "/resetPw")
	public void resetPw(@ModelAttribute userDTO user, HttpServletResponse response) throws Exception{
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8"); 
		int Reset = loginService.resetPw(user);
		if (Reset == 1) {
			response.getWriter().println("<script>alert('비밀번호 변경 성공\\n로그인 창으로 이동 합니다.'); window.location.href = './login'; "+ "</script>");
		}else {
			response.getWriter().println("<script>alert('비밀번호 변경 실패'); window.location.href = './resetPw'; </script>");
		}
	}
	
	//비밀번호 찾기
	@RequestMapping(value = "/forgotPW", method = RequestMethod.GET )
	public String findPwGET(){
		return "forgotPW";
	}
	
	//아이디, 이메일 확인
	@PostMapping(value = "/forgotPW" )
	public String forgotPW(@ModelAttribute userDTO user, HttpServletRequest request) throws IOException {
		int result = loginService.forgotPW(user);
		if (result == 1 ) {	
			
			request.setAttribute("user", user);
			return "resetPw";
			
		} else {
			request.setAttribute("error", "<script>alert('정보가 일치 하지 않습니다.'); window.location.href = document.referrer; "
					+ "</script>");
			return "forgotPW";
		}
	}
	// 아이디 체크
	@ResponseBody
	@PostMapping(value = "/checkID")
	public String checkID(HttpServletRequest request) throws IOException {
		String result = "1";
		int count = loginService.checkID(request.getParameter("u_id"));
		result = String.valueOf(count);
		
		return result;
	}
	//이메일 체크
	@ResponseBody
	@PostMapping(value = "/checkEmail")
	public String checkEmail(HttpServletRequest request) throws IOException {
	String result ="1";
	int count = loginService.checkEmail(request.getParameter("u_email"));
	result = String.valueOf(count);
	
	return result;

	}
	//닉네임 체크
	@ResponseBody
	@PostMapping(value = "/checkNickname")
	public String checkNickname(HttpServletRequest request) throws IOException {
	String result ="1";
	int count = loginService.checkNickname(request.getParameter("u_nickname"));
	result = String.valueOf(count);
	
	return result;

	}
	// 강사회원가입
	@GetMapping(value = "/join2")
	public String join2() {
		return "join2";
	}
	
	// 강사회원가입 후 관리자 승인 대기
	@PostMapping(value = "/join2")
	public String join2(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		userDTO dto = new userDTO();
		dto.setU_id(request.getParameter("u_id"));
		dto.setU_pw(request.getParameter("u_pw"));
		dto.setU_email(request.getParameter("u_email"));
		dto.setU_name(request.getParameter("u_name"));
		dto.setU_gender(request.getParameter("u_gender"));
		dto.setU_nickname(request.getParameter("u_nickname"));
		dto.setU_birth(request.getParameter("u_birth"));
		dto.setU_tel(request.getParameter("u_tel"));
		
		TeacherDTO dto1 = new TeacherDTO();
		dto1.setT_id(request.getParameter("u_id"));
		dto1.setT_introduce(request.getParameter("t_introduce"));
		dto1.setT_spec(request.getParameter("t_spec"));
		dto1.setT_etc(request.getParameter("t_etc"));
		

		int result = loginService.join2(dto);
		int result1 = loginService.join3(dto1);
		
		if(result == 1 && result1 == 1) {
			response.getWriter().println("<script>alert('강사회원가입성공 \\n관리자승인대기중입니다.'); window.location.href = './login'; "+ "</script>");
			return "redirect:/login";
		}else {
			response.getWriter().println("<script>alert('회원가입실패 \\n회원가입 창으로 이동 합니다.'); window.location.href = './join2'; "+ "</script>");			
			return "redirect:/join2";
		}
	}
	
	//유저 회원가입
	@GetMapping(value = "/join")
	public String join() {
		return "join";
	}
	//유저 회원가입
	@PostMapping(value = "/join")
	public String join(HttpServletRequest request) throws UnsupportedEncodingException {
		
		request.setCharacterEncoding("UTF-8");
		
		userDTO dto = new userDTO();
		
		dto.setU_id(request.getParameter("u_id"));
		dto.setU_pw(request.getParameter("u_pw"));
		dto.setU_pw(request.getParameter("u_pw1"));
		dto.setU_email(request.getParameter("u_email"));
		dto.setU_name(request.getParameter("u_name"));
		dto.setU_nickname(request.getParameter("u_nickname"));
		dto.setU_gender(request.getParameter("u_gender"));
		dto.setU_birth(request.getParameter("u_birth"));
		dto.setU_tel(request.getParameter("u_tel"));
		
		int result = loginService.join(dto);
		
		return "redirect:/login";
	}
	
	@GetMapping(value = "/login")
	public String login() {
		
		return "login";
	}

	// 로그아웃처리
	@GetMapping (value = "/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if ((session.getAttribute("u_id") != null) && (session.getAttribute("u_authority") !=null)) {
			session.removeAttribute("u_id");
			session.removeAttribute("u_authority");
		}
		return "redirect:/login";
	}
	
	
	//탈퇴처리
	@GetMapping(value = "/myDrop")
	public void myDrop(HttpServletRequest rq , HttpServletResponse rs) throws IOException {
		String u_id = rq.getSession().getAttribute("u_id").toString();
		int result = loginService.myDrop(u_id);
		System.out.println(u_id);
		System.out.println(result);
		if(result == 1) {
			rq.getSession().removeAttribute("u_id");
			rq.getSession().removeAttribute("u_authority");
			rs.getWriter().println(result);
		}else {	
			rs.getWriter().println(result);
		}
		
	}

}

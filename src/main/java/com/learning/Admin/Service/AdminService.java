package com.learning.Admin.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.Admin.DAO.AdminDAO;
import com.learning.Admin.DAO.PaymentDAO;
import com.learning.DTO.BannedDTO;
import com.learning.DTO.BoardDTO;
import com.learning.DTO.LectureDTO;
import com.learning.DTO.NoticeDTO;
import com.learning.DTO.PageDTO;
import com.learning.DTO.userDTO;

@Service
public class AdminService {
	@Autowired
	private AdminDAO adminDAO;
	
	@Autowired
	private PaymentDAO paymentDAO;
	
	public int totalCount(int check_total) {
		return adminDAO.totalCount(check_total);
	}
	
	public List<userDTO> userList() {
		return adminDAO.userList();
	}

	public List<userDTO> userSearch(String u_name) {
		return adminDAO.userSearch(u_name);
	}

	public List<String> studentLecture(String u_id) {
		return adminDAO.studentLecture(u_id);
	}

	public List<String> studentReport(String u_id, PageDTO page) {
		return adminDAO.studentReport(u_id,page);
	}

	public int report(BannedDTO dto) {
		return adminDAO.report(dto);
	}

	public void user_report(String u_id) {
		adminDAO.user_report(u_id);
	}

	public void back(String u_id) {
		adminDAO.back(u_id);
	}

	public List<String> teacherList() {
		return adminDAO.teacherList();
	}

	public List<String> studentBanList() {
		return adminDAO.studentBanList();
	}

	public List<userDTO> teacherSearch(String t_nickname) {
		return adminDAO.teacherSearch(t_nickname);
	}

	public List<String> refundList() {
		return adminDAO.refundList();
	}
	public List<String> admin_lectureRequest(String la_no) {
		return adminDAO.admin_lectureRequest(la_no);
	}
	public List<String> teachervideo(String t_nickname) {
		return adminDAO.teachervideo(t_nickname);
	}

	public List<String> teacherBanList() {
		return adminDAO.teacherBanList();
	}

	public int lecture_refund(String p_no, String u_id) {
		return adminDAO.lecture_refund(p_no, u_id);
	}

	public int admin_lectureGet(Map<String, Object> map) {
		return adminDAO.lectureGet(map);
	}

	public void admin_lectureGet1(Map<String, Object> map, String c1) {
		adminDAO.lectureGet1(map,c1);
	}

	public List<String> teacherDetail(String u_id) {
		return adminDAO.teacherDetail(u_id);
	}

	public List<String> admin_teacherRequest(String u_id, PageDTO page) {
		return adminDAO.teacherRequest(u_id, page);
	}
	
	public List<String> teacherRe(String u_id) {
		return adminDAO.teacherRe(u_id);
	}

	public int teacherAccept(Map<String, Object> map) {
		return adminDAO.teacherAccept(map);
	}

	public int addCategory(String c_name, String check) {
		return adminDAO.addCategory(c_name,check);
	}

	public List<String> categoryList() {
		return adminDAO.categorylist();
	}

	public List<NoticeDTO> noticeList(Map<String, Object> map) {
		return adminDAO.admin_notice(map);
	}

	public int notice_totalCount() {
		return adminDAO.notice_totalCount();
	}

	public int notice_request(NoticeDTO notice_request) {
		return adminDAO.notice_request(notice_request);
	}

	public Object notice_update(NoticeDTO notice_update) {
		return adminDAO.notice_update(notice_update);
	}

	public int notice_delete(NoticeDTO notice_delete) {
		return adminDAO.notice_delete(notice_delete);
		
	}

	public List<BoardDTO> boardList(Map<String, Object> map) {
		return adminDAO.boardList(map);
	}

	public int community_totalCount() {
		return adminDAO.community_totalCount();
	}

	public int board_delete(BoardDTO board_delete) {
		return adminDAO.board_delete(board_delete);
		
	}

	public Object notice_update1(NoticeDTO notice_update1) {
		return adminDAO.notice_update1(notice_update1);
	}

	public int msgList() {
	
		return adminDAO.msgList();
	}
	


}

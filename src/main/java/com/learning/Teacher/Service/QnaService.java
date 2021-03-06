package com.learning.Teacher.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.DTO.LectureDTO;
import com.learning.DTO.PageDTO;
import com.learning.Teacher.DAO.QnaDAO;

@Service
public class QnaService {
	@Autowired QnaDAO qnaDAO;
	
	public List<LectureDTO> qnalist(PageDTO page) {
		return qnaDAO.qnaList(page);
	}

	public LectureDTO qna_reply(LectureDTO qna_reply) {
		return qnaDAO.qna_reply(qna_reply);
	}

	public int qna_reply2(LectureDTO qna_reply2) {
		return qnaDAO.qna_reply2(qna_reply2);
	}

	public Object qna_detail(LectureDTO qna_detail) {
		return qnaDAO.qna_detail(qna_detail);
	}

	public int totalCount() {
		return qnaDAO.totalCount();
	}

	public List<LectureDTO> qna_answer(Map<String, Object> map) {
		return qnaDAO.qna_answer(map);
	}

	public Object qna_update(LectureDTO qna_update) {
		return qnaDAO.qna_update(qna_update);
	}

	public int qna_write(LectureDTO qna_write) {
		return qnaDAO.qna_write(qna_write);
	}

	
	
	
}

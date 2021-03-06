package com.learning.Teacher.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.DTO.LectureDTO;
import com.learning.Teacher.DAO.StudentDAO;

@Service
public class StudentService {
	
	@Autowired
	private StudentDAO studentDAO;

	public List<LectureDTO> lectureList(Map<String, Object> map) {
		return studentDAO.lectureList(map);
	}
	
	
	 public List<String> studentList(Map<String, Object> map){ 
		 return studentDAO.studentList(map); 
	}

	public List<LectureDTO> lectureNameSearch(String l_name) {
		return studentDAO.lectureNameSearch(l_name);
	}


	public int totalCount(String name) {
		return studentDAO.totalCount(name);
	}

	public int stulistCount() {
		return studentDAO.stulistCount();
	}


	public List<String> banList(Map<String, Object> map) {
		return studentDAO.banList(map);
	}


	public int ban_reason(LectureDTO dto) {
		return studentDAO.ban_reason(dto);
	}


	


	
	 
}

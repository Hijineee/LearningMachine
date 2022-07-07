package com.learning.Common.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.Common.DAO.NoticeDAO;
import com.learning.DTO.NoticeDTO;

@Service
public class BoardService {
	@Autowired
	private NoticeDAO noticeDAO;
	
	public List<NoticeDTO> noticeList() {
		return noticeDAO.list();
	}
}

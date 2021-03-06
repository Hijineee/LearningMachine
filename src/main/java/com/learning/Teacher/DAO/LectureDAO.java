package com.learning.Teacher.DAO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.learning.DTO.LectureDTO;

@Repository
public class LectureDAO {
	
	@Autowired
	private SqlSession sqlSession;
	public List<LectureDTO> lectureApplyList(Map<String, Object> map) {
		return sqlSession.selectList("lecture.t_lectureApplyList",map);
	}
	
	public List<LectureDTO> lectureList(Map<String, Object> map){
		return sqlSession.selectList("lecture.t_lectureList",map);
	}

	public int lecture_request(LectureDTO lecture_request) {
		return sqlSession.insert("lecture.t_lecture_request", lecture_request);
	}

	public List<LectureDTO> letureNameSearch(String l_name) {
		return sqlSession.selectList("lecture.t_letureNameSearch", l_name);
	}

	public int totalCount() {
		return sqlSession.selectOne("lecture.totalCount");
	}

	public int totalCount1() {
		return sqlSession.selectOne("lecture.totalCount1");
	}

	public List<String> lectureCate() {
		return sqlSession.selectList("lecture.cate");
	}

	public LectureDTO lecture_lookup(LectureDTO lecture_lookup) {
		return sqlSession.selectOne("lecture.lecture_Lookup", lecture_lookup);
	}

	public Object lecture_lookup2(LectureDTO lecture_lookup) {
		return sqlSession.selectOne("lecture.lecture_Lookup2", lecture_lookup);
	}

	public int totalCount2() {
		return sqlSession.selectOne("lecture.totalCount2");
	}

	public List<LectureDTO> lecture_refusal(Map<String, Object> map) {
		return sqlSession.selectList("lecture.lecture_refusal", map);
	}

	public int lecture_update_write(LectureDTO lecture_update_write) {
		sqlSession.update("lecture.lecture_update_recognize",lecture_update_write);
		return sqlSession.update("lecture.lecture_update_write", lecture_update_write);
	}

	public Object lecture_update(LectureDTO lecture_update) {
		return sqlSession.selectOne("lecture.lecture_update", lecture_update);
	}

	public int lecture_delete(LectureDTO lecture_delete) {
		return sqlSession.delete("lecture.lecture_delete", lecture_delete);
	}

	public Object lecture_update2(LectureDTO lecture_update2) {
		return sqlSession.selectOne("lecture.lecture_update2", lecture_update2);
	}

	public int lecture_update_write2(LectureDTO lecture_update_write2) {
		sqlSession.update("lecture.lecture_update_renewal",lecture_update_write2);
		sqlSession.update("lecture.lecture_update_write3", lecture_update_write2);
		return sqlSession.update("lecture.lecture_update_write2", lecture_update_write2);
	}

	public List<LectureDTO> video_List(String l_code) {
		return sqlSession.selectList("lecture.video_List", l_code);
	}

	public Object video_upload_detail(LectureDTO video_upload_detail) {
		return sqlSession.selectOne("lecture.video_upload_detail", video_upload_detail);
	}

	public int video_upload(LectureDTO video_upload) {
		sqlSession.update("lecture.lecture_update_renewal",video_upload);
		return sqlSession.insert("lecture.video_upload", video_upload);
	}

	public Object video_upload_update(LectureDTO video_upload_update) {
		return sqlSession.selectOne("lecture.video_upload_update", video_upload_update);
	}

	public int video_update_write(LectureDTO video_update_write) {
		sqlSession.update("lecture.lecture_update_renewal",video_update_write);
		return sqlSession.update("lecture.video_update_write", video_update_write);
	}

	public int video_upload_delete(LectureDTO video_upload_delete) {
		sqlSession.update("lecture.lecture_update_renewal",video_upload_delete);
		return sqlSession.delete("lecture.video_upload_delete", video_upload_delete);
	}








}

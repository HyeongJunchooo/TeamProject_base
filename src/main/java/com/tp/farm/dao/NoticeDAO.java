package com.tp.farm.dao;

import com.tp.farm.vo.NoticeVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/*
        작성자 : 이영록, 윤태검
        내용 : NoticeDAO 추가

        일시 : 2022.11.01 ~
 */

@Repository
public class NoticeDAO {

    @Autowired
    private SqlSession sqlSession;

    public boolean insertBoard(NoticeVO notice) {
        boolean flag = false;
        int affectedCount = sqlSession.insert("mapper.notice.insertBoard", notice);
        if(affectedCount>0) {
            System.out.println("DAO에서 insert완료");
            flag = true;
        }
        return flag;
    }

    public List<NoticeVO> selectAll() {
        List<NoticeVO> list = sqlSession.selectList("mapper.notice.selectAllBoard");
        return list;
    }


    public boolean updateCount(String seq, String token) {
        boolean flag = false;
        if(token=="on") {
            int affectedCount = sqlSession.update("mapper.notice.updateDownloadCount", seq);
            if (affectedCount > 0) {
                System.out.println("다운로드 완료");
                flag = true;
            } else {
                System.out.println("다운로드 실패");
            }
        }
        return flag;
    }

    public NoticeVO selectOneBoard(String seq) {
        NoticeVO notice = (NoticeVO) sqlSession.selectOne("mapper.notice.selectOneBoardBySeq", seq);
        return notice;
    }

    public boolean update(NoticeVO notice) {
        boolean flag = false;
        int affectedCount = sqlSession.update("updateBoard", notice);
        if(affectedCount>0) {
            System.out.println("DAO update성공");
        } else {
            System.out.println("DAO update실패");
        }
        return flag;
    }

    public boolean deleteOne(String seq) {
        boolean flag = false;
        int affectedCount = sqlSession.delete("mapper.notice.deleteOne", seq);
        if(affectedCount>0) {
            flag = true;
        }
        return flag;
    }
}

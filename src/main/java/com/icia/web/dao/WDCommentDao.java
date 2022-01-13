package com.icia.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.icia.web.model.WDComment;

@Repository("WDCommentDao")
public interface WDCommentDao {
	
	public List<WDComment> commentList(long parentSeq);
	
	public int WDCommentInsert(WDComment wdComment);
	
	public int WDCommentMax(long parentSeq);
	
	public int commentDelete(WDComment wdComment);
	
	public int commentBoardDelete(long parentSeq);
	
	public int commentUpdate(WDComment wdComment);
	
	public int commentListCount(long parentSeq);
}

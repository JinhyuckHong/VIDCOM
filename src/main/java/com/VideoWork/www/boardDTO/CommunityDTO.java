package com.VideoWork.www.boardDTO;

public class CommunityDTO {
	private int no;
	private String id;
	private String nickname;
	private String username;
	private String title;
	private String contents;
	private int viewcount;
	private String wtime;
	private int contentsnum;
	private int replynum;
	private int emptynum;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getViewcount() {
		return viewcount;
	}
	public void setViewcount(int viewcount) {
		this.viewcount = viewcount;
	}
	public String getWtime() {
		return wtime;
	}
	public void setWtime(String wtime) {
		this.wtime = wtime;
	}
	public int getContentsnum() {
		return contentsnum;
	}
	public void setContentsnum(int contentsnum) {
		this.contentsnum = contentsnum;
	}
	public int getReplynum() {
		return replynum;
	}
	public void setReplynum(int replynum) {
		this.replynum = replynum;
	}
	public int getEmptynum() {
		return emptynum;
	}
	public void setEmptynum(int emptynum) {
		this.emptynum = emptynum;
	}
}

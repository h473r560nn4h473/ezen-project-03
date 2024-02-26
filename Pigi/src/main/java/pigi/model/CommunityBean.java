package pigi.model;

import java.util.Date;

public class CommunityBean {

	private int CIDX;
	private String CTITLE;
	private Date CDATE;
	private String CWRITER;
	private int PID;
	private int CCOUNT;
	private String CCONTENT;
	private String CTYPE;
	private int R; // 게시판 출력 번호
	
	public int getR() {
		return R;
	}
	public void setR(int r) {
		R = r;
	}
	
	public int getCIDX() {
		return CIDX;
	}
	public void setCIDX(int cIDX) {
		CIDX = cIDX;
	}
	public String getCTITLE() {
		return CTITLE;
	}
	public void setCTITLE(String cTITLE) {
		CTITLE = cTITLE;
	}
	public Date getCDATE() {
		return CDATE;
	}
	public void setCDATE(Date cDATE) {
		CDATE = cDATE;
	}
	public String getCWRITER() {
		return CWRITER;
	}
	public void setCWRITER(String cWRITER) {
		CWRITER = cWRITER;
	}
	public int getPID() {
		return PID;
	}
	public void setPID(int pID) {
		PID = pID;
	}
	public int getCCOUNT() {
		return CCOUNT;
	}
	public void setCCOUNT(int cCOUNT) {
		CCOUNT = cCOUNT;
	}
	public String getCCONTENT() {
		return CCONTENT;
	}
	public void setCCONTENT(String cCONTENT) {
		CCONTENT = cCONTENT;
	}
	public String getCTYPE() {
		return CTYPE;
	}
	public void setCTYPE(String cTYPE) {
		CTYPE = cTYPE;
	}
	
}

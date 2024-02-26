package pigi.model;

import java.util.Date;

public class CommentBean {
	private int COMMENTIDX;
	private int ARTICLEIDX;
	private String COMMENTWRITER;
	private Date COMMENTDATE;
	private String COMMENTT;
	
	public int getCOMMENTIDX() {
		return COMMENTIDX;
	}
	public void setCOMMENTIDX(int cOMMENTIDX) {
		COMMENTIDX = cOMMENTIDX;
	}
	public int getARTICLEIDX() {
		return ARTICLEIDX;
	}
	public void setARTICLEIDX(int aRTICLEIDX) {
		ARTICLEIDX = aRTICLEIDX;
	}
	public String getCOMMENTWRITER() {
		return COMMENTWRITER;
	}
	public void setCOMMENTWRITER(String cOMMENTWRITER) {
		COMMENTWRITER = cOMMENTWRITER;
	}
	public Date getCOMMENTDATE() {
		return COMMENTDATE;
	}
	public void setCOMMENTDATE(Date cOMMENTDATE) {
		COMMENTDATE = cOMMENTDATE;
	}
	public String getCOMMENTT() {
		return COMMENTT;
	}
	public void setCOMMENTT(String cOMMENTT) {
		COMMENTT = cOMMENTT;
	}
	
}

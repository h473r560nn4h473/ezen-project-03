package pigi.model;

public class BasketBean {
	private int BIDX; // 장바구니를 구분
	private int BNUMBER; // 같은 장바구니일때 같은 번호 
	private String BNAME;
	private int BID;
	private int BPRICE;
	private int BSALE;
	private String BEMAIL;
	private int BCOUNT;
	
	public int getBIDX() {
		return BIDX;
	}
	public void setBIDX(int bIDX) {
		BIDX = bIDX;
	}
	
	public int getBNUMBER() {
		return BNUMBER;
	}
	public void setBNUMBER(int bNUMBER) {
		BNUMBER = bNUMBER;
	}
	public String getBNAME() {
		return BNAME;
	}
	public void setBNAME(String bNAME) {
		BNAME = bNAME;
	}
	public int getBID() {
		return BID;
	}
	public void setBID(int bID) {
		BID = bID;
	}
	public int getBPRICE() {
		return BPRICE;
	}
	public void setBPRICE(int bPRICE) {
		BPRICE = bPRICE;
	}
	public int getBSALE() {
		return BSALE;
	}
	public void setBSALE(int bSALE) {
		BSALE = bSALE;
	}
	public String getBEMAIL() {
		return BEMAIL;
	}
	public void setBEMAIL(String bEMAIL) {
		BEMAIL = bEMAIL;
	}
	public int getBCOUNT() {
		return BCOUNT;
	}
	public void setBCOUNT(int bCOUNT) {
		BCOUNT = bCOUNT;
	}	
}

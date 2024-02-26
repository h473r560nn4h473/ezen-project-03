package pigi.model;

public class Payment {
	private int PAYMENTIDX;
	private int OID;
	private int OBNUMBER;
	private int TOTALSUM;
	private int TOTALPAYMENT;
	private String COUPON;
	private int POINT;
	
	public int getPAYMENTIDX() {
		return PAYMENTIDX;
	}
	public void setPAYMENTIDX(int pAYMENTIDX) {
		PAYMENTIDX = pAYMENTIDX;
	}
	public int getOID() {
		return OID;
	}
	public void setOID(int oID) {
		OID = oID;
	}
	public int getOBNUMBER() {
		return OBNUMBER;
	}
	public void setOBNUMBER(int oBNUMBER) {
		OBNUMBER = oBNUMBER;
	}
	public int getTOTALSUM() {
		return TOTALSUM;
	}
	public void setTOTALSUM(int tOTALSUM) {
		TOTALSUM = tOTALSUM;
	}
	public int getTOTALPAYMENT() {
		return TOTALPAYMENT;
	}
	public void setTOTALPAYMENT(int tOTALPAYMENT) {
		TOTALPAYMENT = tOTALPAYMENT;
	}
	public String getCOUPON() {
		return COUPON;
	}
	public void setCOUPON(String cOUPON) {
		COUPON = cOUPON;
	}
	public int getPOINT() {
		return POINT;
	}
	public void setPOINT(int pOINT) {
		POINT = pOINT;
	}
}
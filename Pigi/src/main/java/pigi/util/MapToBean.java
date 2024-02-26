package pigi.util;

import java.util.Map;
import java.util.Date;

import pigi.model.*;

public class MapToBean {

	public static MemberBean mapToMember(Map<String, Object> map) {
		MemberBean memberBean = new MemberBean();
		
		memberBean.setEMAIL((String) map.get("EMAIL"));
		memberBean.setPASSWORD((String) map.get("PASSWORD"));
		memberBean.setNAME((String) map.get("NAME"));
		memberBean.setJUMIN1((String) map.get("JUMIN1"));
		memberBean.setJUMIN2((String) map.get("JUMIN2"));
		
		memberBean.setMDATE((Date) map.get("MDATE"));
		memberBean.setADDRESS1((String) map.get("ADDRESS1"));
		memberBean.setADDRESS2((String) map.get("ADDRESS2"));
		memberBean.setPOSTCODE((String) map.get("POSTCODE"));
		memberBean.setPHONE((String) map.get("PHONE"));
		
		memberBean.setMOBILE((String) map.get("MOBILE"));
		memberBean.setRANK((String) map.get("RANK"));
		memberBean.setBLOCK((String) map.get("BLOCK"));	

		return memberBean;
	}
	
	public static ProductBean mapToProduct(Map<String, Object> map) {
		ProductBean productBean = new ProductBean();
		
		if(map.get("PID") != null) {
			productBean.setPID(Integer.parseInt(String.valueOf(map.get("PID"))));
		}
		productBean.setPNAME((String) map.get("PNAME"));
		productBean.setPIMAGE((String) map.get("PIMAGE"));
		productBean.setPDATE((Date) map.get("PDATE"));
		if(map.get("PPRICE") != null) {
			productBean.setPPRICE(Integer.parseInt(String.valueOf(map.get("PPRICE"))));
		}
		
		if(map.get("PSALE") != null) {
			productBean.setPSALE(Integer.parseInt(String.valueOf(map.get("PSALE"))));
		}
		if(map.get("PSTOCK") != null) {
			productBean.setPSTOCK(Integer.parseInt(String.valueOf(map.get("PSTOCK"))));
		}
		if(map.get("PSELL") != null) {
			productBean.setPSELL(Integer.parseInt(String.valueOf(map.get("PSELL"))));
		}
		productBean.setPCOM((String) map.get("PCOM"));
		productBean.setPLOC((String) map.get("PLOC"));
		
		if(map.get("PWEIGHT") != null) {
			productBean.setPWEIGHT(Integer.parseInt(String.valueOf(map.get("PWEIGHT"))));
		} else {
			// int 타입은 null을 가질 수 없기 때문에 null은 -1으로 설정
			productBean.setPWEIGHT(-1);
		}
		productBean.setPKIND((String) map.get("PKIND"));
		productBean.setPTYPE((String) map.get("PTYPE"));
		
		return productBean;
	}
	
	public static BasketBean mapToBasket(Map<String, Object> map) {
		BasketBean basketBean = new BasketBean();
		
		if(map.get("BIDX") != null) {
			basketBean.setBIDX(Integer.parseInt(String.valueOf(map.get("BIDX"))));
		}
		if(map.get("BNUMBER") != null) {
			basketBean.setBNUMBER(Integer.parseInt(String.valueOf(map.get("BNUMBER"))));
		}
		basketBean.setBNAME((String) map.get("BNAME"));
		if(map.get("BID") != null) {
			basketBean.setBID(Integer.parseInt(String.valueOf(map.get("BID"))));
		}
		if(map.get("BPRICE") != null) {
			basketBean.setBPRICE(Integer.parseInt(String.valueOf(map.get("BPRICE"))));
		}
		if(map.get("BSALE") != null) {
			basketBean.setBSALE(Integer.parseInt(String.valueOf(map.get("BSALE"))));
		}
		basketBean.setBEMAIL((String) map.get("BEMAIL"));
		if(map.get("BCOUNT") != null) {
			basketBean.setBCOUNT(Integer.parseInt(String.valueOf(map.get("BCOUNT"))));
		}
		
		return basketBean;
	}
	
	public static OrderBean mapToOrder(Map<String, Object> map) {
		OrderBean orderBean = new OrderBean();
		
		if(map.get("OID") != null) {
			orderBean.setOID(Integer.parseInt(String.valueOf(map.get("OID"))));
		}
		if(map.get("OBNUMBER") != null) {
			orderBean.setOBNUMBER(Integer.parseInt(String.valueOf(map.get("OBNUMBER"))));
		} else {
			// int 타입은 null을 가질 수 없기 때문에 null은 -1으로 설정
			orderBean.setOBNUMBER(-1);
		}
		
		orderBean.setOMAIL((String) map.get("OMAIL"));
		orderBean.setONAME((String) map.get("ONAME"));
		orderBean.setODATE((Date) map.get("ODATE"));
		if(map.get("OPID") != null) {
			orderBean.setOPID(Integer.parseInt(String.valueOf(map.get("OPID"))));
		}
		
		orderBean.setOPRODUCT((String) map.get("OPRODUCT"));
		if(map.get("OSALE") != null) {
			orderBean.setOSALE(Integer.parseInt(String.valueOf(map.get("OSALE"))));
		}
		if(map.get("OTOTAL") != null) {
			orderBean.setOTOTAL(Integer.parseInt(String.valueOf(map.get("OTOTAL"))));
		}
		if(map.get("OCOUNT") != null) {
			orderBean.setOCOUNT(Integer.parseInt(String.valueOf(map.get("OCOUNT"))));
		}
		if(map.get("OPRICE") != null) {
			orderBean.setOPRICE(Integer.parseInt(String.valueOf(map.get("OPRICE"))));
		}
		
		orderBean.setOMOBILE((String) map.get("OMOBILE"));		
		orderBean.setOPOSTCODE((String) map.get("OPOSTCODE"));
		
		orderBean.setOADDRESS1((String) map.get("OADDRESS1"));
		orderBean.setOADDRESS2((String) map.get("OADDRESS2"));
		orderBean.setOSTATUS((String) map.get("OSTATUS"));
		orderBean.setOWAYBILL((String) map.get("OWAYBILL"));
		
		return orderBean;
	}
	
	public static CommunityBean mapToCommunity(Map<String, Object> map) {
		CommunityBean communityBean = new CommunityBean();
		
		if(map.get("CIDX") != null) {
			communityBean.setCIDX(Integer.parseInt(String.valueOf(map.get("CIDX"))));
		}
		communityBean.setCTITLE((String) map.get("CTITLE"));
		communityBean.setCDATE((Date) map.get("CDATE"));
		communityBean.setCWRITER((String) map.get("CWRITER"));
		if(map.get("PID") != null) {
			communityBean.setPID(Integer.parseInt(String.valueOf(map.get("PID"))));
		} else {
			// int 타입은 null을 가질 수 없기 때문에 null은 -1으로 설정
			communityBean.setPID(-1);
		}
		
		if(map.get("CCOUNT") != null) {
			communityBean.setCCOUNT(Integer.parseInt(String.valueOf(map.get("CCOUNT"))));
		}
		communityBean.setCCONTENT((String) map.get("CCONTENT"));
		communityBean.setCTYPE((String) map.get("CTYPE"));
		if(map.get("R") != null) {
			communityBean.setR(Integer.parseInt(String.valueOf(map.get("R"))));
		}
		
		return communityBean;
	}
	
	public static CommentBean mapToComment(Map<String, Object> map) {
		CommentBean commentBean = new CommentBean();
		
		if(map.get("COMMENTIDX") != null) {
			commentBean.setCOMMENTIDX(Integer.parseInt(String.valueOf(map.get("COMMENTIDX"))));
		}
		if(map.get("ARTICLEIDX") != null) {
			commentBean.setARTICLEIDX(Integer.parseInt(String.valueOf(map.get("ARTICLEIDX"))));
		}
		commentBean.setCOMMENTWRITER((String) map.get("COMMENTWRITER"));
		commentBean.setCOMMENTDATE((Date) map.get("COMMENTDATE"));
		commentBean.setCOMMENTT((String) map.get("COMMENTT"));
		
		return commentBean;		
	}
	
}
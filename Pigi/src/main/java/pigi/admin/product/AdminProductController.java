package pigi.admin.product;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import pigi.common.basket.BasketService;
import pigi.common.product.ProductService;
import pigi.model.BasketBean;
import pigi.model.ProductBean;
import pigi.util.FileUpload;
import pigi.util.MapToBean;
import pigi.util.Paging;

@Controller
public class AdminProductController {

	@Resource(name="adminProductService")
	private AdminProductService adminProductService;
	
	@Resource(name="productService")
	private ProductService productService;
	
	@Resource(name="basketService")
	private BasketService basketService;
	
	@RequestMapping(value="/adminPList.al")
	public String adminPList(HttpServletRequest request,
			Model model) throws Exception{
		
		/* 페이징을 위한 변수 */
		int pageSize = 10; // 페이지당 출력할 상품의 수
		int START = 1;
		int END = pageSize;
		int currentPage = 1; // 현재 페이지

		int countProductAll; // 전체 상품의 수
		int pageBlock = 5; // 표시할 페이지의 수
		String url = "adminPList.al";
		String searchUrl = "";
		
		/* 검색 조건일 경우 사용 변수 */
		// -1, null일 경우 검색 조건에서 빠진다
		String KEYWORD = request.getParameter("keyword"); // 검색 키워드 문자일때
		int KEYNUMBER = -1;
		
		/* 기본 페이지가 아닐 경우 */
		if(request.getParameter("page")!=null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
			START = 1 + pageSize*(currentPage-1); 
			END = pageSize*currentPage;
		}
		
		/* KEYWORD값이 null이 아닌 ""일 때 null로 설정 */
		if(KEYWORD!=null && KEYWORD.equals("")) {
			KEYWORD = null;
		}
		
		/* KEYWORD를 입력받았을 때 숫자로 이루어져있는지 검사 */
		boolean isNumber = false; // isNumber가 false이면 문자 포함, true이면 수
		if(KEYWORD!=null) {
			int i = 0;
			while(i<KEYWORD.length()) {
				if(!Character.isDigit(KEYWORD.charAt(i))) {
					break;
				}
				i++;
			}
			System.out.println("i    : " + i);
			// 모든 검사 루프를 통과하면 수
			if(i==KEYWORD.length()) {
				isNumber = true;
			}
		}
		/* 만약 KEYWORD가 숫자로 이루어져있으면 KEYNUMBER값에 KEYWORD를 숫자로 변환한 값을 입력 */
		if(isNumber == true) {
			KEYNUMBER = Integer.parseInt(KEYWORD);				
		}
		
		if(KEYWORD == null) {
			countProductAll = productService.allListCount();
		} else {
			countProductAll = adminProductService.allListKeywordCount(KEYWORD, KEYNUMBER);
		}
		
		// 페이징할 아이템의 총 수, 페이지의 수 ex> 1~5 6~10, 한 페이지에 표시할 아이템의 수, 현재 페이지, 이동주소, 검색시 사용할 주소 입력
		Paging paging = new Paging(countProductAll,	pageBlock,
			pageSize ,currentPage, url, searchUrl);
				
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<ProductBean> adminPBeanList = new ArrayList<ProductBean>();

		// 검색 조건에 따라(키워드가 null이 아니면) allListKeywordPaging으로 list설정
		if(KEYWORD == null) {
			list = adminProductService.allListPaging(START, END);
		} else {
			list = adminProductService.allListKeywordPaging(KEYWORD, KEYNUMBER, START, END);
		}
		for(Map<String, Object> mapObject : list) {
			adminPBeanList.add(MapToBean.mapToProduct(mapObject));
		}
		
		model.addAttribute("adminPBeanList", adminPBeanList);
		
		/* 페이징을 위한 값 삽입 */
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("paging", paging);
		
		return "adminPListAjax";
		// return "adminPList";
	}
	
	@RequestMapping(value="/adminPWriteForm.al")
	public String adminPWriteForm(Model model) throws Exception {
		return "adminPWriteForm";
	}
	
	@RequestMapping(value="/adminPWrite.al")
	public String adminPWrite(ProductBean product, 
			MultipartHttpServletRequest request,
			Model model) throws Exception {
		
		MultipartFile main_imageFile = request.getFile("main_image");
		MultipartFile detail_imageFile1 = request.getFile("image1");
		MultipartFile detail_imageFile2 = request.getFile("image2");
		MultipartFile detail_imageFile3 = request.getFile("image3");
		MultipartFile detail_imageFile4 = request.getFile("image4");
		
		// 상품의 종류가 ETC인 경우 분류과 무게를 null로 설정
		if(product.getPTYPE().equals("ETC")) {
			product.setPKIND(null);
			product.setPWEIGHT(-1);
		}
		
		// 마지막에 입력한 상품의 정보로부터 상품번호를 가져온 뒤 (selectPIDMax)
		// 1을 더해서 새로 저장될 파일에 붙여줄 숫자를 구한다
		int newPID = adminProductService.selectPIDMax() + 1;
		
		// 상품번호를 사용해 메인 이미지를 등록
		String uploadMainImageName = "product-" + newPID +".jpg";
		
		// 메인 이미지 이름을 product의 PIMAGE에 입력
		product.setPIMAGE(uploadMainImageName);
		
		// 상품 이미지를 입력할 폴더 설정
		String path = request.getSession().getServletContext().getRealPath("/") + File.separator + "img";
		
		FileUpload.fileUpload(main_imageFile, path, uploadMainImageName);
		
		// 만약 상세 이미지가 존재하면 상세 이미지를 등록
		int detailCount = 0; // 상세 이미지 등록 수, 등록할 때마다 1씩 증가, 등록할 때는 +1로 계산해서 등록 
		if(! detail_imageFile1.isEmpty()) {
			String uploadDetailImageName = "product-" + newPID +"-detail" + (detailCount+1) + ".jpg";
			detailCount++;
			FileUpload.fileUpload(detail_imageFile1, path, uploadDetailImageName);
		} 
		if(! detail_imageFile2.isEmpty()) {
			String uploadDetailImageName = "product-" + newPID +"-detail" + (detailCount+1) + ".jpg";
			detailCount++;
			FileUpload.fileUpload(detail_imageFile2, path, uploadDetailImageName);
		} 
		if(! detail_imageFile3.isEmpty()) {
			String uploadDetailImageName = "product-" + newPID +"-detail" + (detailCount+1) + ".jpg";
			detailCount++;
			FileUpload.fileUpload(detail_imageFile3, path, uploadDetailImageName);
		}
		if(! detail_imageFile4.isEmpty()) {
			String uploadDetailImageName = "product-" + newPID +"-detail" + (detailCount+1) + ".jpg";
			detailCount++;
			FileUpload.fileUpload(detail_imageFile4, path, uploadDetailImageName);
		}		
		
		// DB에 상품 정보 입력
		adminProductService.insertProduct(product);
		
		// 메시지와 url 설정
		model.addAttribute("msg", "상품을 등록하였습니다");
		model.addAttribute("url", "/adminPList.al");
	   
		return "/admin/product/adminPWrite";
	}
	
	@RequestMapping(value="/adminPModifyForm.al")
	public String adminPModifyForm(ProductBean product, Model model) throws Exception {
		// PID값을 파라미터로 넘겨받음
		// PID값을 이용하여 상품 정보를 읽어와 전달		
		Map<String, Object> map = new HashMap<String, Object>();
		ProductBean productBean = new ProductBean();
		
		map = adminProductService.selectProductId(product);
		productBean = MapToBean.mapToProduct(map);
		
		model.addAttribute("productBean", productBean);
		
		return "adminPModifyForm";
	}
	
	@RequestMapping(value="/adminPModify.al")
	public String adminPModify(ProductBean product,
			MultipartHttpServletRequest request,
			Model model) throws Exception {
		// 이클립스 - 톰캣의 구동방식 때문에 상품 이미지 변경에 시간이 걸릴 수 있음
		
		// 상품 이미지를 입력할 폴더 설정
		String path = request.getSession().getServletContext().getRealPath("/") + File.separator + "img";
		
		// 상품 수정이기 때문에 PID값은 폼에서 입력받음
		int newPID = product.getPID();
				
		// 상품번호를 사용해 메인 이미지를 등록
		String UploadMainImageName = "product-" + newPID +".jpg";
				
		// 메인 이미지 이름을 product의 PIMAGE에 입력
		product.setPIMAGE(UploadMainImageName);
				
		MultipartFile main_imageFile = request.getFile("main_image");
		MultipartFile detail_imageFile1 = request.getFile("image1");
		MultipartFile detail_imageFile2 = request.getFile("image2");
		MultipartFile detail_imageFile3 = request.getFile("image3");
		MultipartFile detail_imageFile4 = request.getFile("image4");
		
		// 상품의 종류가 ETC인 경우 분류과 무게를 null로 설정
		if(product.getPTYPE().equals("ETC")) {
			product.setPKIND(null);
			product.setPWEIGHT(-1);
		}		
		
		// 만약 수정할 이미지 파일을 입력했으면 기존 파일을 삭제한 뒤 삽입
		if(! main_imageFile.isEmpty()) {
			String delMainPath = path + "/" + UploadMainImageName;
			File delMainFile = new File(delMainPath);	
			delMainFile.delete();
			FileUpload.fileUpload(main_imageFile, path, UploadMainImageName);
		}
		
		// 만약 수정할 상세 이미지를 업로드했으면 기존 상세 이미지르 삭제하고 새로 등록
		if(! detail_imageFile1.isEmpty()) {
			String delPath = path + "/" + "product-" + newPID + "-detail1.jpg";
			File delFile = new File(delPath);
			delFile.delete();
			String uploadDetailImageName = "product-" + newPID + "-detail1.jpg";
			FileUpload.fileUpload(detail_imageFile1, path, uploadDetailImageName);
		} 
		if(! detail_imageFile2.isEmpty()) {
			String delPath = path + "/" + "product-" + newPID + "-detail2.jpg";
			File delFile = new File(delPath);
			delFile.delete();
			String uploadDetailImageName = "product-" + newPID + "-detail2.jpg";
			FileUpload.fileUpload(detail_imageFile2, path, uploadDetailImageName);
		} 
		if(! detail_imageFile3.isEmpty()) {
			String delPath = path + "/" + "product-" + newPID + "-detail3.jpg";
			File delFile = new File(delPath);
			delFile.delete();
			String uploadDetailImageName = "product-" + newPID + "-detail3.jpg";
			FileUpload.fileUpload(detail_imageFile3, path, uploadDetailImageName);
		}
		if(! detail_imageFile4.isEmpty()) {
			String delPath = path + "/" + "product-" + newPID + "-detail4.jpg";
			File delFile = new File(delPath);
			delFile.delete();
			String uploadDetailImageName = "product-" + newPID + "-detail4.jpg";
			FileUpload.fileUpload(detail_imageFile4, path, uploadDetailImageName);
		}		
		
		// DB에서 상품 정보 수정
		adminProductService.updateProduct(product);
		
		// 메시지와 url 설정
		model.addAttribute("msg", "상품을 수정하였습니다");
		model.addAttribute("url", "/adminPList.al");
	   
		return "/admin/product/adminPModify";
	}
	
	@RequestMapping(value="/adminPDelete.al")
	public String adminPDelete(ProductBean product, 
			Model model) throws Exception {
		// PID값을 파라미터로 넘겨받음		
		
		adminProductService.deleteProduct(product);
		
		/* 상품을 삭제하면 해당 상품에 대한 모든 장바구니 정보를 장바구니 테이블에서 삭제한다 */
		int BID = product.getPID();
		BasketBean delBas = new BasketBean();
		delBas.setBID(BID);
		basketService.deleteBasketBid(delBas);
		
		model.addAttribute("msg", "상품을 삭제하였습니다");
		model.addAttribute("url", "/adminPList.al");
		
		return "/admin/product/adminPDelete";
	}
	
	@RequestMapping(value="/adminPDeleteAjax.al")
	@ResponseBody
	public String adminPDeleteAjax(String PID) throws Exception {
		
		ProductBean product = new ProductBean();
		product.setPID(Integer.parseInt(PID));
		
		adminProductService.deleteProduct(product);		
		
		int BID = product.getPID();
		BasketBean delBas = new BasketBean();
		delBas.setBID(BID);
		basketService.deleteBasketBid(delBas);
		
		return "adminPList";
	}
	

	@RequestMapping(value="/adminSellList.al")
	public String adminSellList(HttpServletRequest request, 
			Model model) throws Exception {
		// mybatis allListSearchPaging에서 PSELL값을 이용하여 내림차순 정렬 사용
		
		/* 페이징을 위한 변수 */
		int pageSize = 10; // 페이지당 출력할 상품의 수
		int START = 1;
		int END = pageSize;
		int currentPage = 1; // 현재 페이지

		int countProductAll; // 전체 상품의 수
		int pageBlock = 5; // 표시할 페이지의 수
		String url = "adminSellList.al";
		String searchUrl = "";
		
		/* 기본 페이지가 아닐 경우 */
		if(request.getParameter("page")!=null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
			START = 1 + pageSize*(currentPage-1); 
			END = pageSize*currentPage;
		}
		
		countProductAll = productService.allListCount();
		
		List<Map<String, Object>> list = adminProductService.allListPSELLDescPaging(START, END);
		List<ProductBean> sellList = new ArrayList<ProductBean>();
		for(Map<String, Object> mapObject : list) {
			sellList.add(MapToBean.mapToProduct(mapObject));
		}
		
		model.addAttribute("sellList", sellList);
		
		Paging paging = new Paging(countProductAll,	pageBlock,
				pageSize ,currentPage, url, searchUrl);

		/* 페이징을 위한 값 삽입 */
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("paging", paging);
		
		return "adminSellList";
	}	
}
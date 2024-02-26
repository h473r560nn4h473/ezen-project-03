package pigi.admin;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import pigi.util.FileUpload;

@Controller
public class AdminPageController {
	
	@RequestMapping(value="/adminMainForm.al")
	public String adminMainForm(Model model) {
		return "adminMainForm";
	}
		
	@RequestMapping(value="/adminMainModify.al")
	public String adminMainModify(MultipartRequest multipartRequest, HttpServletRequest request, Model model) 
			throws IOException { 
		String path = request.getSession().getServletContext().getRealPath("/") + File.separator + "img";
		
		String delName1 = path + "/" + "main_1.jpg";
		String delName2 = path + "/" + "main_2.jpg";
		String delName3 = path + "/" + "main_3.jpg";
		String delName4 = path + "/" + "main_4.jpg";
		String delName5 = path + "/" + "main_5.jpg";
		
		File delFile1 = new File(delName1);
		File delFile2 = new File(delName2);
		File delFile3 = new File(delName3);
		File delFile4 = new File(delName4);
		File delFile5 = new File(delName5);
		
		
		MultipartFile main_imageFile1 = multipartRequest.getFile("file1");
		MultipartFile main_imageFile2 = multipartRequest.getFile("file2");
		MultipartFile main_imageFile3 = multipartRequest.getFile("file3");
		MultipartFile main_imageFile4 = multipartRequest.getFile("file4");
		MultipartFile main_imageFile5 = multipartRequest.getFile("file5");
		
		
		String saveName1 = "main_1.jpg";
		String saveName2 = "main_2.jpg";
		String saveName3 = "main_3.jpg";
		String saveName4 = "main_4.jpg";
		String saveName5 = "main_5.jpg";
		  
		if(!main_imageFile1.isEmpty()) {
			delFile1.delete();
			
			FileUpload.fileUpload(main_imageFile1, path, saveName1);
		}
		if(!main_imageFile2.isEmpty()) {
			delFile2.delete();
			
			FileUpload.fileUpload(main_imageFile2, path, saveName2);
		}
		if(!main_imageFile3.isEmpty()) {
			delFile3.delete();
			
			FileUpload.fileUpload(main_imageFile3, path, saveName3);
		}
		if(!main_imageFile4.isEmpty()) {
			delFile4.delete();
			
			FileUpload.fileUpload(main_imageFile4, path, saveName4);
		}
		if(!main_imageFile5.isEmpty()) {
			delFile5.delete();
			
			FileUpload.fileUpload(main_imageFile5, path, saveName5);
		}
		  
		model.addAttribute("msg", "메인 이미지를 등록하였습니다"); 
		model.addAttribute("url", "/main.al");
		  
		return "/admin/adminMainModify"; 
}

}
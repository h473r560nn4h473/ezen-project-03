package pigi.util.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;  

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("------------------ Interceptor : LoginInterceptor ------------------ ");
		
		String email = (String) request.getSession().getAttribute("EMAIL");
		String uri = request.getRequestURI();
		
		/* 로그인하지 않아도 사용할 수 있는 기능들 */
		// main.al loginForm.al login.al joinForm.al joinSuccess.al 
		// findId.al findIdResult.al findPw.al findPwResult.al confirmId.al
		// allList.al aclList.al etcList.al
		// noticeList.al noticeDetail.al qnaList.al qnaDetail.al

		System.out.println("uri :" + uri);
		if(email == null &&
				(
					uri.equals("/Pigi/main.al") || uri.equals("/Pigi/loginForm.al") || uri.equals("/Pigi/login.al") ||
					uri.equals("/Pigi/joinForm.al") || uri.equals("/Pigi/joinSuccess.al") || uri.equals("/Pigi/findId.al") ||
					uri.equals("/Pigi/findIdResult.al") || uri.equals("/Pigi/findPw.al") || uri.equals("/Pigi/findPwResult.al") ||
					uri.equals("/Pigi/confirmId.al") || uri.equals("/Pigi/allList.al") || uri.equals("/Pigi/aclList.al") ||
					uri.equals("/Pigi/etcList.al") || uri.equals("/Pigi/noticeList.al") || uri.equals("/Pigi/noticeDetail.al") ||
					uri.equals("/Pigi/qnaList.al") || uri.equals("/Pigi/qnaDetail.al") || uri.equals("/Pigi/confirmIdAjax.al")
				)) {
			System.out.println("- 로그인하지 않음 -");
			return true;
		} else if(email != null) {
			/* 로그인했을 경우 */

				System.out.println("uri.substring(6, 11) : " + uri.substring(6, 11));
			
				/* 일반 사용자인 경우 관리자 페이지에 접근 불가 */
				if( (!email.equals("ADMIN")) && (
						uri.substring(6, 11).equals("admin") || uri.equals("/Pigi/memberList.al") || uri.equals("/Pigi/memberDetail.al") ||
						uri.equals("/Pigi/memberModify.al") || uri.equals("/Pigi/memberDelete.al")
						)) {
					System.out.println("- 일반 사용자가 관리자 페이지에 접근 -");
					response.sendRedirect("main.al");
					return false;					
				}
				/* 관리자일 경우와 일반 사용자가 관리자 페이지가 아닌 페이지에 접근할 경우*/
				else {
					System.out.println("- 로그인 함 올바른 사용 -");
					return true;				
				}				
		} else {
			/* 로그인하지 않았을 때 사용할 수 없는 페이지에 접근할 경우 */
			System.out.println("- 로그인하지않았는데 사용할 수 없는 페이지에 접근 -");
			
			/* 성인인증 메시지 출력 후 주소로 이동 */
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인이 필요합니다 회원가입을 해주세요'); location.href='/Pigi/loginForm.al';</script>");
			out.flush();
			
			// response.sendRedirect("/Pigi/loginForm.al"); return false;
			 
			return false;
		}
	}
}
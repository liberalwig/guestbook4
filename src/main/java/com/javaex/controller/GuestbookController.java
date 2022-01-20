package com.javaex.controller; //Vo는 그릇이고 Dao는 기능있는 도구격이므로 Vo는 autowired안 해

import java.io.IOException;
import java.util.List;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.GuestbookDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value = "/guestbook")
public class GuestbookController {

	// 필드
	@Autowired
	private GuestbookDao guestbookDao;
	private GuestbookVo guestbookVo;

	@RequestMapping(value = "/addList", method = { RequestMethod.GET, RequestMethod.POST })
	public String addList() {
		System.out.println("GuestController > addList() ");

		return "writeForm";
	}
	/*
	 * if ("addlist".equals(action)) { // 유저가 addList페이지를 원했다면(= value 입력할 페이지를 보여
	 * 주려면) System.out.println("action=addlist");
	 * 
	 * request.setAttribute("gList", guestList); WebUtil.forward(request, response,
	 * "/WEB-INF/deleteForm.jsp");
	 * 
	 * }
	 */

	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	private String add(@RequestParam("name") String name, @RequestParam("password") String password,
			@RequestParam("content") String content) {
		System.out.println("GuestController > add() ");

		int count = guestbookDao.insert(guestvookVo);
		phoneDao.personInsert(personVo);

		// 저장
		return "redirect: /guestbook4/gbc?action=addList"; // redierect를 안 쓰면 phone/list파일을 찾는다

	}else if("add".equals(action))

	{ // 유저가 add페이지 원했다면(= 유저가 value를 입력해서 다음으로 넘겨 줘야 한다면)
	System.out.println("action=add");

	String name = request.getParameter("name"); // 유저가 입력하는 값을 파라미터로 받는다
	String password = request.getParameter("password");
	String content = request.getParameter("content");

	GuestbookVo vo = new GuestbookVo(name, password, content); // 유저가 입력한 값(values)을 Vo그릇 형태 유지해서 메모리에 올린다
	int count = guestbookDao.insert(vo); // vo에 입력한 걸 건수(int)로 파악하고
	System.out.println(count + "건 등록되었습니다"); // 위의 건수를 출력해준다. '음,유저 둘이 등록한 대로 두 건이 잘 올라갔군'
	
	//리다이렉트
	//response.sendRedirect("/guestbook2/gbc?action=addlist");
	
	//리다이렉트는 리스폰의 메소드를 사용함(: 주소값)
	WebUtil.redirect(request, response, "/guestbook2/gbc?action=addList");
	
	
	



@WebServlet("/gbc") //gbc?action=addList
public class GuestbookController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("GuestbookController");// 예외처리 한 후 request와 response받아지는 지 출력해서 확인한다--접속되면 프린트로 찍힘

			List<GuestbookVo> guestList = guestbookDao.getList(); // GuestbookVo에서 받은 걸 Dao 폼에 넣되 배열화 해서 넣는다--Dao에
																// getList()(jdbc)=>배열을 리턴해줘요

		String action = request.getParameter("action"); // GuestbookVo의 파라미터를 뽑아 쓴다 -- 주소의 action 파라미터 값을 문자열로 가져온다.
		// ex /gbc?action=addlist&name=유재석

		} else if ("deleteForm".equals(action)) { // 유저가 deleteform페이지 원했다면
			System.out.println("action=deleteform");

			String stringNo = request.getParameter("no"); // no파라미터 하나에 다른 파라미터들이 묶이게 한다?????????
			request.setAttribute("stringNo", stringNo); // forward방식으로 deleteForm.jsp한테 일을 넘긴다

			//포워드
			//RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/deleteForm.jsp"); // 그 일을 할 jsp파일의 위치를 찍어준다
			//rd.forward(request, response); // forward방식 거쳐서 request에 대한 request를 유저에게 내놓는다--이 2개를 넘김
			
			//포워드는 리스폰의 메소드를 사용함(: 경로)
			WebUtil.forward(request, response, "/WEB-INF/deleteForm.jsp");
			
			
		} else if ("delete".equals(action)) {
			System.out.println("action=delete");

			int no = Integer.parseInt(request.getParameter("no")); // no파라미터가 받은 스트링을 Int로 형변환해주고 그 형변환해준 걸 메모리에 올린다
			String password = request.getParameter("password");

			GuestbookVo vo = new GuestbookVo();
			vo.setNo(no);
			vo.setPassword(password);
			int count = guestbookDao.delete(vo);

			System.out.println(count + "건 삭제되었습니다.");

			// 리다이렉트
			//response.sendRedirect("/guestbook2/gbc?action=addlist");
			WebUtil.redirect(request, response, "/guestbook2/gbc?action=addList");
			
		} else {
			System.out.println("파라미터 없음");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
package mytld.mycompany.myapp;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.dbcp.BasicDataSource;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	BasicDataSource dbinfo;

	@Resource(name = "membership_DAO")
	membership_DAO dao;

	@Resource(name = "m_md5")
	m_md5 md5 = null;

	HttpSession session = null;

	PrintWriter pw = null;

	String msg = null;

	// kakao2.jsp에서 넘어옴
	// @Post도 받고 @Get도 받아야됨
	// @Post => 일반로그인, kakao api, @Get => kakao Script
	// 일반로그인 + Kakao Script Login => @RequestMapping
	// 일반로그인 + Kakao API => @PostMapping
	/*
	 * ServletRequest, ServletResponse => session 사용 불가능 HttpServletRequest,
	 * HttpServletResponse => session 사용가능
	 */
	@RequestMapping("/ajax/web_loginok.do")
	public String web_loginok( // DTO로 받아도됨
			@RequestParam(name = "code") String code, @RequestParam(name = "mid", required = false) String mid,
			@RequestParam(name = "mpass", required = false) String mpass,
			@RequestParam(name = "kakao_id", required = false) String kakao_id,
			@RequestParam(name = "kakao_nicknm", required = false) String kakao_nicknm, Model m,
			HttpServletRequest req) {
		
		this.session = req.getSession();

		if (code.equals("1")) { // 일반 로그인 처리

			String pw = this.md5.md5_pass(mpass); // 사용자가 입력한 값을 암호화하여 회신
			List<membership_DTO> all = this.dao.id_info(mid, pw);
			System.out.println(all.size());

			if (all.size() > 0) {
				// 해당 로그인시 아이디를 session으로 등록함
				this.session.setAttribute("mid", all.get(0).getMID());

				System.out.println("세션 MID: " + this.session.getAttribute("mid"));

//				this.msg = "alert('로그인하였습니다');location.href='./myinfo.do';";
				this.msg = "setTimeout(function() { alert('로그인하였습니다'); location.href='./myinfo.do'; }, 300);";

				this.logger.info("로그인확인");
			} else {
				this.msg = "alert('로그인 실패하였습니다');" + "history.go(-1);";
				this.logger.info("로그인미확인");
			}
			m.addAttribute("msg", msg);

		} else if (code.equals("2")) { // 카카오 로그인 처리
			List<membership_DTO> all = this.dao.id_info(kakao_id, "");
			if (all.size() > 0) {
				//해당 로그인시 아이디를 session으로 등록함
				this.session.setAttribute("mid", all.get(0).getMID());
				msg = "alert('로그인 하셨습니다.'); location.href='./myinfo.do';";
			} else {
				// sessionStorage를 이용하여 간편회원가입을 등록하려함
				// 단 닉네임일 경우 특수문자를 사용할 수있으므로 생성시 ''로 변수값을 적용하여 처리
				this.msg = "alert('카카오 사용자로 로그인시 간편회원가입이 필요합니다.');"
						+ "sessionStorage.setItem('mid','"+ kakao_id +"');"
						+ "sessionStorage.setItem('mnick','"+ kakao_nicknm +"');"
						+ "location.href='../join.jsp';";
			}
			m.addAttribute("msg", msg);
		}

		return "joinok";
	}

	// 아이디 체크
	@PostMapping("/login_idck.do")
	public String login_idck(@RequestParam(name = "id") String id, ServletResponse res) {
		String result = null;
		try {
//			this.logger.info(id);
			this.pw = res.getWriter();

			// 아이디 대소문자 구별함
			// toUpperCase : 대문자로
			// toLowerCase : 소문자로
			result = this.dao.id_row(id.toLowerCase());
//			this.logger.info(result);
			if (result == null || result.equals("0")) {
				this.pw.write("ok");
			} else {
				this.pw.write("no");
			}

		} catch (Exception e) {
			this.logger.info(e.toString());
		} finally {
			this.pw.close();
		}

		return null;
	}

	@PostMapping("/joinok.do")
	public String joinok(@ModelAttribute membership_DTO dto, Model m) {

		String pw = dto.getMPASS();
		dto.setMPASS(this.md5.md5_pass(pw)); // 암호화 후 setter 사용

		try {
			int result = this.dao.join_insert(dto);
			if (result > 0) {

				m.addAttribute("msg", "alert('정상적으로 회원가입이 완료되었습니다');" + "location.href='./kakao2.jsp';");
			} else {
				m.addAttribute("msg", "alert('회원가입이 완료되지 않았습니다.');" + "history.go(-1);");
			}
		} catch (Exception e) {
			this.logger.info(e.toString());
		}

		return null;
	}

	// 로그인 사용자 정보 출력하는 페이지
	@GetMapping("/ajax/myinfo.do")
	public String myinfo(@SessionAttribute("mid") String MID, Model m) {
		//사용자 정보 가져옴
		List<membership_DTO> mydata = this.dao.id_info(MID, "");
		m.addAttribute("mydata",mydata);
		
		//세션 ID 전송
		//this.logger.info(MID);
		m.addAttribute("MID", MID);

		return "/myinfo";
	}

	// 로그아웃
	@GetMapping("/ajax/logout.do")
	public String logout(HttpServletRequest req, Model m) {
		this.session = req.getSession();
		this.session.invalidate();

		this.msg = "alert('로그아웃 완료'); location.href='../kakao2.jsp';";

		m.addAttribute("msg", this.msg);

		return "joinok";
	}
	
	
	//배열키에 맞춰서 보내달라고 해야함 
	
	//API Patch로 개인정보 수정
	@PatchMapping("/ajax/myinfo_modify.do/{key}")
	public String myinfo_modify(ServletResponse res,
			@PathVariable("key") String key,
			@RequestBody String datainfo) {
		 try {
	         this.pw = res.getWriter();
	         if(key.equals("mykey")) {
	        	 JSONObject jo = new JSONObject(datainfo);	//=> MAP
	        	 
	        	 Map<String,String> userdata = new HashMap<String, String>();
	        	 //System.out.println(jo.keySet());
	        	 
	        	 for(String k : jo.keySet()) {
	        		 if(!jo.get(k).equals("")) {	//값이 비어있지 않을 경우 맵에 추가 
	        			 if(k.equals("MPASS")) {	//패스워드도 넘어온 경우 
	        				 //md5로 암호화해서 맵에 추가 
	        				 userdata.put(k, this.md5.md5_pass(jo.get(k).toString()));
	        			 }else {
	        				 userdata.put(k, jo.getString(k).toString());
	        			 }
	        		 }
	        	 }
	        	
	        	 this.logger.info(datainfo.toString());
	        	 
	        	 int result = this.dao.id_update(userdata);
	        	 if(result > 0) {
	        		 this.pw.write("ok");
	        	 }else {
	        		 this.pw.write("no");
	        	 }
	        	 
	         }
	         else {
	            this.pw.write("key error");
	         }
	      } catch (Exception e) {
	    	  System.out.println(e);
	         this.pw.write("error");
	      }finally {
	         this.pw.close();
	      }

		
		return null;
	}
	
	

	/* ============================================================== */

	@RequestMapping(value = "/test.do", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		try {
			Connection con = this.dbinfo.getConnection();
			String sql = "select count(*) as ctn from MEMBER";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();

			this.logger.info(rs.getString("ctn")); // 컬럼명으로 값을 가져옴
			this.logger.info(con.toString()); // info는 기본적으로 String 이라서 형변환 필요
			this.logger.info("테스트 진행중"); // 이제 sysout 안쓰고 logger 사용하기

			rs.close();
			ps.close();
			con.close();

		} catch (Exception e) {
			this.logger.error(e.toString());
			this.logger.debug("오류발생");
		}

		return "/WEB-INF/views/home";
	}

}

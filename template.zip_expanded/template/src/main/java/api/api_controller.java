package api;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletResponse;

import org.apache.ibatis.javassist.bytecode.analysis.MultiArrayType;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONParserConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mysql.cj.xdevapi.JsonParser;

@Controller
public class api_controller {

	// log로 문제사항 및 실행사항을 체크하는 라이브러리
	private static final Logger logger = LoggerFactory.getLogger(api_controller.class);

	/*
	 // this.logger.info : 해당 메소드에서 실행된 값을 출력하는 역할 
	 // this.logger.error : catch에서 사용하는 형태 error발생시 출력되는 메세지 
	 // this.logger.debug : 해당 코드가 정상적으로 작동하는 테스트 메세지를 출력할 때 씀
	 // this.logger.trace : 해당 코드에 문제가 발생시 좀 더 상세하게 문제사항을 출력 
	 // this.logger.warn : 현재 코드에 대해서 향후 문제가 발생 될 수 있는 원인에 대한 메세지 출력 
	 // this.logger.fatal : 치명적인 오류 발생시 출력되는 역할
	 */

	// js - Ajax(GET)
	PrintWriter pw = null;

	// 문자열 + ok, no, error
	@GetMapping("/ajax/ajax1.do")
//	public String ajax1(@RequestParam(name = "product") String data, ServletResponse res) {
	public String ajax1(@RequestParam(name = "product") String data[], ServletResponse res) {
//		this.logger.info(data);

		// Front-end에서 보낸 name을 원시배열로 받을 경우 자동으로 배열로 변경처리
		// 단, String으로 배열 자료형을 사용하지 않을 경우 split을 이요ㅕㅇ하여 값을 분리시켜야함

		// => 프론트에서 배열로 보냈는데 data[]가 아니라 data로 받은경우
		// ,로 이어진 문자열로 받음 => split 사용 or 그냥 배열로 받기 data[]
		this.logger.info(data[0]);
		this.logger.info(data[1]);
		this.logger.info(data[2]);
		try {
			this.pw = res.getWriter();
			this.pw.print("ok");

		} catch (Exception e) {
			this.logger.error(e.toString());
		}

		return null;
	}
	
	
	
	//POST
	//Array 형태로 전송시 GET형태의 메소드와 동일하게 POST 값을 받음 
	@PostMapping("/ajax/ajax2.do")
	public String ajax2 (@RequestParam(name = "product") String pd[], ServletResponse res) {
		try {
			this.logger.info(pd[0]);
			this.pw = res.getWriter();
			this.pw.write("ok");
		}catch (Exception e) {
			this.logger.error(e.toString());
		}
		return null;
		
	}
	
	
	// POST
	// Formdata 형태로 전송시
	// @RequestHeader : ajax에서만 사용하는 Headers의 값이며, 키에 맞는 데이터를 가져올 수 있음
	//					Front-end에서 setRequestHeader("키","값")을 보낼 경우에만 사용함
	// @RequestBody : name없음! 
	//				ajax.setRequestHeader("content-type","application/json"); 로 전송되었을 경우 사용 
	@PostMapping("/ajax/ajax3.do")
	public String ajax3(
//			@RequestHeader(name = "User") String user,	//첫글자 대문자로 기본으로 잡혀있음 
			@RequestBody String pd,
			ServletResponse res) {
		try {
//			this.logger.info(user);
			this.logger.info(pd);
			this.pw = res.getWriter();
			this.pw.write("ok");
		} catch (Exception e) {
			this.logger.error(e.toString());
		}
		return null;
	}
	
	//모두 다른 키일때 
	//json으로 보내면 무조건 @RequestBody로 받기 
	//JSON.stringify : Front-end가 전송시 무조건 @RequestBody로 처리하기 
	@PostMapping("/ajax/ajax4.do")
	public String ajax4(
			@RequestBody String pd,	//DTO로 받는 경우 키가 정확해야함 
			ServletResponse res) {
		
		try {
			this.logger.info(pd);
			JSONArray ja = new JSONArray(pd);
			this.logger.info(String.valueOf(ja.length()));
			int w = 0;
			while(w <ja.length()) {
				JSONObject jo = (JSONObject)ja.get(w);
				String usernm = jo.get("pd").toString();
				this.logger.info(usernm);
				w++;
			}
			
			this.pw = res.getWriter();
			this.pw.write("ok");
		} catch (Exception e) {
			this.logger.error(e.toString());
		}
		return null;
	}
	
	
	//Jquery - 배열값을 GET으로 받아서 처리한 메소드 
	@GetMapping("/ajax/ajax5.do")
	public String ajax5(@RequestParam("no") String no,	//DTO로 받는 경우 키가 정확해야함 
			ServletResponse res) {
		try {
			this.logger.info(no);
			
			JSONArray ja = new JSONArray(no);	//[]로 묶여있으니까 Array로 풀기 
			int w = 0;
			while(w < ja.length()) {
				this.logger.info(ja.getString(w).toString());
				w++;
			}
			
			this.pw = res.getWriter();
			this.pw.write("ok");
		}catch (Exception e) {
			
		}
		
		return null;
	}
	
	
	//Ajax - Jquery로 전송 (Post) 대표키가 없는 경우 
	@PostMapping("/ajax/ajax6.do")
	public String ajax6(@RequestBody String all_data,
			ServletResponse res) {
		try {
			this.logger.info(all_data);
			
			//대표키 있는 경우 
			JSONObject jo = new JSONObject(all_data);
			this.logger.info(jo.get("userdata").toString());
			
			
			/*
			//대표키 없는 경우 
			JSONArray ja = new JSONArray(all_data);	//[]로 묶여있으니까 Array로 풀기 
			int w = 0;
			while(w < ja.length()) {
				this.logger.info(ja.getString(w).toString());
				w++;
			}
			*/
			
			this.pw = res.getWriter();
			this.pw.write("ok");
		}catch (Exception e) {
			
		}
		
		return null;
	}
	
	//각각의 다른 키로 POST 전송 (Jquery)
	//프론트에서 dto로 보내도 일단을 body String으로 받아야함   
	//각각의 다른 키로 데이터를 받아서 처리 JSON.stringify
	/*
	@PostMapping("/ajax/ajax7.do")
	public String ajax7(@RequestBody String alldata,
			ServletResponse res) {
		try {
			this.logger.info(alldata);	//이거 get으로 못찍음 {}형태니까 jo로 풀기 
			JSONObject jo = new JSONObject(alldata);
			//키 이름을 순차적으로 가져오는 안터페이스 (자바.유틸)
			Iterator i = jo.keys();	//Iterator : 키뽑는애 
			while(i.hasNext()) {
				String a = i.next().toString();		//키를 뽑아옴 
				this.logger.info(jo.getString(a));	//값을 가져옴 
				this.logger.info(a);				//키를 가져옴 
			}
			
			this.logger.info(jo.keys().toString());	 
			this.logger.info(jo.getString("pd1"));	 
			
			
			
			this.pw = res.getWriter();
			this.pw.write("ok");
		}catch (Exception e) {
			
		}
		
		return null;
	}
	*/
	
	
	//Front-end에서 파라미터 형태로 문자열 기준으로 POST전송시 Backend에서는 DTO로 활성
	//각각의 다른키로 POST DTO로 받기 Jquery
	@PostMapping("/ajax/ajax7.do")
	public String ajax7(api_dto dto,
			ServletResponse res) {
		
		//@RequestParam 쓰면 안되는 이유 => 이름이 없으니까
		//@RequestBody 쓰면 안되는 이유 => JSON.stringify 미사용 
		
		
		try {
			this.logger.info(dto.getPd1());
			this.logger.info(dto.getPd2());
			this.logger.info(dto.getPd4());
			
			
			this.pw = res.getWriter();
			this.pw.write("ok");
		}catch (Exception e) {
			
		}
		
		return null;
	}

	//formdata post 전송 
	@PostMapping("/ajax/ajax8.do")
	public String ajax8(@RequestParam(value="fdata", defaultValue = "", required = false) String fdata,
			ServletResponse res) {
		
		try {
			
			String rdata[] = fdata.split(",");
			this.logger.info(rdata[0]);
			
			this.pw = res.getWriter();
			this.pw.write("ok");
		}catch (Exception e) {
			
		}
		
		return null;
	}
	
	/*============================================================================*/

	// ECMA - Ajax (get)
	@GetMapping("/ajax/ajax9.do")
	public String ajax9(@RequestParam(name="mid") String mid, ServletResponse res) {
		try {
			this.logger.info(mid);
			this.pw = res.getWriter();
			this.pw.write("ok");
			
		} catch (Exception e) {

		}

		return null;
	}

	// ECMA - Ajax (post)
	@PostMapping("/ajax/ajax10.do")
	public String ajax10(@RequestParam(name = "mid") String mid, ServletResponse res) {
		try {
			this.logger.info(mid);
			this.pw = res.getWriter();
			this.pw.write("ok");

		} catch (Exception e) {

		}

		return null;
	}

	// ECMA - Ajax (post) : 배열 전송
	@PostMapping("/ajax/ajax11.do")
	public String ajax11(@ModelAttribute api_dto dto, ServletResponse res) {
		try {
			this.logger.info(dto.getMname().toString());
			this.logger.info(dto.getMid().toString());

			this.pw = res.getWriter();
			this.pw.write("ok");

		} catch (Exception e) {

		}

		return null;
	}
	
	//Ajax(PATCH) - API Server
	//@PathVariable : URL에 파라미터 값을 가져오는 어노테이션 {~} 
	//				가상의 파라미터값 {여기}랑 name="여기" 같아야함 딱하나만 사용가능  
	//				JSON.stringify에 대한 정보값을 처리하지 못함 
	@PatchMapping("/ajax/ajax12.do/{data}")
//	public String ajax12(@PathVariable(name="data") String mid, ServletResponse res) {	//하나만 보낼때, 배열로 보낼때 
	public String ajax12(
			@PathVariable(name="data") String data, 
			@RequestBody String myinfo,
			ServletResponse res) {	//JSON으로 보낼때 
		try {
			/*하나만 보낼때, 배열로 보낼때 
			String user[] = mid.split(",");
			this.logger.info(user[1]);
			this.logger.info(user[2]);
			*/
			
			this.pw = res.getWriter();
			if(data.equals("patch_myinfo")) {
				this.logger.info(myinfo);
				this.pw.write("ok");
			}else {
				this.pw.write("error");
			}
			
			
			

		} catch (Exception e) {

		}
		return null;
	}
	
	
	//@RequestPart : MultipartFile 로 받을때 사용
	//@RequestParam : name 또는 파라미터 
	//@ResponseBody + @Mapping : method 선언시 사용 
	//@ResponseBody : 응답에 대한 결과값을 해당 메소드에 바로 출력할 때 사용
	//@RequestBody : 베열값을 처리하는 어노테이션 
	@DeleteMapping("/ajax/ajax13/{key}")
//			@RequestBody String midx
	//formdata => 헤더:urlencoded,send에태워서  @RequestBody로 받
	
	//@RequestParam Map<Object,Object> midx	//방법2 
	//@RequestBody String midx 	//방법1
	   public String ajax13(ServletResponse res, 		//DELETE
	            @PathVariable(name = "key") String key,
	            @RequestBody String midx
	            //@RequestBody String midx 
	               ) {
	         try {
	            this.pw = res.getWriter();
	            if (key.equals("a123456")) {
	               this.logger.info(midx);
	               
	               this.pw.write("delete_ok");
	            } else {
	               this.pw.write("key error");
	            }
	         } catch (Exception e) {
	            this.pw.write("error");
	         }
	         return null;
	      }
	      

	//@RequestBody String data : 정상적으로 값을 받아서 출력 확인 
	@PutMapping("/ajax/ajax14/{key}")		//insert (DTO기본)
	public String ajax14(ServletResponse res, @PathVariable(name = "key") String key,
			@RequestBody String data
			) {
		try {
			this.pw = res.getWriter();
			if (key.equals("a123456")) {
				this.logger.info(data.toString());
//				this.logger.info(jo.getString("pd1"));
				this.pw.write("ok");

			} else {
				this.pw.write("key error");
			}

		} catch (Exception e) {

		}
		return null;
	}
	

}
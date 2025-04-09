package api;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//외부에서 다른도메인으로 접속시 발생하는 CORS 방지하는 어노테이션 
//origins : 접속 ip 도메인 설정 => 보안 		(실무에서는 "*"쓰면 안됨 위험 직접 설정하는것을 주로 씀)
//@CrossOrigin(origins = {"localhost:8080","http://172.30.1.19:8080","http://abc.co.kr"}, allowedHeaders = "*")	
//allowedHeaders : "POST","GET","PUT" 맵핑만 받겠다는 의미 => 보안			
@CrossOrigin(origins = "*", allowedHeaders = "*")
//주의!!! @RestController 이놈은 절대 view를 찾지 않음!! 
//그래서 return에 페이지 적어도 view로 이동하지 않음 view를 찍으려면 @Conroller사용 => view 사용 가능 단, 보안상 API View일 경우 외부에서 직접적으로 jsp 접근을 차단  
//@RestController => view를 사용하지 않음  => PrintWriter + res.setContentType 사용 

//면접 질문! @RestController와 @Controller의 차이점 : View를 사용할수있는지 여부
@Controller	
public class api_controller2 {

	private static final Logger logger = LoggerFactory.getLogger(api_controller2.class);
	PrintWriter pw = null;

	@Resource(name="api_dao")
	api_dao dao;
	
	/*
	POST, GET은 key를 @PathVariable(name = "key") String key로 받을수없음 /api_select.do/{key}형태를 못쓰기때문
	get은 그냥 url에 key를 ?로 봍여 @RequestParam("key") String key으로 받으면 됨
	post는 body에 키를 태워서 보내기  
	*/
	
	@GetMapping("/ajax/api_select.do")
	public String api_select(@RequestParam("key") String key, Model m) {
		JSONObject jo2 = null;
		try {
			this.logger.info(key);
			if (key.equals("koo")) {
				//Oracle에서 Data를 DTO를 이용하여 배열로 가져오는 코드 
				List<api_dto> all = dao.pdlist();
				this.logger.info(String.valueOf(all.size()));
				//Front-end에게 Data를 원하는 형태의 배열로 생성하여 회신 
				int w = 0;
				JSONArray ja = new JSONArray();
				while (w < all.size()) {
					JSONObject jo = new JSONObject();
					jo.put("midx", all.get(w).getMidx());
					jo.put("pd1", all.get(w).getPd1());
					jo.put("pd2", all.get(w).getPd2());
					jo.put("pd3", all.get(w).getPd3());
					jo.put("pd4", all.get(w).getPd4());
					jo.put("pd5", all.get(w).getPd5());
					ja.put(jo);
					w++;
				}
				jo2 = new JSONObject();
				jo2.put("data_all", ja);
				this.logger.info(jo2.toString());
				m.addAttribute("msg",jo2);
			} else {
				m.addAttribute("msg","{data_all:error}");	//실무형 에러메세지 : 대표키를 사용하여 에러를 찍어줌 => 배열 풀었을때 에러 나옴 
			}
		} catch (Exception e) {
			m.addAttribute("msg","{data_all:key_error}");	
		}
		return "/WEB-INF/views/api_select";		//JSON 데이터를 jsp에 출력하는 방식 API

	}
}

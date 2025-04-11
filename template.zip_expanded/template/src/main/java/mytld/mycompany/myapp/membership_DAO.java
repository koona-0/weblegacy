package mytld.mycompany.myapp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository("membership_DAO")
public class membership_DAO implements membership_mapper {

	@Resource(name="sqltemp")
	public SqlSessionTemplate st;
	
	Map<String, String> mp = null;
	private static final Logger logger = LoggerFactory.getLogger(membership_DAO.class);
	@Override
	public List<membership_DTO> id_info(String mid, String mpass) {
		//Map 생성하는 이유 : mapper.xml에서 choose을 사용하여 각 when(조건) 별로 query를 다르게 실행 시키기 위함
		this.mp = new HashMap<String, String>();
		if(mpass != "") {
			this.mp.put("part", "login");
			this.mp.put("mid", mid);
			this.mp.put("mpass", mpass);
		}
		else {
			this.mp.put("part", "myinfo");
			this.mp.put("mid", mid);
		}
		//해당 조건에 mapper값을 return받음
		List<membership_DTO> all = this.st.selectList("id_info",this.mp);
		return all;
	}
	

	@Override
	public String id_row(String mid) {
		String result = null;
		try {
			//id는 대문자 소문자를 구분한다.
			result = st.selectOne("id_row",mid);
		}catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}
	
	
	@Override
	public int join_insert(membership_DTO dto) {
		int result = 0;
		result = st.insert("join_insert",dto);
		return result;
	}
	
	
	@Override
	public int id_update(Map<String, String> map) {
		int result = this.st.update("id_update",map);
		return result;
	}
}
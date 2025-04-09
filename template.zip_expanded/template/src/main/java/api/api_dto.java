package api;

import org.springframework.stereotype.Repository;

import lombok.Data;

@Repository("api_dto")
@Data
public class api_dto {
	String pd1,pd2,pd3,pd4,pd5;
	String midx,mid,mname;
}

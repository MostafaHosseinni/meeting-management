package ir.mine.project.base.authenticate.sec.authenticate.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

public interface BaseUserDetail extends UserDetails, Serializable{
	
	List<String> getUserOperations();

}

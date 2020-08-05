package ir.mine.project.base.authenticate.conditional.config.annotation.basic;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ir.mine.project.base.authenticate.sec.authenticate.base.ErrorUrlAfterFailedAuthentication;


public class BasicErrorNavigatorHandler implements ErrorUrlAfterFailedAuthentication {

    @Override
    public Map<String, String> decideErrorUrlAfterFailedAuthentication(String baseUrl) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(UsernameNotFoundException.class.getName(), baseUrl + "?error=1");
        map.put(BadCredentialsException.class.getName(), baseUrl + "?error=1");
        map.put(AccountExpiredException.class.getName(), baseUrl + "?error=2");
        map.put(CredentialsExpiredException.class.getName(), baseUrl + "?error=2");
        map.put(DisabledException.class.getName(), baseUrl + "?error=3");
        return map;
    }

}

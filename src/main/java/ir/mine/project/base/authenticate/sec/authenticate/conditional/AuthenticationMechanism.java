package ir.mine.project.base.authenticate.sec.authenticate.conditional;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Conditional;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(AuthenticateCondition.class)
public @interface AuthenticationMechanism {
	
	AuthenticationType value() default AuthenticationType.BASIC;

}

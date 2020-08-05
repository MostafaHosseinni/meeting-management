package ir.mine.project.base.authorization.autoupdate.annot;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface SecurityUser {

	String username();
	String password();
	boolean superUser() default false;
	String[] roles();
}

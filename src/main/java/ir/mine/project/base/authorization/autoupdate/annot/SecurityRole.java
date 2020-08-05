package ir.mine.project.base.authorization.autoupdate.annot;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface SecurityRole {

	String name();
	String description();
	String[] operations();
}

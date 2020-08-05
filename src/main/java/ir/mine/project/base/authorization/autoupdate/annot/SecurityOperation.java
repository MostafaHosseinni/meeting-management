package ir.mine.project.base.authorization.autoupdate.annot;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface SecurityOperation {

	String operationEntityName();
	String displayName();
	String operationName();
	String oprationGroup();

	
}

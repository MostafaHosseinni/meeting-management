package ir.mine.project.base.excphndl.exceptionServer.db.spring;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class BaseVendorExceptionTranslator implements
		IVendorSpecificExceptionTranslator {

	protected String getTableFromQuery(String query) {
		Matcher matcher = getInsertMatcher(query);
		if (matcher.matches()) {
			return matcher.group(1);
		}
		return null;
	}

	protected Matcher getInsertMatcher(String query) {
		query = query.toLowerCase();
		Pattern compile = Pattern.compile("insert into (.*) \\(.*\\) values(.*)");
		Matcher matcher = compile.matcher(query);
		return matcher;
	}
}

package ir.mine.project.base.excphndl.db;//package vezarat.behdasht.yas.base.excphndl.db;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.support.ReloadableResourceBundleMessageSource;
//import org.springframework.stereotype.Component;
//import vezarat.behdasht.yas.base.excphndl.converter.FrameworkExceptionConverter;
//import vezarat.behdasht.yas.base.excphndl.converter.impl.BaseExceptionConverter;
//import vezarat.behdasht.yas.base.excphndl.exceptionClient.db.ClientDBException;
//import vezarat.behdasht.yas.base.excphndl.exceptionServer.db.CoreDBException;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Locale;
//
//@Component
//public class DbExceptionConverter extends BaseExceptionConverter<CoreDBException, ClientDBException> {
//
//	private ReloadableResourceBundleMessageSource messageSource;
//
//	@Autowired
//	public DbExceptionConverter(
//			FrameworkExceptionConverter frameworkExceptionConverter) {
//		super(frameworkExceptionConverter);
//	}
//
//	@Override
//	public ClientDBException convertException(CoreDBException serverException) {
//		ClientDBException convertException = super.convertException(serverException);
//		List<String> propertyNames = serverException.getPropertyNames();
//		String entityName = serverException.getEntityName();
//		String entityResolved = getI18nValue(entityName);
//		List<String> propertyNamesResolved = new ArrayList<String>();
//		for (String propertyName : propertyNames) {
//			propertyNamesResolved.add(getI18nValue(entityName + "." + propertyName));
//		}
//		convertException.setI18nArgs(new String[]{propertyNamesResolved.toString(), entityResolved});
//		return convertException;
//	}
//
//	private String getI18nValue(String entityName) {
//		return messageSource.getMessage(entityName, new Object[]{}, new Locale("fa", "IR"));
//	}
//
//	@Override
//	protected String getI18nKey(CoreDBException serverException) {
//		switch (serverException.getType()) {
//		case uniqueConstraint:
//			return "uniqueConstraint";
//
//		default:
//			break;
//		}
//		return null;
//	}
//
//	@Autowired
//	public void setMessageSource(
//			ReloadableResourceBundleMessageSource messageSource) {
//		this.messageSource = messageSource;
//	}
//}

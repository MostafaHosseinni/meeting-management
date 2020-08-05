package ir.mine.project.base.excphndl.basebussiness;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ir.mine.project.base.excphndl.converter.FrameworkExceptionConverter;
import ir.mine.project.base.excphndl.converter.impl.BaseExceptionConverter;
import ir.mine.project.base.excphndl.exceptionClient.BaseClientException;
import ir.mine.project.base.excphndl.exceptionServer.BaseServerBusinessException;

@Component
public class BaseBussinessExceptionConverter extends
		BaseExceptionConverter<BaseServerBusinessException, BaseClientException> {

	@Autowired
	public BaseBussinessExceptionConverter(
			FrameworkExceptionConverter frameworkExceptionConverter) {
		super(frameworkExceptionConverter);
	}

	@Override
	protected String getI18nKey(BaseServerBusinessException serverException) {
		return serverException.getMessageKey();
	}
	
}

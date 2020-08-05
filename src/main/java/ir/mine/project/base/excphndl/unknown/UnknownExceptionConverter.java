package ir.mine.project.base.excphndl.unknown;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ir.mine.project.base.excphndl.converter.FrameworkExceptionConverter;
import ir.mine.project.base.excphndl.converter.impl.BaseExceptionConverter;
import ir.mine.project.base.excphndl.exceptionClient.BaseClientException;

@Component
public class UnknownExceptionConverter extends
        BaseExceptionConverter<Throwable, BaseClientException> {

    @Autowired
    public UnknownExceptionConverter(
            FrameworkExceptionConverter frameworkExceptionConverter) {
        super(frameworkExceptionConverter);
    }

    @Override
    protected String getI18nKey(Throwable serverException) {
        return "exc.unknown";
    }

}

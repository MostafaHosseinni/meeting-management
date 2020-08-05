package ir.mine.project.base.excphndl.converter;

import ir.mine.project.base.excphndl.exceptionClient.BaseClientException;

public interface IExceptionConverter<SE extends Throwable, CE extends BaseClientException> {

    CE convertException(SE serverException);

}

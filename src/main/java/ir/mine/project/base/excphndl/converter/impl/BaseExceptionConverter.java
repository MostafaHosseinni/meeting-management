package ir.mine.project.base.excphndl.converter.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;

import ir.mine.project.base.excphndl.converter.FrameworkExceptionConverter;
import ir.mine.project.base.excphndl.converter.IExceptionConverter;
import ir.mine.project.base.excphndl.exceptionClient.BaseClientException;

public abstract class BaseExceptionConverter<SE extends Throwable, CE extends BaseClientException> implements IExceptionConverter<SE, CE> {

    protected Class<SE> serverExcClass;
    protected Class<CE> clientExcClass;

    @SuppressWarnings("unchecked")
    public BaseExceptionConverter(FrameworkExceptionConverter frameworkExceptionConverter) {
        if (getClass().getGenericSuperclass() instanceof ParameterizedType) {
            ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
            if (genericSuperclass != null && genericSuperclass.getActualTypeArguments() != null && genericSuperclass.getActualTypeArguments().length > 0) {
                if (genericSuperclass.getActualTypeArguments()[0] instanceof Class) {
                    serverExcClass = (Class<SE>) genericSuperclass.getActualTypeArguments()[0];
                    clientExcClass = (Class<CE>) genericSuperclass.getActualTypeArguments()[1];
                }
            }
        }
        frameworkExceptionConverter.registerExceptionConverter(serverExcClass, this);
    }

    @Override
    public CE convertException(SE serverException) {
        String i18nKey = getI18nKey(serverException);
        CE clientException = createClientException(serverException, i18nKey);
        return clientException;
    }

    protected abstract String getI18nKey(SE serverException);

    protected CE createClientException(SE serverException, String i18nKey) {
        try {
            Constructor<CE> constructor = clientExcClass.getConstructor(String.class);
            CE newInstance = constructor.newInstance(i18nKey);
            return newInstance;
        } catch (Exception e) {
            return null;
        }
    }

}

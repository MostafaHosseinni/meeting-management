package ir.mine.project.base.excphndl.converter;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ir.mine.project.base.excphndl.exceptionClient.BaseClientException;

@Component
public class FrameworkExceptionConverter {

    private static Logger EXCEPTION_LOGGER = LoggerFactory.getLogger("ExceptionLogger");
    private Map<Class<? extends Throwable>, IExceptionConverter<?, ?>> exceptionConverterMap = new HashMap<Class<? extends Throwable>, IExceptionConverter<?, ?>>();

    public FrameworkExceptionConverter() {
    }

    @SuppressWarnings("unchecked")
    public BaseClientException convertException(Throwable exception) {
        EXCEPTION_LOGGER.error("A General Exception arriving at FrameworkExceptionConverter", exception);
        Class<?> excClass = exception.getClass();
        IExceptionConverter<Throwable, BaseClientException> exceptionConverter = (IExceptionConverter<Throwable, BaseClientException>) exceptionConverterMap.get(excClass);
        while (exceptionConverter == null) {
            excClass = excClass.getSuperclass();
            exceptionConverter = (IExceptionConverter<Throwable, BaseClientException>) exceptionConverterMap.get(excClass);
        }
        BaseClientException clientException = exceptionConverter.convertException(exception);
        return clientException;
    }

    public void registerExceptionConverter(Class<? extends Throwable> serverExceptionClass, IExceptionConverter<?, ?> exceptionConverter) {
        exceptionConverterMap.put(serverExceptionClass, exceptionConverter);
    }

}

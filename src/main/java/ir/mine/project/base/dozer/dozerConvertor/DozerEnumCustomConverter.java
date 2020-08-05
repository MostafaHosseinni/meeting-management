package ir.mine.project.base.dozer.dozerConvertor;

import java.lang.reflect.Method;

import org.dozer.CustomConverter;
import org.springframework.stereotype.Component;

import ir.mine.project.base.dto.IHasDisplayName;

@Component
public class DozerEnumCustomConverter implements CustomConverter {

    @Override
    public Object convert(Object destination, Object source, Class<?> destinationClass, Class<?> sourceClass) {
        if (source == null)
            return null;
        if (source instanceof Enum<?>) {
            Enum<?> e = (Enum<?>) source;
            if (e instanceof IHasDisplayName) {
                IHasDisplayName display = (IHasDisplayName) e;
                return display.displayName();
            }
            return e.toString();
        } else if (source instanceof String) {
            try {
                Method method = destinationClass.getMethod("values");
                Object[] enumValues = (Object[]) method.invoke(null);
                for (int i = 0; i < enumValues.length; i++) {
                    Object object = enumValues[i];
                    if (object instanceof IHasDisplayName) {
                        IHasDisplayName display = (IHasDisplayName) object;
                        if (display.displayName().equals((String) source))
                            return display;
                    } else if (enumValues[i].toString().equals((String) source)) {
                        return enumValues[i];
                    }
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        return null;
    }

}

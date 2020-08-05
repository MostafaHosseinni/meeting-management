package ir.mine.project.base.dozer.dozerConvertor;

import org.dozer.CustomConverter;

import ir.mine.project.base.Util.translate.TranslateUtil;

public class DozerStringConverter implements CustomConverter {

    @Override
    public Object convert(Object existingDestinationFieldValue,
                          Object sourceFieldValue, Class<?> destinationClass,
                          Class<?> sourceClass) {
        if (sourceFieldValue == null)
            return null;
        if (sourceFieldValue instanceof String) {
            return TranslateUtil.translateToPersian((String) sourceFieldValue);
        }
        return null;
    }

}

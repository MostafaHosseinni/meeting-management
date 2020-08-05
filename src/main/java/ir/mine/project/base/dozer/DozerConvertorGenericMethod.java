package ir.mine.project.base.dozer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class DozerConvertorGenericMethod<P, K> extends DozerConvertorGeneric<P, K> {


    public <E, T> E convertEntityToDTOMethod(T source, Class<E> dest) {
        E obj = null;
        if (source != null && dest != null) {
            obj = mapper.map(source, dest);
        }
        return obj;
    }

    public <E, T> T convertDTOToEntityMethod(E source, Class<T> dest) {
        T obj = null;
        if (source != null && dest != null) {
            obj = mapper.map(source, dest);
        }
        return obj;
    }

    public <E, T> List<E> convertListEntityToDTOMethod(List<T> sources, Class<E> dest) {
        List<E> retList = new ArrayList<>();
        if (sources != null && sources.size() > 0 && dest != null) {

            for (T object : sources) {
                retList.add(convertEntityToDTOMethod(object, dest));
            }
        }
        return retList;
    }

    public <E, T> List<T> convertListDTOToEntityMethod(List<E> sources, Class<T> dest) {
        List<T> retList = new ArrayList<>();
        if (sources != null && sources.size() > 0 && dest != null) {
            for (E object : sources) {
                retList.add(convertDTOToEntityMethod(object, dest));
            }
        }
        return retList;
    }


}

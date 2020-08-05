package ir.mine.project.base.dozer;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DozerConvertorGeneric<T,E> {

    @Autowired
    protected Mapper mapper;

    public E convertEntityToDTO(T source, Class<?> dest) {
        Object obj = null;
        if (source != null && dest != null) {
            obj =  mapper.map(source, dest);
        }
        return (E) obj;
    }

    public E convertEntityToDTO(T source) {
        Object obj = null;
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        Class<?> DTOClass = (Class) pt.getActualTypeArguments()[1];
        if (source != null) {
            obj = mapper.map(source, DTOClass);
        }
        return (E) obj;
    }

    public T convertDTOToEntity(E source, Class<?> dest) {
        T obj = null;
        if (source != null && dest != null) {
            obj = (T) mapper.map(source, dest);
        }
        return obj;
    }

    public T convertDTOToEntity(E source) {
        T obj = null;
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        Class<?> DTOClass = (Class) pt.getActualTypeArguments()[0];
        if (source != null) {
            obj = (T) mapper.map(source, DTOClass);
        }
        return obj;
    }

    public List<E> convertListEntityToDTO(List<T> sources, Class<?> dest) {
        List<E> retList = new ArrayList<E>();
        if (sources != null && sources.size() > 0 && dest != null) {

            for (T object : sources) {
                retList.add(convertEntityToDTO(object, dest));
            }
        }
        return retList;
    }

    public List<E> convertListEntityToDTO(List<T> sources) {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        List<E> retList = new ArrayList<E>();
        Class<?> DTOClass = (Class) pt.getActualTypeArguments()[1];
        if (sources != null && sources.size() > 0 && DTOClass != null) {

            for (T object : sources) {
                retList.add(convertEntityToDTO(object, DTOClass));
            }
        }
        return retList;
    }

    public List<T> convertListDTOToEntity(List<E> sources, Class<?> dest) {
        List<T> retList = new ArrayList<T>();
        if (sources != null && sources.size() > 0 && dest != null) {
            for (E object : sources) {
                retList.add(convertDTOToEntity(object, dest));
            }
        }
        return retList;
    }

    public List<T> convertListDTOToEntity(List<E> sources) {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        Class<?> DTOClass = (Class) pt.getActualTypeArguments()[1];
        List<T> retList = new ArrayList<T>();
        if (sources != null && sources.size() > 0 && DTOClass != null) {

            for (E object : sources) {
                retList.add(convertDTOToEntity(object, DTOClass));
            }
        }
        return retList;
    }
}

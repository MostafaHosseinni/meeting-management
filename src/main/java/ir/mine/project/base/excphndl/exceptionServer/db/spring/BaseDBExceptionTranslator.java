package ir.mine.project.base.excphndl.exceptionServer.db.spring;

import java.lang.reflect.ParameterizedType;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.PersistenceExceptionTranslator;

import ir.mine.project.base.excphndl.exceptionServer.db.CoreDBException;

public abstract class BaseDBExceptionTranslator<Exc extends RuntimeException> implements PersistenceExceptionTranslator {

    protected SessionFactory sessionFactory;
    protected String dbDriver;
    protected Class<Exc> excClass;
    protected DB db;
    @SuppressWarnings("unchecked")
    public BaseDBExceptionTranslator() {
        if (getClass().getGenericSuperclass() instanceof ParameterizedType) {
            ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
            if (genericSuperclass != null && genericSuperclass.getActualTypeArguments() != null && genericSuperclass.getActualTypeArguments().length > 0) {
                if (genericSuperclass.getActualTypeArguments()[0] instanceof Class) {
                    excClass = (Class<Exc>) genericSuperclass.getActualTypeArguments()[0];
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public DataAccessException translateExceptionIfPossible(RuntimeException ex) {
        if (excClass.isAssignableFrom(ex.getClass())) {
            Exc exc = (Exc) ex;
            return translateException(exc);
        }
        return null;
    }

    protected abstract CoreDBException translateException(Exc exc);

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Value("${jdbc.connection.driver_class}")
    public void setDbDriver(String dbDriver) {
        this.dbDriver = dbDriver;
        if (dbDriver.contains("oracle")) {
            db = DB.oracle;
        } else if (dbDriver.contains("postgresql")) {
            db = DB.postgresql;
        }
    }

    public enum DB {
        oracle, postgresql
    }
}

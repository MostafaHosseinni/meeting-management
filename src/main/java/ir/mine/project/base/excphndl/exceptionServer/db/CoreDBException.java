package ir.mine.project.base.excphndl.exceptionServer.db;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;

import ir.mine.project.base.excphndl.exceptionServer.db.type.DBExceptionType;

public class CoreDBException extends DataAccessException {
    private static final long serialVersionUID = 1L;

    private DBExceptionType type;
    private String entityName;
    private String tableName;
    private List<String> columnNames;
    private List<String> propertyNames;

    public CoreDBException(String msg) {
        super(msg);
    }

    public CoreDBException(SQLException exc) {
        this(exc.getMessage(), exc);
    }

    public CoreDBException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public DBExceptionType getType() {
        return type;
    }

    public void setType(DBExceptionType type) {
        this.type = type;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
    }

    public List<String> getPropertyNames() {
        return propertyNames;
    }

    public void setPropertyNames(List<String> propertyNames) {
        this.propertyNames = propertyNames;
    }

}

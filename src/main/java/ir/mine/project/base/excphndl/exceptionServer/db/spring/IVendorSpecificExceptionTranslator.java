package ir.mine.project.base.excphndl.exceptionServer.db.spring;

import java.sql.SQLException;

import ir.mine.project.base.excphndl.exceptionServer.db.CoreDBException;

public interface IVendorSpecificExceptionTranslator {

    CoreDBException translate(String task, String sql, SQLException ex);

}

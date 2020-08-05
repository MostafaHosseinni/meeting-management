package ir.mine.project.base.excphndl.exceptionClient.db;

import ir.mine.project.base.excphndl.exceptionClient.BaseClientException;

public class ClientDBException extends BaseClientException {

    public ClientDBException() {
    }

    public ClientDBException(String i18nKey) {
        super(i18nKey);
    }


}

package ir.mine.project.base.excphndl.exceptionServer.db.type;

import ir.mine.project.base.dto.empty.IBaseEmptyDTO;

public enum DBExceptionType implements IBaseEmptyDTO {

    notNull, uniqueConstraint, tooLarge, hasChild, other;

    @Override
    public String getKey() {
        return toString();
    }
}

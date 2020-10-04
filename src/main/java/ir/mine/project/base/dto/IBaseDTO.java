package ir.mine.project.base.dto;

import java.io.Serializable;

public interface IBaseDTO<PK> extends Serializable {

	public PK getId();
}

package ir.mine.project.base.dto.empty;

public class BaseEmptyDTO implements IBaseEmptyDTO {

	private static final long serialVersionUID = 1L;

	@Override
	public String getKey() {
		return toString();
	}

}

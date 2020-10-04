package ir.mine.project.domain.enumeration;

import ir.mine.project.base.dto.IHasDisplayName;

/**
 * The ProfileType enumeration.
 */
public enum ProfileType implements IHasDisplayName {
	ADMIN, SECRATERY, INVITEES;

	@Override
	public String displayName() {
		return toString();
	}

	@Override
	public String toString() {
		switch (this) {
		case ADMIN:
			return "مدیر سیستم";
		case SECRATERY:
			return "تنظیم کننده جلسه";
		case INVITEES:
			return "مدعو";
		default:
			break;
		}
		return super.toString();
	}
}
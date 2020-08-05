package ir.mine.project.base.dozer.dozerConvertor;

import java.time.ZonedDateTime;

import org.dozer.CustomConverter;

public class DozerDateCustomConverterInteger
		/* extends DozerConverter<String , ZonedDateTime> { */ implements CustomConverter {

	@Override
	public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass,
			Class<?> sourceClass) {
		if (sourceFieldValue == null)
			return null;
		if (sourceFieldValue instanceof ZonedDateTime) {
			ZonedDateTime e = (ZonedDateTime) sourceFieldValue;
			return e.getDayOfWeek().ordinal();
		}

		return null;
	}

//	public DozerDateCustomConverterGeneric() {
//		super(String.class,ZonedDateTime.class);
//	}
//
//	@Override
//	public ZonedDateTime convertTo(String s, ZonedDateTime zonedDateTime) {
//		String dateString = (String) s;
//		if(dateString !=null) {
//			try {
//				return CalenderUtil.getTimeZoned(dateString);
//			} catch (DateFormatException e) {
//				e.printStackTrace();
//				return  null;
//			}
//		}else {
//			return null;
//		}
//	}
//
//	@Override
//	public String convertFrom(ZonedDateTime zonedDateTime, String s) {
//		if (zonedDateTime != null) {
//			ZonedDateTime e = (ZonedDateTime) zonedDateTime;
//			return CalenderUtil.dateToJalaliCalendar(e);
//		}
//		else {
//			return null;
//		}
//	}

}

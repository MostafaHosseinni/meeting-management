package ir.mine.project.base.Util.date;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import fi.joensuu.joyds1.calendar.JalaliCalendar;

public class CalenderUtil {

	public static final int ONE_SECOND = 1000;
	public static final int ONE_MINUTE = 60 * ONE_SECOND;
	public static final int ONE_HOUR = 60 * ONE_MINUTE;
	public static final long ONE_DAY = 24 * ONE_HOUR;
	public static final long ONE_WEEK = 7 * ONE_DAY;

	public static Date getTime(String date) throws DateFormatException {
		DateWrapper wrapper = validateDate(date);
		wrapper.setHour(0);
		wrapper.setMinute(0);
		wrapper.setSecond(0);
		return new Date(hijriDateToLong(wrapper));
	}

	public static ZonedDateTime getTimeZoned(String date) throws DateFormatException {

		System.out.println("Date : " + date);
		// Get system default time zone id.
		ZoneId defaultZoneId = ZoneId.systemDefault();
		// Convert Date to Instant.
		Instant instant = getTime(date).toInstant();
		// Instant + default time zone.
		return instant.atZone(defaultZoneId);
	}

	public static Date getFirstDayOfMonth(Date date) throws DateFormatException {
		return getFirstDayOfMonth(dateToJalaliCalendar(date));
	}

	public static Date getFirstDayOfMonth(String date) throws DateFormatException {
		DateWrapper wrapper = validateDate(date);
		wrapper.setHour(0);
		wrapper.setMinute(0);
		wrapper.setSecond(0);
		wrapper.setDay(1);
		return new Date(hijriDateToLong(wrapper));
	}

	public static Date getTime(String date, String time) throws DateFormatException {
		DateWrapper wrapper = validateDate(date);
		DateWrapper timeWrapper = validateTime(time);
		wrapper.setHour(timeWrapper.getHour());
		wrapper.setMinute(timeWrapper.getMinute());
		wrapper.setSecond(timeWrapper.getSecond());
		return new Date(hijriDateToLong(wrapper));
	}

	@SuppressWarnings("deprecation")
	public static Date getFirstDayWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int i = c.get(Calendar.DAY_OF_WEEK);
		c.add(Calendar.DATE, -(i % 7));
		c.set(Calendar.DAY_OF_WEEK, 7);

		Date firstDayOfWeek = new Date(c.getTimeInMillis());

		firstDayOfWeek.setHours(0);
		firstDayOfWeek.setMinutes(0);
		firstDayOfWeek.setSeconds(0);

		return firstDayOfWeek;
	}

	@SuppressWarnings("deprecation")
	public static Date getLastDayWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int i = c.get(Calendar.DAY_OF_WEEK);
		c.add(Calendar.DATE, 6 - (i % 7));

		Date lastDayOfWeek = new Date(c.getTimeInMillis());
		lastDayOfWeek.setHours(23);
		lastDayOfWeek.setMinutes(59);
		lastDayOfWeek.setSeconds(59);

		return lastDayOfWeek;
	}

	public static int getDayOfWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int i = c.get(Calendar.DAY_OF_WEEK);
		return i % 7;
	}

	public static String dateToJalaliCalendar(Date date) {
		if (date == null)
			return "_";
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTimeInMillis(date.getTime());
		JalaliCalendar calendar = new JalaliCalendar(gc);

		StringBuffer buffer = new StringBuffer();
		if (calendar.getYear() % 10 == calendar.getYear())
			buffer.append("0");
		buffer.append(calendar.getYear());
		buffer.append("-");
		if (calendar.getMonth() % 10 == calendar.getMonth())
			buffer.append("0");
		buffer.append(calendar.getMonth());
		buffer.append("-");
		if (calendar.getDay() % 10 == calendar.getDay())
			buffer.append("0");
		buffer.append(calendar.getDay());
		return buffer.toString().replace("-", "/");
	}

	public static String dateToJalaliCalendar(ZonedDateTime date) {
		// Convert Instant to Date.
		return dateToJalaliCalendar(Date.from(date.toInstant()));
	}

	public static String getHijriFullTime(Date date) {
		if (date == null)
			return "_";
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTimeInMillis(date.getTime());
		JalaliCalendar calendar = new JalaliCalendar(gc);
		StringBuffer buffer = new StringBuffer();

		if (calendar.getYear() % 10 == calendar.getYear())
			buffer.append("0");
		buffer.append(calendar.getYear());
		buffer.append("-");
		if (calendar.getMonth() % 10 == calendar.getMonth())
			buffer.append("0");
		buffer.append(calendar.getMonth());
		buffer.append("-");
		if (calendar.getDay() % 10 == calendar.getDay())
			buffer.append("0");
		buffer.append(calendar.getDay());

		buffer.append(",");
		buffer.append(" ");
		if (gc.get(Calendar.HOUR_OF_DAY) % 10 == gc.get(Calendar.HOUR_OF_DAY))
			buffer.append("0");
		buffer.append(gc.get(Calendar.HOUR_OF_DAY));
		buffer.append(":");
		if (gc.get(Calendar.MINUTE) % 10 == gc.get(Calendar.MINUTE))
			buffer.append("0");
		buffer.append(gc.get(Calendar.MINUTE));
		buffer.append(":");
		if (gc.get(Calendar.SECOND) % 10 == gc.get(Calendar.SECOND))
			buffer.append("0");
		buffer.append(gc.get(Calendar.SECOND));
		return buffer.toString().replace("-", "/");
	}

	/**
	 * @author zangeneh
	 * @param date
	 * @return String Date jalali + Time
	 */
	public static String fullDateToJalaliCalendar(Date date) {
		if (date == null)
			return "_";
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTimeInMillis(date.getTime());
		JalaliCalendar calendar = new JalaliCalendar(gc);

		StringBuffer buffer = new StringBuffer();
		if (calendar.getYear() % 10 == calendar.getYear())
			buffer.append("0");
		buffer.append(calendar.getYear());
		buffer.append("-");
		if (calendar.getMonth() % 10 == calendar.getMonth())
			buffer.append("0");
		buffer.append(calendar.getMonth());
		buffer.append("-");
		if (calendar.getDay() % 10 == calendar.getDay())
			buffer.append("0");
		buffer.append(calendar.getDay());
		buffer.append(" ");
		buffer.append(date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds());
		return buffer.toString().replace("-", "/");
	}

	public static String fullDateToJalaliCalendar(ZonedDateTime date) {
		return fullDateToJalaliCalendar(Date.from(date.toInstant()));
	}

	private static Long hijriDateToLong(DateWrapper wrapper) throws DateFormatException {
		try {
			JalaliCalendar jc = new JalaliCalendar(wrapper.getYear(), wrapper.getMonth(), wrapper.getDay());
			GregorianCalendar gc = jc.toJavaUtilGregorianCalendar();
			gc.set(Calendar.HOUR_OF_DAY, wrapper.getHour());
			gc.set(Calendar.MINUTE, wrapper.getMinute());
			gc.set(Calendar.SECOND, wrapper.getSecond());
			return gc.getTimeInMillis();
		} catch (Exception e) {
			throw new DateFormatException(e.getMessage());
		}
	}

	private static DateWrapper validateDate(String date) throws DateFormatException {
		String formatMessage = "Date Format is not correct. expected format is YYYY/MM/dd";
		String contentMessage = "value is not numeric";
		Integer value = 0;

		DateWrapper result = new DateWrapper();

		if (date == null || date.trim().equals("")) {
			throw new DateFormatException(formatMessage);
		}
		date = date.trim();
		StringTokenizer tokenizer = new StringTokenizer(date, "/");
		int cnt = tokenizer.countTokens();
		if (cnt != 3) {
			throw new DateFormatException(formatMessage);
		}
		String year = tokenizer.nextToken();
		if (year.length() != 4) {
			throw new DateFormatException(formatMessage);
		}
		try {
			value = Integer.parseInt(year);
			result.setYear(value);
		} catch (Exception e) {
			throw new DateFormatException("Year" + " " + contentMessage);
		}
		String month = tokenizer.nextToken();
		if (month.length() != 2) {
			throw new DateFormatException(formatMessage);
		}
		try {
			value = Integer.parseInt(month);
			result.setMonth(value);
		} catch (Exception e) {
			throw new DateFormatException("Month" + " " + contentMessage);
		}
		String day = tokenizer.nextToken();
		if (day.length() != 2) {
			throw new DateFormatException(formatMessage);
		}
		try {
			value = Integer.parseInt(day);
			result.setDay(value);
		} catch (Exception e) {
			throw new DateFormatException("Day" + " " + contentMessage);
		}
		return result;
	}

	private static DateWrapper validateTime(String time) throws DateFormatException {
		String formatMessage = "Time Format is not correct. expected format is hh:mm:ss";
		String contentMessage = "value is not numeric";
		Integer value = 0;

		DateWrapper result = new DateWrapper();

		if (time == null || time.trim().equals("")) {
			throw new DateFormatException(formatMessage);
		}
		time = time.trim();
		StringTokenizer tokenizer = new StringTokenizer(time, ":");
		int cnt = tokenizer.countTokens();
		if (cnt != 3) {
			throw new DateFormatException(formatMessage);
		}
		String hour = tokenizer.nextToken();
		if (hour.length() != 2) {
			throw new DateFormatException(formatMessage);
		}
		try {
			value = Integer.parseInt(hour);
			result.setHour(value);
		} catch (Exception e) {
			throw new DateFormatException("Hour" + " " + contentMessage);
		}
		String minute = tokenizer.nextToken();
		if (minute.length() != 2) {
			throw new DateFormatException(formatMessage);
		}
		try {
			value = Integer.parseInt(minute);
			result.setMinute(value);
		} catch (Exception e) {
			throw new DateFormatException("Minute" + " " + contentMessage);
		}
		String second = tokenizer.nextToken();
		if (second.length() != 2) {
			throw new DateFormatException(formatMessage);
		}
		try {
			value = Integer.parseInt(second);
			result.setSecond(value);
		} catch (Exception e) {
			throw new DateFormatException("Second" + " " + contentMessage);
		}
		return result;
	}

	public static Date getPreviousDayDate(Date date) {
		long time = date.getTime();
		time -= ONE_DAY;
		return new Date(time);
	}

	public static Date getNextDayDate(Date date) {
		long time = date.getTime();
		time += ONE_DAY;
		return new Date(time);
	}

	public static Date getDifferentDate(Date date, int day) {
		long time = date.getTime();
		time += (day * ONE_DAY);
		return new Date(time);
	}

	public static int getDifferentDate(Date date1, Date date2) {
		Long time1 = date1.getTime();
		Long time2 = date2.getTime();
		Long time = time2 - time1;
		int day = (int) (time / ONE_DAY);
		return day;
	}

	public static void main(String[] args) {
		System.out.println(new Date().getTime());
	}

	public static Date getDayStart(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Date getDayEnd(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	public static ZonedDateTime getDayStart(ZonedDateTime date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(Date.from(date.toInstant()));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date.from(java.time.ZonedDateTime.now().toInstant());

		return calendar.getTime().toInstant().atZone(date.getZone());
	}

	public static ZonedDateTime getDayEnd(ZonedDateTime date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(Date.from(date.toInstant()));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime().toInstant().atZone(date.getZone());
	}
}

class DateWrapper {

	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private int second;

	public DateWrapper() {
	}

	public DateWrapper(int year, int month, int day, int hour, int minute, int second) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}

	public DateWrapper(int year, int month, int day) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = 0;
		this.minute = 0;
		this.second = 0;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}
}

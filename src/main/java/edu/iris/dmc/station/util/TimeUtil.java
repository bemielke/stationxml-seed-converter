package edu.iris.dmc.station.util;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import edu.iris.dmc.seed.BTime;

public class TimeUtil {

	public static XMLGregorianCalendar toCalendar(Date date) throws DatatypeConfigurationException {
		GregorianCalendar c = new GregorianCalendar();
		c.setTimeZone(TimeZone.getTimeZone("GMT"));
		c.setTime(date);
		return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
	}

	public static XMLGregorianCalendar toCalendar(BTime bTime) throws DatatypeConfigurationException {
		GregorianCalendar c = new GregorianCalendar();
		c.setTimeZone(TimeZone.getTimeZone("GMT"));
		c.set(Calendar.YEAR, bTime.getYear());

		c.set(Calendar.DAY_OF_YEAR, bTime.getDayOfYear());
		c.set(Calendar.HOUR_OF_DAY, bTime.getHour());
		c.set(Calendar.MINUTE, bTime.getMinute());
		c.set(Calendar.SECOND, bTime.getSecond());
		c.set(Calendar.MILLISECOND, bTime.getTenthMilliSecond());

		return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
	}

	public static ZonedDateTime toZonedDateTime(BTime bTime) throws DatatypeConfigurationException {
		if (bTime == null) {
			return null;
		}

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy,DDD,HH:mm:ss.nZ");
		String date = bTime.toSeedString() + "00000+0000";

		return ZonedDateTime.parse(date, dateTimeFormatter);
	}

	public static BTime toBTime(ZonedDateTime time) throws DatatypeConfigurationException {
		if (time == null) {
			return null;
		}
		ZonedDateTime utc = time.withZoneSameInstant(ZoneId.of("UTC"));

		return new BTime(utc.getYear(), utc.getDayOfYear(), utc.getHour(), utc.getMinute(), utc.getSecond(),
				utc.get(ChronoField.MILLI_OF_SECOND));
	}

	public static XMLGregorianCalendar now() throws DatatypeConfigurationException {
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTimeZone(TimeZone.getTimeZone("GMT"));
		DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
		return datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
	}
}

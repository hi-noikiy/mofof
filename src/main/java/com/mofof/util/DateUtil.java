package com.mofof.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/** Utility library for working with {@link Date}s. */
public final class DateUtil {

  private DateUtil() {
  }

  /**
   * Returns the {@link Date} of midnight at the start of the given {@link Date}.
   *
   * <p>
   * This returns a {@link Date} formed from the given {@link Date} at the time of
   * midnight, 00:00, at the start of this {@link Date}.
   *
   * @return the {@link Date} of midnight at the start of the given {@link Date}
   */
  public static LocalDateTime atStartOfDay(Date date) {
    LocalDateTime localDateTime = dateToLocalDateTime(date);
    LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
    return startOfDay;
  }

  public static LocalDateTime atStartOfDay(String date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    LocalDateTime startOfDay = LocalDate.parse(date, formatter).atTime(LocalTime.MIN);
    return startOfDay;
  }

  /**
   * Returns the {@link Date} at the end of day of the given {@link Date}.
   *
   * <p>
   * This returns a {@link Date} formed from the given {@link Date} at the time of
   * 1 millisecond prior to midnight the next day.
   *
   * @return the {@link Date} at the end of day of the given {@link Date}j
   */
  public static LocalDateTime atEndOfDay(Date date) {
    LocalDateTime localDateTime = dateToLocalDateTime(date);
    LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
    return endOfDay;
  }

  public static LocalDateTime atEndOfDay(String date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    LocalDateTime endOfDay = LocalDate.parse(date, formatter).atTime(LocalTime.MAX);
    return endOfDay;
  }

  /**
   * 
   * @param yearMonth
   * @return
   */
  public static LocalDateTime atStartOfMonth(String yearMonth) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
    LocalDate localDate = YearMonth.parse(yearMonth, formatter).atDay(1);
    return localDate.atTime(LocalTime.MIN);
  }

  public static LocalDateTime atEndOfMonth(String yearMonth) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
    LocalDate localDate = YearMonth.parse(yearMonth, formatter).atEndOfMonth();
    return localDate.atTime(LocalTime.MAX);
  }

  private static LocalDateTime dateToLocalDateTime(Date date) {
    return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
  }

  static Date localDateTimeToDate(LocalDateTime localDateTime) {
    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
  }

  /**
   * 格式化时间
   */
  public static LocalDate getLocalDate(String dateStr){
    if(dateStr==null||"".equals(dateStr)){
      return null;
    }
    try {
      DateFormat df = null;
      //根据长度判断下时间格式
      if(dateStr.length()>12){
        df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      }else{
        df = new SimpleDateFormat("yyyy-MM-dd");
      }
      Date date = df.parse(dateStr);
      return getLocalDateByDate(date);
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * 将date类型转成 LocalDate
   * @param date
   * @return
   */
  public static LocalDate getLocalDateByDate(Date date){
    Instant instant = date.toInstant();
    ZoneId zoneId = ZoneId.systemDefault();
    LocalDate localDate = instant.atZone(zoneId).toLocalDate();
    return localDate;
  }

  public static void main(String[] args) throws ParseException {

    LocalDateTime ldt = atStartOfDay("20190508");
    System.out.println(ldt);
    LocalDateTime ldt2 = atEndOfDay("20190507");
    System.out.println(ldt2);

    LocalDateTime ldt3 = atStartOfMonth("201905");
    System.out.println(ldt3);
    LocalDateTime ldt4 = atEndOfMonth("201905");
    System.out.println(ldt4);


  }
}

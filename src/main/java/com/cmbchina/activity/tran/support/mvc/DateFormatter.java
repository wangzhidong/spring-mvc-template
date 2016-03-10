package com.cmbchina.activity.tran.support.mvc;

/**
 * Created by wangtingbang on 16/3/10.
 */

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;
import com.cmbchina.commons.util.DateTimeUtils;

/**
 *
 * @author dave
 *
 */
public class DateFormatter implements Formatter<Date> {

  @Override
  public String print(Date arg0, Locale arg1) {
    return DateTimeUtils.fromDateTime(arg0);
  }

  @Override
  public Date parse(String arg0, Locale arg1) throws ParseException {
    Date date = null;
    try {
      date = DateTimeUtils.toDate(arg0);
    } catch (Exception e) {
      date = DateTimeUtils.toDateTime(arg0);
    }
    return date;
  }

}

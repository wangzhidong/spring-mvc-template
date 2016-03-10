package com.cmbchina.activity.tran.support.mvc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

/**
 * Created by wangtingbang on 16/3/10.
 */
public class TranFastJsonHttpMessageConverter extends FastJsonHttpMessageConverter{

  @Override
  protected void writeInternal(Object obj, HttpOutputMessage outputMessage) throws IOException,
    HttpMessageNotWritableException {
    OutputStream out = outputMessage.getBody();
    SerializeConfig sc = new SerializeConfig();
    sc.put(Date.class, new SimpleDateFormatSerializer("yyyy/MM/dd HH:mm:ss:SSS"));
//    String text = JSON.toJSONString(obj, super.getFeatures());
    String text = JSON.toJSONString(obj, sc, super.getFeatures());
    byte[] bytes = text.getBytes(super.getCharset());
    out.write(bytes);
  }
}

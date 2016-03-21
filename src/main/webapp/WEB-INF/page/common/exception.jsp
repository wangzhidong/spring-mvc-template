<%-- <%@page import="com.jq.p2p.op.support.mvc.OpApiCode"%> --%>
<%@page import="com.cmbchina.commons.util.JsonUtil"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.cmbchina.commons.bean.BusinessException"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Exception ex = (Exception) request.getAttribute("ex");
	BusinessException e = null;
	if(ex instanceof BusinessException){
	  e = (BusinessException) ex;
	}else{
	  /* e = new BusinessException(OpApiCode.UNKNOWN_ERROR.getCode()); */
	  e = new BusinessException("999999");
	}
	Map m = new HashMap();
	m.put("code", e.getCode());
	if(StringUtils.isNotBlank(e.getMessage())){
	  m.put("message", e.getMessage());
	}
	m.put("data","{}");
	String json = JsonUtil.toJson(m);
%>
<%=json%>

<%@ page contentType="text/html; charset=utf-8" import="java.net.*"
	language="java" import="com.calculator.domain.Result"
	import="java.text.SimpleDateFormat" errorPage=""%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>网贷计算器</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jQuery/jquery-1.7.2.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(function() {
		$("#frame1").change(function() {
			var flag = true;
			$(":input").each(function(){
				if($(this).val() == "")				
					flag = false;
			});
			if(flag == true){
				var url = "${pageContext.request.contextPath}/CalculatorControl";
				var args = {"date" : $(":input[name='date']").val(),
							"money" : $(":input[name='money']").val(),
							"limit": $(":input[name='limit']").val(), 
							"limitIsDay": $(":input[name='limitIsDay']").val(), 
							"rate" : $(":input[name='rate']").val(),
							"rateIsDay": $(":input[name='rateIsDay']").val(), 
							"method": $(":input[name='method']").val(),
							"time" : new Date(),
				};
				$.getJSON(url, args, function(data) {
					$("#frame2 table tr:not(:first)").remove();
					$.each(data, function(i,obj){												  
						var num=$("<td align='center'></td>").append("<font></font>").append(obj.num);	
						var total=$("<td align='center'></td>").append("<font></font>").append(obj.total);	
						var principal=$("<td align='center'></td>").append("<font></font>").append(obj.principal);	
						var interest=$("<td align='center'></td>").append("<font></font>").append(obj.interest);	
						var date=$("<td align='center'></td>").append("<font></font>").append(obj.date);	
						$("<tr></tr>").append(num)
									.append(total)
									.append(principal)
									.append(interest)
									.append(date)
									.appendTo("#frame2 table");
					});  
				});
			}  
			return false;
		}); 
	})
</script>
</head>

<body style="background-image:url(images/background.jpg)">
	<div style="height:10%; width:100%; float:left;" id="NameBlock">
		<font id="Name">网贷计算器</font>
	</div>
	<div style="height:90%; width:100%">
		<div id='frame1' style="float:left">
			<div id='inputBlock'>
				<form method="post" action="">
					<table width="350" border="0">
						<tr>
							<td><div align="right">
									<font>借出日期</font>
								</div></td>
							<td><input name="date" type="date" /></td>
							<td><div align="left"></div></td>
						</tr>
						<tr>
							<td><div align="right">
									<font>借出金额</font>
								</div></td>
							<td><input name="money" type="number" /></td>
							<td><div align="left"></div></td>
						</tr>
						<tr>
							<td><div align="right">
									<font>借出期限</font>
								</div></td>
							<td><input name="limit" type="number" /></td>
							<td></td>
						</tr>
						<td></td>
						<td><input name="limitIsDay" type="radio" value="0"
							checked="checked" /><font>个月 </td>
						<td></td>
						</tr>
						<tr>
							<td><div align="right">
									<font>利率</font>
								</div></td>
							<td><input name="rate" type="number" /></td>
							<td><font>%</font></td>
						</tr>
						<td></td>
						<td><input name="rateIsDay" type="radio" value="0"
							checked="checked" /><font>年利率</font> <input name="rateIsDay"
							type="radio" value="1" /><font>日利率</font></td>
						<td></td>
						</tr>
						<tr>
							<td><div align="right">
									<font>还款方式</font>
								</div></td>
							<td><select name="method">
									<option value="A">月还息到期还本</option>
									<option value="B">到期还本期</option>
							</select></td>
							<td><div align="left"></div></td>
						</tr>
					</table>
				</form>
			</div>
		</div>

		<div id='frame2' style="float:right;overflow-y:scroll">
			<table width="100%" border="0">
				<tr>
					<th align="center"><font>期号</font></th>
					<th align="center"><font>代收（含本金）</font></th>
					<th align="center"><font>本金</font></th>
					<th align="center"><font>利息</font></th>
					<th align="center"><font>到期时间</font></th>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>

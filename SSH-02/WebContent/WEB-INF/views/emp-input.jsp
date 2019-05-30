<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加员工信息</title>
<script type="text/javascript" src="scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
	
	$(function(){
		$(":input[name=lastName]").change(function(){
			var val = $(this).val();
			val = $.trim(val);
			var $this = $(this);
			
			if(val != ""){
				//把当前节点后面的所有 font 兄弟节点删除
				$this.nextAll("font").remove();
				
				var url = "emp-validateLastName";
				var args = {"lastName":val, "time":new Date()};
				$.post(url, args, function(data){
					//表示可用
					if(data == "1"){
						$this.after("<font color='green'>LastName可用!</font>");
					}
					//不可用
					else if(data == "0"){
						$this.after("<font color='red'>LastName不可用!</font>");						
					}
					//服务器错误
					else{
						alert("服务器错误!");
					}
				});
			}else{
				alert("lastName 不能为空");
				$(this).val("");
				$this.focus();
			}
		});
	})
	
</script>
</head>
<body>
	<s:form action="emp-save" method="post">
		<s:if test="id != null">
			<s:textfield name="lastName" label="姓名" disabled="true"></s:textfield>
			<s:hidden name="id"></s:hidden>
		</s:if>
		<s:else>
			<s:textfield name="lastName" label="姓名"></s:textfield>
		</s:else>
		<s:textfield name="email" label="邮箱"></s:textfield>
		<s:textfield name="birth" label="生日"></s:textfield>
		<s:select list="#request.departments"
			listKey="id" listValue="departmentName" name="department.id"
			label="部门">
		</s:select>
		<s:submit></s:submit>
	</s:form>
</body>
</html>
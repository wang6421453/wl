<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ include file="/base.jsp" %>
<meta charset="UTF-8" />
<html>
<head>
<title>代码版本控制</title>
<link rel="stylesheet" href="<%=webRoot%>/css/bootstrap.min.css">
<link rel="stylesheet" href="css/stringHelper.css">
</head>

<body>
	<div>
		<div style="float: center">
			<form name="fileCompareForm" novalidate>
				<div class="form-group">
					<div class="col-md-6">
						<div contenteditable="true" class="form-control" id="string1"  style="height:600px;width:100%;border:1px solid #00ff00;overflow: auto;"></div>
					</div>
					<div class="col-md-6">
						<div contenteditable="true" class="form-control" id="string2"  style="height:600px;width:100%;border:1px solid #00ff00;overflow: auto;"></div>
					</div>
				</div>
				<div class="form-group">
				   <div class="col-md-3 col-md-offset-3" style="margin-top:20px">
						<button type="button" class="btn btn-lg btn-success btn-block"
							onclick="doIt()">Do it</button>
				   </div>
				   <div class="col-md-2" style="margin-top:20px">
						<button type="button" class="btn btn-lg btn-primary btn-block"
							onclick="clearEdit()">清 空</button>
				   </div>
				</div>
			</form>
		</div>
		
	</div>
</body>

<script src="<%=webRoot%>/js/jquery-1.8.2.js"></script>
<script src="<%=webRoot%>/js/nicEdit.js"></script>
<script>

</script>
</html>
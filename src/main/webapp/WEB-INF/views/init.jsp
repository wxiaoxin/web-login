<%
    request.setAttribute("path", request.getContextPath());
%>
<%-- bootstrap --%>
<link rel="stylesheet" href="${path}/resouces/plugins/bootstrap/bootstrap.min.css">

<%-- jquery --%>
<script src="${path}/resouces/plugins/jquery/jquery-3.1.1.min.js"></script>

<%-- 全局上下变量 --%>
<script type="text/javascript">
    var path ="${path}";
</script>
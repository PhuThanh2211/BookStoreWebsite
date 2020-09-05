<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2 class="pageheading">Order Detail ID ${order.orderId }</h2>
	</div>
	
	<c:if test="${message != null }">
	<div align="center">
		<h4 class="message">${message }</h4>
	</div>
	</c:if>
	
	<div align="center">
		<h2 class="pageheading">Order Overview</h2>
		<jsp:directive.include file="../common/order_detail.jsp" />
	</div>
	
	<div align="center">
		<br/>
		<button type="button" id="editOrder" >Edit This Order</button>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button type="button" id="deleteOrder" >Delete This Order</button>
	</div>
	
<jsp:directive.include file="footer.jsp" />
<script>
	$(document).ready(function() {
		$("#editOrder").click(function() {
			window.location = "${pageContext.request.contextPath}/admin/editOrder?id="${order.orderId};
		});
		
		$("#deleteOrder").click(function() {
			window.location = "${pageContext.request.contextPath}/admin/deleteOrder?id="${order.orderId}";
		});
	});
</script>


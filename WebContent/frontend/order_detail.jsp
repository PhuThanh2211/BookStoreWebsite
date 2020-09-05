<jsp:directive.include file="header.jsp" />
	<c:if test="${order == null }">
		<div align="center">
			<h2 class="pageheading">Sorry, you are not authorized to view this order</h2>
		</div>
	</c:if>
	
	<c:if test="${order != null }">
		<div align="center">
			<h2 class="pageheading">Your Order Detail ID ${order.orderId }</h2>
		</div>
		
		<div align="center">
		<h2 class="pageheading">Order Overview</h2>
			<jsp:directive.include file="../common/order_detail.jsp" />
		</div>
	</c:if>
<jsp:directive.include file="footer.jsp" />


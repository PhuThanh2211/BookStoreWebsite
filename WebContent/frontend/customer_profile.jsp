<jsp:directive.include file="header.jsp" />
	<div class="center">
		<br/>
		<h2 class="pageheading">Welcome, ${loggedCustomer.fullname }</h2>
		<br/>
		
		<table class="customer">
			<tr>
				<td><b>Email Address:</b></td>
				<td>${loggedCustomer.email }</td>
			</tr>
			
			<tr>
				<td><b>Full Name:</b></td>
				<td>${loggedCustomer.fullname }</td>
			</tr>
			
			<tr>
				<td><b>Phone Number:</b></td>
				<td>${loggedCustomer.phone }</td>
			</tr>
			
			<tr>
				<td><b>Address:</b></td>
				<td>${loggedCustomer.address }</td>
			</tr>
			
			<tr>
				<td><b>City:</b></td>
				<td>${loggedCustomer.city }</td>
			</tr>
			
			<tr>
				<td><b>Zip Code:</b></td>
				<td>${loggedCustomer.zipcode }</td>
			</tr>
			
			<tr>
				<td><b>Country:</b></td>
				<td>${loggedCustomer.country }</td>
			</tr>
			
			<tr>
				<td>&nbsp;</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<b><a href="edit_profile">Edit My Profile</a></b>
				</td>
			</tr>
		</table>
	</div>
<jsp:directive.include file="footer.jsp" />
<table class="form">
	<tr>
		<td align="right">Email:</td>
		<td align="left"><input type="text" name="email" value="${customer.email }" id="email" size="45"/></td>
	</tr>	
	<tr>
		<td align="right">First Name:</td>
		<td align="left"><input type="text" name="firstname" value="${customer.firstname }" id="firstname" size="45"/></td>
	</tr>
	<tr>
		<td align="right">Last Name:</td>
		<td align="left"><input type="text" name="lastname" value="${customer.lastname }" id="lastname" size="45"/></td>
	</tr>
	<tr>
		<td align="right">Password:</td>
		<td align="left"><input type="password" name="password" id="password" size="45"/></td>
	</tr>
	<tr>
		<td align="right">Confirm Password:</td>
		<td align="left"><input type="password" name="confirmPassword" id="confirmPassword" size="45"/></td>
	</tr>
	<tr>
		<td align="right">Phone Number:</td>
		<td align="left"><input type="text" name="phone" value="${customer.phone }" id="phone" size="45"/></td>
	</tr>
	<tr>
		<td align="right">Address Line 1:</td>
		<td align="left"><input type="text" name="address1" value="${customer.addressLine1 }" id="address1" size="45"/></td>
	</tr>
	<tr>
		<td align="right">Address Line 2:</td>
		<td align="left"><input type="text" name="address2" value="${customer.addressLine2 }" id="address2" size="45"/></td>
	</tr>
	<tr>
		<td align="right">City:</td>
		<td align="left"><input type="text" name="city" value="${customer.city }" id="city" size="45"/></td>
	</tr>
	<tr>
		<td align="right">State:</td>
		<td align="left"><input type="text" name="state" value="${customer.state }" id="state" size="45"/></td>
	</tr>
	<tr>
		<td align="right">Zip Code:</td>
		<td align="left"><input type="text" name="zipCode" value="${customer.zipcode }" id="zipCode" size="45"/></td>
	</tr>
	<tr>
		<td align="right">Country:</td>
		<td align="left">
			<select name="country" id="country">
				<c:forEach items="${mapCountries }" var="country">
					<option value="${country.value }"
						<c:if test="${customer.country eq country.value }">
							selected="selected"
						</c:if> >
						${country.key }
					</option>
				</c:forEach>
			</select>
		</td>
	</tr>

	<tr>
		<td colspan="2" align="center">
			<button class="spaceButton" type="submit">Save</button>
			<button type="button" id="cancelButton">Cancel</button>
		</td>
	</tr>
</table>










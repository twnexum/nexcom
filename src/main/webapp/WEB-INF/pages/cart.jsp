<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<?xml version="1.0" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<body>

	<a href="/storefront">[Storefront]</a> 
	| [Cart Contents: ${shoppingCartPositionSize} item(s) / ${shoppingCartTotalAmount}]
	| <a href="/checkout">[Proceed To Checkout]</a>
	
	<br/>

	<h1>Your cart</h1>
	
	<c:if test="${not empty cartPositions}">
		<table border="1">
			<tr>
				<th>Product</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Remove</th>
			</tr>
			<c:forEach var="cartPosition" items="${cartPositions}">
				<c:set var="product" value="${cartPosition.cartItem}"/>
					<form name="removeFromCart_${product.id}" id="removeFromCart_${product.id}" action="/removeFromCart" method="post">
						<input type="hidden" name="productId" value="${product.id}" />			
					</form>
					<tr>
						<td>${product.attributesMap.title}</td>
						<td>${product.price}</td>
						<td>
							<form name="updateQuantity_${product.id}" id="updateQuantity_${product.id}" action="/updateQuantity" method="post">
								<input type="hidden" name="productId" value="${product.id}" />	
								<select name="quantity">
									<c:forEach var="i" begin="1" end="10">
										<c:choose>
											 <c:when test="${i == cartPosition.quantity}">
											 	<option value="${i}" selected>${i}</option>
											 </c:when>
											 <c:otherwise>
											 	<option value="${i}">${i}</option>
											 </c:otherwise>
										</c:choose>									
									</c:forEach>							
								</select>
							</form>
							<a href="#" onClick="javascript:document.forms['updateQuantity_${product.id}'].submit();">[Update]</a>
						</td>	
						<td><a href="#" onClick="javascript:document.forms['removeFromCart_${product.id}'].submit();">[X]</a></td>
					</tr>

			</c:forEach>
		</table>
		
		<b>TOTAL: ${shoppingCartTotalAmount}</b>
	</c:if>	
 
</body>
</html>
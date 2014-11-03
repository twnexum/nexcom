<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<?xml version="1.0" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<body>

	[Storefront] 
	<c:choose>
		<c:when test = "${shoppingCartPositionSize gt 0}">
			| <a href="/showCart">[Cart Contents: ${shoppingCartPositionSize} item(s) / ${shoppingCartTotalAmount}]</a>
			| <a href="/checkout">[Proceed To Checkout]</a>		
		</c:when>
		<c:otherwise>
			| [Cart Contents: -/-]</a>
			| [Proceed To Checkout]
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${not isCompactView}">
			| <a href="/storefront?compactView=true">[Hide Variants]</a>					
		</c:when>
		<c:otherwise>
			| <a href="/storefront?compactView=false">[Show Variants]</a>
		</c:otherwise>
	</c:choose>		
	
	<br/>

	<h1>Storefront</h1>
	
	<c:if test="${not empty storefrontPositions}">
		<table border="1">
			<c:forEach var="storefrontPosition" items="${storefrontPositions}">
				<c:set var="cartItem" value="${storefrontPosition.cartItem}"/>
				<form name="addToCart_${cartItem.id}" id="addToCart_${cartItem.id}" action="/addToCart" method="post">
					<input type="hidden" name="cartItemId" value="${cartItem.id}" />				
					<tr>
						<td><img src="${cartItem.attributes.image}"/></td>
						<td>${cartItem.attributes.title}</td>
						<td>${storefrontPosition.price.amount}</td>
						<td>${storefrontPosition.price.currency}</td>
						<c:choose>
							<c:when test="${not isCompactView}">
								<td>
									<select name="quantity">
										<option value="0">0</option>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
									</select>
								</td>	
								<td><a href="#" onClick="javascript:document.forms['addToCart_${cartItem.id}'].submit();">[Add To Cart]</a></td>							
							</c:when>
							<c:otherwise>
								<td><a href="/detailView?id=${cartItem.id}">[View]</a></td>
							</c:otherwise>
						</c:choose>										
					</tr>
				</form>
			</c:forEach>
		</table>
	</c:if>	
 
</body>
</html>
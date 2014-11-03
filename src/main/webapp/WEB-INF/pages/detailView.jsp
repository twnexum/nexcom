<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<?xml version="1.0" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<body>

	<a href="/storefront">[Storefront]</a> 
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
	
	<br/>

	<c:choose>
		<c:when test="${not empty storefrontPosition}">
			<c:set var="cartItem" value="${storefrontPosition.cartItem}"/>
			
			<h1>${cartItem.attributes.title}</h1>			
			<p><img id="productImage" src="${cartItem.attributes.image}"/></p>			
			<p>${cartItem.attributes.description}</p>
			
			<c:choose>
				<c:when test="${isVariantProduct}">
					<form name="addToCart_variantProduct" id="addToCart_${cartItem.id}" action="/detailViewAddToCart" method="post">
						<select name="cartItemId" onChange="document.getElementById('productImage').src=this.options[this.selectedIndex].getAttribute('image')">
							<c:forEach var="entry" items="${variantSelectionsById}">
								<option value="${entry.key}" image="${variantImagesById[entry.key]}">${entry.value}</option>
							</c:forEach>						
						</select>	
						<select name="quantity">
							<option value="0">0</option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
						</select>									
					</form>	
					<p><a href="#" onClick="javascript:document.forms['addToCart_variantProduct'].submit();">[Add To Cart]</a></p>
				</c:when>
				<c:otherwise>
					<p style="font-weight:bold;">Price: ${cartItem.price}</p>					
					<form name="addToCart_${cartItem.id}" id="addToCart_${cartItem.id}" action="/detailViewAddToCart" method="post">
						<input type="hidden" name="cartItemId" value="${cartItem.id}" />
						<select name="quantity">
							<option value="0">0</option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
						</select>			
					</form>	
					<p><a href="#" onClick="javascript:document.forms['addToCart_${cartItem.id}'].submit();">[Add To Cart]</a></p>
				</c:otherwise>
			</c:choose>
			
		</c:when>
		<c:otherwise>
			<p style="font-style:italic;">Sorry, but we couldn't find a product with this ID...!</p>
		</c:otherwise>
	</c:choose>
 
</body>
</html>
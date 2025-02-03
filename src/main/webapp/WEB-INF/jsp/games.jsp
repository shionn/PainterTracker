<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<t:template>
<jsp:attribute name="content">
<table class="collections">
	<thead>
		<tr>
			<th colspan="3">${totals.painted} / ${totals.qty}</th>
			<th>Aquisition</th>
			<th>Peinture</th>
			<th>Temps de Peinture</th>
		</tr>
	</thead>
	<c:forEach items="${games}" var="c">
		<thead>
			<tr>
				<th colspan="6">${c.name} ${c.paintedQty} / ${c.qty}
					<em><fmt:formatNumber maxFractionDigits="2" value="${c.progress}"/> %</em> 
				</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${c.figurines}" var="f">
				<tr>
					<td>${f.qty}x</td>
					<td><c:if test="${f.painted}"><i class="fa fa-paint-brush" aria-hidden="true"></i></c:if></td>
					<td>${f.name}</td>
					<td><fmt:formatDate value="${f.acquireDate}" pattern="dd MMM YYYY"/></td>
					<td><c:if test="${f.painted}"><fmt:formatDate value="${f.paintedDate}" pattern="dd MMM YYYY"/></c:if></td> 
					<td>${f.formatedDuration}</td>
				</tr>
			</c:forEach>
		</tbody>
	</c:forEach>
</table>
</jsp:attribute>
</t:template>

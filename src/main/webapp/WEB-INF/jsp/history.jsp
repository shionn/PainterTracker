<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<t:template>
<jsp:attribute name="content">

<table>
	<thead>
		<tr>
			<td>Mois</td>
			<c:forEach items="${games}" var="g">
				<td colspan="2">${g}</td>
			</c:forEach>
		</tr>
	</thead>
	<c:forEach items="${histories}" var="h">
		<tr>
			<td>${h.formatedMonth}</td>
			<c:forEach items="${h.shames}" var="s">
				<td style="background-color:${s.varColor}">
					<c:if test="${s.acquiredQty != 0}">+${s.acquiredQty}</c:if>
				</td>
				<td style="background-color:${s.varColor}">
					<c:if test="${s.paintedQty != 0}">-${s.paintedQty}</c:if>
				</td>
			</c:forEach>
		</tr>
	</c:forEach>
</table>
</jsp:attribute>
<jsp:attribute name="scripts">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script type="text/javascript" src='<spring:url value="js/autochart.js"/>'></script>
</jsp:attribute>
</t:template>

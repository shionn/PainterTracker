<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<t:template>
<jsp:attribute name="content">
<spring:url value="/jobs" var="base"/>

<h1>Création</h1>
<form:form action="${base}">
	<label for="date">Date</label>
	<input name="date" type="date" id="date"/>
</form:form>

<h1>Taches en cours</h1>
<table>
	<tobdy>
		<c:forEach items="${jobs}" var="j">
			<c:if test="${not j.finished}">
				<tr>
					<td>${j.description}</td>
					<td>${j.date}</td>
					<td>${j.end}</td>
					<td>${j.size}</td>
					<td>Edit</td>
				</tr>
			</c:if>
		</c:forEach>
	</tobdy>
</table>

<h1>Taches accomplies</h1>
<table>
	<tobdy>
		<c:forEach items="${jobs}" var="j">
			<c:if test="${j.finished}">
				<tr>
					<td>${j.description}</td>
					<td>${j.date}</td>
					<td>${j.end}</td>
					<td>${j.size}</td>
					<td>Edit</td>
				</tr>
			</c:if>
		</c:forEach>
	</tobdy>
</table>
</jsp:attribute>
</t:template>

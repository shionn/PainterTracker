<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<t:template>
<jsp:attribute name="content">
<spring:url value="/figurines" var="base"/>

<c:if test="${not empty figurine}">
	<h1>Édition ${figurine.id}</h1>
	<form:form action="${base}/edit">
		<input type="hidden" name="id" value="${figurine.id}"/>
		<label for="name">Nom</label>
		<input name="name" type="text" value="${figurine.name}"/>
		<label for="game">Jeu</label>
		<input name="game" type="text" value="${figurine.game}"/>
		<label for="collection">Collection (Armée)</label>
		<input name="collection" type="text" value="${figurine.collection}"/>
		<label for="acquireDate">Date d'aquisition</label>
		<input name="acquireDate" type="date" value="<fmt:formatDate value="${figurine.acquireDate}" pattern="yyyy-MM-dd"/>"/>
		<label for="qty">Quantité</label>
		<input name="qty" type="number" value="${figurine.qty}"/>
		<label for="description">Description</label>
		<input name="description" type="text" value="${figurine.description}"/>
		<br/>
		<input type="submit" value="Valider"/>
	</form:form>
</c:if>


<h1>Figurines</h1>
<form:form action="${base}/filter" method="GET" class="inline">
	<select name="game">
		<option value="ALL">Tous</option>
		<c:forEach items="${games}" var="g">
			<option value="${g}" <c:if test="${g==filter.game}">selected="selected"</c:if>>${g}</option>
		</c:forEach>
	</select>
	<select name="collection">
		<option value="ALL">Tous</option>
		<c:forEach items="${collections}" var="c">
			<option value="${c}" <c:if test="${c==filter.collection}">selected="selected"</c:if>>${c}</option>
		</c:forEach>
	</select>
	<input type="submit" value="Filtrer"/>
</form:form>
<table class="figurines">
	<thead>
		<tr>
			<th>Collection</th>
			<th colspan="3">Figurine</th>
			<th>Aquisition</th>
			<th><a href="${base}/create">Ajouter</a></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${figurines}" var="f">
			<tr>
				<td>${f.game} ${f.collection}</td>
				<td><c:if test="${f.painted}"><i class="fa fa-paint-brush" aria-hidden="true"></i></c:if></td>
				<td>${f.qty}</td>
				<td>${f.name}</td>
<%-- 				<td>${f.description}</td> --%>
				<td><fmt:formatDate value="${f.acquireDate}" pattern="dd/MM/yyyy"/></td>
				<td><a href="${base}/edit/${f.id}">Edit</a> <a href="${base}/clone/${f.id}">Clone</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</jsp:attribute>
</t:template>

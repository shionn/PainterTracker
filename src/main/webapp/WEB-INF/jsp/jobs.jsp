<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<t:template>
<jsp:attribute name="content">
<spring:url value="/jobs" var="base"/>

<c:if test="${not empty job}">
	<h1>Édition ${job.id}</h1>
	<form:form action="${base}/edit">
		<input type="hidden" name="job" value="${job.id}"/>
		<label for="date">Date de création</label>
		<input name="date" type="date" value="<fmt:formatDate value="${job.date}" pattern="yyyy-MM-dd"/>"/>
		<input name="finished" type="checkbox"<c:if test="${job.finished}"> checked="checked"</c:if>/>
		<label for="finished">Fini</label>
		<br/>
		<input type="submit" value="Valider"/>
	</form:form>
	<form:form action="${base}/add-figurine">
		<input type="hidden" name="job" value="${job.id}"/>
		<label for="figurine">Ajout de Figurine</label>
		<select name="figurine">
			<c:forEach items="${figurines}" var="f">
				<c:if test="${f.available(job)}">
					<option value="${f.id}">${f.name} x${f.qty} <fmt:formatDate value="${f.acquireDate}" pattern="dd/MM/yyyy"/> (${f.collection} - ${f.description})</option>
				</c:if>
			</c:forEach>
		</select>
		<input type="submit" value="Ajouter"/>
	</form:form>
	
</c:if>

<h1>Taches en cours</h1>
<table>
	<thead>
		<tr>
			<th>Description</th>
			<th>Date de création</th>
			<th>Date de début</th>
			<th>Date de fin</th>
			<th>Taille</th>
			<th><a href="${base}/create">Ajouter</a></th>
		</tr>
	</thead>
	<tobdy>
		<c:forEach items="${jobs}" var="j">
			<c:if test="${not j.finished}">
				<tr>
					<td>${j.description}</td>
					<td><fmt:formatDate value="${j.date}" pattern="dd/MM/yyyy"/></td>
					<td><fmt:formatDate value="${j.start}" pattern="dd/MM/yyyy"/></td>
					<td><fmt:formatDate value="${j.end}" pattern="dd/MM/yyyy"/></td>
					<td>${j.size}</td>
					<td>
						<a href="${base}/edit/${j.id}">Edit</a>
						<a href="${base}/${j.id}/session/create">Start</a>
					</td>
				</tr>
				<c:forEach items="${j.sessions}" var="s">
					<tr>
						<c:if test="${not empty s.end}">
							<td colspan="2" style="padding-left: 45px">${s.description}</td>
							<td><fmt:formatDate value="${s.start}" pattern="dd/MM HH:mm"/></td>
							<td><fmt:formatDate value="${s.end}" pattern="dd/MM HH:mm"/></td>
							<td colspan="2"> </td>
						</c:if>
						<c:if test="${empty s.end}">
							<td colspan="2">
								<form:form class="inline" action="${base}/${j.id}/session/${s.id}/edit">
									<input type="text" name="description" value="${s.description}"/>
									<input type="submit" value="Edit" />
								</form:form>
							</td>
							<td><fmt:formatDate value="${s.start}" pattern="dd/MM HH:mm"/></td>
							<td colspan="2"> </td>
							<td>
								<a href="${base}/${j.id}/session/${s.id}/end">End</a>
							</td>
						</c:if>
					</tr>
				</c:forEach>
			</c:if>
		</c:forEach>
	</tobdy>
</table>

<h1>Taches accomplies</h1>
<table>
	<thead>
		<tr>
			<th>Description</th>
			<th>Date de création</th>
			<th>Date de début</th>
			<th>Date de fin</th>
			<th>Taille</th>
			<th>#</th>
		</tr>
	</thead>
	<tobdy>
		<c:forEach items="${jobs}" var="j">
			<c:if test="${j.finished}">
				<tr>
					<td>${j.description} <em>(${j.collection})</em></td>
					<td><fmt:formatDate value="${j.date}" pattern="dd/MM/yyyy"/></td>
					<td><fmt:formatDate value="${j.start}" pattern="dd/MM/yyyy"/></td>
					<td><fmt:formatDate value="${j.end}" pattern="dd/MM/yyyy"/></td>
					<td>${j.size}</td>
					<td><a href="${base}/edit/${j.id}">Edit</a></td>
				</tr>
			</c:if>
		</c:forEach>
	</tobdy>
</table>
</jsp:attribute>
</t:template>

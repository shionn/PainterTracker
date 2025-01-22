<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<t:template>
<jsp:attribute name="content">
<h1>Figurines</h1>
<table>
	<tobdy>
		<c:forEach items="${figurines}" var="f">
			<tr>
				<td>${f.collection}</td>
				<td>${f.name}</td>
				<td>${f.qty}</td>
				<td><fmt:formatDate value="${f.acquireDate}" pattern="dd/MM/yyyy"/></td>
				<td>${f.painted}</td>
				<td>${f.duration}</td>
				<td>Edit</td>
			</tr>
		</c:forEach>
	</tobdy>
</table>
</jsp:attribute>
</t:template>

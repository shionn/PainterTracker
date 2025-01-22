<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
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
				<td>${f.acquireDate}</td>
				<td>${f.painted}</td>
				<td>Edit</td>
			</tr>
		</c:forEach>
	</tobdy>
</table>
</jsp:attribute>
</t:template>

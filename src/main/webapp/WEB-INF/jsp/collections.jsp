<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<t:template>
<jsp:attribute name="content">
<ul>
	<c:forEach items="${collections}" var="c">
		<li>
			<span>${c.name} ${c.paintedQty} / ${c.qty}</span>
			<ul>
				<c:forEach items="${c.figurines}" var="f">
					<li>${f.qty}x ${f.name} ${f.painted} ${f.acquireDate}/${f.paintedDate} </li>
				</c:forEach>
			</ul>
		</li>
	</c:forEach>
</ul>
</jsp:attribute>
</t:template>

<%@ tag pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ attribute name="content" fragment="true"%>
<%@ attribute name="scripts" fragment="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<meta name="mobile-web-app-capable" content="yes" />
<title>Painter Tracker</title>
<link rel="stylesheet" href="<spring:url value="/css/mvp.css"/>" />
</head>
<body>
	<header>
		<nav>
			<spring:url value="/" var="base"/>
			<a href="${base}">Painter Tracker</a>
			<ul>
				<li><a href="${base}collections">Collections</a></li>
				<li><a href="${base}history">Historique</a></li>
				<li><a href="${base}jobs">Tâche de Peinture</a></li>
				<li><a href="${base}figurines">Figurines</a></li>
			</ul>
		</nav>
	</header>
	<main>
		<jsp:invoke fragment="content" />
	</main>
	<footer>
		<hr>
		<p>Painter Tracker by <a href="mailto:shionn@gmail.com">shionn</a></p>
	</footer>
	<jsp:invoke fragment="scripts" />
</body>
</html>

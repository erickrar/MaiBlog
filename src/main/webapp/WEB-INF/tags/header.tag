<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@attribute name="title" type="java.lang.String" required="true"%>


<fmt:message key="site.title" var="defaulTitle" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="http://www.maino.com.br/wp-content/uploads/2017/05/Favicon16_Maino.png"/> 	

  <title>
<c:set var="fullTitle" value="${title} - ${defaulTitle}" />
		<c:out value="${empty title ? defaulTitle : fullTitle }" escapeXml="true" />
</title>

<base href="<c:url value="/" />">
<link rel="stylesheet" href="<c:url value="/resources/bootstrap-3.2.0-dist/css/bootstrap.min.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/clean-blog.css"/>">
    
    
    <!-- Custom Fonts -->
    <link href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <nav class="navbar navbar-default navbar-custom navbar-fixed-top">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    Menu <i class="fa fa-bars"></i>
                </button>
                <!-- <a class="navbar-brand" href="${linkTo[IndexController].index}">Home</a> --> 
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="${linkTo[IndexController].index}"><fmt:message key="home"/></a>
                    </li>
                 
                     <c:choose>
        				<c:when test="${loggedUser.loggedIn}">
        				<li>
                        <a href="${linkTo[PostController].newPost}"><fmt:message key="post"/></a>
                        </li> 
                        <li>
                        <a href="${linkTo[ProfileController].signOut}"><fmt:message key="signout"/></a>
                        </li>
                        </c:when>
                        <c:otherwise>
                        <li>
                        <a href="${linkTo[ProfileController].signin}"><fmt:message key="signin"/></a>
                        </li>
                        <li>
                        <a href="${linkTo[ProfileController].signup}"><fmt:message key="signup"/></a>
                        </li>
                        </c:otherwise>
                        </c:choose>
                </ul>
            </div>
        </div>
    </nav>

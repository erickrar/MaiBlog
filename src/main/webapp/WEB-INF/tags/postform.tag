<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@attribute name="formAction" type="java.lang.String" required="true"%>



  <script src="<c:url value="/resources/js/ckeditor/ckeditor.js"/>"></script>  

   <!-- Page Header -->
    <!-- Set your background image for this header on the line below. -->
     <c:choose>
     <c:when test="${empty post.cover}">
    	 <header class="intro-header" style="background-image: url('resources/imgs/home-bg.jpg')">
     </c:when>
     <c:otherwise>
     <header class="intro-header" style="background-image: url('${post.cover}')">
     </c:otherwise>
     </c:choose>
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                    <div class="site-heading">
                        <h1>Mainô blog</h1>
                        <hr class="small">
                        <span class="subheading">Blog pro processo de seleção da Mainô</span>
                    </div>
                </div>
            </div>
        </div>
    </header>    



<form action="${formAction}" class="form-horizontal" role="form" enctype="multipart/form-data" method="post">
	<div class="col-sm-4 col-md-offset-4">
	<div class="jumbotron blackblock">
	
	<div class="form-group form-group-lg">
	<label for="title" class="control-label"><fmt:message key="post.title"/></label> 
     <input id="title"  class="form-control text-center" type="text" name="post.title" autofocus="true" value="${post.title}" placeholder="<fmt:message key="post.title"/>"/> 
     <h4>${errors.from('title')} </h4>
     </div>
    
    
	<div class="form-group form-group-lg">
	<label for="description" class="control-label"><fmt:message key="post.description"/></label> 
     <input id="description"  class="form-control text-center" type="text" name="post.description" value="${post.description}" placeholder="<fmt:message key="post.description"/>"/> 
     <h4>${errors.from('description')} </h4>
     </div>
     
     	<div class="form-group form-group-lg">
	<label for="cover" class="control-label"><fmt:message key="post.cover"/></label> 
     <input id="cover"  class="form-control text-center" type="text" name="post.cover" value="${post.cover}" placeholder="<fmt:message key="post.cover.tip"/>"/> 
     <h4>${errors.from('cover')} </h4>
     </div>
     
     <div class="form-group form-group-lg">
    	<label for="category" class="control-label"><fmt:message key="post.category"/></label>
    		<select id="category" name="post.category.id" class="form-control  text-center">
	 					<option value="0"><fmt:message key="select.one"/></option>   
            		<c:forEach var="c" items="${categories}">  
               			 <option value="${c.id}" <c:if test="${c.id == post.category.id}"> selected="selected"></c:if>  >${c.name}</option>
            		</c:forEach> 
    		</select>
    </div> 
     
     <div class="form-group form-group-lg">
	<label for="post" class="control-label"><fmt:message key="post.post"/></label> 
     <textarea id="post.post"  class="form-control text-center" type="text" name="post.post" value="${post.post}" placeholder="<fmt:message key="post.post"/>">
     	${post.post} 
     </textarea> 
     <h4>${errors.from('post')} </h4>
     </div>
    
   
  
    
    <div class="col-sm-4 col-md-offset-4">
      <input type="hidden" name="user.id" value="${user.id}" />
       <button type="submit" class="btn-block btn btn-default btn-primary-color"><fmt:message key="button.save"/></button>
       </div>
     </div> 
     </div> 
  <input type="hidden" name="post.id" value="${post.id}"/> 
  
   <script>
           CKEDITOR.replace( 'post.post' ); 
    </script>
</form>


<hr>

<form action="${linkTo[PostController].remove}" class="form-horizontal" role="form" enctype="multipart/form-data" method="post">
	
    <div class="col-sm-4 col-md-offset-4">
      <input type="hidden" name="user.id" value="${user.id}" />
       <button type="submit" class="btn-block btn btn-default btn btn-danger"><fmt:message key="post.remove"/></button> 
       </div>
      
  <input type="hidden" name="post.id" value="${post.id}"/> 
 
</form>
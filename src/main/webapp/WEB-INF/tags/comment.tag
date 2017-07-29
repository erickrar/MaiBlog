<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


    <div class="row">
            <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
            <h3><fmt:message key="comments"/></h3>
            <c:forEach  var="comment" items="${comments}"	varStatus="counter">
            <div class="post-preview">
             	             ${comment.comment} 
                   <p class="post-meta"><fmt:message key="comment.by"/> ${comment.authorName} <fmt:message key="post.onday"/> <fmt:formatDate value="${comment.createdDate}" pattern="dd 'de' MMMM 'de' YYYY" /></p>
                </div>
                <hr>
            </c:forEach>
         </div>
    </div>


<form action="${linkTo[CommentController].save}" class="form-horizontal" role="form" enctype="multipart/form-data" method="post">
	<div class="col-sm-4 col-md-offset-4">
	<div class="jumbotron blackblock">
	
	<div class="form-group form-group-lg">
	<label for="comment.authorName" class="control-label"><fmt:message key="comment.authorName"/></label> 
     <input id="comment.authorName"  class="form-control text-center" type="text" name="comment.authorName" value="${comment.authorName}" placeholder="<fmt:message key="comment.authorName"/>"/> 
     <h4>${errors.from('authorName')} </h4>
     </div>
    
     <div class="form-group form-group-lg">
	<label for="comment.comment" class="control-label"><fmt:message key="comment.comment"/></label> 
     <textarea id="comment.comment"  rows="20" class="form-control text-center" type="text" name="comment.comment" placeholder="<fmt:message key="comment.comment"/>">
     	${comment.comment} 
     </textarea> 
     <h4>${errors.from('comment.comment')} </h4>
     </div>
    
   
  
    
    <div class="col-sm-4 col-md-offset-4">
       <button type="submit" class="btn-block btn btn-default btn-primary-color"><fmt:message key="comment.save"/></button> 
       </div>
     </div> 
     </div> 
  <input type="hidden" name="comment.post.id" value="${post.id}"/> 
</form>
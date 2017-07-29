<fmt:message key="site.pagenotfound.title" var="notFoundTitle" />
<fmt:message key="site.title" var="genericTitle" />
<fmt:message key="site.pagenotfound.description" var="notFoundDescription" />
<tags:header title="${notFoundTitle} - ${genericTitle}" description="${notFoundDescription}" />
<tags:topMenu loggedUser="${loggedUser}"  />


<div class="page-header">
<h1>500</h1>
</div>
<div class="col-sm-8">
<h3>${notFoundDescription}</h3>
</div>
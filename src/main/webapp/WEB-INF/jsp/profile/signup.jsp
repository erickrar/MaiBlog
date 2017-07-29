<tags:header title="Cadastre-se" />
<fmt:message key="site.signin" var="signin" />
<fmt:message key="site.signin.description" var="ogDescription" />

   <!-- Page Header -->
    <!-- Set your background image for this header on the line below. -->
    <header class="intro-header" style="background-image: url('resources/imgs/home-bg.jpg')">
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



<form action="${linkTo[ProfileController].insert}" class="form-horizontal" role="form" enctype="multipart/form-data" method="post">
	<div class="col-sm-4 col-md-offset-4">
	<div class="jumbotron blackblock">
	
	<div class="form-group form-group-lg">
	<label for="name" class="control-label"><fmt:message key="user.name"/></label> 
     <input id="name"  class="form-control text-center" type="text" name="user.name" autofocus="true" value="${user.name}" placeholder="<fmt:message key="user.name"/>"/> 
     <h4 class="error-message">${errors.from('name')} </h4>
     </div>
    
    
    <div class="form-group form-group-lg">
     <label for="email" class="control-label" ><fmt:message key="user.email"/></label>
       <input id="email"  class="form-control text-center" type="email" name="user.email"  value="${user.email}" placeholder="<fmt:message key="user.email"/>"/>
       <h4 class="error-message">${errors.from('email')} </h4>
     </div>
     
    <div class="form-group form-group-lg">
     <label for="login" class="control-label" ><fmt:message key="user.login"/></label>
       <input id="login"  class="form-control text-center" type="text" name="user.login"  value="${user.login}" placeholder="<fmt:message key="user.login"/>"/>
       <h4 class="error-message">${errors.from('login')} </h4>
     </div>
     
      <div class="form-group form-group-lg">
      <label for="password" class="control-label" ><fmt:message key="user.password"/></label>
       <input id="password"  class="form-control text-center" type="password" name="password" placeholder="<fmt:message key="user.password"/>"/>
     </div>
     
     <div class="form-group form-group-lg">
     <label for="confirm.password" class="control-label" ><fmt:message key="user.confirm.password"/></label>
       <input id="confirm.password"  class="form-control text-center" type="password" name="confirmPassword" placeholder="<fmt:message key="user.confirm.password"/>"/>
         <h4 class="error-message">${errors.from('password')} </h4> 
     </div>
     
      <div class="col-sm-4 col-md-offset-4">
      <input type="hidden" name="user.id" value="${user.id}" />
       <button type="submit" class="btn-block btn btn-default btn-primary-color"><fmt:message key="button.save"/></button>
       </div>
     </div> 
     </div>
  
</form>
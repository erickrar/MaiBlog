
<tags:header title="Entrar" />

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
 

<form action="${linkTo[ProfileController].signIn}" class="form-horizontal" role="form" method="post">

<div class="container">
	<div class="row">
		<div class="col-sm-4 col-md-offset-4"> 
			<div class="blackblock">
				<h4>
					<p>${messages}</p>
				</h4>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-4 col-md-offset-4">
			<div class="blackblock">
			<c:forEach var="error" items="${errors}">
				<h4>${error.message}</h4>
</c:forEach>
			</div>
		</div>
	</div>

</div>
	<div class="col-sm-4 col-md-offset-4">
		<div class="jumbotron blackblock">
			<div class="form-group form-group-lg">
				<label for="login" class="control-label"><fmt:message
						key="user.login" /></label> <input id="login"
					class="form-control text-center" type="text" name="login"
					placeholder="<fmt:message key="user.email"/>/<fmt:message key="user.login"/>"
					autofocus="true" />
				<p>${errors.from('login')}</p>
			</div>

			<div class="form-group form-group-lg">
				<label for="password" class="control-label"><fmt:message
						key="user.password" />:</label> <input id="password"
					class="form-control text-center" type="password" name="password"
					placeholder="<fmt:message key="user.password"/>" />
				<p>${errors.from('password')}</p>
			</div>

			<div class="col-sm-4 col-md-offset-4">
				<button type="submit"
					class="btn-block btn btn-default btn-primary-color">
					<fmt:message key="button.signin" />
				</button>
			</div>
		</div>
	</div>
	<input type="hidden" name="redirectUrl" value="${redirectUrl}" />
</form>

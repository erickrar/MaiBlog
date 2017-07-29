
<tags:header title="${post.title}" />

    <!-- Page Header -->
    <!-- Set your background image for this header on the line below. -->
    <header class="intro-header" style="background-image: url('${post.cover}')">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                    <div class="post-heading">
                        <h1>${post.title} 
                 		    <c:if test="${loggedUser.isThePostAuthor(post)}">
 							<small class="meta"><a href="${linkTo[PostController].edit(post.id)}" title="<fmt:message key="button.edit"/>" >
 							<span class="glyphicon glyphicon-pencil" aria-hidden="true"> 
 							</a></small>
 					</c:if>       
                        </h1> 
                        <h2 class="subheading">${post.description}</h2>
                        <span class="meta">Postado por <a href="${linkTo[IndexController].byAuthor(post.author.id,post.author.name)}"  title="<fmt:message key="author.seePosts"/>
                         ${post.author.name}">${post.author.name}
                        </a> no dia <fmt:formatDate value="${post.createdDate}" pattern="dd 'de' MMMM 'de' YYYY" /></span>
                    </div> 
                </div>
            </div>
        </div>
    </header>

    <!-- Post Content -->
    <article>
        <div class="container">
        <span class="alert-message">${messages}</span>
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                 <c:out value="${post.post}"  escapeXml="false" /> 
                  </div>
               </div>
            </div>
    </article>

    <hr>
    
    <tags:comment /> 
    



 
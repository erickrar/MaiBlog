<tags:header title="Mainô" />

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
 







    <!-- Main Content -->
    <div class="container">
    
    <span class="alert-message">${messages}</span>
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
            <c:forEach  var="post" items="${posts}"	varStatus="counter">
            <div class="post-preview">
             	 <a href="${linkTo[PostController].show(post.id,post.sluggedTitle)}" title="${post.title}">
                        <h2 class="post-title">
                           ${post.title}
                        </h2>
                        <h3 class="post-subtitle">
                            ${post.description}
                        </h3>
                    </a>
                    <p class="post-meta"><fmt:message key="post.by"/> <a href="${linkTo[IndexController].byAuthor(post.author.id,post.author.name)}"  title="<fmt:message key="author.seePosts"/> ${post.author.name}">
                    	${post.author.name}
                    </a> <fmt:message key="post.onday"/> <fmt:formatDate value="${post.createdDate}" pattern="dd 'de' MMMM 'de' YYYY" />
                    <c:if test="${!empty post.category.name}">
                      <fmt:message key="post.in"/>  <a href="${linkTo[IndexController].byCategory(post.category.name)}"> ${post.category.name}</a>
                    </c:if>
                    </p>
                </div> 
                <hr>
            </c:forEach>
           <hr>

   
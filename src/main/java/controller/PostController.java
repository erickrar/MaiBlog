package controller;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.brutauth.auth.annotations.CustomBrutauthRules;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import dao.CategoryDAO;
import dao.CommentDAO;
import dao.PostDAO;
import entity.Comment;
import entity.LoggedProfile;
import rule.LoggedRule;
import rule.UserIsthePostAuthorRule;
import util.FastI18nMessage;

@Controller
public class PostController {

	@Inject private CommentDAO commentRepository;
	@Inject private PostDAO postRepository;
	@Inject private Result result;
	@Inject private LoggedProfile loggedUser;
	@Inject private Validator validator;

	@Get
	@Path({"/post/{id}-{sluggedTitle}"})
	public void show(Long id, String sluggedTitle){

		try {
			entity.Post post= postRepository.searchById(id.longValue());
			List<Comment> comments = commentRepository.getPostComments(id.longValue());
			result.include("comments", comments);
			result.include("post",post); 
		} catch (Exception e) {
			e.printStackTrace();
			result.redirectTo(ErrorController.class).errorpage();
		}	
	}
	
	@Get
	@Path({"/post/{id}"})
	public void show(Long id){
		try {
			entity.Post post= postRepository.searchById(id.longValue());
			result.redirectTo(this).show(post.getId(), post.getSluggedTitle());
		} catch (Exception e) {
			e.printStackTrace();
			result.redirectTo(ErrorController.class).errorpage();
		}	
	}

	@CustomBrutauthRules(LoggedRule.class)
	public void newPost(){ 
		try {
			result.include("categories", new CategoryDAO().listAll());
		} catch (Exception e) {
			e.printStackTrace();
			result.redirectTo(ErrorController.class).errorpage();
		}
	}
	
	@Get
	@Path({"/post/{id}/editar"})
	@CustomBrutauthRules(UserIsthePostAuthorRule.class)
	public void edit(Long id){
		try {
			validator.onErrorUse(Results.referer()).redirect();
			result.include("categories", new CategoryDAO().listAll());
			entity.Post post= postRepository.searchById(id);
			result.include("post",post);
		} catch (Exception e) {
			e.printStackTrace();
			result.redirectTo(ErrorController.class).errorpage();
		}
	}

	@Post
	public void save(entity.Post post){
		try {
			
			post.setAuthor(loggedUser.getProfile());
			postRepository.insert(post);
			result.include("messages",FastI18nMessage.getMessage("post.success.create"));
			result.redirectTo(IndexController.class).index();
		} catch (Exception e) {
			e.printStackTrace();
			result.redirectTo(ErrorController.class).errorpage();
		}
	}
	
	
	public void update(entity.Post post){
		try {
			post.setAuthor(loggedUser.getProfile());
			postRepository.update(post);
			result.include("messages",FastI18nMessage.getMessage("post.success.update"));
			result.redirectTo(PostController.class).show(post.getId(), post.getSluggedTitle());
		} catch (Exception e) {
			e.printStackTrace();
			result.redirectTo(ErrorController.class).errorpage();
		}
	}
	
	public void remove(entity.Post post){
		try {
			postRepository.delete(post);
			result.include("messages",FastI18nMessage.getMessage("post.success.delete"));
			result.redirectTo(IndexController.class).index();
		} catch (Exception e) {
			e.printStackTrace();
			result.redirectTo(ErrorController.class).errorpage();
		}
	}
}

package controller;

import java.sql.SQLException;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import dao.PostDAO;
import dao.filter.PostFilter;
import entity.Post;

@Controller
public class IndexController {

	@Inject private Result result;
	@Inject private PostDAO postRepository;

	@Path("/")
	@ApplicationScoped
	public void index() {
		try {
			List<Post> posts = postRepository.listAll();
			result.include("posts",posts);
		} catch (SQLException e) {
			e.printStackTrace();
			result.redirectTo(ErrorController.class).errorpage();
		}
	}
	

	@Get
	@Path({"/post/por/{id}-{authorName}"})
	public void byAuthor(Long id, String authorName){
		try {
			PostFilter pf = new PostFilter();
			pf.byAuthor(id);
			List<Post> posts = postRepository.search(pf);
			result.include("posts",posts);
			result.of(this).index();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	

	@Get
	@Path({"/post/categoria/{name}"})
	public void byCategory(String name){
		try {
			PostFilter pf = new PostFilter();
			pf.byCategory(name);
			List<Post> posts = postRepository.search(pf);
			result.include("posts",posts);
			result.of(this).index();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}
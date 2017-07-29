package controller;

import java.sql.SQLException;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import dao.CommentDAO;

@Controller
public class CommentController {

	@Inject private CommentDAO commentRepository;
	@Inject private Result result;
	
	@Post
	public void save(entity.Comment comment){
		try {
			commentRepository.insert(comment);
			result.redirectTo(PostController.class).show(comment.getPost().getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
package painter.tracker.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
import painter.tracker.db.dao.FigurineDao;

@Controller
@RequiredArgsConstructor
public class FigurinesController {

	private final SqlSession session;

	@GetMapping("/figurines")
	public ModelAndView list() {
		return new ModelAndView("figurines").addObject("figurines", session.getMapper(FigurineDao.class).list());
	}

}

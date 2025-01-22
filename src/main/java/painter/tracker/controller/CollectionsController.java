package painter.tracker.controller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
import painter.tracker.db.dao.HomeDao;
import painter.tracker.db.dbo.Collection;

@Controller
@RequiredArgsConstructor
public class CollectionsController {

	final private SqlSession session;

	@GetMapping({ "/", "/collections" })
	public ModelAndView home() {
		List<Collection> collections = session.getMapper(HomeDao.class).list();
		return new ModelAndView("collections").addObject("collections", collections);
	}
}

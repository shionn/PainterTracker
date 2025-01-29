package painter.tracker.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		return new ModelAndView("collections").addObject("collections", collections).addObject("totals",
				buildTotals(collections));
	}

	private Map<String, Object> buildTotals(List<Collection> collections) {
		Map<String, Object> totals = new HashMap<>();
		totals.put("qty", collections.stream().map(c -> c.getQty()).reduce(0, (a, b) -> a + b));
		totals.put("painted", collections.stream().map(c -> c.getPaintedQty()).reduce(0, (a, b) -> a + b));
		return totals;
	}
}

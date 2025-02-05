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
public class GameController {

	final private SqlSession session;

	@GetMapping({ "/", "/games" })
	public ModelAndView games() {
		List<Collection> games = session.getMapper(HomeDao.class).listGames();
		return new ModelAndView("games").addObject("games", games).addObject("totals", buildTotals(games));
	}

	private Map<String, Object> buildTotals(List<Collection> collections) {
		Map<String, Object> totals = new HashMap<>();
		totals.put("qty", collections.stream().map(c -> c.getQty()).reduce(0, (a, b) -> a + b));
		totals.put("painted", collections.stream().map(c -> c.getPaintedQty()).reduce(0, (a, b) -> a + b));
		return totals;
	}

}

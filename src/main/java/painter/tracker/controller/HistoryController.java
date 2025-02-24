package painter.tracker.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
import painter.tracker.controller.history.History;
import painter.tracker.db.dao.HistoryDao;
import painter.tracker.db.dbo.EventHistory;
import painter.tracker.db.dbo.ShameState;

@Controller
@RequiredArgsConstructor
public class HistoryController {

	final private SqlSession session;

	@GetMapping("/history")
	public ModelAndView games() {
		List<ShameState> shames = session.getMapper(HistoryDao.class).listHistory();
		List<String> games = shames.stream().map(ShameState::getGame).map(this::shortGameName).distinct().toList();
		List<History> histories = shames
				.stream()
				.collect(Collectors.groupingBy(s -> s.getMonth()))
				.entrySet()
				.stream()
				.map(e -> History.builder().month(e.getKey()).shames(e.getValue()).build())
				.sorted()
				.toList();
		List<EventHistory> events = session.getMapper(HistoryDao.class).listEventHistory();
		return new ModelAndView("history").addObject("histories", histories)
				.addObject("events", events)
				.addObject("games", games);
	}

	private String shortGameName(String name) {
		return switch (name) {
		case "Dungeon & Laser" -> "D&L";
		case "Warhammer 40K" -> "40k";
		default -> name;
		};
	}

}

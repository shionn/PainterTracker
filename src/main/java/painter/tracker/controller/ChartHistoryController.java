package painter.tracker.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.apache.ibatis.session.SqlSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.RequiredArgsConstructor;
import painter.tracker.controller.chart.Chart;
import painter.tracker.controller.chart.ChartData;
import painter.tracker.controller.chart.ChartData.ChartDataBuilder;
import painter.tracker.controller.chart.ChartDataSets;
import painter.tracker.controller.chart.ChartDataSets.ChartDataSetsBuilder;
import painter.tracker.db.dao.HomeDao;
import painter.tracker.db.dbo.ShameState;

@Controller
@RequiredArgsConstructor
public class ChartHistoryController {

	final private SqlSession session;

	@GetMapping(path = "/games/chart/history", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public Chart gamesChart() {
		List<ShameState> shames = session.getMapper(HomeDao.class).listShameHistory();
		return Chart.builder().title("Games").data(toChartData(shames)).type("line").build();
	}

	private ChartData<Integer> toChartData(List<ShameState> shames) {
		ChartDataBuilder<Integer> data = ChartData.builder();
		return data.labels(toLabels(shames)).datasets(toDataSets(shames)).build();
	}

	private List<String> toLabels(List<ShameState> shames) {
		return shames.stream().map(s -> s.getMonth()).distinct().map(i -> (i % 100) + "/" + (i / 100)).toList();
	}

	private List<ChartDataSets<Integer>> toDataSets(List<ShameState> shames) {
//		List<String> ignored = Arrays.asList("Decort", "Jeu", "Repaint");
		List<String> games = shames
				.stream()
				.map(s -> s.getGame())
				.distinct()
				// .filter(g -> !ignored.contains(g))
				.sorted()
				.toList();
		List<ChartDataSets<Integer>> results = new ArrayList<>();
		games.forEach(game -> {
			results.add(toDataSet(game, game + " P", shames, ShameState::getPaintedTotal));
			results.add(toDataSet(game, game + " S", shames, ShameState::getShameTotal));
		});
		return results;
	}

	private ChartDataSets<Integer> toDataSet(String game, String title, List<ShameState> shames,
			Function<ShameState, Integer> submapper) {
		ChartDataSetsBuilder<Integer> dataset = ChartDataSets.builder();
		return dataset
				.label(title)
				.borderColor(toBorderColor(title))
				.data(shames.stream().filter(s -> s.getGame().equalsIgnoreCase(game)).map(submapper).toList())
				.build();
	}

	private String toBorderColor(String title) {
		return switch (title) {
		case "ALL P" -> "#FFFFFF";
		case "ALL S" -> "#AAAAAA";

		case "AoS P" -> "#FF00FF";
		case "AoS S" -> "#AA00AA";
		case "Battle P" -> "#00FFFF";
		case "Battle S" -> "#00AAAA";
		case "Dungeon & Laser P" -> "#FFFF00";
		case "Dungeon & Laser S" -> "#AAAA00";

		case "King of War P" -> "#FFAA00";
		case "King of War S" -> "#AA7700";
		case "Jeu P" -> "#00FFAA";
		case "Jeu S" -> "#00AA77";

		case "Divers P" -> "#00FF00";
		case "Divers S" -> "#00AA00";
		case "Warhammer 40K P" -> "#FF0000";
		case "Warhammer 40K S" -> "#AA0000";

		default -> "#FF0000";
		};
	}

}

package painter.tracker.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.apache.ibatis.session.SqlSession;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
import painter.tracker.db.dao.FigurineDao;
import painter.tracker.db.dbo.Figurine;

@Controller
@RequiredArgsConstructor
@SessionScope
public class FigurinesController implements Serializable {

	private static final long serialVersionUID = -3742689706457414671L;

	private Filter filter = new Filter();

	private final SqlSession session;

	@GetMapping("/figurines")
	public ModelAndView list() {
		FigurineDao dao = session.getMapper(FigurineDao.class);
		return new ModelAndView("figurines")
				.addObject("figurines", applyFilter(dao))
				.addObject("games", dao.listGames())
				.addObject("collections", dao.listCollections())
				.addObject("sorts", FilterSort.values())
				.addObject("filter", filter);
	}

	@GetMapping("/figurines/filter")
	public String filter(@RequestParam("game") String game, @RequestParam("collection") String collection,
			@RequestParam("sort") FilterSort sort) {
		filter.setGame(game);
		filter.setCollection(collection);
		filter.setSort(sort);
		return "redirect:/figurines";
	}

	@GetMapping("/figurines/create")
	public String create() {
		Figurine figurine = Figurine.builder().acquireDate(new Date()).build();
		session.getMapper(FigurineDao.class).create(figurine);
		session.commit();
		return "redirect:/figurines/edit/" + figurine.getId();
	}

	@GetMapping("/figurines/edit/{id}")
	public ModelAndView edit(@PathVariable("id") int id) {
		FigurineDao dao = session.getMapper(FigurineDao.class);
		Figurine figurine = dao.read(id);
		return new ModelAndView("figurines")
				.addObject("figurines", applyFilter(dao))
				.addObject("figurine", figurine)
				.addObject("games", dao.listGames())
				.addObject("collections", dao.listCollections())
				.addObject("sorts", FilterSort.values())
				.addObject("filter", filter);
	}

	@GetMapping("/figurines/clone/{id}")
	public String clone(@PathVariable("id") int id) {
		FigurineDao dao = session.getMapper(FigurineDao.class);
		Figurine figurine = dao.read(id);
		dao.create(figurine);
		dao.update(figurine);
		session.commit();
		return "redirect:/figurines/edit/" + figurine.getId();
	}

	@PostMapping("/figurines/edit")
	public String edit(@RequestParam("id") int id, @RequestParam("name") String name,
			@RequestParam("collection") String collection, @RequestParam("game") String game,
			@RequestParam("acquireDate") @DateTimeFormat(iso = ISO.DATE) Date acquireDate, @RequestParam("qty") int qty,
			@RequestParam("description") String description) {

		Figurine figurine = Figurine.builder().id(id).acquireDate(acquireDate).collection(collection)
				.description(description).name(name).qty(qty).game(game).build();
		FigurineDao dao = session.getMapper(FigurineDao.class);
		dao.update(figurine);
		session.commit();
		return "redirect:/figurines";
	}

	private List<Figurine> applyFilter(FigurineDao dao) {
		Stream<Figurine> stream = dao.list(filter.getSort().getDb()).stream();
		if (this.filter.getCollection() != null && !filter.getCollection().equalsIgnoreCase("ALL")) {
			stream = stream.filter(f -> f.getCollection().equalsIgnoreCase(filter.getCollection()));
		}
		if (this.filter.getGame() != null && !filter.getGame().equalsIgnoreCase("ALL")) {
			stream = stream.filter(f -> f.getGame().equalsIgnoreCase(filter.getGame()));
		}
		return stream.toList();
	}

}

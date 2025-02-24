package painter.tracker.controller;

import lombok.Data;

@Data
public class Filter {
	private String game;
	private String collection;
	private FilterSort sort = FilterSort.Jeu;
}

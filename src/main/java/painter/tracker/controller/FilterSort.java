package painter.tracker.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FilterSort {
	Jeu("game, collection, name, acquire_date DESC"),
	Figurine("name"),
	Aquisition("acquire_date DESC, game, collection, name");
	
	private final String db;
}

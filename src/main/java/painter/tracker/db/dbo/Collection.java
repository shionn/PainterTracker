package painter.tracker.db.dbo;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Collection {

	private String name;
	private int qty, paintedQty;
	private List<Figurine> figurines;
	private List<Collection> collections;

	public double getProgress() {
		return paintedQty * 100.0 / qty;
	}

	public int getIntProgress() {
		return (int) getProgress();
	}

	public String getColor() {
		int hue = name.replaceAll("[^A-Za-z]", "").chars().map(a -> a - 64).reduce(0, (a, b) -> a + b)
				% 360;
		return "hsl(" + hue + ",100%,50%)";
	}

}

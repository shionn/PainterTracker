package painter.tracker.controller.history;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import painter.tracker.db.dbo.ShameState;

@Data
@Builder
public class History implements Comparable<History> {

	private int month;
	private List<ShameState> shames;

	public String getFormatedMonth() {
		String formated = (month % 100) + "/" + (month / 100);
		if (formated.length() == 6) {
			formated = "0" + formated;
		}
		return formated;
	}

	@Override
	public int compareTo(History o) {
		return Integer.compare(o.month, this.month);
	}
}

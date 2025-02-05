package painter.tracker.db.dbo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShameState {
	private int month;
	private String game;
	private int acquiredQty, paintedQty, shameVar, acquiredTotal, paintedTotal, shameTotal;

	public String getVarColor() {
		if (shameVar < 0) {
			return "#008800";
		}
		if (shameVar > 0) {
			return "#880000";
		}
		return "none";
	}
}

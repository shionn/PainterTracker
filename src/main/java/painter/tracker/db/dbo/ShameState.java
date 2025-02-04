package painter.tracker.db.dbo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShameState {
	private int month;
	private String game;
	private int acquiredQty, paintedQty, shameVar, acquiredTotal, paintedTotal, shameTotal;
}

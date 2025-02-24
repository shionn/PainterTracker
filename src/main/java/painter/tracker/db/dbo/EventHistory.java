package painter.tracker.db.dbo;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventHistory {

	private EventHistoryType type;
	private Date date;
	private Figurine figurine;

}

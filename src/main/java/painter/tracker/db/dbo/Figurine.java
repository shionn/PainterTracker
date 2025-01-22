package painter.tracker.db.dbo;

import java.sql.Date;
import java.time.Duration;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Figurine {
	private String name;
	private String collection;
	private int qty;
	private boolean painted;
	private Duration duration;
	private Date acquireDate, paintedDate;
}

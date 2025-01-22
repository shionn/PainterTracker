package painter.tracker.db.dbo;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Figurine {
	private int id;
	private String name;
	private String collection;
	private int qty;
	private boolean painted;
	private String duration;
	private Date acquireDate, paintedDate;

	public String getFormatedDuration() {
		return duration;
	}

}

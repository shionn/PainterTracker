package painter.tracker.db.dbo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Figurine implements HaveDuration {
	private int id;
	private String name;
	private String collection, game;
	private String description;
	private int qty;
	private boolean painted;
	private long duration;
	private Date acquireDate, paintedDate;

	public boolean available(Job job) {
		return job.getDate().after(acquireDate);
	}
}

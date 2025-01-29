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
public class Figurine {
	private int id;
	private String name;
	private String collection, game;
	private String description;
	private int qty;
	private boolean painted;
	private int duration;
	private Date acquireDate, paintedDate;

	public String getFormatedDuration() {
		if (duration > 0) {
			int h = duration / 3600;
			String m = Integer.toString((duration / 60) % 60);
			if (m.length() == 1) {
				m = "0" + m;
			}
			return h + ":" + m;
		}
		return null;
	}

	public boolean available(Job job) {
		return job.getDate().after(acquireDate);
	}
}

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
public class JobSession implements HaveDuration {
	private int id;
	private Date start, end;
	private String description;

	public long getDuration() {
		return (end.getTime() - start.getTime()) / 1000;
	}
}

package painter.tracker.db.dbo;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Job {
	private int id;
	private boolean finished;
	private Date date, start, end;
	private int size;

	private List<Figurine> figurines;

	public String getDescription() {
		String desc = figurines.stream().map(f -> f.getQty() + "x " + f.getName()).collect(Collectors.joining(", "));
		if (desc.length() > 45) {
			desc = desc.substring(0, 45).concat("...");
		}
		return desc;
	}
}

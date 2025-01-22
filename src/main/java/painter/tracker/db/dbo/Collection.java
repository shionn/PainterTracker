package painter.tracker.db.dbo;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Collection {

	private String name;
	private int qty, paintedQty;
	private List<Figurine> figurines;

}

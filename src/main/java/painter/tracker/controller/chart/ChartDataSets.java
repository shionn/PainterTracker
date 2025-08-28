package painter.tracker.controller.chart;

import java.util.List;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;

@Data
@Builder
public class ChartDataSets<T> {

	private String label;
	private List<T> data;
	@Default()
	private int borderWidth = 1;
	@Default()
	private boolean fill = false;
	@Default
	private String borderColor = "#000000";
	@Default
	private float tension = 0;
	@Default
	private boolean stepped = true;
}

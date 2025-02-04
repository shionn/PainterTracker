package painter.tracker.controller.chart;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Chart {
	private String title;
	private ChartData data;
}

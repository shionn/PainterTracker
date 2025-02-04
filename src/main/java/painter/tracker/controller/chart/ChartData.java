package painter.tracker.controller.chart;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChartData {
	private List<String> labels;
	private List<ChartDataSets> datasets;
}

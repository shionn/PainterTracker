package painter.tracker.controller.chart;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChartData<T> {
	private List<String> labels;
	private List<ChartDataSets<T>> datasets;
}

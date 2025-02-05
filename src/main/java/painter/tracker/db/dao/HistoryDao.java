package painter.tracker.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import painter.tracker.db.dbo.ShameState;

public interface HistoryDao {

	@Select("SELECT * FROM game_shame_history_v ORDER BY month DESC, game ASC")
	List<ShameState> listHistory();
}

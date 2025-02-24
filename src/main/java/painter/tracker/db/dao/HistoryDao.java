package painter.tracker.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import painter.tracker.db.dbo.EventHistory;
import painter.tracker.db.dbo.ShameState;

public interface HistoryDao {

	@Select("SELECT * FROM game_shame_history_v ORDER BY month DESC, game ASC")
	List<ShameState> listHistory();

	@Select("SELECT * FROM event_history_v ORDER BY date DESC, game, collection, name")
	@Results({ @Result(column = "name", property = "figurine.name"),
			@Result(column = "collection", property = "figurine.collection"),
			@Result(column = "game", property = "figurine.game"),
			@Result(column = "qty", property = "figurine.qty"), })
	List<EventHistory> listEventHistory();
}

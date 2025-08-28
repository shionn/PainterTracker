package painter.tracker.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import painter.tracker.db.dbo.Collection;

public interface ChartDao {

	@Select("SELECT game AS name, qty, painted_qty FROM game_v ORDER BY game")
	@Results({
			@Result(column = "name", property = "name"),
			@Result(column = "name", property = "collections", many = @Many(select = "listArmies"))
	})
	List<Collection> listGames();

	@Select("SELECT collection AS name, qty, painted_qty FROM collection_v WHERE game = #{game} ORDER BY collection")
	List<Collection> listArmies(String game);

}

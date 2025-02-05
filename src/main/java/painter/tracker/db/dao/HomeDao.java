package painter.tracker.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import painter.tracker.db.dbo.Collection;
import painter.tracker.db.dbo.Figurine;
import painter.tracker.db.dbo.ShameState;

public interface HomeDao {

	@Select("SELECT * FROM game_v ORDER BY game")
	@Results({
			@Result(column = "game", property = "name"),
			@Result(column = "game", property = "figurines", many = @Many(select = "listGameFigurines")),
			@Result(column = "game", property = "collections", many = @Many(select = "listGameCollection"))
	})
	public List<Collection> listGames();

	@Select("SELECT * FROM figurine_v WHERE game = #{game} ORDER BY collection, name")
	public List<Figurine> listGameFigurines(String game);

	@Select("SELECT * FROM collection_v WHERE game = #{game} ORDER BY collection")
	@Results({ @Result(column = "collection", property = "name"),
			@Result(column = "{collection=collection,game=game}", property = "figurines", many = @Many(select = "listFigurines")) })
	public List<Collection> listGameCollection(String game);

	@Select("SELECT * FROM figurine_v WHERE collection = #{collection} AND game = #{game} ORDER BY name")
	public List<Figurine> listFigurines(@Param("collection") String collection, @Param("game") String game);

	@Select("SELECT * FROM game_shame_history_v ORDER BY month, game")
	public List<ShameState> listShameHistory();

}

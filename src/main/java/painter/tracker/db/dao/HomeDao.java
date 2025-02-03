package painter.tracker.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import painter.tracker.db.dbo.Collection;
import painter.tracker.db.dbo.Figurine;

public interface HomeDao {

	@Select("SELECT * FROM collection_v ORDER BY collection")
	@Results({
			@Result(column = "collection", property = "name"),
			@Result(column = "collection", property = "figurines", many = @Many(select = "listFigurines"))
	})
	public List<Collection> listCollections();

	@Select("SELECT * FROM figurine_v WHERE collection = #{collection} ORDER BY name")
	public List<Figurine> listFigurines(String collection);

	@Select("SELECT * FROM game_v ORDER BY game")
	@Results({
			@Result(column = "game", property = "name"),
			@Result(column = "game", property = "figurines", many = @Many(select = "listGameFigurines"))
	})
	public List<Collection> listGames();

	@Select("SELECT * FROM figurine_v WHERE game = #{game} ORDER BY collection, name")
	public List<Figurine> listGameFigurines(String game);

}

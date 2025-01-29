package painter.tracker.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import painter.tracker.db.dbo.Figurine;

public interface FigurineDao {

	@Select("SELECT * FROM figurine_v ORDER BY game, collection, acquire_date DESC, name")
	List<Figurine> list();

	@Select("SELECT * FROM figurine WHERE id = #{id}")
	Figurine read(int id);

	@Insert("INSERT INTO figurine (name, collection, game, acquire_date) VALUE ('', '', '', #{acquireDate} )")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	int create(Figurine figurine);

	@Update("UPDATE figurine "
			+ "SET name = #{name}, collection = #{collection}, game = #{game}, description = #{description}, qty = #{qty}, acquire_date = #{acquireDate} "
			+ "WHERE id = #{id}")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	int update(Figurine figurine);

	@Select("SELECT DISTINCT(game) FROM figurine ORDER BY game")
	List<String> listGames();

	@Select("SELECT DISTINCT(collection) FROM figurine ORDER BY collection")
	List<String> listCollections();

}

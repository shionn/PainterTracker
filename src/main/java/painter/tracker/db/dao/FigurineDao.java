package painter.tracker.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import painter.tracker.db.dbo.Figurine;

public interface FigurineDao {

	@Select("SELECT * FROM figurine_v ORDER BY collection, acquire_date DESC, name")
	List<Figurine> list();

}

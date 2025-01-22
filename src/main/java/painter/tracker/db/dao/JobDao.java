package painter.tracker.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import painter.tracker.db.dbo.Figurine;
import painter.tracker.db.dbo.Job;

public interface JobDao {

	@Select("SELECT * FROM paint_job_v ORDER BY date DESC")
	@Results({ @Result(column = "id", property = "id"),
			@Result(column = "id", property = "figurines", many = @Many(select = "listJobFigurines")) })
	List<Job> list();

	@Select("SELECT * FROM figurine WHERE id IN ( SELECT figurine FROM paint_job_content WHERE job = #{id} )")
	List<Figurine> listJobFigurines(int id);

}

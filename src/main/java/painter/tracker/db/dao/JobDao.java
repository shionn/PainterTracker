package painter.tracker.db.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import painter.tracker.db.dbo.Figurine;
import painter.tracker.db.dbo.Job;
import painter.tracker.db.dbo.JobSession;

public interface JobDao {

	@Select("SELECT * FROM paint_job_v ORDER BY start DESC")
	@Results({ @Result(column = "id", property = "id"),
			@Result(column = "id", property = "figurines", many = @Many(select = "listJobFigurines")),
			@Result(column = "id", property = "sessions", many = @Many(select = "listJobSessions")) })
	List<Job> list();

	@Select("SELECT * FROM figurine WHERE id IN ( SELECT figurine FROM paint_job_content WHERE job = #{id} )")
	List<Figurine> listJobFigurines(int id);

	@Select("SELECT * FROM figurine WHERE id NOT IN ( SELECT figurine FROM paint_job_content) ORDER BY name, acquire_date")
	List<Figurine> listNotInJobFigurines();

	@Select("SELECT * FROM paint_job_session WHERE job = #{id} ORDER BY start DESC")
	List<JobSession> listJobSessions(int id);

	@Insert("INSERT INTO paint_job () VALUES ()")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	int create(Job job);

	@Update("UPDATE paint_job SET date = #{date}, finished = #{finished} WHERE id = #{job}")
	int edit(@Param("job") int job, @Param("date") Date date, @Param("finished") boolean finished);

	@Insert("INSERT INTO paint_job_content (job,figurine) VALUES (#{job}, #{figurine})")
	int addJobContent(@Param("job") int job, @Param("figurine") int figurine);

	@Insert("INSERT INTO paint_job_session (job, start) VALUES (#{job}, #{start})")
	int createSessionForJob(@Param("job") int job, @Param("start") Date start);

	@Update("UPDATE paint_job_session SET description = #{description} WHERE id = #{session}")
	int updateJobSessionDescription(@Param("session") int session, @Param("description") String description);

	@Update("UPDATE paint_job_session SET end = #{end} WHERE id = #{session}")
	int updateJobSessionEnd(@Param("session") int session, @Param("end") Date end);
}

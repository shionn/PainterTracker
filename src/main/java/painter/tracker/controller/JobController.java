package painter.tracker.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
import painter.tracker.db.dao.JobDao;

@Controller
@RequiredArgsConstructor
public class JobController {

	private final SqlSession session;

	@GetMapping("/jobs")
	public ModelAndView list() {
		JobDao dao = session.getMapper(JobDao.class);
		return new ModelAndView("jobs").addObject("jobs", dao.list());
	}

}

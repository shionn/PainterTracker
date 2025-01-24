package painter.tracker.controller;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
import painter.tracker.db.dao.JobDao;
import painter.tracker.db.dbo.Figurine;
import painter.tracker.db.dbo.Job;

@Controller
@RequiredArgsConstructor
public class JobController {

	private final SqlSession session;

	@GetMapping("/jobs")
	public ModelAndView list() {
		JobDao dao = session.getMapper(JobDao.class);
		return new ModelAndView("jobs").addObject("jobs", dao.list());
	}

	@GetMapping("/jobs/create")
	public String create() {
		Job job = new Job();
		session.getMapper(JobDao.class).create(job);
		session.commit();
		return "redirect:/jobs/edit/" + job.getId();
	}

	@GetMapping("/jobs/edit/{id}")
	public ModelAndView edit(@PathVariable(name = "id") int id) {
		JobDao dao = session.getMapper(JobDao.class);
		List<Job> jobs = dao.list();
		List<Figurine> figurines = dao.listNotInJobFigurines();
		Job job = jobs.stream().filter(j -> j.getId() == id).findAny().get();
		return new ModelAndView("jobs").addObject("jobs", jobs).addObject("job", job).addObject("figurines", figurines);
	}

	@PostMapping("/jobs/edit")
	public String edit(@RequestParam(name = "job") int job,
			@RequestParam(name = "date") @DateTimeFormat(iso = ISO.DATE) Date date,
			@RequestParam(name = "finished", defaultValue = "false") boolean finished) {
		JobDao dao = session.getMapper(JobDao.class);
		dao.edit(job, date, finished);
		session.commit();
		if (finished) {
			return "redirect:/jobs";
		}
		return "redirect:/jobs/edit/" + job;
	}
	@PostMapping("/jobs/add-figurine")
	public String addFigurine(@RequestParam(name = "job") int job, @RequestParam(name = "figurine") int figurine) {
		JobDao dao = session.getMapper(JobDao.class);
		dao.addJobContent(job, figurine);
		session.commit();
		return "redirect:/jobs/edit/" + job;
	}
	

}

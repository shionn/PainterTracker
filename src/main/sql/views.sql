;


CREATE OR REPLACE VIEW paint_job_duration AS
SELECT pj.id as job, CAST(SUM(TIMEDIFF(pjs.end,pjs.start)) AS TIME) AS duration, min(pjs.start) AS start, max(pjs.end) AS end
	FROM paint_job AS pj
	LEFT JOIN paint_job_session AS pjs ON pjs.job = pj.id
	GROUP BY job;

CREATE OR REPLACE VIEW paint_job_size AS
SELECT pj.id as job, IFNULL(SUM(f.qty),0) AS size
	FROM paint_job AS pj
	LEFT JOIN paint_job_content AS pjc ON pjc.job = pj.id
	LEFT JOIN figurine AS f ON f.id = pjc.figurine
	GROUP BY job;

CREATE OR REPLACE VIEW paint_job_v AS
SELECT pj.id, finished, 
		date, IFNULL(start,date) AS start, IFNULL(end,date) AS end, 
		duration, size, CAST(SEC_TO_TIME(TIME_TO_SEC(duration)/size) AS TIME) AS average
	FROM paint_job AS pj
	LEFT JOIN paint_job_duration AS pjd ON pjd.job = pj.id
	LEFT JOIN paint_job_size AS pjs ON pjs.job = pj.id;
	
CREATE OR REPLACE VIEW figurine_v AS
SELECT f.id, f.name, f.qty, f.collection, f.description, 
	IFNULL(pj.finished,FALSE) AS painted, 
	CAST(SEC_TO_TIME(TIME_TO_SEC(pj.average)*f.qty) AS TIME) AS duration,
	f.acquire_date AS acquire_date, pj.end AS painted_date
	FROM figurine AS f 
	LEFT JOIN paint_job_content AS pjc ON f.id = pjc.figurine
	LEFT JOIN paint_job_v AS pj ON pj.id = pjc.job;

	
CREATE OR REPLACE VIEW collection_v AS
SELECT collection, SUM(qty) AS qty, SUM(painted*qty) as painted_qty, CAST(SUM(duration) AS TIME) as duration
	FROM figurine_v 
	GROUP BY collection;
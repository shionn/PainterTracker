;


CREATE OR REPLACE VIEW paint_job_duration AS
SELECT pjs.job as job, SUM(TIME_TO_SEC(TIMEDIFF(pjs.end,pjs.start))) AS duration, min(pjs.start) AS start, max(pjs.end) AS end
	FROM paint_job_session AS pjs
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
		IFNULL(pjd.duration, pj.duration) as duration, size, IFNULL(pjd.duration, pj.duration)/size AS average
	FROM paint_job AS pj
	LEFT JOIN paint_job_duration AS pjd ON pjd.job = pj.id
	LEFT JOIN paint_job_size AS pjs ON pjs.job = pj.id;
	
CREATE OR REPLACE VIEW figurine_v AS
SELECT f.id, f.name, f.qty, f.game, f.collection, f.description, 
	IFNULL(pj.finished,FALSE) AS painted, 
	pj.average*f.qty AS duration,
	f.acquire_date AS acquire_date, EXTRACT(YEAR_MONTH FROM f.acquire_date) AS acquire_month, 
	pj.end AS painted_date, EXTRACT(YEAR_MONTH FROM pj.end) AS painted_month
	FROM figurine AS f 
	LEFT JOIN paint_job_content AS pjc ON f.id = pjc.figurine
	LEFT JOIN paint_job_v AS pj ON pj.id = pjc.job;

CREATE OR REPLACE VIEW collection_v AS
SELECT collection, SUM(qty) AS qty, SUM(painted*qty) as painted_qty, SUM(duration) as duration
	FROM figurine_v 
	GROUP BY collection;

CREATE OR REPLACE VIEW month_activity_v AS
SELECT month
FROM ( SELECT acquire_month AS month FROM figurine_v) acquire
UNION ( SELECT painted_month AS month FROM figurine_v WHERE painted IS TRUE);

CREATE OR REPLACE VIEW figurine_acquire_per_month_v AS
	SELECT DISTINCT(acquire_month) AS month, SUM(qty) AS qty, 'ALL' as game 
	FROM figurine_v GROUP BY month 
UNION
	SELECT DISTINCT(acquire_month) AS month, SUM(qty) AS qty, game 
	FROM figurine_v GROUP BY game,month;

CREATE OR REPLACE VIEW figurine_painted_per_month_v AS
	SELECT DISTINCT(EXTRACT(YEAR_MONTH FROM painted_date)) AS month, SUM(qty) AS qty, 'ALL' as game
	FROM figurine_v WHERE painted IS TRUE GROUP BY month 
UNION
	SELECT DISTINCT(EXTRACT(YEAR_MONTH FROM painted_date)) AS month, SUM(qty) AS qty, game
	FROM figurine_v WHERE painted IS TRUE GROUP BY game, month;

SELECT * 
FROM month_activity_v AS m, ( SELECT DISTINCT(game) AS game FROM figurine UNION SELECT 'ALL' ) AS g
LEFT JOIN figurine_acquire_per_month_v AS a ON a.month = m.month AND a.game = g.game
LEFT JOIN figurine_painted_per_month_v AS p ON p.month = m.month AND p.game = g.game


	


SELECT * 
FROM month_activity_v m
LEFT 









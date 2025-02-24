package painter.tracker.db.dbo;

public interface HaveDuration {

	long getDuration();

	default String getFormatedDuration() {
		long duration = getDuration();
		if (duration > 0) {
			long h = duration / 3600;
			String m = Long.toString((duration / 60) % 60);
			if (m.length() == 1) {
				m = "0" + m;
			}
			return h + ":" + m;
		}
		return null;
	}

}

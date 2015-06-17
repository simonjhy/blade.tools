package blade.migrate.api;

public interface Reporter {
	public int FORMAT_SHORT = 0 << 1;
	public int FORMAT_LONG = 0 << 2;

	public void beginReporting(int format);

	public void report(Problem problem);

	public void endReporting();

}

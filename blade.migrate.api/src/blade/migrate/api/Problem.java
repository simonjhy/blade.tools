package blade.migrate.api;

import java.io.File;

public class Problem
{
	public Problem() {
	}

	public Problem( String title, String url,
			String summary, String type, String ticket, File file,
			int lineNumber) {

		this.title = title;
		this.url = url;
		this.summary = summary;
		this.type = type;
		this.ticket = ticket;
		this.file = file;
		this.lineNumber = lineNumber;
	}

	public File file;
	public int lineNumber;
	public int number;
	public String summary;
	public String ticket;
	public String title;
	public String type;
	public String url;

}
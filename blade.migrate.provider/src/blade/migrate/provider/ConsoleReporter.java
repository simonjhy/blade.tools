package blade.migrate.provider;

import blade.migrate.api.Problem;
import blade.migrate.api.Reporter;

import dnl.utils.text.table.TextTable;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;

@Component
public class ConsoleReporter implements Reporter {

	@Override
	public void beginReporting(int format) {
		if (format == FORMAT_SHORT) {
			columnNames.add("Title");
			columnNames.add("Type");
			columnNames.add("File");
			columnNames.add("Line");
		}
		else {
			columnNames.add("Title");
			columnNames.add("Summary");
			columnNames.add("Url");
			columnNames.add("Type");
			columnNames.add("Ticket");
			columnNames.add("File");
			columnNames.add("Line");
		}
	}

	@Override
	public void endReporting() {
		Object[][] data = new Object[rowData.size()][columnNames.size()];

		for (int i = 0; i < rowData.size(); i++) {
			for (int j = 0; j < columnNames.size(); j++) {
				data[i][j] = rowData.get(i)[j];
			}
		}

		TextTable tt = new TextTable(columnNames.toArray(new String[0]), data);
		tt.setAddRowNumbering(true);
		tt.setSort(0);
		tt.printTable();

		this.columnNames.clear();
		this.rowData.clear();
	}

	@Override
	public void report(Problem problem) {
		if (columnNames.size() == 4) {
			rowData.add(new Object[] { problem.title, problem.type,
					problem.file.getName(), problem.lineNumber
				});
		} else {
			rowData.add(new Object[] { problem.title, problem.summary,
					problem.url, problem.type, problem.ticket,
					problem.file.getName(), problem.lineNumber
				});
		}
	}

	private final List<String> columnNames = new ArrayList<>();
	private final List<Object[]> rowData = new ArrayList<>();

}
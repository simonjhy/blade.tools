package blade.migrate.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import java.util.ArrayList;
import java.util.List;

public class FileHelper {

	public List<File> findFiles(final File dir, final String ext) {
		final List<File> files = new ArrayList<>();

		final FileVisitor<Path> visitor = new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(
					Path path, BasicFileAttributes attrs)
				throws IOException {
				File file = path.toFile();

				if (file.isFile())
				{
					if (file.getName().endsWith( ext ))
					{
						files.add(file);
					}
				}

				return super.visitFile(path, attrs);
			}
		};

		try {
			Files.walkFileTree(dir.toPath(), visitor);
		} catch (IOException e) {

			// TODO properly log exception

			e.printStackTrace();
		}

		return files;
	}

	public String readFile(File file)
		throws FileNotFoundException, IOException {

		String returnValue = null;

		try (FileInputStream stream = new FileInputStream(file)) {
			Reader r = new BufferedReader(new InputStreamReader(stream), 16384);
			StringBuilder result = new StringBuilder(16384);
			char[] buffer = new char[16384];

			int len;
			while ((len = r.read(buffer, 0, buffer.length)) >= 0) {
				result.append(buffer, 0, len);
			}

			returnValue = result.toString();
			r.close();

		}

		return returnValue;
	}

}
package com.loxx3450.hw_25_01_25;

import com.loxx3450.hw_25_01_25.task1.TxtFilesComparer;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
	public static void main(String[] args) {
		Path path1 = Paths.get("resources", "task1_file1.txt");
		Path path2 = Paths.get("resources", "task1_file2.txt");
		TxtFilesComparer.compare(path1, path2);
	}
}

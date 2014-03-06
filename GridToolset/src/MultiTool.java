/*
 * -- MultiTool --
 * 
 * This class represents the main interface 
 * to the other tools in the toolkit.
 * 
 * -- Crawler
 * -- Shingler
 * -- Comparator
 * 
 * */

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import toolset.ShingleComparator;
import toolset.Shingler;

/**
 * Add your name if you make edits.
 * 
 * @author Jordan Willis
 * 
 */
public class MultiTool {
	// TODO: implement a main to handle args and call the correct

	public static void main(String[] args) {

		final String helpBlock = "Crawler Shingler Tool -- V 0.1 \n\n"
				+ "\tUSAGE\n"
				+ "\t\tjava -jar MultiTool.jar crawl <seed> <runtime (min)>\n"
				+ "\t\tjava -jar MultiTool.jar shingle <path-to-file> \n"
				+ "\t\tjava -jar MultiTool.jar compare <path-to-file-1> <path-to-file-2>";

		if (args.length > 0) {

			if (args[0].equals("crawl")) {

			} else if (args[0].equals("shingle")) {

				// SHINGLE MODE
				if (!new File(args[1]).exists()) // check that the file exists
					System.err.println("Error: file not found \"" + args[2]
							+ "\"");
				else if (Integer.valueOf(args[2]) <= 0)
					System.err.println("Error: invalid shingle size");
				else {
					try {
						for (String s : Shingler.shingle(
								readFile(args[1], StandardCharsets.UTF_8),
								Integer.valueOf(args[2])))
							System.out.println(s);
					} catch (IOException e) {
						System.err
								.println("There was a problem reading from file: "
										+ e.getMessage());
					}
				}
			} else if (args[0].equals("compare")) {
				// SHINGLE MODE
				if (!new File(args[1]).exists()) // check that the file exists
					System.err.println("Error: file not found \"" + args[1]
							+ "\"");
				else if (!new File(args[2]).exists()) // check that the file
														// exists
					System.err.println("Error: file not found \"" + args[2]
							+ "\"");
				else
					try {
						System.out.println(ShingleComparator.compare(
								readFile(args[1], StandardCharsets.UTF_8)
										.split("\\n"),
								readFile(args[2], StandardCharsets.UTF_8)
										.split("\\n")));
					} catch (IOException e) {
						System.err
								.println("There was a problem reading from file: "
										+ e.getMessage());
					}

			} else
				System.out.println(helpBlock);
		} else
			System.out.println(helpBlock);

	}

	// http://stackoverflow.com/questions/326390/how-to-create-a-java-string-from-the-contents-of-a-file
	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return encoding.decode(ByteBuffer.wrap(encoded)).toString();
	}

}

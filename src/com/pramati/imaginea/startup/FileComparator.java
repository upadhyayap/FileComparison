/**
 * 
 */
package com.pramati.imaginea.startup;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.pramati.imaginea.bobj.TextFile;

/**
 * @author anandu
 *
 */
public class FileComparator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			if (args.length < 2) {
				System.out
						.println("Please enter atleast two file names to compare");
				return;
			}
			TextFile sourceFile = new TextFile(new File(args[0]));
			TextFile targetFile = new TextFile(new File(args[1]));
			Map<String, Set<String>> matches = sourceFile
					.intersection(targetFile);
			Iterator<Map.Entry<String, Set<String>>> matchiterator = matches
					.entrySet().iterator();
			while (matchiterator.hasNext()) {
				String matchingKey = matchiterator.next().getKey();
				Iterator<String> matchingwords = matches.get(matchingKey)
						.iterator();
				while (matchingwords.hasNext()) {
					System.out.println(matchingKey + "---- matched with----"
							+ matchingwords.next());
				}
			}
			System.out.println("Total " + matches.size() + "matches found");

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

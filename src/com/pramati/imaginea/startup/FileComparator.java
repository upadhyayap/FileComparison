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
			if (args.length!=2 ) {
				System.out
						.println("Please enter complete path of exact two file names separate by space Changed by aditya");
				return;
			}
			TextFile sourceFile = new TextFile(new File(args[0]));
			TextFile targetFile = new TextFile(new File(args[1]));
			Map<String, Set<String>> matches = sourceFile
					.intersection(targetFile);
			Iterator<Map.Entry<String, Set<String>>> matchIterator = matches
					.entrySet().iterator();
			while (matchIterator.hasNext()) {
				String matchingKey = matchIterator.next().getKey();
				Iterator<String> matchingWords = matches.get(matchingKey)
						.iterator();
				while (matchingWords.hasNext()) {
					System.out.println(matchingKey + "---- matched with----"
							+ matchingWords.next());
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

/**
 * 
 */
package com.pramati.imaginea.bobj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author anandu
 *
 */
public class TextFile {

	private File sourceFile;
	private Set<String> content = new HashSet<String>();
	private boolean contentLoaded = false;

	public TextFile(File File) {
		this.sourceFile = File;
	}

	public Set<String> getContent() throws FileNotFoundException, IOException {
		if (!this.contentLoaded) {
			read();
		}
		return content;
	}

	public Map<String, Set<String>> intersection(TextFile targetFile)
			throws IllegalArgumentException, IOException, FileNotFoundException {
		Map<String, Set<String>> matchingContent = new HashMap<String, Set<String>>();
		if (!this.getContent().isEmpty()) {
			Iterator<String> contentIterator = this.content.iterator();
			String lineData = "";
			while (contentIterator.hasNext()) {
				lineData = contentIterator.next();
				String directMatch = getDirectMatch(targetFile.getContent(),
						lineData);
				Set<String> zigZagMatch = new HashSet<String>();
				if (!directMatch.equals("")) {
					zigZagMatch.add(directMatch);
					matchingContent.put(lineData, zigZagMatch);
				} else if (!(zigZagMatch = getZigZagMatches(
						targetFile.getContent(), lineData, " ")).isEmpty()) {
					matchingContent.put(lineData, zigZagMatch);
				}
			}
		}
		return matchingContent;
	}

	private void read() throws IOException, FileNotFoundException {

		FileReader reader = new FileReader(sourceFile);
		BufferedReader buffer = new BufferedReader(reader);
		try {
			String PersonName = "";
			while (((PersonName = buffer.readLine()) != null)) {
				this.content.add(PersonName);
			}
			contentLoaded = true;
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				buffer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getDirectMatch(Set<String> content, String searchWord) {
		String match = "";
		if (!content.isEmpty()) {
			Iterator<String> contentIterator = content.iterator();
			while (contentIterator.hasNext()) {
				String nextWord = contentIterator.next();
				if (searchWord.equalsIgnoreCase(nextWord)) {
					match = nextWord;
				}
			}
		}
		return match;
	}

	public Set<String> getZigZagMatches(Set<String> content, String searchWord,
			String delimiter) {
		Set<String> matches = new HashSet<String>();
		String targetWord;
		Iterator<String> contentIterator = content.iterator();
		while (contentIterator.hasNext()) {
			targetWord = contentIterator.next();
			if (findCommonData(searchWord.split(delimiter),
					targetWord.split(delimiter)).size() >= 2) {
				matches.add(targetWord);
			}
		}
		return matches;
	}

	public Set<String> findCommonData(String[] sourceArray, String[] targetArray) {
		Set<String> commonData = new HashSet<String>(Arrays.asList(sourceArray));
		commonData.retainAll(new HashSet<>(Arrays.asList(targetArray)));
		return commonData;
	}
}

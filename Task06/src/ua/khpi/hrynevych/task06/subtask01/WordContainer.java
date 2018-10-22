package ua.khpi.hrynevych.task06.subtask01;

import java.util.ArrayList;
import java.util.Scanner;

// you can extend this class from one of the core container
// or aggregate it inside of class 
public class WordContainer {  

	private ArrayList<Word> wordList;
	
	public WordContainer() {
		wordList = new ArrayList<Word>();
	}
	
	public static String getText() {
		StringBuffer text = new StringBuffer();
		Scanner in = new Scanner(System.in);
		
		while (in.hasNextLine()) {
			String temp = in.nextLine();
			int stopPos = temp.indexOf("stop");
			
			if (stopPos == -1) {
				text.append(temp).append(" ");
			} else {
				text.append(temp.substring(0, stopPos));
				break;
			}
		}
		in.close();
		return text.toString();
	}
	
	public void fillTheList(String text) {
		String[] arr = text.split("[\\s]+");
		for (String s : arr) {
			if (s.length() == 0) {
				continue;
			}
			Word temp = new Word(s);
			int pos = wordList.indexOf(temp);
			
			if (pos == -1) {
				wordList.add(temp);
			} else {
				wordList.get(pos).increaseFrequency();
			}
		}
	}
	
	public void sort() {
		wordList.sort((o1, o2) -> o1.compareTo(o2));
	}
	
	public void printResult() {
		for (Word w : wordList) {
			System.out.println(w.getWord() + " : " + w.getFrequency());
		}
	}
	
	public static void main(String[] args) {
		String text = getText();
		WordContainer obj = new WordContainer();
		
		obj.fillTheList(text);
		obj.sort();
		obj.printResult();
	}
}

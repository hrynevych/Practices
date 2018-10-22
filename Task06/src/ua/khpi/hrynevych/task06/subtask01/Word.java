package ua.khpi.hrynevych.task06.subtask01;

public class Word implements Comparable<Word> {
	
	private String content;
	
	private int frequency;

	public Word(String content) {
		this.content = content;
		frequency = 1;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Word other = (Word) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		return true;
	}
	
	public void increaseFrequency() {
		frequency++;
	}
	
	public String getWord() {
		return content;
	}
	
	public int getFrequency() {
		return frequency;
	}

	@Override
	public int compareTo(Word o) {
		int dif = o.getFrequency() - this.getFrequency();
		
		if (dif == 0) {
			return this.getWord().compareTo(o.getWord());
		} else {
			return dif;
		}
	}
	
}

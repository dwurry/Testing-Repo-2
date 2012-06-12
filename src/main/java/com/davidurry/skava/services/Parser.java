package com.davidurry.skava.services;

import java.util.List;

public class Parser{
	String 			parseValue;
	int				firstRepeat;
	int				words;
	List<String> 	sequences;
		
	public void setParseValue(String parseValue){
		this.parseValue = parseValue;
	}

	public String getParseValue(){
		return parseValue;
	}

	public void setFirstRepeat(int firstRepeat){
		this.firstRepeat = firstRepeat;
	}

	public int getFirstRepeat(){
		return firstRepeat;
	}

	public void setWords(int words){
		this.words = words;
	}

	public int getWords(){
		return words;
	}

	public void setSequences(List<String> sequences){
		this.sequences = sequences;
	}

	public List<String> getSequences(){
		return sequences;
	}		
}

package com.davidurry.skava.services;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.davidurry.skava.services.Parser;

@XmlRootElement(name = "XMLParser")
public class XMLParser{
	String 			parseValue;
	int				firstRepeat;
	int				words;
	String[]	 	sequences;
		
	@XmlElement
	public void setParseValue(String parseValue){
		this.parseValue = parseValue;
	}

	public String getParseValue(){
		return parseValue;
	}

	@XmlElement
	public void setFirstRepeat(int firstRepeat){
		this.firstRepeat = firstRepeat;
	}

	public int getFirstRepeat(){
		return firstRepeat;
	}

	@XmlElement
	public void setWords(int words){
		this.words = words;
	}

	public int getWords(){
		return words;
	}

	@XmlElement
	public void setSequences(String[] sequences){
		this.sequences = sequences;
	}

	public String[] getSequences(){
		return sequences;
	}
	
	public XMLParser(Parser newParser){
		this.parseValue = newParser.getParseValue();
		this.firstRepeat = newParser.getFirstRepeat();
		this.words = newParser.getWords();
		this.sequences = (String[]) newParser.getSequences().toArray();
	}
}

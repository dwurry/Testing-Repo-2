package com.davidurry.skava.services;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataBuilder {
	public static int getWordCount(String parseString){
		return  parseString.split("/b").length;  //this should be punctuation and internationalization approved word boundary.
	}
	
	public static int getFirstNonRepeatingCharacter(String parseString){
		/* First non-repeating character taken literally means the first character without the same character after it.
		 * However, it could mean the first character that is not part of a repeating sequence.  I'm implementing the first character
		 * without a character after it.  However, this point should be clarified.
		 * [FIXME:  Clarify "first non-repeating character"]
		 */
		int nonRepeat;
		for (nonRepeat=0; nonRepeat < parseString.length(); nonRepeat++){
			if (nonRepeat == parseString.length() | parseString.charAt(nonRepeat) != parseString.charAt(nonRepeat+1)) break;
		}
		if (nonRepeat == parseString.length()) nonRepeat = 0; else nonRepeat++;
		return nonRepeat;
	}

	public static List<String> getSequences(String s) {
		List<String> repeats=new ArrayList<String>();
		/*
		  * The regex below still needs work.  Complex strings like aabbcceeddeeaabb do real well.
		  * however, more realistic examples like ronjoeronbob fail.  Why?  No idea.
		  * If this were a real job I'd talk to the customer about what they need.
		  * [FIXME regex not working properly]
		  */
        Matcher m = Pattern.compile("(.+)\\1+").matcher(s);
//        while (m.find()) {//note keys are stored 1...n with 0 holding the final count.
//        	repeatsStr[i] = m.group();
//        	i++;
//            repeats.put(i, repeatsStr[i-1]);
//        }
//        repeats.put(0, Integer.toString(i)); //0 holds the final count.

       while (m.find()) {//note keys are stored 1...n with 0 holding the final count.
     	repeats.add(m.group());
       }
	   return repeats;
   	}

	public static String getDate(Locale locale){
			Date date = new Date();
			DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
			return dateFormat.format(date);
	 }
	 
	public static Parser setRestults(String parseString, 
			 					String retValue, String retWords, 
			 					String retFirstRepeat, String retSequences){
		Parser results = new Parser();

		if (retValue == null || !retValue.equals("false")){
			results.setParseValue(parseString);
		}
		if (retWords == null || !retWords.equals("false")){
			results.setWords(getWordCount(parseString));
		}
		if (retFirstRepeat == null || !retFirstRepeat.equals("false")){
			results.setFirstRepeat(getFirstNonRepeatingCharacter(parseString));
		}
		
		if (retSequences == null || !retSequences.equals("false")){
			/*
			* Repeating patterns can be tricky.  We take a full on heavy weight approach that regex does not seem to match.
			* for every character in the string, verify that it does not repeat.  Reporting the results gets a little tricky
			* because there are several edge cases.  For example given the string "aaaa": "a" repeats four times and "aa" repeats twice.  
			* psutocode:
			*/
			List<String> sequences;
			sequences = getSequences(parseString);
			results.setSequences(sequences);
		}
		return results; 
	 }
}

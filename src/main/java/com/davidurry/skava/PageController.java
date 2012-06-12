package com.davidurry.skava;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.davidurry.skava.services.DataBuilder;
import com.davidurry.skava.services.Parser;
import com.davidurry.skava.services.XMLParser;

/**
 * Handles requests for the application home page.
 */
@Controller
public class PageController {
	
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
		
    /**
     *  Define the request parameters for the REST command.      
     */
    @ModelAttribute
    public String parseString(@RequestParam(required=false) String parseString) {
        return (parseString);
    }
    @ModelAttribute
    public String retValue(@RequestParam(required=false) String retValue) {
        return (retValue);
    }
    @ModelAttribute
    public String retFirstRepeat(@RequestParam(required=false) String retFirstRepeat) {
        return (retFirstRepeat);
    }
    @ModelAttribute
    public String retWords(@RequestParam(required=false) String retWords) {
        return (retWords);
    }
    @ModelAttribute
    public String retSequences(@RequestParam(required=false) String retSequences) {
        return (retSequences);
    }
    
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! the client locale is "+ locale.toString());
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
		 
	@RequestMapping(value = "/parse", method = RequestMethod.GET)
	public String parseRequest(Locale locale, String parseString, Model model){
		logger.info("parseRequest: the requested string is "+ parseString);
		
		Parser results = DataBuilder.setRestults(parseString, "true", "true", "true", "true");
		
		setHTMLResults(model, results, DataBuilder.getDate(locale));

		return("parse");
	}

	private void setHTMLResults(Model model, Parser results, String formattedDate){
		model.addAttribute("parseString", results.getParseValue());
		model.addAttribute("wordCount", results.getWords() );
		model.addAttribute("firstNonRepeatingChar", results.getFirstRepeat());  // note:  Zero returned = no non-repeating characters
		model.addAttribute("sequenceCount", results.getSequences().size());
		model.addAttribute("sequences", results.getSequences());
		model.addAttribute("serverTime", formattedDate );		
	}

	@RequestMapping(value = "/json", method = RequestMethod.GET)
	public @ResponseBody Parser parseJSON(Locale locale, String parseString, Model model, 
								String retValue, String retFirstRepeat, String retWords, 
								String retSequences) {
		logger.info("parseJSON: the requested string is "+ parseString);

		Parser results = DataBuilder.setRestults(parseString, retValue, retFirstRepeat, 
									 retWords, retSequences);

		return results;
	}

	@RequestMapping(value = "/xml", method = RequestMethod.GET)
	public @ResponseBody XMLParser parseXML(Locale locale, String parseString, Model model, 
								String retValue, String retFirstRepeat, String retWords, 
								String retSequences) {
		logger.info("parseJSON: the requested string is "+ parseString);

		Parser results = DataBuilder.setRestults(parseString, retValue, retFirstRepeat, 
									 retWords, retSequences);

		return new XMLParser(results);
	}

}

package edu.cmu.wireless;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.*;

public class XMLParser {

	/**
	 * @param args
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
//		SAXParserFactory parserFactor = SAXParserFactory.newInstance();
//		SAXParser parser = parserFactor.newSAXParser();
//		
//		DefaultHandler handler = new DefaultHandler();
//		parser.parse(ClassLoader.getSystemResourceAsStream("data/eichstaett-raw-dump.xml"),
//				handler);
		
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setValidating(true);
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		builder.setErrorHandler(new ErrorHandler() {
		    @Override
		    public void error(SAXParseException exception) throws SAXException {
		        // do something more useful in each of these handlers
		        exception.printStackTrace();
		    }
		    @Override
		    public void fatalError(SAXParseException exception) throws SAXException {
		        exception.printStackTrace();
		    }

		    @Override
		    public void warning(SAXParseException exception) throws SAXException {
		        exception.printStackTrace();
		    }
		});
		Document doc = (Document) builder.parse("data/mtview-raw-dump.xml");

	}

}

package edu.cmu.wireless;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class VanetXMLHandler extends DefaultHandler {
	//List to hold Employees object
    private List<Timestep> timeStepList = null;
    private Timestep timestep = null;

	public List<Timestep> getTimeStepList() {
		return timeStepList;
	}

	public void setTimeStepList(List<Timestep> timeStepList) {
		this.timeStepList = timeStepList;
	}

    boolean isLane = false;
    boolean isVehicle = false;
    boolean isEdge = false;
    boolean isTimestep = false;
 
 
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
 
        if (qName.equalsIgnoreCase("timestep")) {
            //get the time of the times
            double time = Double.parseDouble(attributes.getValue("time"));
            
            //initialize Timestep object and set time attribute
           this.timestep = new Timestep(time);
           
            //initialize list
            if (timeStepList == null)
                timeStepList = new ArrayList<Timestep>();
        } else if (qName.equalsIgnoreCase("edge")) {
            //set boolean values for fields, will be used in setting Employee variables
            isEdge = true;
        } else if (qName.equalsIgnoreCase("lane")) {
            isLane = true;
        } else if (qName.equalsIgnoreCase("vehicle")) {
            isVehicle = true;
        }
    }
 
 
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("timestep")) {
            timeStepList.add(timestep);
        }
    }
 
 
    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
 
        if (bAge) {
            //age element, set Employee age
            emp.setAge(Integer.parseInt(new String(ch, start, length)));
            bAge = false;
        } else if (bName) {
            emp.setName(new String(ch, start, length));
            bName = false;
        } else if (bRole) {
            emp.setRole(new String(ch, start, length));
            bRole = false;
        } else if (bGender) {
            emp.setGender(new String(ch, start, length));
            bGender = false;
        }
    }
}

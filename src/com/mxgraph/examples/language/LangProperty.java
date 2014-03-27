package com.mxgraph.examples.language;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="properties")
public class LangProperty {

	@XmlElement(name = "comment")
    public String comment;
	
	@XmlElement(name = "entry")
	public LangEntry langEntry;
}

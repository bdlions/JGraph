package com.mxgraph.examples.language;
/*
 * Created by bdlions
 * */
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlValue;

public class LangEntry {
	@XmlAttribute(name = "key")
	public String key;
	
	@XmlValue
	public String entry;
}

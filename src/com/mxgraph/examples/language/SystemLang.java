package com.mxgraph.examples.language;
/*
 * Created by bdlions
 * */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.mxgraph.util.mxResources;

public class SystemLang {
	private static LangProperty langProperty;
	
	public static void changeDefaultLang(String languageName) throws JAXBException, FileNotFoundException, URISyntaxException{
		langProperty.langEntry.entry = languageName;
		
		JAXBContext jaxbContext = JAXBContext.newInstance(LangProperty.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		
		URL resourceURL = SystemLang.class.getResource("/com/mxgraph/examples/language/resources/lang_property.xml");
		OutputStream os = new FileOutputStream( new File(resourceURL.toURI()) );
		
		marshaller.marshal(langProperty, os);
		mxResources.add(getDefaultLangFilePath());
	}
	
	public static String getDefaultLangFilePath() throws JAXBException{
		String filePath = "com/mxgraph/examples/swing/resources/editor";
		
		JAXBContext jaxbContext = JAXBContext.newInstance(LangProperty.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		langProperty = (LangProperty)unmarshaller.unmarshal(SystemLang.class.getResourceAsStream("/com/mxgraph/examples/language/resources/lang_property.xml"));

		return filePath + "_" + getLangShortForm();
	}
	
	public static String getLangShortForm(){
		
		String langFileMapperPath = "com/mxgraph/examples/language/resources/langFileMapper";
		mxResources.add(langFileMapperPath);
		
		return mxResources.get(langProperty.langEntry.entry);
	}
	
	public static String getLang(){
		return langProperty.langEntry.entry;
	}
	
	public static void main(String args[]) throws JAXBException, FileNotFoundException, URISyntaxException{
		
		System.out.println(getDefaultLangFilePath());
		  
	    System.out.println(getLang());
	    
	    //changeDefaultLang("French");
	    
	    //System.out.println(langProperty.comment);  
	    //System.out.println(langProperty.langEntry.entry);
	    
	}
}

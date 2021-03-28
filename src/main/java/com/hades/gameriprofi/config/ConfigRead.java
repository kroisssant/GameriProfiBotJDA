//MADE BY HADES

package com.hades.gameriprofi.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ConfigRead {
    String result = "";
	InputStream inputStream;
    File propFile = new File("config.properties");
    
    public String getStringValue(String propName) throws IOException, ParseException{
        JSONParser parser = new JSONParser();
        Reader reader = new FileReader("config.json");
        JSONObject file = (JSONObject) parser.parse(reader);
        JSONArray config = (JSONArray) file.get("config");
        return propName;
        
    }
    
    public void writeToPorp(String propName, String newProp) throws IOException{
        FileInputStream in = new FileInputStream(propFile);
        Properties props = new Properties();
        props.load(in);
        
        FileOutputStream out = new FileOutputStream(propFile);
        props.setProperty(propName, newProp);
        props.store(out, null);
        in.close();
        out.close();
        
    }
}
//MADE BY HADES

package com.hades.gameriprofi.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigRead {
    String result = "";
	InputStream inputStream;
    
    public String getStringValue(String propName) throws IOException{
        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            return prop.getProperty(propName);
        }catch(Exception e) {
            System.out.println(e);
            return null;
        }
        finally {
            inputStream.close();
        }
        
    }
    
    public void writeToPorp(String propName, String newProp) throws IOException{
        FileOutputStream out = new FileOutputStream("config.properties");
        FileInputStream in = new FileInputStream("config.properties");
        Properties props = new Properties();
        props.load(in);
        in.close();
        props.setProperty(propName, newProp);
        props.store(out, null);
        out.close();
    }
}
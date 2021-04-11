//MADE BY HADES

package com.hades.gameriprofi.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CreateWarnFile {
    static String pathWarn = "warn.json";
    static String pathWordList = "noWordList.json";
    static String pathLog = "log.txt";
    
    static boolean createWarnFile = false;

    static File logFile = new File(pathLog);
    static File tryFile = new File(pathWarn);
    static File fWordList = new File(pathWordList);

    public static void createFiles() {
        createWarnFile();
        createLogFile();
        createWordList();
    }

    private static void createWarnFile() {
        try {
            if (tryFile.createNewFile()) {
                System.out.println("File created: " + tryFile.getName());
                createWarnFile = true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        if (createWarnFile) {
            JSONObject obj = new JSONObject();
            JSONArray warn = new JSONArray();
            obj.put("warn", warn);

            try (FileWriter file = new FileWriter(pathWarn)) {
                file.write(obj.toJSONString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void createLogFile() {
        try {
            if(logFile.createNewFile()){
                System.out.println("File created: " + logFile.getName());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private static void createWordList() {
        try {
            if(fWordList.createNewFile()){
                
                JSONObject obj = new JSONObject();
                JSONArray main = new JSONArray();
                obj.put("main", main);
                JSONObject word = new JSONObject();
                JSONArray wordList = new JSONArray();
                word.put("WordList", wordList);
                main.add(word);
                JSONObject ignorChan = new JSONObject();
                JSONArray ignorChanList = new JSONArray();
                ignorChan.put("Ignored Channels", ignorChanList);
                main.add(ignorChan);
                try (FileWriter file = new FileWriter(pathWordList)) {
                    file.write(obj.toJSONString());
                    System.out.println("File created: " + fWordList.getName());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

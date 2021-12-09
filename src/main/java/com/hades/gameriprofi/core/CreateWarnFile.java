//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

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
    static File logFile;
    static File tryFile;
    static File fWordList;

    public CreateWarnFile() {
    }

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
        } catch (Exception var15) {
            var15.printStackTrace();
        }

        if (createWarnFile) {
            JSONObject obj = new JSONObject();
            JSONArray warn = new JSONArray();
            obj.put("warn", warn);

            try {
                FileWriter file = new FileWriter(pathWarn);
                Throwable var3 = null;

                try {
                    file.write(obj.toJSONString());
                } catch (Throwable var14) {
                    var3 = var14;
                    throw var14;
                } finally {
                    if (file != null) {
                        if (var3 != null) {
                            try {
                                file.close();
                            } catch (Throwable var13) {
                                var3.addSuppressed(var13);
                            }
                        } else {
                            file.close();
                        }
                    }

                }
            } catch (IOException var17) {
                var17.printStackTrace();
            }
        }

    }

    private static void createLogFile() {
        try {
            if (logFile.createNewFile()) {
                System.out.println("File created: " + logFile.getName());
            }
        } catch (IOException var1) {
            var1.printStackTrace();
        }

    }

    private static void createWordList() {
        try {
            if (fWordList.createNewFile()) {
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

                try {
                    FileWriter file = new FileWriter(pathWordList);
                    Throwable var7 = null;

                    try {
                        file.write(obj.toJSONString());
                        System.out.println("File created: " + fWordList.getName());
                    } catch (Throwable var18) {
                        var7 = var18;
                        throw var18;
                    } finally {
                        if (file != null) {
                            if (var7 != null) {
                                try {
                                    file.close();
                                } catch (Throwable var17) {
                                    var7.addSuppressed(var17);
                                }
                            } else {
                                file.close();
                            }
                        }

                    }
                } catch (IOException var20) {
                    var20.printStackTrace();
                }
            }
        } catch (IOException var21) {
            var21.printStackTrace();
        }

    }

    static {
        logFile = new File(pathLog);
        tryFile = new File(pathWarn);
        fWordList = new File(pathWordList);
    }
}

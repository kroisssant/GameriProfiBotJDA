//MADE BY HADES

package com.hades.gameriprofi;

import com.hades.gameriprofi.commands.ChangePropCommands;
import com.hades.gameriprofi.commands.ServerCommands;
import com.hades.gameriprofi.commands.WarnCommand;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.security.auth.login.LoginException;
import java.io.*;
import java.util.Scanner;

public class Bot {

    public static void main(String[] args) throws LoginException, IOException {
        boolean createWarnFile = false;
        Scanner scanner = new Scanner(new File("token.txt"), "UTF-8");
        String token = scanner.nextLine();
        JDABuilder
                .create(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.GUILD_BANS, GatewayIntent.DIRECT_MESSAGE_REACTIONS, GatewayIntent.GUILD_EMOJIS)
                .setActivity(Activity.playing("^help for help"))
                .addEventListeners(new Listener())
                .addEventListeners(new WarnCommand())
                .addEventListeners(new ServerCommands())
                .addEventListeners(new ChangePropCommands())
                .build();

        String pathWarn = "warn.json";
        File tryFile = new File(pathWarn);
        try {
            if (tryFile.createNewFile()) {
                System.out.println("File created: " + tryFile.getName());
                createWarnFile = true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        if(createWarnFile){
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
}

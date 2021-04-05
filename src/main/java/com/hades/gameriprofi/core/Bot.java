//MADE BY HADES

package com.hades.gameriprofi.core;

import com.hades.gameriprofi.commands.ModCommands;
import com.hades.gameriprofi.commands.ServerCommands;
import com.hades.gameriprofi.commands.WarnCommand;
import com.hades.gameriprofi.events.FWord;
import com.hades.gameriprofi.events.Listener;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.io.*;
import java.util.Scanner;

public class Bot {

    public static void main(String[] args) throws LoginException, IOException {
        Scanner scanner = new Scanner(new File("token.txt"), "UTF-8");
        String token = scanner.nextLine();
        JDABuilder
                .create(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_BANS,
                        GatewayIntent.DIRECT_MESSAGE_REACTIONS, GatewayIntent.GUILD_EMOJIS)
                .setActivity(Activity.playing("^help for help")).addEventListeners(new Listener()).addEventListeners(new FWord())
                .addEventListeners(new WarnCommand()).addEventListeners(new ServerCommands()).addEventListeners(new ModCommands()).build();

        CreateWarnFile.createWarnFile();
        CreateWarnFile.createWordList();
        CreateWarnFile.createLogFile();
    }
}

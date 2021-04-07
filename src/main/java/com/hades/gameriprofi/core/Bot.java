//MADE BY HADES

package com.hades.gameriprofi.core;

import com.hades.gameriprofi.commands.BadWordsCommands;
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
        System.out.println("\n");
        System.out.println(".Mm:-.`     `.::`      `./::-``       -:://////::-        `-::////////:.`      `.:://///..     "); 
        System.out.println(".mdo:       :/dN-`     `/oNmmy/.      `ymmdyyyyyydy/:``   `.yNddyyyyyyyy--    `-+sdhyyyyy++.`  ");
        System.out.println(".mdo:       :/dN-`     .hyssssy/      `ymso`    `+omh+:   `.yNo/`            .+yNy/.     -:..  ");
        System.out.println(".mdo:       :/dN-`    .:Nh-::omo.`    `ymso`      .syms.` `.yNo/`            -hdmo`            ");
        System.out.println(".mdo:       :/dN-`    ++dy```+my/.    `ymso`      `/oMh:. `.yNo/`            .oyMdo+/.         ");
        System.out.println(".mmdyoooooooyhmN-`   `hho/   :hhy:    `ymso`      `-oMd+- `.yNdhsooooooo.`    .:oymmmyooo-.    ");
        System.out.println(".mmhy+++++++symN-`  .-Nm:-   ./yNs-`  `ymso`      `-oMd+- `.yNhyo+++++++``       .:/+oodNhy/:  ");
        System.out.println(".mdo:       :/dN-`  +oMNhysssyhmMds-  `ymso`      `/oMh:. `.yNo/`                     `:/hdhy` ");
        System.out.println(".mdo:       :/dN-`  ddhy///////ohdm/```ymso`      -hhdo`` `.yNo/`                        shdd.`");
        System.out.println(".mdo:       :/dN-`.-Nm::       ./hMs:.`ymso`    .syNh:.   `.yNo/`            .+:       -:dNys` ");
        System.out.println(".mdo:       :/dN-`+omd``       ``oNhs-`ymmmhhhhhhyo:-     `.yNmdhhhhhhhh:-   `:+hhhhhhhddo+-.  ");
        System.out.println(".--.`       ``--` ..-.           .--.` .---------.`         .-----------``     `---------``    ");
        System.out.println("\n");

        Scanner scanner = new Scanner(new File("token.txt"), "UTF-8");
        String token = scanner.nextLine();
        JDABuilder
                .create(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_BANS,
                        GatewayIntent.DIRECT_MESSAGE_REACTIONS, GatewayIntent.GUILD_EMOJIS)
                .setActivity(Activity.playing("^help for help")).addEventListeners(new Listener()).addEventListeners(new FWord()).addEventListeners(new BadWordsCommands())
                .addEventListeners(new WarnCommand()).addEventListeners(new ServerCommands()).addEventListeners(new ModCommands()).build();

        CreateWarnFile.createWarnFile();
        CreateWarnFile.createWordList();
        CreateWarnFile.createLogFile();
    }
}

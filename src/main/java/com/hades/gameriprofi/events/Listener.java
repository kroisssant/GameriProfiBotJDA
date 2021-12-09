//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.hades.gameriprofi.events;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class Listener extends ListenerAdapter {
    public Listener() {
    }

    public void onReady(@NotNull ReadyEvent event) {
        System.out.println("I am ready");
    }

    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        try {
            TextChannel channel = event.getGuild().getTextChannelById("753316202247356478");
            User member = event.getUser();

            assert channel != null;

            channel.sendMessage("<a:partyparrot:782279542399959070> <@" + member.getId() + "> prezent la datorie. ").queue();
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    public void onGuildMemberRemove(@NotNull GuildMemberRemoveEvent event) {
        try {
            TextChannel channel = event.getGuild().getTextChannelById("753316202247356478");
            User member = event.getUser();

            assert channel != null;

            channel.sendMessage("<:clear_effect:753328273081106575> " + member.getAsTag() + " a plecat dupa lapte.").queue();
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }
}

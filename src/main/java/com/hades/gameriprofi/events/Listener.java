//MADE BY HADES
package com.hades.gameriprofi.events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import org.jetbrains.annotations.NotNull;

public class Listener extends ListenerAdapter {
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        System.out.println("I am ready");
    }

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        TextChannel channel;
        try {
            channel = event.getGuild().getTextChannelById("753316202247356478");
            User member = event.getUser();
            assert channel != null;
            channel.sendMessage("<a:partyparrot:782279542399959070>" + member.getAsTag()
                    + "prezent la datorie. <a:partyparrot:782279542399959070>").queue();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(e);
        }

    }

    @Override
    public void onGuildMemberRemove(@NotNull GuildMemberRemoveEvent event) {
        TextChannel channel;
        try {
            channel = event.getGuild().getTextChannelById("753316202247356478");
            User member = event.getUser();
            assert channel != null;
            channel.sendMessage("<a:sadblob:819631090444271618> " + member.getAsTag()
                    + " a plecat dupa lapte. <a:sadblob:819631090444271618>").queue();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(e);
        }

    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        MessageChannel channel = event.getChannel();
        User author = event.getAuthor();
        Message message = event.getMessage();
        String msg = message.getContentDisplay();
        String sugestie = msg.toLowerCase();
        if (author.isBot()) {
            return;
        }
        if (author.isBot())
            return;
        if (msg.equals("^help")) {
            new Thread(() -> {
                EmbedBuilder helpEmbed = new EmbedBuilder();
                helpEmbed.setTitle("Comenzi pentru botul serverului");
                helpEmbed.setDescription("Aici sunt comenziile botului");
                helpEmbed.setColor(0x7289da);
                helpEmbed.setThumbnail(
                        "https://cdn.discordapp.com/attachments/729485295007039599/813133882312228904/server-icon.png");
                helpEmbed.addField("link", "Linkul de invite", false);
                helpEmbed.addField("ip", "Minecraft server ip", false);
                channel.sendMessage(helpEmbed.build()).queue();
            }).start();
        } // done
        if (msg.equals("^mod")) {
            new Thread(() -> {
                EmbedBuilder modHelpEmbed = new EmbedBuilder();
                modHelpEmbed.setTitle("Aici sunt comenzile botului");
                modHelpEmbed.setDescription("Le poti folosi doar daca ai helper sau mai sus");
                modHelpEmbed.setColor(0x7289da);
                modHelpEmbed.setThumbnail(
                        "https://cdn.discordapp.com/attachments/729485295007039599/813133882312228904/server-icon.png");
                modHelpEmbed.addField("^warn <membru> <motiv>", "warn a person", false);
                modHelpEmbed.addField("^ban <membru> <motiv>", "ban a person", false);
                modHelpEmbed.addField("^unban <membru>", "unban a person", false);
                modHelpEmbed.addField("^cwarns <membru>", "Show the number of warns for a member", false);
                modHelpEmbed.addField("kick <membru> <motiv>", "Kick a member", false);
                modHelpEmbed.addField("lock", "Put a channel on lockdown", false);
                modHelpEmbed.addField("unlock", "Unlock a channel", false);
                channel.sendMessage(modHelpEmbed.build()).queue();
            }).start();
        } // done

        // SERVER COMMANDS START

        // SERVER COMMANDS FINISH

        // Mod COMMANDS START
        if (sugestie.contains("sugestie")) {
            new Thread(() -> {
                message.addReaction("✅").queue();
                message.addReaction("❌").queue();
            }).start();
        }

        // done
        // Mod COMMANDS FINISH

    }
}
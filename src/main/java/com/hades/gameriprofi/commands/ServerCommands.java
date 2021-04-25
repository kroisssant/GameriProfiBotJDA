//MADE BY HADES

package com.hades.gameriprofi.commands;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ServerCommands extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        Message message = event.getMessage();
        String msg = message.getContentDisplay();
        User author = event.getAuthor();
        if (author.isBot()) {
            return;
        }
        if (msg.startsWith("^ip")) {
            try {
                channel.sendMessage(
                        "<:ipminecraft:825765163566628885> IP-ul serverului de Minecraft: 147.135.181.192:25582")
                        .queue();
            } catch (Exception e) {
                channel.sendMessage("There was a problem, contact <@498813372788113408> for help").queue();
            }
        }

        if (msg.startsWith("^dev")) {
            channel.sendMessage("<@498813372788113408>").queue();
        }

        if (msg.startsWith("^link")) {
            try {
                channel.sendMessage(
                        "<:linkulserverului:825765164098781214> Link-ul serverului de discord: https://discord.gg/A5m4wN6zbg")
                        .queue();
            } catch (Exception e) {
                channel.sendMessage("There was a problem, contact <@498813372788113408> for help").queue();
            }
        } // TODO: auto gen links

    }
}
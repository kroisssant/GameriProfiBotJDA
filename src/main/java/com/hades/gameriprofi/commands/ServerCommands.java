package com.hades.gameriprofi.commands;

import com.hades.gameriprofi.config.ConfigRead;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ServerCommands extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        Message message = event.getMessage();
        String msg = message.getContentDisplay();
        ConfigRead prop = new ConfigRead();

        if (msg.startsWith("^ip")) {
            try {
                channel.sendMessage(prop.getStringValue("ip_message");
            } catch (Exception e) {
                channel.sendMessage("There was a problem, contact <@498813372788113408> for help").queue();
            }
        }

        if (msg.startsWith("^dev")) {
            channel.sendMessage("<@498813372788113408>").queue();
        }

        if (msg.startsWith("^link")) {
            try {
                channel.sendMessage(prop.getStringValue("link")).queue();
            } catch (Exception e) {
                channel.sendMessage("There was a problem, contact <@498813372788113408> for help").queue();
            }
        } // TODO: auto gen links
    }
}
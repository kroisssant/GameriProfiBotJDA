package com.hades.gameriprofi.commands;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ServerCommands extends ListenerAdapter{
    
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        Message message = event.getMessage();
        String msg = message.getContentDisplay();
        
        if (msg.startsWith("^ip")) {
            channel.sendMessage("Ip-ul serverului de minecraft(1.16.4): 147.135.181.192:25582").queue();
        }
        if (msg.startsWith("^dev")) {
            channel.sendMessage("<@498813372788113408>").queue();
        }
        if (msg.startsWith("^link")) {
            channel.sendMessage("https://discord.gg/bKmTdFxrJj").queue();
        } // TODO: auto gen links
    }
}
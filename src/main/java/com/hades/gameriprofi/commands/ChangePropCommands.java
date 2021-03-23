package com.hades.gameriprofi.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ChangePropCommands extends ListenerAdapter{
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        Member member = event.getMember();
        Message message = event.getMessage();
        String msg = message.getContentDisplay();
        if(msg.startsWith("^change")){
            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("Props you can change");
            embed.setDescription("");
            embed.setColor(0x7289da);
            embed.addField("ip", "change the msg send for the commnad \"^ip\"", true);
            embed.addField("link", "change the msg send for for the command \"^link\"", true);
            embed.addField("welcome channel", "change the channel for the welcome mesg", true);
            embed.addField("bye channel", "change the channel for the bye msg", true);
        }
    }
}

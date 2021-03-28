package com.hades.gameriprofi.commands;

import java.io.IOException;

import com.hades.gameriprofi.config.ConfigRead;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ChangePropCommands extends ListenerAdapter {
    boolean changeStart = false;
    boolean afterTemp = false;
    String temp = null;

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message message = event.getMessage();
        MessageChannel channel = event.getChannel();
        String msg = message.getContentDisplay();
        String[] mes = msg.split(" ");
        User author = event.getAuthor();
        ConfigRead prop = new ConfigRead();
        if (author.isBot()) {
            return;
        }
        if (changeStart && afterTemp) {
            System.out.println(temp);
            if (msg == "cancel") {
                changeStart = false;
                afterTemp = false;
                channel.sendMessage("Change has been canceled").queue();
            }
            
                try {
                    prop.writeToPorp(temp, msg);
                    channel.sendMessage("The change has been made").queue();
                    changeStart = false;
                    afterTemp = false;
                } catch (IOException e) {
                    changeStart = false;
                    afterTemp = false;
                    channel.sendMessage("There was a problem, contact <@498813372788113408> for help").queue();
                    e.printStackTrace();
                }
                
            
        }

        if (changeStart) {
            switch (msg) {
            case "ip":
                temp = "ip_message";
                afterTemp = true;
                break;
            case "link":
                temp = "link";
                afterTemp = true;
                break;
            case "welcome channel":
                temp = "welcome_channel";
                afterTemp = true;
                break;
            case "bye channel":
                temp = "bye channel";
                afterTemp = true;
                break;
            case "cancel":
                temp = null;
                changeStart = false;
                afterTemp = false;
                channel.sendMessage("You canceled the change.").queue();
                break;

            }
            channel.sendMessage(
                    "You choose " + msg + " type cancel to cancel or type the new value if you want to continue")
                    .queue();
            System.out.println(temp);
        }

        if (msg.startsWith("^change") && !changeStart) {
            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("Props you can change");
            embed.setDescription("");
            embed.setColor(0x7289da);
            embed.addField("ip", "change the msg send for the commnad \"^ip\"", true);
            embed.addField("link", "change the msg send for for the command \"^link\"", true);
            embed.addField("welcome channel", "change the channel for the welcome mesg", true);
            embed.addField("bye channel", "change the channel for the bye msg", true);
            embed.addField("cancel", "cancel the change", false);
            channel.sendMessage(embed.build()).queue();
            changeStart = true;
            channel.sendMessage("What do you want to change?").queue();
        }

    }
}
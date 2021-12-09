//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.hades.gameriprofi.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MuteCommands extends ListenerAdapter {
    public MuteCommands() {
    }

    public void onMessageReceived(MessageReceivedEvent event) {
        Message message = event.getMessage();
        User author = event.getAuthor();
        Member member = event.getMember();
        String msg = message.getContentDisplay();
        String[] args = msg.split(" ");
        if (msg.startsWith("^mute") && member.hasPermission(new Permission[]{Permission.MESSAGE_MANAGE})) {
        }

    }
}

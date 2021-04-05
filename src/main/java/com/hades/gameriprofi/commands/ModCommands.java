//MADE BY HADES

package com.hades.gameriprofi.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import com.hades.gameriprofi.log.Logger;

public class ModCommands extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        Member member = event.getMember();
        Message message = event.getMessage();
        String msg = message.getContentDisplay();
        Guild guild = event.getGuild();
        List<Role> roles = guild.getRoles();
        User author = event.getAuthor();
        String logPath = "log.txt";
        if (author.isBot()) {
            return;
        }
        if (msg.startsWith("^kick")) {
            assert member != null;
            if (member.hasPermission(Permission.KICK_MEMBERS)) {
                new Thread(() -> {
                    int i_final_authur = 0;
                    int i_final_kick_member = 0;
                    try {
                        String reason = msg.replace("^kick " + message.getMentionedMembers().get(0), "");
                        if (message.getMentionedMembers().size() > 1) {
                            channel.sendMessage("You can kick only one member at the time!").queue();
                            return;
                        }
                        if (message.getMentionedMembers().get(0).getRoles().size() == 0) {
                            message.getMentionedMembers().get(0).kick(reason).queue();
                            channel.sendMessage(
                                    "<:kick:825765163655102465> " + message.getMentionedUsers().get(0).getAsTag() + " a fost dat afara.")
                                    .queue();
                        } else {
                            for (int i = 0; i < roles.size(); i++) {
                                Role role_1 = message.getMentionedMembers().get(0).getRoles().get(0);
                                System.out.println("role_kicked " + role_1);
                                if (role_1 == roles.get(i)) {
                                    i_final_kick_member = i;
                                    System.out.println("i_final_kick_member " + i_final_kick_member);
                                    break;
                                }
                            }
                            for (int i = 0; i < roles.size(); i++) {
                                Role role_2 = member.getRoles().get(0);
                                System.out.println("authour_kicked " + role_2);
                                if (role_2 == roles.get(i)) {
                                    i_final_authur = i;
                                    System.out.println("i_final_authur " + i_final_authur);
                                    break;
                                }
                            }
                            if (i_final_authur < i_final_kick_member) {
                                message.getMentionedMembers().get(0).kick(reason).queue();
                                channel.sendMessage(
                                        "<:kick:825765163655102465> " + message.getMentionedUsers().get(0).getAsTag() + " a fost dat afara.")
                                        .queue();
                                Logger.log(logPath, author.getAsTag() + " kicked "
                                        + message.getMentionedUsers().get(0).getAsTag() + " for " + msg);
                            } else {
                                channel.sendMessage("You can't kick a person with a higher rank.").queue();
                            }
                        }

                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }).start();
            } else {
                channel.sendMessage("You don't permision to do that").queue();
            }
        } // TODO: error handling
        if (msg.startsWith("^ban")) {
            assert member != null;
            if (member.hasPermission(Permission.BAN_MEMBERS)) {
                new Thread(() -> {
                    try {
                        int i_final_authur = 0;
                        int i_final_ban_member = 0;
                        String reason = msg.replace("^ban " + message.getMentionedMembers().get(0), "");
                        if (message.getMentionedMembers().size() > 1) {
                            channel.sendMessage("You can ban only one member at the time!").queue();
                            return;
                        }
                        if (message.getMentionedMembers().get(0).getRoles().size() == 0) {
                            message.getMentionedMembers().get(0).kick(reason).queue();
                            channel.sendMessage("<:ban:825765163994054676> " + message.getMentionedUsers().get(0).getAsTag() + " a fost banat.")
                                    .queue();
                        } else {
                            for (int i = 0; i < roles.size(); i++) {
                                Role role_1 = message.getMentionedMembers().get(0).getRoles().get(0);
                                if (role_1 == roles.get(i)) {
                                    i_final_ban_member = i;
                                    break;
                                }
                            }
                            for (int i = 0; i < roles.size(); i++) {
                                Role role_2 = member.getRoles().get(0);
                                if (role_2 == roles.get(i)) {
                                    i_final_authur = i;
                                    break;
                                }
                            }
                            if (i_final_authur < i_final_ban_member) {
                                message.getMentionedMembers().get(0).kick(reason).queue();
                                channel.sendMessage("<:ban:825765163994054676> " + message.getMentionedUsers().get(0).getAsTag() + " a fost banat.")
                                        .queue();
                                Logger.log(logPath, author.getAsTag() + " banned "
                                        + message.getMentionedUsers().get(0).getAsTag() + " for " + msg);
                            } else {
                                channel.sendMessage("You can't ban a person with a higher rank.").queue();
                            }
                        }

                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }).start();
            } else {
                channel.sendMessage("You don't permision to do that").queue();
            }
        } // TODO: error handling
        if (msg.startsWith("^unban")) {
            assert member != null;
            if (member.hasPermission(Permission.BAN_MEMBERS)) {
                String[] args = msg.split(" ");
                String ban_person_id = args[1];
                guild.unban(ban_person_id).queue();
                channel.sendMessage("<:unban:825765163990908998> Persoana nu mai are ban.").queue();
                Logger.log(logPath,
                        author.getAsTag() + " unbanned " + message.getMentionedUsers().get(0).getAsTag());
            
            } else {

                channel.sendMessage("You don't permission to do that").queue();
            }
        } // TODO: error handling
        if (msg.startsWith("^clear")) {
            assert member != null;
            if (member.hasPermission(Permission.MESSAGE_MANAGE)) {
                String[] args = msg.split(" ");
                int purge = Integer.parseInt(args[1]);
                List<Message> messages = channel.getHistory().retrievePast(purge).complete();
                new Thread(() -> {
                    event.getTextChannel().purgeMessages(messages);
                }).start();
                channel.sendMessage("<:del_message:826476428412321812> " + purge + " mesaje au fost sterse.").queue();
                
                Logger.log(logPath,
                        author.getAsTag() + " deleted " + purge + " messages form channel " + channel.getId());
                
            } else {
                channel.sendMessage("You don't permission to do that").queue();
            }
        } // TODO: error handling
        if (msg.startsWith("^lock")) {
            assert member != null;
            if (member.hasPermission(Permission.MANAGE_CHANNEL)) {
                event.getTextChannel().putPermissionOverride(event.getTextChannel().getGuild().getPublicRole())
                        .setDeny(Permission.MESSAGE_WRITE).queue();
                channel.sendMessage("<:lock_server:825765163944247296> "+channel.getName() + " a fost blocat.").queue();
                
                Logger.log(logPath, author.getAsTag() + " has put " + channel + " on lockdown");
                
            } else {
                channel.sendMessage("You don't permission to do that").queue();
            }
        }
        if (msg.startsWith("^unlock")) {
            assert member != null;
            if (member.hasPermission(Permission.MANAGE_CHANNEL)) {
                event.getTextChannel().putPermissionOverride(event.getTextChannel().getGuild().getPublicRole())
                        .setAllow(Permission.MESSAGE_WRITE).queue();
                channel.sendMessage("<:unlock_server:825765163734007810> " + channel.getName() + " a fost deblocat.").queue();
                    Logger.log(logPath, author.getAsTag() + " unlocked " + channel.getId());
            } else {
                channel.sendMessage("You don't permission to do that").queue();
            }
        }
    }
}

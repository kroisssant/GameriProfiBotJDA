//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.hades.gameriprofi.commands;

import java.util.List;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class ModCommands extends ListenerAdapter {
    public ModCommands() {
    }

    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        Member member = event.getMember();
        Message message = event.getMessage();
        String msg = message.getContentDisplay();
        Guild guild = event.getGuild();
        List<Role> roles = guild.getRoles();
        User author = event.getAuthor();
        if (!author.isBot()) {
            if (msg.startsWith("^kick")) {
                assert member != null;

                if (member.hasPermission(new Permission[]{Permission.KICK_MEMBERS})) {
                    (new Thread(() -> {
                        int i_final_authur = 0;
                        int i_final_kick_member = 0;

                        try {
                            String reason = msg.replace("^kick " + message.getMentionedMembers().get(0), "");
                            if (message.getMentionedMembers().size() > 1) {
                                channel.sendMessage("You can kick only one member at the time!").queue();
                                return;
                            }

                            if (((Member)message.getMentionedMembers().get(0)).getRoles().size() == 0) {
                                ((Member)message.getMentionedMembers().get(0)).kick(reason).queue();
                                channel.sendMessage("<:kick:825765163655102465> " + ((User)message.getMentionedUsers().get(0)).getAsTag() + " a fost dat afara.").queue();
                            } else {
                                int i;
                                Role role_2;
                                for(i = 0; i < roles.size(); ++i) {
                                    role_2 = (Role)((Member)message.getMentionedMembers().get(0)).getRoles().get(0);
                                    if (role_2 == roles.get(i)) {
                                        i_final_kick_member = i;
                                        break;
                                    }
                                }

                                for(i = 0; i < roles.size(); ++i) {
                                    role_2 = (Role)member.getRoles().get(0);
                                    if (role_2 == roles.get(i)) {
                                        i_final_authur = i;
                                        break;
                                    }
                                }

                                if (i_final_authur < i_final_kick_member) {
                                    ((Member)message.getMentionedMembers().get(0)).kick(reason).queue();
                                    channel.sendMessage("<:kick:825765163655102465> " + ((User)message.getMentionedUsers().get(0)).getAsTag() + " a fost dat afara.").queue();
                                } else {
                                    channel.sendMessage("You can't kick a person with a higher rank.").queue();
                                }
                            }
                        } catch (Exception var10) {
                            var10.printStackTrace();
                        }

                    })).start();
                } else {
                    channel.sendMessage("You don't permision to do that").queue();
                }
            }

            if (msg.startsWith("^ban")) {
                assert member != null;

                if (member.hasPermission(new Permission[]{Permission.BAN_MEMBERS})) {
                    (new Thread(() -> {
                        try {
                            int i_final_authur = 0;
                            int i_final_ban_member = 0;
                            if (message.getMentionedMembers().size() > 1) {
                                channel.sendMessage("You can ban only one member at the time!").queue();
                                return;
                            }

                            if (((Member)message.getMentionedMembers().get(0)).getRoles().size() == 0) {
                                ((Member)message.getMentionedMembers().get(0)).ban(1).reason("Ban by " + author.getAsTag()).queue();
                                channel.sendMessage("<:ban:825765163994054676> " + ((User)message.getMentionedUsers().get(0)).getAsTag() + " a fost banat.").queue();
                            } else {
                                int i;
                                Role role_2;
                                for(i = 0; i < roles.size(); ++i) {
                                    role_2 = (Role)((Member)message.getMentionedMembers().get(0)).getRoles().get(0);
                                    if (role_2 == roles.get(i)) {
                                        i_final_ban_member = i;
                                        break;
                                    }
                                }

                                for(i = 0; i < roles.size(); ++i) {
                                    role_2 = (Role)member.getRoles().get(0);
                                    if (role_2 == roles.get(i)) {
                                        i_final_authur = i;
                                        break;
                                    }
                                }

                                if (i_final_authur < i_final_ban_member) {
                                    ((Member)message.getMentionedMembers().get(0)).ban(1).reason("Ban by " + author.getAsTag()).queue();
                                    channel.sendMessage("<:ban:825765163994054676> " + ((User)message.getMentionedUsers().get(0)).getAsTag() + " a fost banat.").queue();
                                } else {
                                    channel.sendMessage("You can't ban a person with a higher rank.").queue();
                                }
                            }
                        } catch (Exception var9) {
                            var9.printStackTrace();
                        }

                    })).start();
                } else {
                    channel.sendMessage("You don't permision to do that").queue();
                }
            }

            String[] args;
            if (msg.startsWith("^unban")) {
                assert member != null;

                if (member.hasPermission(new Permission[]{Permission.BAN_MEMBERS})) {
                    args = msg.split(" ");
                    String ban_person_id = args[1];
                    guild.unban(ban_person_id).queue();
                    channel.sendMessage("<:unban:825765163990908998> Persoana nu mai are ban.").queue();
                } else {
                    channel.sendMessage("You don't permission to do that").queue();
                }
            }

            if (msg.startsWith("^clear")) {
                assert member != null;

                if (member.hasPermission(new Permission[]{Permission.MESSAGE_MANAGE})) {
                    args = msg.split(" ");
                    int purge = Integer.parseInt(args[1]);
                    List<Message> messages = (List)channel.getHistory().retrievePast(purge).complete();
                    (new Thread(() -> {
                        event.getTextChannel().purgeMessages(messages);
                    })).start();
                    channel.sendMessage("<:del_message:826476428412321812> " + purge + " mesaje au fost sterse.").queue();
                } else {
                    channel.sendMessage("You don't permission to do that").queue();
                }
            }

            if (msg.startsWith("^lock")) {
                assert member != null;

                if (member.hasPermission(new Permission[]{Permission.MANAGE_CHANNEL})) {
                    event.getTextChannel().putPermissionOverride(event.getTextChannel().getGuild().getPublicRole()).setDeny(new Permission[]{Permission.MESSAGE_WRITE}).queue();
                    channel.sendMessage("<:lock_server:825765163944247296> " + channel.getName() + " a fost blocat.").queue();
                } else {
                    channel.sendMessage("You don't permission to do that").queue();
                }
            }

            if (msg.startsWith("^unlock")) {
                assert member != null;

                if (member.hasPermission(new Permission[]{Permission.MANAGE_CHANNEL})) {
                    event.getTextChannel().putPermissionOverride(event.getTextChannel().getGuild().getPublicRole()).setAllow(new Permission[]{Permission.MESSAGE_WRITE}).queue();
                    channel.sendMessage("<:unlock_server:825765163734007810> " + channel.getName() + " a fost deblocat.").queue();
                } else {
                    channel.sendMessage("You don't permission to do that").queue();
                }
            }

        }
    }
}

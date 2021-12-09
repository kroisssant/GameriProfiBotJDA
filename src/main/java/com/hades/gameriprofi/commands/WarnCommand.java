//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.hades.gameriprofi.commands;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class WarnCommand extends ListenerAdapter {
    public WarnCommand() {
    }

    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        Member member = event.getMember();
        Message message = event.getMessage();
        String msg = message.getContentDisplay();
        String pathWarn = "warn.json";
        User author = event.getAuthor();
        if (!author.isBot()) {
            if (msg.startsWith("^warn")) {
                assert member != null;

                if (member.hasPermission(new Permission[]{Permission.MESSAGE_MANAGE})) {
                    if (message.getMentionedMembers().size() > 1) {
                        channel.sendMessage("You can warn only one person at a time!").queue();
                        return;
                    }

                    (new Thread(() -> {
                        boolean inJSON = false;

                        try {
                            JSONParser parser = new JSONParser();
                            Reader reader = new FileReader(pathWarn);
                            JSONObject file = (JSONObject)parser.parse(reader);
                            JSONArray warn = (JSONArray)file.get("warn");
                            if (author.getId().equals(((User)message.getMentionedUsers().get(0)).getId())) {
                                channel.sendMessage("You can't warn yourself.").queue();
                                return;
                            }

                            Iterator var10 = warn.iterator();

                            while(var10.hasNext()) {
                                Object o = var10.next();
                                JSONObject warnObj = (JSONObject)o;
                                if (warnObj.get(((Member)message.getMentionedMembers().get(0)).getId()) != null) {
                                    JSONArray warns = (JSONArray)warnObj.get(((Member)message.getMentionedMembers().get(0)).getId());
                                    warns.add(msg);
                                    inJSON = true;
                                }
                            }

                            if (!inJSON) {
                                JSONObject warned = new JSONObject();
                                JSONArray warnsx = new JSONArray();
                                warnsx.add(msg);
                                warned.put(((Member)message.getMentionedMembers().get(0)).getId(), warnsx);
                                warn.add(warned);
                            }

                            try {
                                FileWriter writer = new FileWriter(pathWarn);
                                Throwable var30 = null;

                                try {
                                    writer.write(file.toJSONString());
                                    channel.sendMessage("<:warn:825765163822481449> " + ((User)message.getMentionedUsers().get(0)).getAsTag() + " a primit un warn.").queue();
                                } catch (Throwable var23) {
                                    var30 = var23;
                                    throw var23;
                                } finally {
                                    if (writer != null) {
                                        if (var30 != null) {
                                            try {
                                                writer.close();
                                            } catch (Throwable var22) {
                                                var30.addSuppressed(var22);
                                            }
                                        } else {
                                            writer.close();
                                        }
                                    }

                                }
                            } catch (IOException var25) {
                                var25.printStackTrace();
                                channel.sendMessage("There was a problem, contact <@498813372788113408> for help").queue();
                            }
                        } catch (Exception var26) {
                            var26.printStackTrace();
                            channel.sendMessage("There was a problem, contact <@498813372788113408> for help").queue();
                        }

                    })).start();
                } else {
                    channel.sendMessage("You don't permission to do that").queue();
                }
            }

            if (msg.startsWith("^delwarns")) {
                assert member != null;

                if (member.hasPermission(new Permission[]{Permission.MESSAGE_MANAGE})) {
                    if (message.getMentionedMembers().size() > 1) {
                        channel.sendMessage("You can warn only one person at a time!").queue();
                        return;
                    }

                    (new Thread(() -> {
                        boolean inJSON = false;

                        try {
                            if (author.getId().equals(((User)message.getMentionedUsers().get(0)).getId())) {
                                channel.sendMessage("You can't delete your warns.").queue();
                                return;
                            }

                            JSONParser parser = new JSONParser();
                            Reader reader = new FileReader(pathWarn);
                            JSONObject file = (JSONObject)parser.parse(reader);
                            JSONArray warn = (JSONArray)file.get("warn");
                            Iterator var9 = warn.iterator();

                            while(var9.hasNext()) {
                                Object o = var9.next();
                                JSONObject warnObj = (JSONObject)o;
                                if (warnObj.get(((Member)message.getMentionedMembers().get(0)).getId()) != null) {
                                    JSONArray warns = (JSONArray)warnObj.get(((Member)message.getMentionedMembers().get(0)).getId());
                                    warns.clear();
                                    inJSON = true;
                                }
                            }

                            if (!inJSON) {
                                channel.sendMessage("<:delwarn:825765163957354517>" + ((User)message.getMentionedUsers().get(0)).getAsTag() + " nu are warnuri").queue();
                            }

                            try {
                                FileWriter writer = new FileWriter(pathWarn);
                                Throwable var27 = null;

                                try {
                                    writer.write(file.toJSONString());
                                    channel.sendMessage("<:delwarn:825765163957354517> I-am scos warn-uri lui " + ((User)message.getMentionedUsers().get(0)).getAsTag()).queue();
                                } catch (Throwable var22) {
                                    var27 = var22;
                                    throw var22;
                                } finally {
                                    if (writer != null) {
                                        if (var27 != null) {
                                            try {
                                                writer.close();
                                            } catch (Throwable var21) {
                                                var27.addSuppressed(var21);
                                            }
                                        } else {
                                            writer.close();
                                        }
                                    }

                                }
                            } catch (IOException var24) {
                                var24.printStackTrace();
                                channel.sendMessage("There was a problem, contact <@498813372788113408> for help").queue();
                            }
                        } catch (Exception var25) {
                            var25.printStackTrace();
                            channel.sendMessage("There was a problem, contact <@498813372788113408> for help").queue();
                        }

                    })).start();
                } else {
                    channel.sendMessage("You don't permission to do that").queue();
                }
            }

            if (msg.startsWith("^cwarns")) {
                (new Thread(() -> {
                    String id = author.getId();
                    String tag = author.getAsTag();
                    boolean inJSON = false;

                    try {
                        if (message.getMentionedMembers().size() < 1) {
                            id = author.getId();
                            tag = author.getAsTag();
                        } else if (message.getMentionedMembers().size() == 1) {
                            id = ((Member)message.getMentionedMembers().get(0)).getId();
                            tag = ((User)message.getMentionedUsers().get(0)).getAsTag();
                        } else if (message.getMentionedMembers().size() > 1) {
                            channel.sendMessage("You can see the number o warns for only one person at a time").queue();
                            return;
                        }

                        JSONParser parser = new JSONParser();
                        Reader reader = new FileReader(pathWarn);
                        JSONObject file = (JSONObject)parser.parse(reader);
                        JSONArray warn = (JSONArray)file.get("warn");
                        Iterator var11 = warn.iterator();

                        while(var11.hasNext()) {
                            Object o = var11.next();
                            JSONObject warnObj = (JSONObject)o;
                            if (warnObj.get(id) != null) {
                                JSONArray warns = (JSONArray)warnObj.get(id);
                                if (warns.size() > 1) {
                                    channel.sendMessage("<:cwarns:825765163940577330> " + tag + " are " + warns.size() + " warn-uri").queue();
                                } else if (warns.size() == 1) {
                                    channel.sendMessage("<:cwarns:825765163940577330> " + tag + " are un warn.").queue();
                                } else {
                                    channel.sendMessage("<:cwarns:825765163940577330> " + tag + " nu are niciun warn.").queue();
                                }

                                inJSON = true;
                            }
                        }

                        if (!inJSON) {
                            channel.sendMessage("<:cwarns:825765163940577330> " + tag + " nu are niciun warn.").queue();
                        }
                    } catch (Exception var15) {
                        var15.printStackTrace();
                        channel.sendMessage("There was a problem, contact <@498813372788113408> for help").queue();
                    }

                })).start();
            }

        }
    }
}

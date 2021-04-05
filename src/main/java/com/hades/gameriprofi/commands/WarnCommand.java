//MADE BY HADES

package com.hades.gameriprofi.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;

import com.hades.gameriprofi.log.Logger;

public class WarnCommand extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        Member member = event.getMember();
        Message message = event.getMessage();
        String msg = message.getContentDisplay();
        String pathWarn = "warn.json";
        String logPath = "log.txt";
        User author = event.getAuthor();
        if (author.isBot()) {
            return;
        }
        if (msg.startsWith("^warn")) {
            assert member != null;
            if (member.hasPermission(Permission.MESSAGE_MANAGE)) {
                if (message.getMentionedMembers().size() > 1) {
                    channel.sendMessage("You can warn only one person at a time!").queue();
                }
                new Thread(() -> {
                    boolean inJSON = false;
                    System.out.println("thread start ^warn");
                    try {
                        JSONParser parser = new JSONParser();
                        Reader reader = new FileReader(pathWarn);
                        JSONObject file = (JSONObject) parser.parse(reader);
                        JSONArray warn = (JSONArray) file.get("warn");
                        for (Object o : warn) {
                            JSONObject warnObj = (JSONObject) o;
                            if (warnObj.get(message.getMentionedMembers().get(0).getId()) != null) {
                                JSONArray warns = (JSONArray) warnObj.get(message.getMentionedMembers().get(0).getId());
                                warns.add(msg);
                                inJSON = true;
                            }
                        }
                        if (!inJSON) {
                            JSONObject warned = new JSONObject();
                            JSONArray warns = new JSONArray();
                            warns.add(msg);
                            warned.put(message.getMentionedMembers().get(0).getId(), warns);
                            warn.add(warned);
                        }
                        try (FileWriter writer = new FileWriter(pathWarn)) {
                            writer.write(file.toJSONString());
                            channel.sendMessage("<:warn:825765163822481449> " + message.getMentionedUsers().get(0).getAsTag() + " a primit un warn.")
                                    .queue();
                            Logger.log(logPath, author.getAsTag() + " warned "
                                    + message.getMentionedUsers().get(0).getAsTag() + " for " + msg);
                        } catch (IOException e) {
                            System.out.println(e);
                            channel.sendMessage("There was a problem, contact <@498813372788113408> for help").queue();
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                        channel.sendMessage("There was a problem, contact <@498813372788113408> for help").queue();
                    }
                }).start();
            } else {
                channel.sendMessage("You don't permission to do that").queue();
            }
        } // TODO: Error handling
        if (msg.startsWith("^delwarns")) {
            assert member != null;
            if (member.hasPermission(Permission.MESSAGE_MANAGE)) {
                if (message.getMentionedMembers().size() > 1) {
                    channel.sendMessage("You can warn only one person at a time!").queue();
                }
                new Thread(() -> {
                    boolean inJSON = false;
                    System.out.println("thread start ^delwarn");
                    try {
                        JSONParser parser = new JSONParser();
                        Reader reader = new FileReader(pathWarn);
                        JSONObject file = (JSONObject) parser.parse(reader);
                        JSONArray warn = (JSONArray) file.get("warn");
                        for (Object o : warn) {
                            JSONObject warnObj = (JSONObject) o;
                            if (warnObj.get(message.getMentionedMembers().get(0).getId()) != null) {
                                JSONArray warns = (JSONArray) warnObj.get(message.getMentionedMembers().get(0).getId());
                                warns.clear();
                                inJSON = true;
                            }
                        }
                        if (!inJSON) {
                            channel.sendMessage("<:delwarn:825765163957354517>" + message.getMentionedUsers().get(0).getAsTag() + " nu are warnuri")
                                    .queue();
                        }
                        try (FileWriter writer = new FileWriter(pathWarn)) {
                            writer.write(file.toJSONString());
                            channel.sendMessage("<:delwarn:825765163957354517> I-am scos warn-uri lui " +
                                    message.getMentionedUsers().get(0).getAsTag())
                                    .queue();
                        } catch (IOException e) {
                            System.out.println(e);
                            channel.sendMessage("There was a problem, contact <@498813372788113408> for help").queue();
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                        channel.sendMessage("There was a problem, contact <@498813372788113408> for help").queue();
                    }
                }).start();
            } else {
                channel.sendMessage("You don't permission to do that").queue();
            }
        } // TODO: Error handling
        if (msg.startsWith("^cwarns")) {
            assert member != null;
            if (member.hasPermission(Permission.MESSAGE_MANAGE)) {
                if (message.getMentionedMembers().size() > 1) {
                    channel.sendMessage("You can delete warns for only one person at a time!").queue();
                }
                new Thread(() -> {
                    boolean inJSON = false;
                    try {
                        JSONParser parser = new JSONParser();
                        Reader reader = new FileReader(pathWarn);
                        JSONObject file = (JSONObject) parser.parse(reader);
                        JSONArray warn = (JSONArray) file.get("warn");
                        for (Object o : warn) {
                            JSONObject warnObj = (JSONObject) o;
                            if (warnObj.get(message.getMentionedMembers().get(0).getId()) != null) {
                                JSONArray warns = (JSONArray) warnObj.get(message.getMentionedMembers().get(0).getId());
                                if (warns.size() > 1) {
                                    channel.sendMessage("<:cwarns:825765163940577330> " +message.getMentionedUsers().get(0).getAsTag() + " are "
                                            + warns.size() + " warn-uri").queue();
                                } else if (warns.size() == 1) {
                                    channel.sendMessage(
                                            "<:cwarns:825765163940577330> " + message.getMentionedUsers().get(0).getAsTag() + " are un warn.").queue();
                                } else {
                                    channel.sendMessage(
                                        "<:cwarns:825765163940577330> " + message.getMentionedUsers().get(0).getAsTag() + " nu are niciun warn.")
                                            .queue();
                                }
                                inJSON = true;
                            }
                        }
                        if (!inJSON) {
                            channel.sendMessage(
                                "<:cwarns:825765163940577330> " + message.getMentionedUsers().get(0).getAsTag() + " nu are niciun warn.").queue();
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                        channel.sendMessage("There was a problem, contact <@498813372788113408> for help").queue();
                    }
                }).start();
            }
        }
    }
}
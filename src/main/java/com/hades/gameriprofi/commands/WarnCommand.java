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

public class WarnCommand extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        Member member = event.getMember();
        Message message = event.getMessage();
        String msg = message.getContentDisplay();
        String pathWarn = "warn.json";
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
                            channel.sendMessage(message.getMentionedUsers().get(0).getAsTag() + " has been warned!").queue();
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
        }// TODO: Error handling
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
                            channel.sendMessage(message.getMentionedUsers().get(0).getAsTag()+ " has no warns.").queue();
                        }
                        try (FileWriter writer = new FileWriter(pathWarn)) {
                            writer.write(file.toJSONString());
                            channel.sendMessage(message.getMentionedUsers().get(0).getAsTag() + " has their warn deleted deleted!").queue();
                        } catch (IOException e) {
                            System.out.println(e);
                            channel.sendMessage("There was a problem, contact <@498813372788113408> for help").queue();
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                        channel.sendMessage("There was a problem, contact <@498813372788113408> for help").queue();
                    }
                }).start();
            }else {
                channel.sendMessage("You don't permission to do that").queue();
            }
        }// TODO: Error handling
        if(msg.startsWith("^cwarns")){
            assert member != null;
            if (member.hasPermission(Permission.MESSAGE_MANAGE)) {
                if (message.getMentionedMembers().size() > 1) {
                    channel.sendMessage("You can delete warns for only one person at a time!").queue();
                }
                new Thread(()->{
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
                                if(warns.size() > 1) {
                                    channel.sendMessage(message.getMentionedUsers().get(0).getAsTag() + " has "+ warns.size() + " warns").queue();
                                }else if(warns.size() == 1){
                                    channel.sendMessage(message.getMentionedUsers().get(0).getAsTag() + " has one warns").queue();
                                }else{
                                    channel.sendMessage(message.getMentionedUsers().get(0).getAsTag() + " doesn't have any warns").queue();
                                }
                                inJSON = true;
                            }
                        }
                        if (!inJSON) {
                            channel.sendMessage(message.getMentionedUsers().get(0).getAsTag() + " doesn't have any warns").queue();
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
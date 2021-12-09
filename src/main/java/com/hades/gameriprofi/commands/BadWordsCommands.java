//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.hades.gameriprofi.commands;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class BadWordsCommands extends ListenerAdapter {
    public BadWordsCommands() {
    }

    public void onMessageReceived(MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        User author = event.getAuthor();
        Message message = event.getMessage();
        String msg = message.getContentDisplay();
        String[] lMsg = msg.split(" ");
        Member auth = message.getMember();
        if (!author.isBot()) {
            if (msg.startsWith("^list") && auth.hasPermission(new Permission[]{Permission.MANAGE_SERVER})) {
                try {
                    Reader reader = new FileReader("noWordList.json");
                    JSONParser parser = new JSONParser();
                    JSONObject file = (JSONObject)parser.parse(reader);
                    JSONArray main = (JSONArray)file.get("main");
                    JSONObject wordListObj = (JSONObject)main.get(0);
                    JSONObject ignorChannelObj = (JSONObject)main.get(1);
                    JSONArray wordList = (JSONArray)wordListObj.get("WordList");
                    JSONArray ignoreChannel = (JSONArray)ignorChannelObj.get("Ignored Channels");
                    if (message.getMentionedChannels().size() > 1) {
                        channel.sendMessage("You can mention only one channel at a time").queue();
                        return;
                    }

                    if (lMsg.length > 3) {
                        channel.sendMessage("You can add only a word at a time!").queue();
                        return;
                    }

                    if (lMsg.length == 1) {
                        channel.sendMessage("<:list:825769483787829268> Lista cuvintelor interzise: " + wordList.toString()).queue();
                        return;
                    }

                    String var16 = lMsg[1];
                    byte var17 = -1;
                    switch(var16.hashCode()) {
                    case -1190396462:
                        if (var16.equals("ignore")) {
                            var17 = 1;
                        }
                        break;
                    case -934610812:
                        if (var16.equals("remove")) {
                            var17 = 2;
                        }
                        break;
                    case -303893077:
                        if (var16.equals("unignore")) {
                            var17 = 3;
                        }
                        break;
                    case 96417:
                        if (var16.equals("add")) {
                            var17 = 0;
                        }
                    }

                    switch(var17) {
                    case 0:
                        wordList.add(lMsg[2]);
                        channel.sendMessage("<:list_add:825769483712856144> " + lMsg[2] + " a fost adaugat in lista cuvintelor interzise.").queue();
                        break;
                    case 1:
                        ignoreChannel.add(((TextChannel)message.getMentionedChannels().get(0)).getId());
                        channel.sendMessage("<:list_ignore_channel:825769483774853180> Se ignora " + ((TextChannel)message.getMentionedChannels().get(0)).getName() + ".").queue();
                        break;
                    case 2:
                        wordList.remove(lMsg[2]);
                        channel.sendMessage("<:list_remove:825769483666194433> " + lMsg[2] + " a fost scos din lista cuvintelor interzise.").queue();
                        break;
                    case 3:
                        ignoreChannel.remove(((TextChannel)message.getMentionedChannels().get(0)).getId());
                        channel.sendMessage(((TextChannel)message.getMentionedChannels().get(0)).getId() + " a fost scos din lista canalelor ignorate.").queue();
                    }

                    try {
                        FileWriter writer = new FileWriter("noWordList.json");
                        Throwable var33 = null;

                        try {
                            writer.write(file.toJSONString());
                        } catch (Throwable var28) {
                            var33 = var28;
                            throw var28;
                        } finally {
                            if (writer != null) {
                                if (var33 != null) {
                                    try {
                                        writer.close();
                                    } catch (Throwable var27) {
                                        var33.addSuppressed(var27);
                                    }
                                } else {
                                    writer.close();
                                }
                            }

                        }
                    } catch (Exception var30) {
                        channel.sendMessage("Idk what the fack just happend, contact hades").queue();
                    }
                } catch (Exception var31) {
                    var31.printStackTrace();
                }
            }

        }
    }
}

//MADE BY HADES

package com.hades.gameriprofi.commands;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BadWordsCommands extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        User author = event.getAuthor();
        Message message = event.getMessage();
        String msg = message.getContentDisplay();
        String[] lMsg = msg.split(" ");
        Member auth = message.getMember();
        if (author.isBot()) {
            return;
        }
        if (msg.startsWith("^list") && auth.hasPermission(Permission.MANAGE_SERVER)) {

            try {
                Reader reader = new FileReader("noWordList.json");
                JSONParser parser = new JSONParser();

                JSONObject file = (JSONObject) parser.parse(reader);
                JSONArray main = (JSONArray) file.get("main");
                JSONObject wordListObj = (JSONObject) main.get(0);
                JSONObject ignorChannelObj = (JSONObject) main.get(1);
                JSONArray wordList = (JSONArray) wordListObj.get("WordList");
                JSONArray ignoreChannel = (JSONArray) ignorChannelObj.get("Ignored Channels");
                if(message.getMentionedChannels().size() > 1){
                    channel.sendMessage("You can mention only one channel at a time").queue();
                    return;
                }
                if(lMsg.length > 3){
                    channel.sendMessage("You can add only a word at a time!").queue();
                    return;
                }

                if(lMsg.length == 1){
                    channel.sendMessage("<:list:825769483787829268> Lista cuvintelor interzise: "+wordList.toString()).queue();
                    return;
                }
                switch (lMsg[1]) {
                case "add":
                    wordList.add(lMsg[2]);
                    channel.sendMessage("<:list_add:825769483712856144> " + lMsg[2] +" a fost adaugat in lista cuvintelor interzise.").queue();
                    break;
                case "ignore":
                    ignoreChannel.add(message.getMentionedChannels().get(0).getId());
                    channel.sendMessage("<:list_ignore_channel:825769483774853180> Se ignora " + message.getMentionedChannels().get(0).getName() + ".").queue();
                    break;
                case "remove":
                    wordList.remove(lMsg[2]);
                    channel.sendMessage("<:list_remove:825769483666194433> " + lMsg[2] + " a fost scos din lista cuvintelor interzise." ).queue();
                    break;
                case "unignore":
                    ignoreChannel.remove(message.getMentionedChannels().get(0).getId());
                    channel.sendMessage(message.getMentionedChannels().get(0).getId() + " a fost scos din lista canalelor ignorate.").queue();
                    break;
                }
                try (FileWriter writer = new FileWriter("noWordList.json")) {
                    writer.write(file.toJSONString());
                }catch(Exception e){
                    channel.sendMessage("Idk what the fack just happend, contact hades").queue();
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}

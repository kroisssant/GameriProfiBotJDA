//MADE BY HADES

package com.hades.gameriprofi.events;

import java.io.FileReader;
import java.io.Reader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class FWord extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        new Thread(() -> {
            Message message = event.getMessage();
            String msg = message.getContentDisplay();
            MessageChannel channel = event.getChannel();
            User author = message.getAuthor();
            boolean isIgnored = false;
            boolean isBadWord = false;
            if(author.isBot()){return;}
            try {
                JSONParser parser = new JSONParser();
                Reader reader;
                reader = new FileReader("noWordList.json");
                JSONObject file = (JSONObject) parser.parse(reader);
                JSONArray main = (JSONArray) file.get("main");
                JSONObject word = (JSONObject) main.get(0);
                JSONArray wordArray = (JSONArray) word.get("WordList");
                JSONObject fChannelsObj = (JSONObject) main.get(1);
                JSONArray fChannels = (JSONArray) fChannelsObj.get("Ignored Channels");
                for (int i = 0; i < wordArray.size(); i++) {
                    String sWord = (String) wordArray.get(i);
                    if(msg.toLowerCase().contains(sWord.toLowerCase())){
                        isBadWord = true;
                    }
                }
                for(int j = 0; j < fChannels.size(); j++) {
                    String sChannel = (String) fChannels.get(j);
                    if(sChannel.equals(channel.getId())){
                        isIgnored = true;
                    }
                }
                if(isBadWord && !isIgnored){
                    channel.deleteMessageById(message.getId()).queue();
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }).start();

    }
}
// TODO solve the java.lang.NullPointerException
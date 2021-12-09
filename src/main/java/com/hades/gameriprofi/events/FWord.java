//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.hades.gameriprofi.events;

import java.io.FileReader;
import java.io.Reader;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class FWord extends ListenerAdapter {
    public FWord() {
    }

    public void onMessageReceived(MessageReceivedEvent event) {
        (new Thread(() -> {
            Message message = event.getMessage();
            String msg = message.getContentDisplay();
            MessageChannel channel = event.getChannel();
            User author = message.getAuthor();
            boolean isIgnored = false;
            boolean isBadWord = false;
            if (!author.isBot()) {
                try {
                    JSONParser parser = new JSONParser();
                    Reader reader = new FileReader("noWordList.json");
                    JSONObject file = (JSONObject)parser.parse(reader);
                    JSONArray main = (JSONArray)file.get("main");
                    JSONObject word = (JSONObject)main.get(0);
                    JSONArray wordArray = (JSONArray)word.get("WordList");
                    JSONObject fChannelsObj = (JSONObject)main.get(1);
                    JSONArray fChannels = (JSONArray)fChannelsObj.get("Ignored Channels");

                    int j;
                    String sChannel;
                    for(j = 0; j < wordArray.size(); ++j) {
                        sChannel = (String)wordArray.get(j);
                        if (msg.toLowerCase().contains(sChannel.toLowerCase())) {
                            isBadWord = true;
                        }
                    }

                    for(j = 0; j < fChannels.size(); ++j) {
                        sChannel = (String)fChannels.get(j);
                        if (sChannel.equals(channel.getId())) {
                            isIgnored = true;
                        }
                    }

                    if (isBadWord && !isIgnored) {
                        channel.deleteMessageById(message.getId()).queue();
                    }
                } catch (Exception var17) {
                    var17.printStackTrace();
                }

            }
        })).start();
    }
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.hades.gameriprofi.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ServerCommands extends ListenerAdapter {
    public ServerCommands() {
    }

    public void onMessageReceived(MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        Message message = event.getMessage();
        String msg = message.getContentDisplay();
        User author = event.getAuthor();
        String sugestie = msg.toLowerCase();
        if (!author.isBot()) {
            if (msg.startsWith("^ip")) {
                try {
                    channel.sendMessage("<:ipminecraft:825765163566628885> IP-ul serverului de Minecraft: 147.135.181.192:25582").queue();
                } catch (Exception var9) {
                    channel.sendMessage("There was a problem, contact <@498813372788113408> for help").queue();
                }
            }

            if (msg.startsWith("^dev")) {
                channel.sendMessage("<@498813372788113408>").queue();
            }

            if (msg.startsWith("^link")) {
                try {
                    channel.sendMessage("<:linkulserverului:825765164098781214> Link-ul serverului de discord: https://discord.gg/N8prJJBMsT").queue();
                } catch (Exception var8) {
                    channel.sendMessage("There was a problem, contact <@498813372788113408> for help").queue();
                }
            }

            if (!author.isBot()) {
                if (!author.isBot()) {
                    if (msg.equals("^help")) {
                        (new Thread(() -> {
                            EmbedBuilder helpEmbed = new EmbedBuilder();
                            helpEmbed.setTitle("Comenzi pentru botul serverului");
                            helpEmbed.setDescription("Aici sunt comenziile botului");
                            helpEmbed.setColor(7506394);
                            helpEmbed.setThumbnail("https://cdn.discordapp.com/attachments/755376537322258523/840634014028267520/InternetCafe_logo.jpg");
                            helpEmbed.addField("link", "Linkul de invite", false);
                            helpEmbed.addField("ip", "Minecraft server ip", false);
                            channel.sendMessage(helpEmbed.build()).queue();
                        })).start();
                    }

                    if (msg.equals("^mod")) {
                        (new Thread(() -> {
                            EmbedBuilder modHelpEmbed = new EmbedBuilder();
                            modHelpEmbed.setTitle("Aici sunt comenzile botului");
                            modHelpEmbed.setDescription("Le poti folosi doar daca ai helper sau mai sus");
                            modHelpEmbed.setColor(7506394);
                            modHelpEmbed.setThumbnail("https://cdn.discordapp.com/attachments/755376537322258523/840634014028267520/InternetCafe_logo.jpg");
                            modHelpEmbed.addField("^warn <membru> <motiv>", "warn a person", false);
                            modHelpEmbed.addField("^ban <membru> <motiv>", "ban a person", false);
                            modHelpEmbed.addField("^unban <membru>", "unban a person", false);
                            modHelpEmbed.addField("^cwarns <membru>", "Show the number of warns for a member", false);
                            modHelpEmbed.addField("kick <membru> <motiv>", "Kick a member", false);
                            modHelpEmbed.addField("lock", "Put a channel on lockdown", false);
                            modHelpEmbed.addField("unlock", "Unlock a channel", false);
                            channel.sendMessage(modHelpEmbed.build()).queue();
                        })).start();
                    }

                    if (sugestie.startsWith("sugestie")) {
                        (new Thread(() -> {
                            message.addReaction("✅").queue();
                            message.addReaction("❌").queue();
                        })).start();
                    }

                    if (msg.equalsIgnoreCase("wlc")) {
                        channel.sendMessage(author.getAsMention() + " iti ureaza bun venit!").queue();
                    }

                }
            }
        }
    }
}

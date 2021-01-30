package de.steallight.testbot.listener;

import de.steallight.testbot.main.Bot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.Objects;

public class IdeenListener extends ListenerAdapter {

    String channelId = "";

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {


        User member = e.getMessage().getAuthor();
        if (e.getChannel().getId().equals("780067626486857750")) {
            if (Objects.requireNonNull(e.getMember()).getUser().isBot()) {
            } else {


                e.getMessage().addReaction("\u2705").queue();
                e.getMessage().addReaction("\u274C").queue();

            }

        }
    }

    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent e) {
        TextChannel tc = Bot.shardMan.getTextChannelById("780156140658622474");


        PrivateChannel pc = e.getJDA().openPrivateChannelById("438200912599580675").complete();

        if (e.getChannel().getId().equals("780067626486857750")) {

            if (e.getReaction().getReactionEmote().getName().equals("\u274C")) {

                e.getChannel().retrieveMessageById(e.getMessageId()).queue(message -> {
                    message.retrieveReactionUsers("\u274C").queue(users -> {

                                    if (!e.getMember().getRoles().contains(e.getGuild().getRoleById("780067930359988246"))) {
                                        if (users.size() >= 4) {

                                            EmbedBuilder ebTo = new EmbedBuilder();
                                            ebTo.setTitle("Deine Idee wurde leider abgelehnt!");
                                            ebTo.setColor(Color.RED);

                                            message.getAuthor().openPrivateChannel().queue(sendto -> {
                                                sendto.sendMessage(ebTo.build()).queue();
                                            });
                                            message.delete().queue();
                                        }


                                    } else {
                                        EmbedBuilder ebTo = new EmbedBuilder();
                                        ebTo.setTitle("Deine Idee wurde leider abgelehnt!");
                                        ebTo.setColor(Color.RED);

                                        message.getAuthor().openPrivateChannel().queue(sendto -> {
                                            sendto.sendMessage(ebTo.build()).queue();
                                        });

                                        message.delete().queue();
                                    }
                    });
                            }
                    );


            }


            } else if (e.getReaction().getReactionEmote().getName().equals("\u2705")) {

                e.getChannel().retrieveMessageById(e.getMessageId()).queue(message -> {
                    message.retrieveReactionUsers("\u2705").queue(users -> {

                                if (users.size() >= 4) {

                                    EmbedBuilder eb = new EmbedBuilder();
                                    EmbedBuilder ebTo = new EmbedBuilder();
                                    eb.setTitle("Idee");
                                    eb.setColor(Color.green);
                                    eb.setDescription(message.getContentRaw());
                                    eb.addField("Vorgeschlagen von", message.getAuthor().getAsMention() + "", false);
                                    eb.setThumbnail(message.getAuthor().getAvatarUrl());


                                    ebTo.setTitle("Deine Idee liegt nun den Mod-Team vor!");
                                    ebTo.setColor(Color.green);

                                    message.getAuthor().openPrivateChannel().queue(sendto -> {
                                        sendto.sendMessage(ebTo.build()).queue();
                                    });
                                    e.getJDA().getTextChannelById("780156140658622474").sendMessage(e.getGuild().getRoleById("780067930359988246").getAsMention() + "" + e.getGuild().getRoleById("780074754027159563").getAsMention()).queue();
                                    e.getJDA().getTextChannelById("780156140658622474").sendMessage(eb.build()).queue();


                                    message.delete().queue();
                                }


                            }

                    );


                });

            }


        }
        }





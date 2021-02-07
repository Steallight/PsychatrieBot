package de.steallight.testbot.listener;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class EmoteIdeaListener extends ListenerAdapter {


    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getChannel().getId().equals("")) {
            e.getMessage().addReaction("\u2705").queue();
        }
    }

    public void onMessageReactionAdd(MessageReactionAddEvent e) {
        if (e.getUser() != null) {
            if (!e.getUser().isBot()) {
                if (e.getReaction().getReactionEmote().getName().equals("\u2705")) {
                    if (e.getMember().hasPermission(Permission.MESSAGE_MANAGE)) {
                        if (e.getChannel().getId().equals("804790473016737799")) {
                            e.getChannel().retrieveMessageById(e.getMessageId()).queue(message -> {
                                message.delete().queue();
                            });
                        }

                    }
                }
            }
        }
    }

}

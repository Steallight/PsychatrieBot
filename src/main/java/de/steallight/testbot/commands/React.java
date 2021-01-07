package de.steallight.testbot.commands;

import de.azraanimating.maddoxengine.handling.command.Command;
import de.azraanimating.maddoxengine.handling.command.CommandEvent;
import de.azraanimating.maddoxengine.handling.objects.MaddoxGuild;
import de.azraanimating.maddoxengine.handling.objects.MaddoxMember;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class React extends Command {

    public React() {

        this.setName("react");

    }

    @Override
    protected void execute(final CommandEvent event, final MaddoxMember sender, final MaddoxGuild server) {

            final String[] args = event.getMessage().getContentDisplay().split(" ");
        if (sender.hasPermission(Permission.MESSAGE_MANAGE)) {

            final List<TextChannel> channels = event.getMessage().getMentionedChannels();
            List<Emote> emotes = event.getMessage().getEmotes();



            if (!channels.isEmpty()) {
                final TextChannel tc = event.getMessage().getMentionedChannels().get(0);
                final String messageIDString = args[2];

                try {
                    final long messageID = Long.parseLong(messageIDString);

                    final StringBuilder stringBuilder = new StringBuilder();

                    for (Emote emote : emotes) {


                        tc.addReactionById(messageID, emote).queue();

                    }
                    event.deleteEventMessage();
                    final EmbedBuilder eb = new EmbedBuilder();
                    eb.setTitle("Deine Reaktion wurde hinzugefügt");
                    eb.setColor(Color.green);
                    event.getChannel().sendMessage(eb.build()).complete().delete().queueAfter(3, TimeUnit.SECONDS);


                } catch (final NumberFormatException e) {
                    e.printStackTrace();
                }
            }


        }

    }
}


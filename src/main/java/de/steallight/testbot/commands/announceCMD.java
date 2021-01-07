package de.steallight.testbot.commands;

import de.azraanimating.maddoxengine.handling.command.Command;
import de.azraanimating.maddoxengine.handling.command.CommandEvent;
import de.azraanimating.maddoxengine.handling.objects.MaddoxGuild;
import de.azraanimating.maddoxengine.handling.objects.MaddoxMember;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class announceCMD extends Command {

    public announceCMD(){
        this.setName("announce");
    }

    @Override
    protected void execute(CommandEvent event, MaddoxMember sender, MaddoxGuild server) {


        final String[] args = event.getMessage().getContentDisplay().split(" ");
        final List<TextChannel> channels = event.getMessage().getMentionedChannels();

        if (sender.hasPermission(Permission.MESSAGE_MANAGE)) {

            if (!channels.isEmpty()) {
                final TextChannel tc = event.getMessage().getMentionedChannels().get(0);
                final String message = args[2];

                try {
                    StringBuilder builder = new StringBuilder();

                    for (int i = 2; i < args.length; i++) {
                        builder.append(args[i]).append(" ");
                    }

                    String finishedString = builder.toString().trim();
                    event.deleteEventMessage();
                    EmbedBuilder embedBuilder = new EmbedBuilder();

                    embedBuilder.setTitle("Die Information wurde geposted!");
                    embedBuilder.setColor(Color.green);

                    EmbedBuilder eb = new EmbedBuilder();
                    eb.setDescription(finishedString);
                    eb.setFooter("posted by " + event.getSender().getUser().getName());

                    event.getChannel().sendMessage(embedBuilder.build()).complete().delete().queueAfter(5, TimeUnit.SECONDS);
                    tc.sendMessage(eb.build()).complete();


                } catch (final NumberFormatException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}

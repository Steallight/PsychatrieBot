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

public class delchannel extends Command {

    EmbedBuilder eb = new EmbedBuilder();

    public delchannel(){

        this.setName("delchannel");

    }

    @Override
    protected void execute(CommandEvent event, MaddoxMember sender, MaddoxGuild server) {
        if(sender.hasPermission(Permission.MANAGE_CHANNEL)) {

            final List<TextChannel> channels = event.getMessage().getMentionedChannels();
            final TextChannel tc = event.getMessage().getMentionedChannels().get(0);
            if (!channels.isEmpty()) {
                if(event.getChannel() == tc) {



                    tc.delete().queue();


                }else {
                    eb.setTitle("Channel wurde gelöscht!");
                    eb.setColor(Color.GREEN);

                    event.deleteEventMessage();
                    event.getChannel().sendMessage(eb.build()).complete().delete().queueAfter(5, TimeUnit.SECONDS);
                    tc.delete().queue();
                }
            }else {
                eb.setTitle("Bitte gebe einen Channel an");
                eb.setColor(Color.RED);
                event.getChannel().sendMessage(eb.build()).complete().delete().queueAfter(5,TimeUnit.SECONDS);
                event.deleteEventMessage();

            }
        }else{
            eb.setTitle("Dafür hast du keine Rechte!");
            eb.setColor(Color.RED);
            event.getChannel().sendMessage(eb.build()).complete().delete().queueAfter(5,TimeUnit.SECONDS);
            event.deleteEventMessage();
        }
    }
}

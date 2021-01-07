package de.steallight.testbot.commands;

import de.azraanimating.maddoxengine.handling.command.Command;
import de.azraanimating.maddoxengine.handling.command.CommandEvent;
import de.azraanimating.maddoxengine.handling.objects.MaddoxGuild;
import de.azraanimating.maddoxengine.handling.objects.MaddoxMember;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Avatar extends Command {

    public Avatar() {

        this.setName("avatar");

    }

    @Override
    protected void execute(final CommandEvent event, final MaddoxMember sender, final MaddoxGuild server) {



            if (sender.getID().equals("438200912599580675")) {

                event.getChannel().sendMessage(event.getExecutorAvatar(1024)).complete().delete().queueAfter(10, TimeUnit.SECONDS);
                event.getChannel().sendMessage("Das ist ein Cooler Dude").complete().delete().queueAfter(10, TimeUnit.SECONDS);
                event.deleteEventMessage();

            } else if (sender.getID().equals("694340147004833850")) {

                event.getChannel().sendMessage(event.getExecutorAvatar(1024)).complete().delete().queueAfter(10, TimeUnit.SECONDS);
                event.getChannel().sendMessage("Das ist Müll!").complete().delete().queueAfter(10, TimeUnit.SECONDS);
                event.deleteEventMessage();
            } else if (sender.getID().equals("508744920165515306")) {

                event.getChannel().sendMessage(event.getExecutorAvatar(1024)).complete().delete().queueAfter(10, TimeUnit.SECONDS);
                event.getChannel().sendMessage("Sie ist baba! Finger Weg!").complete().delete().queueAfter(10, TimeUnit.SECONDS);
                event.deleteEventMessage();

            } else {
                event.getChannel().sendMessage(event.getExecutorAvatar(1024)).complete().delete().queueAfter(10, TimeUnit.SECONDS);
            }

    }
}

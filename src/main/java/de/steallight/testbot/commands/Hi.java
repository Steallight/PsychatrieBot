package de.steallight.testbot.commands;

import de.azraanimating.maddoxengine.handling.command.Command;
import de.azraanimating.maddoxengine.handling.command.CommandEvent;
import de.azraanimating.maddoxengine.handling.objects.MaddoxGuild;
import de.azraanimating.maddoxengine.handling.objects.MaddoxMember;

import java.util.concurrent.TimeUnit;

public class Hi extends Command {

    public Hi() {

        this.setName("hi");


    }

    @Override
    protected void execute(final CommandEvent event, final MaddoxMember sender, final MaddoxGuild server) {



        event.getChannel().sendMessage("Hi " + sender.getAsMention() + " ^^").complete().delete().queueAfter(5, TimeUnit.SECONDS);
        event.deleteEventMessage();

    }

}

package de.steallight.testbot.commands;

import de.azraanimating.maddoxengine.handling.command.Command;
import de.azraanimating.maddoxengine.handling.command.CommandEvent;
import de.azraanimating.maddoxengine.handling.objects.MaddoxGuild;
import de.azraanimating.maddoxengine.handling.objects.MaddoxMember;

import java.util.Objects;

public class schmutz extends Command {

    public schmutz(){

        this.setName("schmutz");

    }

    @Override
    protected void execute(CommandEvent event, MaddoxMember sender, MaddoxGuild server) {
event.deleteEventMessage();
try {


    event.getChannel().sendMessage(server.getMemberById("426076212549386251").getAsMention() + " ist SCHMUTZ!").queue();
}catch (NullPointerException e){
    e.printStackTrace();
}

    }
}

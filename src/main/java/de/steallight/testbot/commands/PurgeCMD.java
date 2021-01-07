package de.steallight.testbot.commands;

import de.azraanimating.maddoxengine.handling.command.Command;
import de.azraanimating.maddoxengine.handling.command.CommandEvent;
import de.azraanimating.maddoxengine.handling.objects.MaddoxGuild;
import de.azraanimating.maddoxengine.handling.objects.MaddoxMember;
import net.dv8tion.jda.api.Permission;

public class PurgeCMD extends Command {

    public PurgeCMD(){

        this.setName("purge");

    }

    @Override
    protected void execute(CommandEvent event, MaddoxMember sender, MaddoxGuild server) {

        if(sender.hasPermission(Permission.MANAGE_CHANNEL)){
            if(event.getArguments().size() > 0){

            }
try {

Integer position = event.getChannel().getPosition();
    event.getChannel().createCopy().setPosition(position).queue();
    event.getChannel().delete().queue();
}catch (NullPointerException e){
    e.printStackTrace();
}
        }

    }
}

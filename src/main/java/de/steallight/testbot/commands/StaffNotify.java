package de.steallight.testbot.commands;

import de.azraanimating.maddoxengine.handling.command.Command;
import de.azraanimating.maddoxengine.handling.command.CommandEvent;
import de.azraanimating.maddoxengine.handling.objects.MaddoxGuild;
import de.azraanimating.maddoxengine.handling.objects.MaddoxMember;
import de.steallight.testbot.main.LiteSQL;
import net.dv8tion.jda.api.Permission;

import java.util.concurrent.TimeUnit;

public class StaffNotify extends Command {

    public StaffNotify(){this.setName("setnotify");}
    @Override
    protected void execute(CommandEvent event, MaddoxMember sender, MaddoxGuild server) {
        if(sender.hasPermission(Permission.MANAGE_CHANNEL)){
            LiteSQL.onUpdate("INSERT INTO NotifyChannel (channelId) VALUES (" + event.getChannel().getId() + ")");
            event.deleteEventMessage();
            event.getChannel().sendMessage("Der Notify Channel wurde gesetzt!").complete().delete().queueAfter(5, TimeUnit.SECONDS);
        }
    }
}

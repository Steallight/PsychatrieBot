package de.steallight.testbot.commands;

import de.azraanimating.maddoxengine.handling.command.Command;
import de.azraanimating.maddoxengine.handling.command.CommandEvent;
import de.azraanimating.maddoxengine.handling.objects.MaddoxGuild;
import de.azraanimating.maddoxengine.handling.objects.MaddoxMember;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class devCMD extends Command {

    public devCMD(){this.setName("dev");}

    @Override
    protected void execute(CommandEvent event, MaddoxMember sender, MaddoxGuild server) {

        event.deleteEventMessage();


        EmbedBuilder eb = new EmbedBuilder();

        eb.setTitle("Der Bot wurde von " + server.getMemberById("438200912599580675").getAsMention() + " programmiert");
        eb.setColor(Color.WHITE);

            event.reply(eb.build());

    }
}

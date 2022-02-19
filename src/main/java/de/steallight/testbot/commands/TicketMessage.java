package de.steallight.testbot.commands;

import de.azraanimating.maddoxengine.handling.command.Command;
import de.azraanimating.maddoxengine.handling.command.CommandEvent;
import de.azraanimating.maddoxengine.handling.objects.MaddoxGuild;
import de.azraanimating.maddoxengine.handling.objects.MaddoxMember;
import de.steallight.testbot.main.LiteSQL;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class TicketMessage extends Command {


    public TicketMessage(){
        this.setName("setticket");
    }
    public static TextChannel tc;
    @Override
    protected void execute(CommandEvent event, MaddoxMember sender, MaddoxGuild server) {




            EmbedBuilder eb = new EmbedBuilder();
            TextChannel tc = event.getChannel();
            if(!sender.getUser().isBot()){
                if(sender.hasPermission(Permission.MANAGE_PERMISSIONS)) {


                    LiteSQL.onUpdate("INSERT INTO TicketChannel (channelId) VALUES ("+ event.getChannel().getId()+ ")");
                    eb.setTitle("Ticket Support!");
                    eb.setDescription("Reagiere einfach mit \uD83D\uDCE9 um ein Support-Ticket zu erstellen!");
                    eb.setColor(Color.GREEN);

                    tc.sendMessage(eb.build()).queue(message -> {
                        message.addReaction("\uD83D\uDCE9").queue();
                        message.pin().queue();
                        event.deleteEventMessage();
                    });

                }else {
                    event.deleteEventMessage();
                    eb.setTitle("Keine Berechtigungen");
                    eb.setColor(Color.RED);
                    tc.sendTyping().queue();
                    tc.sendMessage(eb.build()).complete().delete().queueAfter(5, TimeUnit.SECONDS);


                }
            }

    }


}

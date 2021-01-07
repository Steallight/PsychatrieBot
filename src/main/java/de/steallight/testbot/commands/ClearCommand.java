package de.steallight.testbot.commands;

import de.azraanimating.maddoxengine.handling.command.Command;
import de.azraanimating.maddoxengine.handling.command.CommandEvent;
import de.azraanimating.maddoxengine.handling.objects.MaddoxGuild;
import de.azraanimating.maddoxengine.handling.objects.MaddoxMember;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ClearCommand extends Command {

    public ClearCommand() {
        this.setName("clear");

    }

    @Override
    protected void execute(CommandEvent event, MaddoxMember sender, MaddoxGuild server) {
        MessageChannel channel = event.getChannel();
        String[] args = event.getMessage().getContentRaw().split(" ");

        if (sender.hasPermission(event.getChannel(), Permission.MESSAGE_MANAGE)) {




            if (args.length == 2) {

                try {
                    int amount = Integer.parseInt(args[1]);

                    event.getChannel().purgeMessages(get(channel, amount));
                    EmbedBuilder embedBuilder = new EmbedBuilder();
                    embedBuilder.setDescription("Es wurden " + amount + " Nachrichten entfernt.");
                    embedBuilder.setColor(Color.blue);
                    event.getChannel().sendMessage(embedBuilder.build()).complete().delete().queueAfter(3, TimeUnit.SECONDS);


                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

            }
        }else{
            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(Color.RED);
            embed.setDescription("Dafür fehlen dir die Rechte");
            event.getChannel().sendMessage(embed.build()).complete().delete().queueAfter(5,TimeUnit.SECONDS);
        }

    }

    public List<Message> get(MessageChannel channel, int amount) {

        List<Message> messages = new ArrayList<>();
        int i = amount + 1;

        for (Message message : channel.getIterableHistory().cache(false)) {
            if (!message.isPinned()) {
                messages.add(message);
                if (--i <= 0) break;
            }
        }

        return messages;

    }

}

package de.steallight.testbot.commands;

import de.azraanimating.maddoxengine.handling.command.Command;
import de.azraanimating.maddoxengine.handling.command.CommandEvent;
import de.azraanimating.maddoxengine.handling.objects.MaddoxGuild;
import de.azraanimating.maddoxengine.handling.objects.MaddoxMember;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.PrivateChannel;

import java.awt.*;

public class Help extends Command {

    public Help() {

        this.setName("help");

    }


    String prefix = "!";
    @Override
    protected void execute(final CommandEvent event, final MaddoxMember sender, final MaddoxGuild server) {
        if (!sender.hasPermission(Permission.ADMINISTRATOR)) {
            final EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("----Hilfe Menü----");
            eb.setColor(Color.decode("#A900C3"));
            eb.addBlankField(false);
            eb.addField("", "**<> = Benötigt**", false);
            eb.addBlankField(false);
            eb.addField("Server Commands", "", false);
            eb.addField(prefix+"Help", "Zeigt dieses Hilfemenü an", false);
            eb.addField( prefix+"Hi", "Lässt den Bot Hi sagen", false);
            // eb.addField("-Join", "Schickt dir den Link sodass du dich bewerben kannst", false);
            eb.addField(prefix + "Avatar", "Zeigt dir deinen avatar vergrößert an.", false);
            eb.setFooter("made by Steallight");

            final PrivateChannel pc = sender.getUser().openPrivateChannel().complete();

            pc.sendMessage(eb.build()).queue();
            event.deleteEventMessage();
        } else {
            final EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("----Hilfe Menü----");
            eb.setColor(Color.decode("#A900C3"));
            eb.addBlankField(false);
            eb.addField("", "**<> = Benötigt**", false);
            eb.addBlankField(false);
            eb.addField("Server Commands", "", false);
            eb.addField(prefix+"Help", "Zeigt dieses Hilfemenü an", false);
            eb.addField(prefix+"Hi", "Lässt den Bot Hi sagen", false);
            eb.addField(prefix+"Join", "Schickt dir den Link sodass du dich bewerben kannst", false);
            eb.addField(prefix+"Avatar", "Zeigt dir deinen Avatar vergrößert an.", false);
            eb.addField(prefix+"React", "Reagiert für dich auf Nachrichten z.B. bei Abstimmungen.", false);
            eb.addField(prefix+"clear <Zahl>", "Löscht für dich die Anzahl an Nachrichten", false);
            eb.addField(prefix+"purge", "Löscht den gesamten Channelinhalt!", false);
            eb.setFooter("made by Steallight");

            final PrivateChannel pc = sender.getUser().openPrivateChannel().complete();
            event.deleteEventMessage();
            pc.sendMessage(eb.build()).queue();

        }
    }

}


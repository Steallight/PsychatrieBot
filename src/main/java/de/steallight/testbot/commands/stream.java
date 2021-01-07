package de.steallight.testbot.commands;

import com.github.twitch4j.TwitchClient;
import de.azraanimating.maddoxengine.handling.command.Command;
import de.azraanimating.maddoxengine.handling.command.CommandEvent;
import de.azraanimating.maddoxengine.handling.objects.MaddoxGuild;
import de.azraanimating.maddoxengine.handling.objects.MaddoxMember;
import de.steallight.testbot.listener.TwitchListener;
import net.dv8tion.jda.api.EmbedBuilder;

public class stream extends Command {

    public stream(){
        this.setName("stream");
    }




    @Override
    protected void execute(CommandEvent event, MaddoxMember sender, MaddoxGuild server) {

        if(sender.getRoles().contains(event.getGuild().getRoleById("780074754027159563"))){



                event.getChannel().sendMessage(event.getGuild().getPublicRole().getAsMention() + " Steallight ist jetzt live!").queue();
                event.getChannel().sendMessage("https://twitch.tv/steallight").queue();
            event.deleteEventMessage();

        }else {
            event.deleteEventMessage();
        }

    }
}

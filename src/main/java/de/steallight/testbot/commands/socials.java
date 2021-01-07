package de.steallight.testbot.commands;

import de.azraanimating.maddoxengine.handling.command.Command;
import de.azraanimating.maddoxengine.handling.command.CommandEvent;
import de.azraanimating.maddoxengine.handling.objects.MaddoxGuild;
import de.azraanimating.maddoxengine.handling.objects.MaddoxMember;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class socials extends Command {

    public socials(){
        this.setName("socials");
    }

    String instagram = "https://instagram.com/";
    String Twitch = "https://twitch.tv/";

    @Override
    protected void execute(CommandEvent event, MaddoxMember sender, MaddoxGuild server) {
        EmbedBuilder info = new EmbedBuilder();
        EmbedBuilder insta = new EmbedBuilder();
EmbedBuilder twitch = new EmbedBuilder();

        info.setTitle("Meine Socials");
        info.setColor(Color.WHITE);


        //Instagram
        insta.setTitle("Instagram", instagram+ "steallightmusic");

        insta.setThumbnail("https://cdn.icon-icons.com/icons2/1826/PNG/512/4202090instagramlogosocialsocialmedia-115598_115703.png");
        insta.setColor(Color.decode("#E1306C"));




        //Twitch


        twitch.setTitle("Twitch", Twitch + "steallight");
        twitch.setThumbnail("https://media-exp1.licdn.com/dms/image/C560BAQHm82ECP8zsGw/company-logo_200_200/0?e=2159024400&v=beta&t=r94Gy3UzJ4RMyHVIeECB9Q67gfAFC_FZVq9uCenVHXs");
        twitch.setColor(Color.decode("#9933ff"));

        event.reply(info.build());
        event.reply(insta.build());
        event.reply(twitch.build());
        event.deleteEventMessage();
    }
}

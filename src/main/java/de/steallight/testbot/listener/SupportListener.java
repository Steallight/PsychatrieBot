package de.steallight.testbot.listener;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.exceptions.RateLimitedException;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.RestAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class SupportListener extends ListenerAdapter {

    String channelid = "655373012131905550";
    String textid = "773841319805452318";


    @Override
    public void onGuildVoiceJoin(GuildVoiceJoinEvent e) {
        String user = e.getMember().getUser().getName();


        if (e.getChannelJoined().getId().equalsIgnoreCase(channelid)) {


            String name = e.getMember().getUser().getName();
            Guild guild = e.getGuild();

            Role admin = e.getGuild().getRoleById("655363884978405376");
            Role mods = e.getGuild().getRoleById("662338553895845918");
List usejoined = e.getMember().getRoles();
            TextChannel tc = e.getGuild().getTextChannelById(textid);


            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle(e.getMember().getUser().getName() + " befindet sich im Warteraum");
            eb.addField("User", e.getMember().getUser().getAsMention(), true);




            tc.sendMessage(eb.build()).complete().delete().queueAfter(10, TimeUnit.MINUTES);
            tc.sendMessage(admin.getAsMention()+" "+ mods.getAsMention()).complete().delete().queueAfter(10, TimeUnit.MINUTES);


        }
    }

    @Override
    public void onGuildVoiceMove(GuildVoiceMoveEvent e) {

        if (e.getChannelJoined().getId().equalsIgnoreCase(channelid)) {


            String name = e.getMember().getUser().getName();
            Guild guild = e.getGuild();

            Role supporter = e.getGuild().getRoleById("655363884978405376");

            TextChannel tc = e.getGuild().getTextChannelById(textid);


            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle(e.getMember().getUser().getName() + " befindet sich im Warteraum");
            eb.addField("User", e.getMember().getUser().getAsMention(), true);
            eb.addField("Rolle", supporter.getAsMention(), false);


            tc.sendMessage(eb.build()).queue();


        }
    }
}








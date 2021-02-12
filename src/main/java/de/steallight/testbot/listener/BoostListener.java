package de.steallight.testbot.listener;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateBoostTimeEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class BoostListener extends ListenerAdapter {

    @Override
    public void onGuildMemberUpdateBoostTime(GuildMemberUpdateBoostTimeEvent e) {
        Member boostMember = e.getMember();
        TextChannel tc = e.getGuild().getTextChannelById("780067626051043332");

        if (!(tc == null)) {
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("Der Server wurde geboosted!");
            eb.setColor(Color.decode("#FF68BE"));
            eb.addField("Wer?", boostMember.getAsMention(), false);
            eb.setThumbnail(boostMember.getUser().getAvatarUrl());

            tc.sendMessage(eb.build()).queue();

        }
    }

}

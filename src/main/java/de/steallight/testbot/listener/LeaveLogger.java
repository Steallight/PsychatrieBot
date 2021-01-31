package de.steallight.testbot.listener;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class LeaveLogger extends ListenerAdapter {

    @Override
    public void onGuildMemberRemove(GuildMemberRemoveEvent e) {

        TextChannel tc = e.getGuild().getTextChannelById("787420293366939708");
        if (tc != null) {
            tc.sendMessage("Der User " + e.getMember() + " hat den Server verlassen!").queue();
        }
    }

}

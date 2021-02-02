package de.steallight.testbot.listener;

import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MemberCounter extends ListenerAdapter {
    String memberChannel = "805189806639284234";


    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent e) {

        int members = e.getGuild().getMemberCount();

        VoiceChannel vc = e.getGuild().getVoiceChannelById(memberChannel);
        if ((vc != null)) {
            vc.getManager().setName("Members: " + members).queue();

        }


    }

    @Override
    public void onGuildMemberRemove(GuildMemberRemoveEvent e) {
        int members = e.getGuild().getMemberCount();


        VoiceChannel vc = e.getGuild().getVoiceChannelById(memberChannel);
        if (vc != null) {
            vc.getManager().setName("Members: " + members).queue();
        }
    }


}

package de.steallight.testbot.listener;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MemberCounter extends ListenerAdapter {
    String memberChannel = "805189806639284234";
    String botChannel = "805545855490064454";

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent e) {

        int members = e.getGuild().getMemberCount();
        Role botRole = e.getGuild().getRoleById("780068924158509088");
        VoiceChannel botVC = e.getGuild().getVoiceChannelById(botChannel);
        VoiceChannel vc = e.getGuild().getVoiceChannelById(memberChannel);
        if ((vc != null) && (botVC != null)) {
            vc.getManager().setName("Members: " + members).queue();
            botVC.getManager().setName("Bots: " + e.getGuild().getMembersWithRoles(botRole).size()).queue();
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

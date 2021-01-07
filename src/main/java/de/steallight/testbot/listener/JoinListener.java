package de.steallight.testbot.listener;

import de.steallight.testbot.main.LiteSQL;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class JoinListener extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(final GuildMemberJoinEvent e) {
        final Member joinedMember = e.getMember();
        final Long memberId = joinedMember.getIdLong();
        Role JoinRole = e.getGuild().getRoleById("780069105528733707");

        if(JoinRole == null) {

            System.out.println("Welpe Rolle nicht gefunden.");

        }else {
            joinedMember.getGuild().addRoleToMember(joinedMember, JoinRole).queue();
        }


        LiteSQL.onUpdate("INSERT INTO UserDataBase(name, memberid) VALUES('" + e.getMember().getEffectiveName() + "', '" + e.getMember().getId() + "')");


    }



}

package de.steallight.testbot.listener;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class VoiceListener extends ListenerAdapter {
    private final static long joinChannel = 774649165300105256L;
    private final List<Long> tempChannels = new ArrayList<>();

    @Override
    public void onGuildVoiceUpdate(GuildVoiceUpdateEvent e) {
        if (e.getChannelJoined() != null)
            onJoin(e.getChannelJoined(), e.getEntity());
        if (e.getChannelLeft() != null)
            onLeave(e.getChannelLeft());
        
    }

    public void onJoin(VoiceChannel joined, Member member) {
        if(joined.getIdLong() == joinChannel) {
            EnumSet<Permission> allow = EnumSet.of(Permission.MANAGE_CHANNEL);

            Category cat = joined.getParent();
            cat.createVoiceChannel("|⏳ " + member.getEffectiveName())
                    .flatMap(channel -> {
                        channel.getManager().setUserLimit(joined.getUserLimit()).queue();
            channel.getManager().putPermissionOverride(member, allow, null).queue();
                        tempChannels.add(channel.getIdLong());
                        return channel.getGuild().moveVoiceMember(member, channel);
                    }).queue();
        }
    }

    public void onLeave(VoiceChannel channel) {
        if (channel.getMembers().isEmpty()) {
            if (tempChannels.remove(channel.getIdLong())) {
                channel.delete().queueAfter(3, TimeUnit.SECONDS);
            }
        }
    }
}

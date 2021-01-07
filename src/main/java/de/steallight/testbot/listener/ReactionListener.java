package de.steallight.testbot.listener;
// This class was created by SteallightTTV

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReactionListener extends ListenerAdapter {

    @Override
    public void onMessageReactionAdd(final MessageReactionAddEvent e) {
        Role Glumanda = e.getGuild().getRoleById("781254199026909214");
        Role Shiggy = e.getGuild().getRoleById("781555323378204702");
        Role Pikachu = e.getGuild().getRoleById("781579228192964638");
if(e.getChannel().getId().equals("781253186609872927")){

    if(e.getMessageId().equals("781257942745743390")){
        if(e.getReactionEmote().getEmote().equals(e.getGuild().getEmoteById("781255541790998529"))){

            e.retrieveMember().queue(member -> {
                if (!member.getUser().isBot()) {
                    e.getGuild().addRoleToMember(member, Glumanda).queue();

                }
            });

        }else if(e.getReactionEmote().getEmote().equals(e.getGuild().getEmoteById("781554566276972552"))){

            e.retrieveMember().queue(member -> {
                if (!member.getUser().isBot()) {
                    e.getGuild().addRoleToMember(member, Shiggy).queue();
                }
            });

        }else if(e.getReactionEmote().getEmote().equals(e.getGuild().getEmoteById("781578701849886772"))){

            e.retrieveMember().queue(member -> {
                if (!member.getUser().isBot()) {
                    e.getGuild().addRoleToMember(member, Pikachu).queue();
                }
            });

        }

    }


}





    }

    @Override
    public void onMessageReactionRemove(MessageReactionRemoveEvent e){
        Role Shiggy = e.getGuild().getRoleById("781555323378204702");
        Role Glumanda = e.getGuild().getRoleById("781254199026909214");
        Role Pikachu = e.getGuild().getRoleById("781579228192964638");
        if(e.getChannel().getId().equals("781253186609872927")){

            if(e.getMessageId().equals("781257942745743390")) {
               
                    if (e.getReactionEmote().getEmote().equals(e.getGuild().getEmoteById("781255541790998529"))) {

                        e.retrieveMember().queue(member -> {
                            e.getGuild().removeRoleFromMember(member, Glumanda).queue();
                        });

                    } else if (e.getReactionEmote().getEmote().equals(e.getGuild().getEmoteById("781554566276972552"))) {

                        e.retrieveMember().queue(member -> {
                            if (!member.getUser().isBot()) {
                                e.getGuild().removeRoleFromMember(member, Shiggy).queue();
                            }
                        });

                    }else if(e.getReactionEmote().getEmote().equals(e.getGuild().getEmoteById("781578701849886772"))){

                        e.retrieveMember().queue(member -> {
                            if (!member.getUser().isBot()) {
                                e.getGuild().removeRoleFromMember(member, Pikachu).queue();
                            }
                        });

                    }

                }
            }


        }

    }



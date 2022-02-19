package de.steallight.testbot.listener;

import de.steallight.testbot.main.LiteSQL;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.sql.SQLException;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ReactionAddEvent extends ListenerAdapter {

    public static List<Member> supporter;
    public static List<Long> SupportChannel;
    public static TextChannel notifyChannel;

    @Override
    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent e) {
        try {


            notifyChannel = e.getGuild().getTextChannelById(LiteSQL.onQuery("SELECT * FROM NotifyChannel").getString(1));

            Guild guild = e.getGuild();
            TextChannel tc = e.getGuild().getTextChannelById(LiteSQL.onQuery("SELECT * FROM TicketChannel").getString(1));

            EmbedBuilder eb = new EmbedBuilder();
            System.out.println(e.getReactionEmote().getEmoji());
            Category cat = e.getGuild().getCategoryById("822901086675271681");
            EnumSet<Permission> allow = EnumSet.of(Permission.MESSAGE_WRITE);
            EnumSet<Permission> deny = EnumSet.of(Permission.VIEW_CHANNEL);
            Role supporterRole = e.getGuild().getRolesByName("Supporter/-in", true).get(0);
            if (e.getReactionEmote().getEmoji().equals("\uD83D\uDCE9")) {
                if (e.getChannel() == tc) {
                    if (!e.getUser().isBot()) {

                        e.retrieveMember().queue(member -> {
                            eb
                                    .setTitle("**Neuer Support**")
                                    .setColor(Color.GREEN)
                                    .addField("User", member.getAsMention(), true)
                                    .setDescription(supporterRole.getAsMention());


                            TextChannel(guild, member.getUser().getName() + "", cat, member);

                            if (!(notifyChannel == null)) {

                                notifyChannel.sendMessage(eb.build()).queue(message -> {
                                    message.addReaction("\u2714\uFE0F").queue();
                                });
                                notifyChannel.sendMessage(supporterRole.getAsMention()).complete().delete().queueAfter(1, TimeUnit.SECONDS);

                            }
                            e.getReaction().removeReaction(member.getUser()).queue();


                        });

                    }


                }
            }

            if (e.getReactionEmote().getEmoji().equals("\uD83D\uDD10")) {
                if (!e.getUser().isBot()) {
                    if (e.getMember().getRoles().contains((e.getGuild().getRolesByName("Supporter/-in", true).get(0)))) {


                        e.getChannel().delete().queueAfter(3, TimeUnit.SECONDS);
                    } else {
                        eb.setDescription("Nur Supporter können Tickets schließen!");
                        eb.setColor(Color.RED);
                        e.getReaction().removeReaction(e.getUser()).queue();
                        e.getChannel().sendMessage(eb.build()).complete().delete().queueAfter(3, TimeUnit.SECONDS);
                    }
                }
            } else if (e.getReactionEmote().getEmoji().equals("\u2714\uFE0F")) {
                if (!e.getUser().isBot()) {
                    deleteMsg(e.getReaction().getMessageIdLong(), guild);
                }
            }
        }catch (SQLException ee){
            ee.printStackTrace();
        }

    }
    public void TextChannel(Guild guild, String channelName, Category cat, Member member) {


        //Hier kannst du die Embed Msg Definieren
        EmbedBuilder builder = new EmbedBuilder();
        builder
                .setTitle("Wilkommen im Support!")
                .setDescription("Bitte gebe hier dein Problem an")
                .setColor(Color.GREEN)
                .addField("User", member.getAsMention(), false);

        guild.createTextChannel(channelName, cat)
                //Hier werden die einzelnen Permissions für den erstellten Channel gesetzt
                .addPermissionOverride(guild.getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))
                .addPermissionOverride(member, EnumSet.of(Permission.MESSAGE_WRITE), null)
                .addRolePermissionOverride(822924843020320799L, EnumSet.of(Permission.MESSAGE_WRITE), null)
                //Hier kannst du den Ersten Text im neuen Channel einstellen
                .flatMap((channel) -> channel.sendMessage(builder.build()))
                .flatMap((message) -> message.addReaction("\uD83D\uDD10")).queue();




    }
    public void deleteMsg(long messageid, Guild guild){

        notifyChannel.deleteMessageById(messageid).queue();
        notifyChannel.sendMessage("Support angenommen!").complete().delete().queueAfter(10,TimeUnit.SECONDS);

    }

}

package de.steallight.testbot.listener;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.common.util.CryptoUtils;
import com.github.twitch4j.events.ChannelGoLiveEvent;
import com.github.twitch4j.events.ChannelGoOfflineEvent;
import com.github.twitch4j.helix.domain.UserList;
import de.steallight.testbot.main.Bot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TwitchListener extends ListenerAdapter {

    private final Cache<String, Boolean> recentlyOffline = Caffeine.newBuilder().expireAfterWrite(15, TimeUnit.MINUTES).build();
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
    UserList resultList = Bot.twitchClient.getHelix().getUsers(null, null, Arrays.asList("steallight")).execute();
    private final String twitch = "https://twitch.tv/";
    public long liveMsg1;
    public long liveMsg2;
    private final Bot bot;

    public TwitchListener(TwitchClient twitchClient, Bot bot) {
        this.bot = bot;
        twitchClient.getEventManager().onEvent(ChannelGoLiveEvent.class, this::handleChannelGoLiveEvent);
        twitchClient.getEventManager().onEvent(ChannelGoOfflineEvent.class, this::handleChannelGoOfflineEvent);
    }

    public void handleChannelGoLiveEvent(ChannelGoLiveEvent event) {

        if(recentlyOffline.getIfPresent(event.getChannel().getId())== null) {
            executor.schedule(() -> {
                EmbedBuilder eb = new EmbedBuilder();

                TextChannel tc = Bot.shardMan.getTextChannelById("780067626051043332");

                eb.setTitle(event.getStream().getTitle(), twitch + event.getChannel().getName().toLowerCase());

                resultList.getUsers().forEach(user -> {
                    eb.setThumbnail(user.getProfileImageUrl());
                });



                eb.setImage(event.getStream().getThumbnailUrl(1280, 720) + "?c=" + CryptoUtils.generateNonce(4));

                eb.addField("Game", "" + event.getStream().getGameName(), true);



                try {

                    if (tc != null) {
                        tc.sendMessage(tc.getGuild().getPublicRole().getAsMention() + " " + event.getChannel().getName() + " ist jetzt live. Schaut alle rein!").queue(message -> {
                            liveMsg1 = message.getIdLong();
                        });
                        tc.sendMessage(eb.build()).queue(message -> {
                            liveMsg2 = message.getIdLong();
                        });
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();

                }
            }, 2, TimeUnit.MINUTES);

        }

    }

    public void handleChannelGoOfflineEvent(ChannelGoOfflineEvent event) {
        recentlyOffline.put(event.getChannel().getId(), true);
        TextChannel tc = Bot.shardMan.getTextChannelById(780067626051043332L);
        if (tc != null) {
            tc.deleteMessageById(liveMsg1).queue();
            tc.deleteMessageById(liveMsg2).queue();
        } else {
            System.out.println("Channel nicht verfügbar");
        }
    }
}
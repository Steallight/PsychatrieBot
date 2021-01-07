package de.steallight.testbot.listener;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.common.events.TwitchEvent;
import com.github.twitch4j.common.util.CryptoUtils;
import com.github.twitch4j.events.ChannelGoLiveEvent;
import com.github.twitch4j.events.ChannelGoOfflineEvent;
import com.github.twitch4j.helix.domain.GameList;
import com.github.twitch4j.helix.domain.UserList;
import com.netflix.hystrix.HystrixCommand;
import de.azraanimating.maddoxengine.handling.objects.MaddoxGuild;
import de.steallight.testbot.main.Bot;
import feign.Param;
import javafx.scene.input.PickResult;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.sharding.DefaultShardManager;
import net.dv8tion.jda.api.sharding.ShardManager;

import java.awt.image.ImageProducer;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TwitchListener extends ListenerAdapter {

    private final Cache<String, Boolean> recentlyOffline = Caffeine.newBuilder().expireAfterWrite(15, TimeUnit.MINUTES).build();
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
    UserList resultList = Bot.twitchClient.getHelix().getUsers(null, null, Arrays.asList("steallight")).execute();


private String twitch = "https://twitch.tv/";
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

                eb.addField("Zuschauer", "" + event.getStream().getViewerCount(), true);



                try {


                    tc.sendMessage(tc.getGuild().getPublicRole().getAsMention() + " " + event.getChannel().getName() + " ist jetzt live. Schaut alle rein!").queueAfter(5, TimeUnit.SECONDS);
                    tc.sendMessage(eb.build()).queue();

                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }, 2, TimeUnit.MINUTES);

        }

    }
    public void handleChannelGoOfflineEvent(ChannelGoOfflineEvent event){
        recentlyOffline.put(event.getChannel().getId(), true);
    }
}
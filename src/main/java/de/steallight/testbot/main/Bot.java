package de.steallight.testbot.main;

import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import de.azraanimating.maddoxengine.handling.command.CommandHandler;
import de.steallight.testbot.codes.CodeStorage;
import de.steallight.testbot.commands.*;
import de.steallight.testbot.listener.*;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

public class Bot {

    public static AudioPlayerManager audioPlayerManager;
    public static Bot INSTANCE;
    private Thread loop;
    private final ActivityManager activityManager;
    public static ShardManager shardMan;
public static TextChannel tc;
    private final CommandHandler handler;
    public static TwitchClient twitchClient;



    public Bot() throws LoginException {
        INSTANCE = this;
        this.handler = new CommandHandler();
        this.activityManager = new ActivityManager(this);

     twitchClient = TwitchClientBuilder.builder()
                .withClientId(CodeStorage.TwitchClientID)
                .withClientSecret(CodeStorage.TwitchSecret)
                .withEnableHelix(true)

                .build();

        twitchClient.getClientHelper().enableStreamEventListener("steallight");


        shardMan = DefaultShardManagerBuilder.createDefault(CodeStorage.BotToken,
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.DIRECT_MESSAGES,
                GatewayIntent.GUILD_BANS,
                GatewayIntent.GUILD_PRESENCES,
                GatewayIntent.GUILD_VOICE_STATES,
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.DIRECT_MESSAGE_REACTIONS,
                GatewayIntent.DIRECT_MESSAGE_TYPING,
                GatewayIntent.GUILD_EMOJIS,
                GatewayIntent.GUILD_MESSAGE_REACTIONS


        )
                .enableCache(
                        CacheFlag.VOICE_STATE,
                        CacheFlag.MEMBER_OVERRIDES,
                        CacheFlag.CLIENT_STATUS,
                        CacheFlag.EMOTE,
                        CacheFlag.ACTIVITY,
                        CacheFlag.MEMBER_OVERRIDES
                )

                .build();

        shardMan.setActivity(Activity.playing("programmieren"));
        shardMan.setStatus(OnlineStatus.DO_NOT_DISTURB);



        /*
        builder.setActivity(Activity.playing("Work in Progress"));
        builder.setStatus(OnlineStatus.DO_NOT_DISTURB);

         */
        try {


            audioPlayerManager = new DefaultAudioPlayerManager();

        } catch (NoClassDefFoundError e) {
            e.addSuppressed(e);
        }
        new TwitchListener(twitchClient, this);
        shardMan.addEventListener(new GuildMessageReceivedListener(this.handler));
        shardMan.addEventListener(new IdeenListener());

        shardMan.addEventListener(new ReactionListener());
        shardMan.addEventListener(new JoinListener());

        shardMan.addEventListener(new VoiceListener());

        shardMan.addEventListener(new MemberCounter());

        //   shardMan.addEventListener(new SupportListener());

        this.handler.registerCommand(new delchannel());
        this.handler.registerCommand(new Help());


        handler.registerCommand(new devCMD());
        handler.registerCommand(new stream());
        this.handler.registerCommand(new ClearCommand());
        handler.registerCommand(new socials());
        handler.registerCommand(new PurgeCMD());

        this.handler.registerCommand(new React());
        this.handler.registerCommand(new Hi());

        this.handler.registerCommand(new Avatar());
        this.handler.registerCommand(new announceCMD());
        //this.handler.registerCommand(new PlayCommand(this));
        //this.handler.registerCommand(new StopCommand());

        this.activityManager.loadPresence();
        AudioSourceManagers.registerRemoteSources(audioPlayerManager);
        audioPlayerManager.getConfiguration().setFilterHotSwapEnabled(true);


    }

    public AudioPlayerManager getAudioPlayerManager() {
        return audioPlayerManager;
    }


    public CommandHandler getHandler() {
        return this.handler;
    }

    public ShardManager getShardMan() {
        return shardMan;
    }


}

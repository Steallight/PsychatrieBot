package de.steallight.testbot.listener;

import de.azraanimating.maddoxengine.handling.command.CommandHandler;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class GuildMessageReceivedListener extends ListenerAdapter {

    private final CommandHandler handler;

    public GuildMessageReceivedListener(final CommandHandler commandHandler) {

        this.handler = commandHandler;

    }

    @Override
    public void onGuildMessageReceived(final @NotNull GuildMessageReceivedEvent e) {

        this.handler.handle(e, e.getMessage().getContentRaw(), "!");

    }

}

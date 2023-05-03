package org.example.listeners;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.example.UrlModifier;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MessageListener extends ListenerAdapter {

    private final String[] randomAaronReplies = {"ratio", "dn", "and you?", "you and you?", "bro and bro?", "thanks bro, you?", "and you bro", "you?", "and?"};

    private final HashMap<String, Integer> cmdCount = new HashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().contains("http://") || event.getMessage().getContentRaw().contains("https://")) {
            if (event.getAuthor().isBot()) return;
            String message = event.getMessage().getContentRaw();
            String vxMessage = UrlModifier.extractUrl(message);
            event.getChannel().sendMessage("use vx twitter idiot " + vxMessage).queue();
        }
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("dn")) {
            String userId = event.getUser().getId();
            int count = cmdCount.getOrDefault(userId, 0);

            if (count >= 3) {
                event.reply("You have used this command too many times, please wait a few seconds").setEphemeral(true).queue();
                return;
            }

            final String randomResponse = randomAaronReplies[new Random().nextInt(randomAaronReplies.length)];
            event.reply(randomResponse).queue();

            cmdCount.put(userId, count + 1);

            if (count + 1 >= 3) {
                scheduler.schedule(() -> cmdCount.remove(userId), 10, TimeUnit.SECONDS);
            }
        }
    }
}

package org.example;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.example.listeners.MessageListener;
import org.example.listeners.OnReadyListener;


public class Main {
    public static void main(String[] args) {
        JDABuilder builder = JDABuilder.createDefault(Config.getDiscordApiToken());

        JDA jda = builder
                .addEventListeners(new OnReadyListener(), new MessageListener())
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .build();

        jda.upsertCommand("dn", "deez nuts").setGuildOnly(true).queue();
    }
}
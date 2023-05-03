package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static Properties props = new Properties();
    static {
        try {
            props.load(new FileInputStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getDiscordApiToken() {
        return props.getProperty("DISCORD_API_TOKEN");
    }
}

package com.example.examplemod;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = "skynetplus")
@LangKey("skynetplus" + ".config.title")
public final class ModConfig {

    @LangKey("skynetplus" + ".config.group")
    public static String group = "";

    @Mod.EventBusSubscriber(modid = "skynetplus")
    private static class ConfigEventSubscriber {

        /**
         * Inject the new values and save to the config file when the config has been changed from the GUI.
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals("skynetplus")) {
                ConfigManager.sync("skynetplus", Config.Type.INSTANCE);
            }
        }
    }
}

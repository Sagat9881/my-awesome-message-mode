package ru.awesome.mods.message;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import ru.awesome.mods.message.screen.mymessage.MyMessageScreen;

public class MyAwesomeModeClient implements ClientModInitializer {
    public static final KeyBinding TOGGLE_SCREEN_KEY =
            KeyBindingHelper.registerKeyBinding(
                    new KeyBinding(
                            "key.examplemod.togglescreen",
                            11,
                            KeyBinding.Category.GAMEPLAY
                    ));

    @Override
    public void onInitializeClient() {
        // Открываем экран по нажатию
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (TOGGLE_SCREEN_KEY.isPressed()) {
                client.execute(() -> {
                    client.setScreen(MyMessageScreen.SCREEN);
                });
            }
        });
    }

}
package ru.awesome.mods.message.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.awesome.mods.message.screen.mymessage.MyMessageScreen;

@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {

    protected TitleScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At("RETURN"))
    public void openTitleScreen( CallbackInfo ci)  {
        this.addDrawableChild(
                ButtonWidget.builder(
                                Text.literal("send message"),
                                button -> MinecraftClient.getInstance().setScreen(MyMessageScreen.SCREEN))
                        .dimensions(this.width / 2 - 100 + 205, 70, 50, 20)
                        .build()
        );
    }
}

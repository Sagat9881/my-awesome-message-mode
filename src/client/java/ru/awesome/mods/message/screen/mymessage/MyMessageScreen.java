package ru.awesome.mods.message.screen.mymessage;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.awesome.mods.message.dto.ClientMessageOuterClass;
import ru.awesome.mods.message.dto.MyC2SMessage;

public class MyMessageScreen extends Screen {
    public static final MyMessageScreen SCREEN = new MyMessageScreen(Text.literal("Отправка сообщения!"));
    private TextFieldWidget textField;
    private ButtonWidget sendButton;

    public static final Logger LOGGER = LoggerFactory.getLogger(MyMessageScreen.class);

    public MyMessageScreen(Text title) {
        super(title);
    }

    @Override
    protected void init() {
        // Определяем размеры и положение виджетов
        int buttonWidth = 60;
        int buttonHeight = 20;
        int fieldWidth = 200;
        int fieldHeight = 20;

        // Создаём поле ввода
        this.textField = new TextFieldWidget(
                this.textRenderer,
                this.width / 2 - fieldWidth / 2,
                this.height / 2 - fieldHeight / 2 - 10,
                fieldWidth,
                fieldHeight,
                Text.literal("")
        );
        this.textField.setFocused(true);
        this.textField.setMaxLength(256);
        this.addDrawableChild(this.textField);

        // Создаём кнопку отправки
        this.sendButton = ButtonWidget.builder(
                        Text.literal("Отправить"),
                        (button) -> {
                            sendMessage();
                            close();
                        }
                )
                .dimensions(
                        this.width / 2 - buttonWidth / 2,
                        this.height / 2 + 10,
                        buttonWidth,
                        buttonHeight
                )
                .build();
        this.addDrawableChild(this.sendButton);
    }

    // Метод, который будет вызываться при нажатии кнопки "Отправить"
    private void sendMessage() {
        String message = this.textField.getText();
        if (!message.isEmpty()) {
            System.out.println(message);
            if (client.player != null) {
                // Отправляем пакет
                ClientPlayNetworking.send(
                        new MyC2SMessage(
                                ClientMessageOuterClass.ClientMessage.newBuilder()
                                        .setText(message)
                                        .setPlayerId(client.player.getUuid().toString())
                                        .build())
                );
                LOGGER.debug("Отправлено сообщение: {}", message);
            }
        }
    }

}

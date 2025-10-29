package ru.awesome.mods.message.listener;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.awesome.mods.message.dto.MyC2SMessage;
import ru.awesome.mods.message.service.MessageService;
import ru.awesome.mods.message.service.MessageServiceImpl;

public class MessageC2SListener implements C2SListener<MyC2SMessage> {
    public static final Logger LOGGER = LoggerFactory.getLogger(MessageC2SListener.class);

    public MessageC2SListener(MessageService service) {
        this.service = service;
    }

    public MessageC2SListener() {
        this(new MessageServiceImpl());
    }

    private final MessageService service;

    @Override
    public void onMessage(MyC2SMessage payload, ServerPlayNetworking.Context context) {
        context.server().execute(() -> {

            service.insertToDB(payload.protobufMessage(), context);

            context.player().sendMessage(Text.of("Protobuf-message: " + payload.protobufMessage().getText()), false);
            LOGGER.info("Message received from client: {}", payload.protobufMessage().getText());
        });
    }

}

package ru.awesome.mods.message.listener;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.awesome.mods.message.dto.ClientMessageOuterClass;
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
            ClientMessageOuterClass.ClientMessage protobufMessage = payload.protobufMessage();
            ServerPlayerEntity player = context.player();

            service.insertToDB(protobufMessage);

            player.sendMessage(Text.of("Protobuf-message: " + protobufMessage.getText()), false);
            LOGGER.info("Message received from client: {}", protobufMessage.getText());
        });
    }

    @Override
    public Class<MyC2SMessage> type() {
        return MyC2SMessage.class;
    }


}

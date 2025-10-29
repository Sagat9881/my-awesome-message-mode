package ru.awesome.mods.message.dto;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import static ru.awesome.mods.message.MyAwesomeMode.LOGGER;
import static ru.awesome.mods.message.MyAwesomeMode.MOD_ID;

public record MyC2SMessage(ClientMessageOuterClass.ClientMessage protobufMessage) implements CustomPayload {
    public static final Id<MyC2SMessage> ID = new Id<>(Identifier.of(MOD_ID, "protobuf_packet"));


    public static final PacketCodec<PacketByteBuf, MyC2SMessage> CODEC = PacketCodec.of(
            MyC2SMessage::write,
            MyC2SMessage::new
    );

    // Чтение
    private MyC2SMessage(PacketByteBuf buf) {
        this(readProtobufFromBuf(buf));
    }

    //Записи
    private void write(PacketByteBuf buf) {
        buf.writeByteArray(protobufMessage.toByteArray());
    }

    // Вспомогательный метод для десериализации
    private static ClientMessageOuterClass.ClientMessage readProtobufFromBuf(PacketByteBuf buf) {
        try {
            return ClientMessageOuterClass.ClientMessage.parseFrom(buf.readByteArray());
        } catch (Exception e) {
            // Обработка ошибок десериализации
            LOGGER.error("Failed to read protobuf message", e);
            return null;
        }
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
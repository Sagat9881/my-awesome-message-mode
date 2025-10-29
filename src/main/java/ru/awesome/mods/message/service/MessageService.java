package ru.awesome.mods.message.service;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import ru.awesome.mods.message.dto.ClientMessageOuterClass;

public interface MessageService {

    void insertToDB(ClientMessageOuterClass.ClientMessage protobufMessage, ServerPlayNetworking.Context context);
}

package ru.awesome.mods.message.service;

import ru.awesome.mods.message.dto.ClientMessageOuterClass;

public interface MessageService {

    void insertToDB(ClientMessageOuterClass.ClientMessage protobufMessage);
}

package ru.awesome.mods.message.listener;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;


public interface C2SListener<PAYLOAD> {
    void onMessage(PAYLOAD payload, ServerPlayNetworking. Context context);

    Class<PAYLOAD> type();



}

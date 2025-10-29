package ru.awesome.mods.message;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.awesome.mods.message.dto.ClientMessageOuterClass;
import ru.awesome.mods.message.dto.MyC2SMessage;
import ru.awesome.mods.message.entity.MessageEntity;
import ru.awesome.mods.message.listener.C2SListener;
import ru.awesome.mods.message.listener.MessageC2SListener;
import ru.awesome.mods.message.util.HibernateSessionFactoryUtil;

import java.io.Serializable;
import java.util.*;

public class MyAwesomeMode implements ModInitializer {
    public static final String MOD_ID = "my-awesome-mode";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        PayloadTypeRegistry.playC2S().register(MyC2SMessage.ID, MyC2SMessage.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(MyC2SMessage.ID, (payload, context) -> {
            new MessageC2SListener().onMessage(payload, context);
        });

    }


}
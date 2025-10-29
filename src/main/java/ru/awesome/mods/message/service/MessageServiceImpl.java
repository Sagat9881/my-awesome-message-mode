package ru.awesome.mods.message.service;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.awesome.mods.message.dto.ClientMessageOuterClass;
import ru.awesome.mods.message.entity.MessageEntity;
import ru.awesome.mods.message.util.HibernateSessionFactoryUtil;

public class MessageServiceImpl implements MessageService{

    public static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);

    public void insertToDB(ClientMessageOuterClass.ClientMessage protobufMessage, ServerPlayNetworking.Context context) {
        LOGGER.info("Start inserting to db");
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setText(protobufMessage.getText());
        messageEntity.setPlayerId(context.player().getUuid());

        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(messageEntity);
            session.getTransaction().commit();

            LOGGER.info("Entity inserted to db with ID: {}", messageEntity.getId());
        } catch (Exception e) {
            LOGGER.error("Exception while inserting to db: ", e);
        }
    }


}

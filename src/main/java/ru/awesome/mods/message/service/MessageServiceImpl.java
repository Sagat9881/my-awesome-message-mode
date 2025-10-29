package ru.awesome.mods.message.service;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.awesome.mods.message.dto.ClientMessageOuterClass;
import ru.awesome.mods.message.entity.MessageEntity;
import ru.awesome.mods.message.util.HibernateSessionFactoryUtil;

import java.util.UUID;

public class MessageServiceImpl implements MessageService{

    public static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);

    public void insertToDB(ClientMessageOuterClass.ClientMessage protobufMessage) {
        LOGGER.info("Start inserting to db");


        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            MessageEntity messageEntity = new MessageEntity();
            messageEntity.setText(protobufMessage.getText());
            messageEntity.setId(UUID.randomUUID());
            session.persist(messageEntity);
            session.getTransaction().commit();

            LOGGER.info("Entity inserted to db with ID: {}", messageEntity.getId());
        } catch (Exception e) {
            LOGGER.error("Exception while inserting to db: ", e);
        }
    }


}

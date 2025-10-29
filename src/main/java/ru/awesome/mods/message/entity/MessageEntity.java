package ru.awesome.mods.message.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "message")
public class MessageEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "player_id")
    private UUID playerId;

    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MessageEntity() {
    }
}

package com.example.shareviewnotificationservice.entities;

import com.example.shareviewnotificationservice.exceptions.BadArgumentException;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Notification {

    private final String subject;
    private final String message;

    public Notification(String subject, String message) {
        validateSubject(subject);
        validateMessage(message);

        this.subject = subject;
        this.message = message;
    }

    private void validateSubject(String subject) {
        if (Objects.isNull(subject))
            throw new BadArgumentException("A notificação deve possuir um assunto.");
    }

    private void validateMessage(String message) {
        if (Objects.isNull(message))
            throw new BadArgumentException("A notificação deve possuir um mensagem.");
    }
}

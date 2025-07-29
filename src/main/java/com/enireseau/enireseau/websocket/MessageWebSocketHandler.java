package com.enireseau.enireseau.websocket;

import com.enireseau.enireseau.entites.Message;
import com.enireseau.enireseau.services.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class MessageWebSocketHandler implements WebSocketHandler {

    private final ObjectMapper mapper = new ObjectMapper();
    private final MessageService messageService;

    // Map des sessions connectées
    private final Map<Integer, List<WebSocketSession>> sessionsByUser = new HashMap<>();

    public MessageWebSocketHandler(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String userId = getQueryParam(session, "userId");
        if (userId != null) {
            int uid = Integer.parseInt(userId);
            sessionsByUser.putIfAbsent(uid, new ArrayList<>());
            sessionsByUser.get(uid).add(session);
        }
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws IOException {
        Message msg = mapper.readValue(message.getPayload().toString(), Message.class);
        msg.setDate_envoi(LocalDateTime.now());
        Message saved = messageService.cree(msg);

        String payload = mapper.writeValueAsString(saved);

        // envoyer au destinataire
        List<WebSocketSession> destSessions = sessionsByUser.get(msg.getDestinataire());
        if (destSessions != null) {
            for (WebSocketSession s : destSessions) {
                if (s.isOpen()) s.sendMessage(new TextMessage(payload));
            }
        }

        // écho à l'expéditeur
        List<WebSocketSession> expSessions = sessionsByUser.get(msg.getExpediteur());
        if (expSessions != null) {
            for (WebSocketSession s : expSessions) {
                if (s.isOpen()) s.sendMessage(new TextMessage(payload));
            }
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        // gestion erreurs
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessionsByUser.values().forEach(list -> list.remove(session));
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    private String getQueryParam(WebSocketSession session, String param) {
        String uri = session.getUri().toString();
        return Arrays.stream(uri.split("\\?"))
                .skip(1)
                .findFirst()
                .flatMap(query -> Arrays.stream(query.split("&"))
                        .filter(p -> p.startsWith(param + "="))
                        .map(p -> p.split("=")[1])
                        .findFirst())
                .orElse(null);
    }
}

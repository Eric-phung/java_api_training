package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class HttpServerHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Définir le contenu de la réponse
        String body = "OK";

        // Envoyer la réponse avec le code 200 (OK)
        exchange.sendResponseHeaders(200, body.length());

        // Écrire le corps de la réponse dans le flux de sortie
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(body.getBytes());
        }
    }
}
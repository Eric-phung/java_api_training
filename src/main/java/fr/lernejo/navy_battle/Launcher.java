package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Launcher {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: java -jar navy_battle.jar <port>");
            System.exit(1);
        }

        int port = Integer.parseInt(args[0]);

        // Création du serveur HTTP
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        // Définition d'un ExecutorService avec une taille fixe (1 thread)
        server.setExecutor(java.util.concurrent.Executors.newFixedThreadPool(1));

        // Création et ajout d'un contexte pour le chemin "/ping"
        server.createContext("/ping", new PingHandler());

        // Démarrage du serveur
        server.start();

        System.out.println("Server is listening on port " + port);
    }

    // Implémentation d'un gestionnaire pour le chemin "/ping"
    static class PingHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String body = "Hello";
exchange.sendResponseHeaders(200, body.length());
try (OutputStream os = exchange.getResponseBody()) { // (1)
    os.write(body.getBytes());
}
        }
    }
}
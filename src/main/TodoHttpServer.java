package main;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import dao.TodoDAO;
import model.Todo;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class TodoHttpServer {

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);

        server.createContext("/add", (HttpExchange exchange) -> {

            // ===== CORS HEADERS =====
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

            // ===== HANDLE PREFLIGHT =====
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            // ===== HANDLE POST =====
            if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                try {
                    String body = new String(
                            exchange.getRequestBody().readAllBytes(),
                            StandardCharsets.UTF_8
                    );

                    TodoDAO dao = new TodoDAO();
                    dao.addTodo(new Todo(body));

                    String response = "Task added";
                    exchange.sendResponseHeaders(200, response.length());

                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();

                } catch (Exception e) {
                    String error = "Error adding task";
                    exchange.sendResponseHeaders(500, error.length());

                    OutputStream os = exchange.getResponseBody();
                    os.write(error.getBytes());
                    os.close();

                    e.printStackTrace();
                }
            }
        });

        server.start();
        System.out.println("✅ Server started at http://localhost:8081 - TodoHttpServer.java:63");
    }
}
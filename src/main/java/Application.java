import jdk.jfr.ContentType;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Application
{
    public static void main(String[] args) throws IOException, InterruptedException {
        String openAIKey = System.getenv("OPEN_AI_KEY"); // created OpenAi Api key and set it in an environmental variable
        var requestBody = """
                {
                    "model": "gpt-4o",
                    "messages": [
                        {
                            "role": "user",
                            "content": "Tell me about java springboot annotations"
                        }
                    ]
                }""";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.openai.com/v1/chat/completions"))
                .header("Content-Type", "application/json")
                .header("Authorization","Bearer"+openAIKey)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpClient client =HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}

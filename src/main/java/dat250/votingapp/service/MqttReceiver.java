package dat250.votingapp.service;

import org.eclipse.paho.client.mqttv3.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MqttReceiver {

    public static void main(String[] args) {
        String brokerUrl = "tcp://172.20.10.2:1883";
        String clientId = "FeedApp";
        String greenTopic = "pushbutton/Button2/state";
        String redTopic = "pushbutton/Button1/state";

        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            MqttClient client = new MqttClient(brokerUrl, clientId);
            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            //options.setConnectionTimeout(100);
            client.connect(options);

            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    // Called when the client lost the connection to the broker
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    // Called when a message arrives from the server that matches any subscription made by the client
                    System.out.println("Received message: " + topic + new String(message.getPayload()));
                    registerVote(topic);
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    // Called when an outgoing publish is complete
                }

                public void registerVote(String topic) throws IOException, InterruptedException {

                    String suburl = "";

                    if (topic.equals(greenTopic)) {
                        suburl = "greenVote";
                    } else {
                        suburl = "redVote";
                    }

                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create("http://localhost:4200/api/notification" + suburl))
                            .POST(HttpRequest.BodyPublishers.ofString(suburl))
                            .header("Content-Type", "application/json")
                            .build();
                    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                    System.out.println("Message Sent: " + suburl);
                }
            });

            client.subscribe(greenTopic);
            client.subscribe(redTopic);

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}


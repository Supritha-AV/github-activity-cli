package com.example.github;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class GitHubActivityCLI {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Enter a GitHub username: ");
            String username = reader.readLine();

            String apiUrl = "https://api.github.com/users/" + username + "/events";
            
            // Fetch data from the GitHub API
            String response = fetchGitHubActivity(apiUrl);
            if (response == null) {
                System.out.println("Unable to fetch activity. Please check the username or try again later.");
                return;
            }

            // Parse and display the activity
            parseAndDisplayActivity(response);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static String fetchGitHubActivity(String apiUrl) {
        try {
            // Create a connection to the API
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            
            // Optionally, set a User-Agent to avoid issues with API request rejection
            connection.setRequestProperty("User-Agent", "GitHubActivityCLI");

            // Check the response code
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                System.out.println("Error: Received HTTP status code " + responseCode);
                return null;
            }

            // Read the response
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                return response.toString();
            }
        } catch (Exception e) {
            System.out.println("Error while fetching data: " + e.getMessage());
            return null;
        }
    }

    private static void parseAndDisplayActivity(String jsonResponse) {
        // Parse the JSON response
        JSONArray events = new JSONArray(jsonResponse);
        if (events.length() == 0) {
            System.out.println("No recent activity found.");
            return;
        }

        // Loop through the events and display relevant activity
        for (int i = 0; i < events.length(); i++) {
            JSONObject event = events.getJSONObject(i);
            String eventType = event.getString("type");
            String repoName = event.getJSONObject("repo").getString("name");

            switch (eventType) {
                case "PushEvent":
                    int commitCount = event.getJSONObject("payload").getJSONArray("commits").length();
                    System.out.println("- Pushed " + commitCount + " commits to " + repoName);
                    break;
                case "IssuesEvent":
                    String action = event.getJSONObject("payloSad").getString("action");
                    System.out.println("- " + action + " an issue in " + repoName);
                    break;
                case "WatchEvent":
                    System.out.println("- Starred " + repoName);
                    break;
                default:
                    System.out.println("- Performed " + eventType + " in " + repoName);
                    break;
            }
        }
    }
}

package com.anirudh.dailyprog;

import org.apache.commons.text.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import picocli.CommandLine;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

// Add .jar to jdeploy-bundle folder for jdeploy to access and run it.
@CommandLine.Command(name = "dailyprog",
        sortOptions = false,
        headerHeading = "%n@|bold,green,underline Usage|@:%n",
        header = "Fetch Coding Questions.",
        synopsisHeading = "",
        descriptionHeading = "%n@|bold Description|@:%n",
        optionListHeading = "%n@|bold Options|@:%n",
        description = "Fetches Coding Questions from the r/dailyprogrammer subreddit " +
                "based on the question number and difficulty level entered.")
public class DailyProg implements Runnable {

    private enum Difficulty
    {
        easy, intermediate, hard
    }

    @CommandLine.Option(names = {"-q", "--question"}, required = true)
    private int questionNumber;

    @CommandLine.Option(names = {"-d", "--difficulty"}, required = true)
    private Difficulty difficultyLevel;

    @Override
    public void run() {
        String prefixKeywords = "q=title:Challenge%20%23";
        String searchText = prefixKeywords + questionNumber + "%20" +
                difficultyLevel;
        final HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        try {
            String basicUrl = "https://www.reddit.com/r/dailyprogrammer/search.json?";
            String url = basicUrl + searchText + "&restrict_sr=1";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject myObject = new JSONObject(response.body());
            JSONObject data = myObject.optJSONObject("data");
            JSONArray children = data.optJSONArray("children");
            if(children != null && !children.isEmpty()) {
                JSONObject childData = children.optJSONObject(0).optJSONObject("data");
                if(isCorrectQuestion(childData.get("title").toString(), questionNumber, difficultyLevel.toString())) {
                    System.out.print("-------- TITLE: " + childData.get("title") + " --------");
                    System.out.println(StringEscapeUtils.unescapeHtml4(StringEscapeUtils.unescapeHtml4(String.valueOf(childData.get("selftext")))));
                } else {
                    System.out.println("Question: '" + questionNumber + "', with Difficulty: '" + difficultyLevel +
                            "' not present.");
                }
            } else {
                System.out.println("Question: '" + questionNumber + "', with Difficulty: '" + difficultyLevel +
                        "' not present.");
            }
        } catch (ConnectException e) {
            System.out.println("Please check your Internet Connection.");
        } catch (URISyntaxException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isCorrectQuestion(String title, int questionNumber, String difficultyLevel) {
        String question = String.valueOf(questionNumber);
        boolean qFound = false, dFound = false;
        for(int i=0; i<title.length(); i++) {
            if(!qFound && title.charAt(i) == '#' && i +question.length() < title.length()) {
                String titleQ = title.substring(i+1, i +question.length() +1);
                if(question.equals(titleQ)) {
                    qFound = true;
                }
            } else if(!dFound && title.charAt(i) == '[' && i +difficultyLevel.length() < title.length()) {
                String titleD = title.substring(i+1, i +difficultyLevel.length() +1);
                if(difficultyLevel.equals(titleD.toLowerCase())) {
                    dFound = true;
                }
            }
        }
        return qFound && dFound;
    }
}

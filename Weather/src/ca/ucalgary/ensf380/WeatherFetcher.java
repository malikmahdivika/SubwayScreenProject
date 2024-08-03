package ca.ucalgary.ensf380;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WeatherFetcher {

    // Declaring And Initializing The Base URL Used For The Weather Data:
    private static final String BASE_URL = "http://wttr.in/";

    // Public Function/Method Used To Gain Access To The Website API Information Through URL Connection:
    public static String fetchWeather(String cityName, String countryCode) throws Exception {

        // If-Statement To Check For And Implement Default Value For Country Code If That Argument Is Null Or Empty (Edge-Case To Make Sure The URL Works If User Leaves The "countryCode" Empty):
        if (countryCode == null || countryCode.isEmpty()) {
            countryCode = ""; // If The Country Code Is Null Or If There Is No Country Code Then Keep Position Empty.
        } else {
            countryCode = "/" + URLEncoder.encode(countryCode, StandardCharsets.UTF_8.toString()); // If There Is A Country Code Then Add A Forward Slash To Continue Path Of URL Then Add The Country Code To The Right Of The Forward Slash.
        }

        String completeUrl = BASE_URL + URLEncoder.encode(cityName, StandardCharsets.UTF_8.toString()) + countryCode + "?format=%C+%t+%w+%p";

        URL urlObject = new URL(completeUrl);
        HttpURLConnection urlConnection = (HttpURLConnection) urlObject.openConnection(); // Opening Connection To Allow For The Flow Of An API Request.
        urlConnection.setRequestMethod("GET"); // Setting Up API Request "GET" To Gain Access To Data Represented By The URL.

        int responseCode = urlConnection.getResponseCode(); // Getting The Response API Code From The Initial Request "GET" For URL Connection To Website API And Storing It In "responseCode" Local Variable.

        // Handeling API Response Code To Access The Data Contents And Allow For Content Reading Using InputStreamReader and BufferedReading Classes Within The Java "io" Package:
        if (responseCode == 200) {

            // Using try-with-resources to ensure proper closing of BufferedReader
            try (BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {

                String inputLine;
                StringBuilder content = new StringBuilder(); // The Accessed Content Represented By The URL Is Built Into A Java String Object.

                // While Loop Used To Monitor The Access To The Data Represented By The URL And Add Data Values To The "inputLine" Through Content String Appending:
                while ((inputLine = in.readLine()) != null) { // Read Until The End Of The Line Or Page, When Null Has Been Reached That Means Data And Page Has Ended.
                    content.append(inputLine);
                }

                return parseWeatherData(content.toString());

            } finally {
                urlConnection.disconnect(); // Disconnect The URL Connection To The Collection Of Data For Weather And Current Time.
            }

        } else {
            throw new Exception("Error! Cannot Fetch Weather Data: " + responseCode); // If The Weather Data Cannot Be Fetched Throw And Exception With API Response As The Error Code.
        }
    }

    // This Is The Method To Parse The Weather Data From The Weather Data Represented By The URL Connection In The HTML Page Using Regex and Matcher:
    private static String parseWeatherData(String html) {
        String regex = "(\\S+\\s+\\S+)\\s+(\\S+)\\s+(\\S+\\s+\\S+)\\s+(\\S+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(html);

        if (matcher.find()) {
            String condition = matcher.group(1);
            String temperature = matcher.group(2);
            String wind = matcher.group(3);
            String precipitation = matcher.group(4);

            return String.format("Condition: %s, Temperature: %s, Wind: %s, Precipitation: %s", condition, temperature, wind, precipitation);
        } else {
            return "Weather data not found.";
        }
    }

    // Public Function/Method Used To Fetch The Date And Time For A Given City Code:
    public static String fetchDateTime(String cityCode) throws Exception {
        String urlString = "http://worldtimeapi.org/api/timezone/" + URLEncoder.encode(cityCode, StandardCharsets.UTF_8.toString());
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {

            // Using try-with-resources to ensure proper closing of BufferedReader
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                StringBuilder content = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                String dateTime = content.toString();
                return parseDateTime(dateTime);

            } finally {
                connection.disconnect();
            }

        } else {
            throw new Exception("Error fetching date and time: " + responseCode);
        }
    }

    // This Is The Method To Parse The Date And Time Data From The JSON Response Using Regex and Matcher:
    private static String parseDateTime(String json) {
        String regex = "\"datetime\":\"(.*?)\"";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(json);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "Date and time not found.";
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java WeatherFetcher <cityName> <countryCode>");
            return;
        }

        String cityName = args[0];
        String countryCode = args[1];

        try {
            String weather = fetchWeather(cityName, countryCode);
            String dateTime = fetchDateTime(cityName + "/" + countryCode);
            System.out.println("Date and Time in " + cityName + ", " + countryCode + ": " + dateTime);
            System.out.println("Weather in " + cityName + ", " + countryCode + ": " + weather);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

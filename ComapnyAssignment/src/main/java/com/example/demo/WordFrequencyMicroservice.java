package com.example.demo;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WordFrequencyMicroservice {
    public static void main(String[] args) {
        String url = "http://example.com"; // Replace with the desired URL

        try {
            String pageContent = getPageContent(url);
            Map<String, Integer> wordFrequency = getWordFrequency(pageContent);

            String jsonOutput = convertToJson(wordFrequency);
            System.out.println(jsonOutput);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static String getPageContent(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        return document.text();
    }

    private static Map<String, Integer> getWordFrequency(String content) {
        Map<String, Integer> wordFrequency = new HashMap<>();

        String[] words = content.split("\\s+"); // Split content into individual words

        for (String word : words) {
            word = word.toLowerCase();
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
        }

        return wordFrequency;
    }

    private static String convertToJson(Map<String, Integer> wordFrequency) {
        Gson gson = new Gson();
        return gson.toJson(wordFrequency);
    }
    
}

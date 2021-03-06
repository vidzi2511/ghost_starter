package com.google.engedu.ghost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class SimpleDictionary implements GhostDictionary {
    private ArrayList<String> words;

    public SimpleDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        words = new ArrayList<>();
        String line = null;
        while ((line = in.readLine()) != null) {
            String word = line.trim();
            if (word.length() >= MIN_WORD_LENGTH)
                words.add(line.trim());
        }
    }

    @Override
    public boolean isWord(String word) {
        return words.contains(word);
    }

    @Override
    public String getAnyWordStartingWith(String prefix) {

        if (prefix == null) {
            Random random = new Random();
            int n = random.nextInt(words.size());
            return words.get(n);
        } else {
            return binerySearch(prefix, 0, words.size() - 1);
        }
    }

    public String binerySearch(String prefix, int start, int end) {

        int mid = (start + end) / 2;

        if (end > start) {
            String midString = words.get(mid);

            if (midString.equals(prefix)) {
                return null;
            } else if ((midString.length() > prefix.length())
                    && midString.startsWith(prefix)) {
                return midString;
            } else if (midString.compareTo(prefix) < 0) {
                return binerySearch(prefix, mid + 1, end);
            } else if (midString.compareTo(prefix) > 0) {
                return binerySearch(prefix, start, mid - 1);
            }
        } else {
            return null;
        }
        return null;
    }

    @Override
    public String getGoodWordStartingWith(String prefix) {
        return null;
    }
}


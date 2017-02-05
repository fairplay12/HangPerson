package com.hangperson.infrastructure;

import java.util.ArrayList;

/**
 * Created by vova on 05.02.17.
 */
public class HangPersonGame {
    private String word;
    private String guesses;
    private String wrongGuesses;

    public HangPersonGame(String word) {
        this.word = word;
        this.guesses = "";
        this.wrongGuesses = "";
    }

    public boolean guess(String letter) {
        if (letter == null && !letter.matches("[a-zA-Z]"))
            return false;
        letter = letter.toLowerCase();
        if (this.word.contains(letter) && !this.guesses.contains(letter)) {
            this.guesses += letter;
            return true;
        } else if (!this.word.contains(letter) && !this.wrongGuesses.contains(letter)) {
            this.wrongGuesses += letter;
            return true;
        }
        return false;
    }

    public String wordWithGuesses() {
        String result = "";
        for (char letter : this.word.toCharArray()) {
            if (this.guesses.indexOf(letter) >= 0) {
                result += letter;
            } else {
                result += "-";
            }
        }
        return result;
    }

    public GameChecker checkWinOrLose() {
        if (this.word.equals(this.guesses))
            return GameChecker.WIN;
        else if (this.wrongGuesses.length() > 6)
            return GameChecker.LOSE;
        return GameChecker.CONTINUE;
    }



}

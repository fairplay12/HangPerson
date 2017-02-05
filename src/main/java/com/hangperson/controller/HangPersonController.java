package com.hangperson.controller;

import com.hangperson.infrastructure.GameChecker;
import com.hangperson.infrastructure.HangPersonGame;
import org.springframework.web.bind.annotation.*;

/**
 * Created by vova on 05.02.17.
 */
@RestController
public class HangPersonController {
    private HangPersonGame hangPersonGame;

    @RequestMapping(value = {"/new", "/"} , method = RequestMethod.POST)
    public void newGame() throws Exception {
        hangPersonGame = new HangPersonGame(getRandomWord());
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String show() {
        return hangPersonGame.wordWithGuesses();
    }

    @RequestMapping(value = "/guess", method = RequestMethod.POST)
    public String guess(@RequestParam String letter) {
        hangPersonGame.guess(letter);
        GameChecker gameChecker = hangPersonGame.checkWinOrLose();
        return gameChecker.toString();
    }

    private String getRandomWord() throws Exception {

        String url = "http://watchout4snakes.com/wo4snakes/Random/RandomWord";

        return "somestring";
    }


}

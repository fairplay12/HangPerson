package com.hangperson.controller;

import com.hangperson.infrastructure.GameChecker;
import com.hangperson.infrastructure.HangPersonGame;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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

        //String link = "http://watchout4snakes.com/wo4snakes/Random/RandomWord";
        String word;
        String link = "http://www.setgetgo.com/randomword/get.php";
        RestTemplate restTemplate = new RestTemplate();
        word = restTemplate.getForObject(link, String.class);
        return word.toLowerCase();

    }


}

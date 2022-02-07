package com.lotterygame.controller;

import com.lotterygame.entity.Game;
import com.lotterygame.entity.User;
import com.lotterygame.repository.GameRepository;
import com.lotterygame.repository.UserRepository;
import com.lotterygame.service.CheckNum;
import com.lotterygame.service.CompareTips;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;

@Controller
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private CompareTips compareTips;
    private CheckNum checkNum;
    private GameRepository gameRepository;
    private UserRepository userRepository;
    //temporary user_id for games




    @Autowired
    public HomeController(CheckNum checkNum, CompareTips compareTips){
        this.checkNum = checkNum;
        this.compareTips = compareTips;

    }

    @Autowired
    public void setGameRepository(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Autowired
    public void setUserRepository (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @RequestMapping("")
    public String indexController(){
        return "index";
    }

    @RequestMapping("/regisztracio.html")
    public String regController(Model model){
        model.addAttribute("user", new User());
        return "regisztracio";
    }

    @PostMapping("/reg")
    public String regNewUser(@ModelAttribute User user){
        log.info("Új Felhasználó:");
        log.info(user.getUserName());
        log.info(user.getPassword());
        log.info(user.getFirstName());
        log.info(user.getLastName());
        log.info(user.getGender());
        log.info(user.getEmail());
        log.info(user.getPhone());
        log.info(user.getVehicle());
        userRepository.save(user);
        return "index";
    }

    @RequestMapping("/index.html")
    public String indexHtmlController(){
        return "index";
    }


    @RequestMapping("/main.html")
    public String mainController(){
        return "main";
    }

    /**
     *  Előbb el kell küldeni a HTML oldalnak az osztályt, amibe később, visszafelé
     *  bele tudja tenni a submitolt értékeket. Ehhez kell a Model.
     */


    @RequestMapping("/sorsolas.html")
    public String lotteryController(Model model, CompareTips compareTips){
        model.addAttribute("game", new Game());
        return "sorsolas";
    }


    @PostMapping("/sors")
    public String sorsolasAccept(@ModelAttribute Game game, Model model, CheckNum checkNum, CompareTips compareTips){
        log.info("Új Tippek:");
        log.info(String.valueOf(game.getNum1()));
        log.info(String.valueOf(game.getNum2()));
        log.info(String.valueOf(game.getNum3()));
        log.info(String.valueOf(game.getNum4()));
        log.info(String.valueOf(game.getNum5()));

        gameRepository.save(game);
        String msg = checkNum.numbersAreDifferent(game);
        if (msg.equals("OK")) {
            model.addAttribute("gameResult", compareTips.checkNums(game));
            log.info("Játék eredmény:");
            return "sorsolas";
        }else {
            return userInfoController(model, checkNum, game);
        }
    }


    @RequestMapping("/huzasok.html")
    public String resultsController(){


        return "huzasok";
    }


    @RequestMapping("/hibasszam.html")
    public String userInfoController(Model model, CheckNum checkNum, Game game){
        model.addAttribute("errorMessage", checkNum.numbersAreDifferent(game));
        log.info("Hibasszam:");
        log.info(checkNum.numbersAreDifferent(game));

        return "hibasszam";
    }

}



package liftup.user.controller;

import liftup.user.repository.StatsRepository;
import liftup.user.repository.UserRepository;
import liftup.user.model.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class StatsController {
    @Autowired
    StatsRepository statsRepo;

    @Autowired
    UserRepository userRepository;

    //View all created stats
    @GetMapping("/allstats")
    List<Stats> getCreatedStats() {
        return statsRepo.findAll();
    }

    @PostMapping("/addastat")
    Stats addAStat(@RequestBody Stats stats) {
        return statsRepo.save(stats);
    }

    //View a users on stats
    @GetMapping("/{username}/onstats")
    List<Stats> getUsersOnStats(@PathVariable String username) {
        return statsRepo.findAllByUsernameAndIsOn(username, true);
    }

    @GetMapping("/{username}/{statId}/viewstat")
    String viewSpecificStat(@PathVariable String username, @PathVariable int statId) {
        return statsRepo.findByStatId(statId).getStatName() + " for " + username + ": "
                + statsRepo.findByStatId(statId).getValue() + ".";
    }

    //Edit a stat
    @PutMapping("/{username}/{statId}/editvalue")
    Stats editStatValue(@PathVariable String username, @PathVariable int statId, @RequestBody String newValue) {
        statsRepo.editValue(newValue, statId, username);
        return statsRepo.findByStatId(statId);
    }

    //Turn on or off
    @PutMapping("/{username}/{statId}/turnonoff")
    Stats turnOnOff(@PathVariable String username, @PathVariable int statId) {
        statsRepo.turnOnOff(!statsRepo.findByStatId(statId).getIsOn(), statId, username);
        return statsRepo.findByStatId(statId);
    }

    //Delete stat
    @DeleteMapping("/{username}/{statId}/removestat")
    Stats removeStat(@PathVariable String username, @PathVariable int statId) {
        Stats stat = statsRepo.findByStatId(statId);
        statsRepo.deleteStat(statId, username);
        return stat;
    }
}

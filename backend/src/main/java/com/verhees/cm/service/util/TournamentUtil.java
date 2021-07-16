package com.verhees.cm.service.util;

import com.verhees.cm.model.exceptions.DrawException;
import com.verhees.cm.model.exceptions.NoScoreException;
import com.verhees.cm.model.game.Game;
import com.verhees.cm.model.prediction.GamePrediction;
import com.verhees.cm.model.team.Team;
import com.verhees.cm.model.user.User;
import com.verhees.cm.model.user.UserCredentials;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TournamentUtil {
    public static Map<String, Long> getPredictorsByValue(List<GamePrediction> predictions) {
        return predictions.stream()
                .filter(gamePrediction -> gamePrediction.getTeam()
                        .equals(calculateWinner(gamePrediction.getGame())))
                .map(GamePrediction::getUser)
                .map(User::getUserCredentials)
                .map(UserCredentials::getUsername)
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
    }

    public static Team calculateWinner(Game game){
        try{
            return game.calculateWinner();
        } catch (NoScoreException | DrawException e){
            return null;
        }
    }
    public static <K, V> Set<K> getKeys(Map<K, V> map, V value) {
        return map.entrySet().stream()
                .filter(entry -> value.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }
}

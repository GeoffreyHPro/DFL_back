package com.example.demo.service;

import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import org.springframework.stereotype.Service;

import com.example.demo.model.LevelPlayerEnum;
import com.example.demo.model.Player;
import com.example.demo.model.User;
import com.github.javafaker.Faker;

@Service
public class RandomService {
    private final Faker faker = new Faker();

    public String generateLastName() {
        return faker.name().lastName();
    }

    public String generateFirstName() {
        return faker.name().firstName();
    }

    public String generateRandomCountry() {
        Locale locale = new Locale("", faker.address().countryCode());
        return locale.getCountry();
    }

    public int generateNumber(int maxValue) {
        Random random = new Random();
        return random.nextInt(maxValue) + 1;
    }

    public int[] generatePlayerStats(int statsCount, int maxValue) {
        int[] stats = new int[statsCount];

        for (int i = 0; i < statsCount; i++) {
            stats[i] = generateNumber(maxValue);
        }

        return stats;
    }

    public Player generatePlayer(LevelPlayerEnum levelPlayer, int statsCount, User user) {
        int[] stats = new int[statsCount];

        if (levelPlayer.equals(LevelPlayerEnum.BRONZE)) {
            stats = generatePlayerStats(statsCount, 10);
        } else if (levelPlayer.equals(LevelPlayerEnum.SILVER)) {
            stats = generatePlayerStats(statsCount, 12);
        } else if (levelPlayer.equals(LevelPlayerEnum.OR)) {
            stats = generatePlayerStats(statsCount, 15);
        } else {
            stats = generatePlayerStats(statsCount, 18);
        }

        int sum = Arrays.stream(stats).sum();

        boolean isBronzeRight = levelPlayer.equals(LevelPlayerEnum.BRONZE) && sum <= 50;
        boolean isSilverRight = levelPlayer.equals(LevelPlayerEnum.SILVER) && sum <= 80 && sum > 45;
        boolean isOrRight = levelPlayer.equals(LevelPlayerEnum.OR) && sum <= 100 && sum > 75;
        boolean isPlatinumRight = levelPlayer.equals(LevelPlayerEnum.PLATINUM) && sum <= 120 && sum > 95;

        if (isBronzeRight || isSilverRight || isOrRight || isPlatinumRight) {
            Player newPlayer = new Player(levelPlayer,stats[0], stats[1], stats[2], stats[3],
                    stats[4], stats[5], stats[6], stats[7], stats[8],
                    generateFirstName(), generateLastName(), generateRandomCountry());
            newPlayer.setUser(user);
            newPlayer.setOverall(sum);
            return newPlayer;
        }

        return generatePlayer(levelPlayer, statsCount, user);
    }
}
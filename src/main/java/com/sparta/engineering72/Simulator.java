package com.sparta.engineering72;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Simulator {
    static RabbitFluffle rabbitFluffle = new RabbitFluffle();
    static ArrayList<FemaleRabbit> femaleRabbits = RabbitFluffle.getFemaleRabbitList();
    static ArrayList<MaleRabbit> maleRabbits = RabbitFluffle.getMaleRabbitList();
    static ArrayList<Animal> rabbitsToAdd = new ArrayList<>();
    static ArrayList<Animal> rabbitsToRemove = new ArrayList<>();
    static boolean oneMaleAndMature = false;

    public static void runSimulation(int time) {
        rabbitFluffle.addMaleRabbit(new MaleRabbit());
        rabbitFluffle.addFemaleRabbit(new FemaleRabbit());

        for (int i = 0; i < time; i++) {
            for (Rabbit rabbit: maleRabbits) {
                if (rabbit.isReadyToDie()){
                    rabbitsToRemove.add(rabbit);
                } else if (rabbit.isMature()) {
                    oneMaleAndMature = true;
                }
                rabbit.incrementAge();
            }

            for (Rabbit rabbit: femaleRabbits) {
                if (rabbit.isReadyToDie()){
                    rabbitsToRemove.add(rabbit);
                }
                if (((FemaleRabbit) rabbit).isPregnant()) {
                    rabbitsToAdd.addAll((((FemaleRabbit) rabbit).breed())); //FIXME
                }
                if (rabbit.isMature() && oneMaleAndMature) {
                    ((FemaleRabbit) rabbit).getPregnant();
                }
                rabbit.incrementAge();
            }
//            rabbitFluffle.addRabbits(rabbitsToAdd); //FIXME: !!!!
//            rabbitFluffle.removeRabbits(rabbitsToRemove);

            femaleRabbits = RabbitFluffle.getFemaleRabbitList();
            maleRabbits = RabbitFluffle.getMaleRabbitList();
        }

        Printer.printFinalPopulation(rabbitFluffle.getRabbitPopulationSize());
//        Printer.printDeathCount();
        Printer.printMalePopulation(rabbitFluffle.getMaleRabbitPopulation());
        Printer.printFemalePopulation(rabbitFluffle.getFemaleRabbitPopulation());
        Printer.printSimulationTime(time);
    }
}

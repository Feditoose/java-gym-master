package ru.yandex.practicum.gym;

public class CounterOfTrainings implements Comparable<CounterOfTrainings> {
    private Coach coach;
    private Integer numberOfTrainins;

    public CounterOfTrainings(String surname, String name, String middleName, Integer numberOfTrainins) {
        this.coach = new Coach(surname, name, middleName);
        this.numberOfTrainins = numberOfTrainins;
    }

    public Coach getCoach() {
        return coach;
    }

    public Integer getNumberOfTrainins() {
        return numberOfTrainins;
    }

    @Override
    public int compareTo(CounterOfTrainings other) {
        return Integer.compare(other.numberOfTrainins, this.numberOfTrainins);
    }
}
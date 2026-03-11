package ru.yandex.practicum.gym;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Timetable timetable = new Timetable();

        Coach shmotkov = new Coach("Шмотков", "В.", "В.");
        Coach semenov = new Coach("Семёнов", "В.", "К.");
        Coach tihomirov = new Coach("Тихомиров", "А", "Е");
        Coach belyaev = new Coach("Беляев", "Е.", "В.");
        Coach morev = new Coach("Морев", "Е.", "В.");
        Coach uksusov = new Coach("Уксусов", "Н.", "А.");
        Coach smirnov = new Coach("Смирнов", "Н.", "А.");
        Coach mironov = new Coach("Миронов", "Ю.", "Б.");
        Coach toprova = new Coach("Топрова", "А.", "С.");
        Coach strashnaya = new Coach("Страшная", "А.", "С.");
        Coach veselov = new Coach("Веселов", "С.", "А.");
        Coach volkova = new Coach("Волкова", "Г.", "Ю.");

        Group group1 = new Group("группа 1", Age.ADULT, 60);
        Group group2 = new Group("группа 2", Age.ADULT, 60);
        Group group3 = new Group("группа 3", Age.ADULT, 60);
        Group group4 = new Group("группа 4", Age.ADULT, 60);
        Group group8 = new Group("группа 8", Age.ADULT, 60);
        Group group11 = new Group("группа 11", Age.ADULT, 60);
        Group group13 = new Group("группа 13", Age.ADULT, 60);
        Group group15 = new Group("группа 15", Age.ADULT, 60);
        Group group18 = new Group("группа 18", Age.ADULT, 60);
        Group group88 = new Group("группа 88", Age.ADULT, 60);

        timetable.addNewTrainingSession(new TrainingSession(group1, shmotkov, DayOfWeek.MONDAY, new TimeOfDay(10, 0)));
        timetable.addNewTrainingSession(new TrainingSession(group2, shmotkov, DayOfWeek.MONDAY, new TimeOfDay(13, 0)));
        timetable.addNewTrainingSession(new TrainingSession(group18, semenov, DayOfWeek.MONDAY, new TimeOfDay(14, 0)));

        timetable.addNewTrainingSession(new TrainingSession(group3, semenov, DayOfWeek.TUESDAY, new TimeOfDay(12, 0)));
        timetable.addNewTrainingSession(new TrainingSession(group2, shmotkov, DayOfWeek.TUESDAY, new TimeOfDay(15, 0)));

        timetable.addNewTrainingSession(new TrainingSession(group1, tihomirov, DayOfWeek.WEDNESDAY, new TimeOfDay(10, 0)));
        timetable.addNewTrainingSession(new TrainingSession(group2, belyaev, DayOfWeek.WEDNESDAY, new TimeOfDay(12, 0)));
        timetable.addNewTrainingSession(new TrainingSession(group4, morev, DayOfWeek.WEDNESDAY, new TimeOfDay(14, 0)));

        timetable.addNewTrainingSession(new TrainingSession(group15, uksusov, DayOfWeek.THURSDAY, new TimeOfDay(13, 0)));
        timetable.addNewTrainingSession(new TrainingSession(group15, smirnov, DayOfWeek.THURSDAY, new TimeOfDay(15, 0)));

        timetable.addNewTrainingSession(new TrainingSession(group11, mironov, DayOfWeek.FRIDAY, new TimeOfDay(10, 0)));
        timetable.addNewTrainingSession(new TrainingSession(group88, toprova, DayOfWeek.FRIDAY, new TimeOfDay(12, 0)));
        timetable.addNewTrainingSession(new TrainingSession(group8, strashnaya, DayOfWeek.FRIDAY, new TimeOfDay(14, 0)));

        timetable.addNewTrainingSession(new TrainingSession(group13, veselov, DayOfWeek.SATURDAY, new TimeOfDay(13, 0)));
        timetable.addNewTrainingSession(new TrainingSession(group4, volkova, DayOfWeek.SATURDAY, new TimeOfDay(15, 0)));

        List<CounterOfTrainings> coachesTrainings = timetable.getCountByCoaches();

        for (CounterOfTrainings counter : coachesTrainings) {
            System.out.println(counter.getCoach().toString() + " - " + counter.getNumberOfTrainins());
        }
    }
}
//если что-то не так, можете, пожалуйста подсказать, какое конкретно задание надо выполнить
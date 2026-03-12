package ru.yandex.practicum.gym;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TimetableTest {

    @Test
    void testGetTrainingSessionsForDaySingleSession() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(singleTrainingSession);

        //Проверить, что за понедельник вернулось одно занятие
        assertEquals(1, timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY).size());
        //Проверить, что за вторник не вернулось занятий
        assertEquals(0, timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY).size());
    }

    @Test
    void testGetTrainingSessionsForDayMultipleSessions() {
        Timetable timetable = new Timetable();

        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");

        Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);
        TrainingSession thursdayAdultTrainingSession = new TrainingSession(groupAdult, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(20, 0));

        timetable.addNewTrainingSession(thursdayAdultTrainingSession);

        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);
        TrainingSession mondayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(13, 0));
        TrainingSession saturdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.SATURDAY, new TimeOfDay(10, 0));

        timetable.addNewTrainingSession(mondayChildTrainingSession);
        timetable.addNewTrainingSession(thursdayChildTrainingSession);
        timetable.addNewTrainingSession(saturdayChildTrainingSession);

        // Проверить, что за понедельник вернулось одно занятие
        assertEquals(1, timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY).size());
        // Проверить, что за четверг вернулось два занятия в правильном порядке: сначала в 13:00, потом в 20:00
        List<TrainingSession> thursdaySessions = timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY);
        assertEquals(13, thursdaySessions.get(0).getTimeOfDay().getHours());
        assertEquals(0, thursdaySessions.get(0).getTimeOfDay().getMinutes());

        assertEquals(20, thursdaySessions.get(1).getTimeOfDay().getHours());
        assertEquals(0, thursdaySessions.get(1).getTimeOfDay().getMinutes());
        // Проверить, что за вторник не вернулось занятий
        assertEquals(0, timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY).size());
    }

    @Test
    void testGetTrainingSessionsForDayAndTime() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(singleTrainingSession);

        //Проверить, что за понедельник в 13:00 вернулось одно занятие
        assertEquals(1, timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY,
                new TimeOfDay(13, 0)).size());
        //Проверить, что за понедельник в 14:00 не вернулось занятий
        assertTrue(timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, new TimeOfDay(14, 0)).isEmpty());
    }

    @Test
    void testMultipleSessionsAtSameTime() {
        Timetable timetable = new Timetable();

        Coach coach1 = new Coach("Иванов", "Иван", "Иванович");
        Coach coach2 = new Coach("Петров", "Петр", "Петрович");
        Group group = new Group("Йога", Age.ADULT, 60);

        TrainingSession session1 = new TrainingSession(group, coach1,
                DayOfWeek.MONDAY, new TimeOfDay(18, 0));
        TrainingSession session2 = new TrainingSession(group, coach2,
                DayOfWeek.MONDAY, new TimeOfDay(18, 0));

        timetable.addNewTrainingSession(session1);
        timetable.addNewTrainingSession(session2);

        List<TrainingSession> monday18Sessions = timetable.getTrainingSessionsForDayAndTime(
                DayOfWeek.MONDAY, new TimeOfDay(18, 0));
        assertEquals(2, monday18Sessions.size(), "В 18:00 должно быть 2 тренировки");

        assertEquals(session1, monday18Sessions.get(0));
        assertEquals(session2, monday18Sessions.get(1));

        List<TrainingSession> mondaySessions = timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY);
        assertEquals(2, mondaySessions.size(), "В понедельник должно быть 2 тренировки");
    }

    @Test
    void testEmptyTimetable() {
        Timetable timetable = new Timetable();

        List<TrainingSession> mondaySessions = timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY);
        assertTrue(mondaySessions.isEmpty());

        List<TrainingSession> monday10Sessions = timetable.getTrainingSessionsForDayAndTime(
                DayOfWeek.MONDAY, new TimeOfDay(10, 0));
        assertTrue(monday10Sessions.isEmpty());
    }

    @Test
    void testSessionsAtDifferentTimes() {
        Timetable timetable = new Timetable();

        Coach coach = new Coach("Сидоров", "Сидор", "Сидорович");
        Group group = new Group("Пилатес", Age.ADULT, 60);

        timetable.addNewTrainingSession(new TrainingSession(group, coach,
                DayOfWeek.WEDNESDAY, new TimeOfDay(18, 0))); // 18:00
        timetable.addNewTrainingSession(new TrainingSession(group, coach,
                DayOfWeek.WEDNESDAY, new TimeOfDay(9, 0)));  // 9:00
        timetable.addNewTrainingSession(new TrainingSession(group, coach,
                DayOfWeek.WEDNESDAY, new TimeOfDay(15, 30))); // 15:30
        timetable.addNewTrainingSession(new TrainingSession(group, coach,
                DayOfWeek.WEDNESDAY, new TimeOfDay(9, 0)));  // ещё одна в 9:00

        List<TrainingSession> wednesdaySessions = timetable.getTrainingSessionsForDay(DayOfWeek.WEDNESDAY);

        assertEquals(4, wednesdaySessions.size());

        assertEquals(9, wednesdaySessions.get(0).getTimeOfDay().getHours());
        assertEquals(0, wednesdaySessions.get(0).getTimeOfDay().getMinutes());

        assertEquals(9, wednesdaySessions.get(1).getTimeOfDay().getHours());
        assertEquals(0, wednesdaySessions.get(1).getTimeOfDay().getMinutes());

        assertEquals(15, wednesdaySessions.get(2).getTimeOfDay().getHours());
        assertEquals(30, wednesdaySessions.get(2).getTimeOfDay().getMinutes());

        assertEquals(18, wednesdaySessions.get(3).getTimeOfDay().getHours());
        assertEquals(0, wednesdaySessions.get(3).getTimeOfDay().getMinutes());
    }

    @Test
    void testGetCountByCoachesWithDifferentCounts() {
        Timetable timetable = new Timetable();

        Coach coach1 = new Coach("Иванов", "Иван", "Иванович");
        Coach coach2 = new Coach("Петров", "Петр", "Петрович");
        Coach coach3 = new Coach("Сидоров", "Сидор", "Сидорович");
        Group group = new Group("Общая группа", Age.ADULT, 60);

        timetable.addNewTrainingSession(new TrainingSession(group, coach1, DayOfWeek.MONDAY, new TimeOfDay(10, 0)));
        timetable.addNewTrainingSession(new TrainingSession(group, coach1, DayOfWeek.WEDNESDAY, new TimeOfDay(10, 0)));
        timetable.addNewTrainingSession(new TrainingSession(group, coach1, DayOfWeek.FRIDAY, new TimeOfDay(10, 0)));

        timetable.addNewTrainingSession(new TrainingSession(group, coach2, DayOfWeek.TUESDAY, new TimeOfDay(15, 0)));
        timetable.addNewTrainingSession(new TrainingSession(group, coach2, DayOfWeek.THURSDAY, new TimeOfDay(15, 0)));

        timetable.addNewTrainingSession(new TrainingSession(group, coach3, DayOfWeek.SATURDAY, new TimeOfDay(12, 0)));

        List<CounterOfTrainings> counts = timetable.getCountByCoaches();

        assertEquals(3, counts.size(), "Должно быть 3 тренера");

        // Проверяем сортировку по убыванию количества тренировок
        assertEquals(3, counts.get(0).getNumberOfTrainins(), "Первый должен быть тренер с 3 тренировками");
        assertEquals(2, counts.get(1).getNumberOfTrainins(), "Второй должен быть тренер с 2 тренировками");
        assertEquals(1, counts.get(2).getNumberOfTrainins(), "Третий должен быть тренер с 1 тренировкой");

        // Проверяем соответствие тренеров
        assertEquals(coach1, counts.get(0).getCoach());
        assertEquals(coach2, counts.get(1).getCoach());
        assertEquals(coach3, counts.get(2).getCoach());
    }

    @Test
    void testGetCountByCoachesWithEqualCounts() {
        Timetable timetable = new Timetable();

        Coach coach1 = new Coach("Иванов", "Иван", "Иванович");
        Coach coach2 = new Coach("Петров", "Петр", "Петрович");
        Group group = new Group("Общая группа", Age.ADULT, 60);

        timetable.addNewTrainingSession(new TrainingSession(group, coach1, DayOfWeek.MONDAY, new TimeOfDay(10, 0)));
        timetable.addNewTrainingSession(new TrainingSession(group, coach1, DayOfWeek.WEDNESDAY, new TimeOfDay(10, 0)));
        timetable.addNewTrainingSession(new TrainingSession(group, coach2, DayOfWeek.TUESDAY, new TimeOfDay(15, 0)));
        timetable.addNewTrainingSession(new TrainingSession(group, coach2, DayOfWeek.THURSDAY, new TimeOfDay(15, 0)));

        List<CounterOfTrainings> counts = timetable.getCountByCoaches();

        assertEquals(2, counts.size(), "Должно быть 2 тренера");

        // При равном количестве порядок может быть любым, проверяем только количество
        Set<Integer> trainingCounts = new HashSet<>();
        trainingCounts.add(counts.get(0).getNumberOfTrainins());
        trainingCounts.add(counts.get(1).getNumberOfTrainins());

        assertEquals(1, trainingCounts.size(), "Оба тренера должны иметь одинаковое количество тренировок");
        assertEquals(2, counts.get(0).getNumberOfTrainins());
        assertEquals(2, counts.get(1).getNumberOfTrainins());
    }

    @Test
    void testGetCountByCoachesWithNoSessions() {
        Timetable timetable = new Timetable();

        List<CounterOfTrainings> counts = timetable.getCountByCoaches();

        assertTrue(counts.isEmpty(), "Для пустого расписания должен вернуться пустой список");
        assertEquals(0, counts.size());
    }

    @Test
    void testGetCountByCoachesWithMultipleSessionsSameTime() {
        Timetable timetable = new Timetable();

        Coach coach1 = new Coach("Иванов", "Иван", "Иванович");
        Coach coach2 = new Coach("Петров", "Петр", "Петрович");
        Group group = new Group("Общая группа", Age.ADULT, 60);

        timetable.addNewTrainingSession(new TrainingSession(group, coach1, DayOfWeek.MONDAY, new TimeOfDay(18, 0)));
        timetable.addNewTrainingSession(new TrainingSession(group, coach2, DayOfWeek.MONDAY, new TimeOfDay(18, 0)));

        timetable.addNewTrainingSession(new TrainingSession(group, coach1, DayOfWeek.WEDNESDAY, new TimeOfDay(18, 0)));

        List<CounterOfTrainings> counts = timetable.getCountByCoaches();

        assertEquals(2, counts.size());

        // Находим записи для каждого тренера
        CounterOfTrainings coach1Count = counts.stream()
                .filter(c -> c.getCoach().equals(coach1))
                .findFirst()
                .orElse(null);
        CounterOfTrainings coach2Count = counts.stream()
                .filter(c -> c.getCoach().equals(coach2))
                .findFirst()
                .orElse(null);

        assertNotNull(coach1Count);
        assertNotNull(coach2Count);

        assertEquals(2, coach1Count.getNumberOfTrainins(), "Тренер 1 должен иметь 2 тренировки");
        assertEquals(1, coach2Count.getNumberOfTrainins(), "Тренер 2 должен иметь 1 тренировку");
    }
}

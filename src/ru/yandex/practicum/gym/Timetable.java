package ru.yandex.practicum.gym;

import java.util.*;


public class Timetable {

    private HashMap<DayOfWeek, TreeMap<TimeOfDay, List<TrainingSession>>> timetable;

    public Timetable() {
        this.timetable = new HashMap<>();

        for (DayOfWeek day : DayOfWeek.values()) {
            timetable.put(day, new TreeMap<>());
        }
    }

    public void addNewTrainingSession(TrainingSession trainingSession) {
        //сохраняем занятие в расписании
        DayOfWeek day = trainingSession.getDayOfWeek();
        TimeOfDay time = trainingSession.getTimeOfDay();

        TreeMap<TimeOfDay, List<TrainingSession>> currDay = timetable.get(day);
        List<TrainingSession> sessionAtTime = currDay.get(time);

        if (sessionAtTime == null) {
            sessionAtTime = new ArrayList<>();
            currDay.put(time, sessionAtTime);
        }

        sessionAtTime.add(trainingSession);
    }

    public List<TrainingSession> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        //как реализовать, тоже непонятно, но сложность должна быть О(1)
        List<TrainingSession> result = new ArrayList<>();
        TreeMap<TimeOfDay, List<TrainingSession>> sessionsForDay = timetable.get(dayOfWeek);

        for (List<TrainingSession> sessions : sessionsForDay.values()) {
            result.addAll(sessions);
        }

        return result;
    }

    public List<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        //как реализовать, тоже непонятно, но сложность должна быть О(1)
        TreeMap<TimeOfDay, List<TrainingSession>> sessionsForDay = timetable.get(dayOfWeek);
        List<TrainingSession> sessionsAtTime = sessionsForDay.get(timeOfDay);

        return sessionsAtTime;
    }

    public List<CounterOfTrainings> getCountByCoaches() {
        HashMap<Coach, Integer> coachesTrainings = new HashMap<>();

        for (TreeMap<TimeOfDay, List<TrainingSession>> daySessions : timetable.values()) {
            for (List<TrainingSession> timeSessions : daySessions.values()) {
                for (TrainingSession session : timeSessions) {
                    Coach ch = session.getCoach();
                    if (!coachesTrainings.containsKey(ch)) {
                        coachesTrainings.put(ch, 1);
                    } else {
                        int count = coachesTrainings.get(ch);
                        count++;
                        coachesTrainings.put(ch, count);
                    }
                }
            }
        }

        List<CounterOfTrainings> countByCoaches = new ArrayList<>();

        for (Map.Entry<Coach, Integer> entry : coachesTrainings.entrySet()) {
            Coach tempCoach = entry.getKey();
            countByCoaches.add(new CounterOfTrainings(tempCoach.getSurname(), tempCoach.getName(), tempCoach.getMiddleName(), entry.getValue()));
        }
        Collections.sort(countByCoaches);

        return countByCoaches;
    }
}

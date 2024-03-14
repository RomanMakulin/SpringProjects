package com.AnnPsychology.AnnPsychology.services.admin;

public interface iAdminAnalyticsService {
    int earnedMoneyForTheYear ();
    int earnedMoneyForTheMonth ();
    int earnedMoneyForTheWeek ();
    int countSessionsForTheYear();
    int countSessionsForTheMonth();
    int countSessionsForTheWeek();
    int countAllDoneSessions();
}

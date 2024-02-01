package com.example.TramsRest.service;

import com.example.TramsRest.models.Tram;

import java.util.List;

public interface TramService {
    List<Tram> getAllTrams();

    Tram createTram(Tram tram);

    Tram getTramById(int id);

    Tram updateTramById(int id, Tram tramDetails);

    void deleteTramById(int id);
}

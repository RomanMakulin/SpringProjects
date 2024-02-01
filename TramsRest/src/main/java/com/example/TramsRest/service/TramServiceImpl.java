package com.example.TramsRest.service;

import com.example.TramsRest.models.Tram;
import com.example.TramsRest.repository.TramRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class TramServiceImpl implements TramService {
    /**
     * Репозиторий для управления БД
     */
    private final TramRepository tramsRepository;

    /**
     * Получение всех маршрутов
     *
     * @return список маршрутов
     */
    @Override
    public List<Tram> getAllTrams() {
        return tramsRepository.findAll();
    }

    /**
     * Создание нового маршрута
     *
     * @param tram ссылка на новый маршрут
     * @return объект нового маршрута в БД
     */
    @Override
    public Tram createTram(Tram tram) {
        return tramsRepository.save(tram);
    }

    /**
     * Получение маршрута по уникальному идентификатору
     *
     * @param id уникальный идентификатор маршрута
     * @return объект маршрута если он найден
     */
    @Override
    public Tram getTramById(int id) {
        return tramsRepository.findById(id).orElseThrow(null);
    }

    /**
     * Обновление текущего маршрута
     *
     * @param id          уникальный идентификатор для поиска маршрута
     * @param tramDetails ссылка на передаваемый маршрут из тела запроса
     * @return обновленный объект маршрута
     */
    @Override
    public Tram updateTramById(int id, Tram tramDetails) {
        Tram tram = getTramById(id);
        tram.setId(id);
        tram.setCode(tramDetails.getCode());
        tram.setRoad(tramDetails.getRoad());
        return tram;
    }

    /**
     * Удаление маршрута по идентификатору
     *
     * @param id уникальный идентификатор
     */
    @Override
    public void deleteTramById(int id) {
        tramsRepository.delete(getTramById(id));
    }
}

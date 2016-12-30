/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.codefire.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.codefire.dao.IRepository;
import ua.com.codefire.models.Places;

/**
 *
 * @author user
 */
public class PlacesService implements IService{
    
    @Autowired
    private IRepository repository;
    
    @Override
    public List<Places> getAllPlaces() {
        return repository.getAll();
    }

    @Override
    public List<Places> getFromTo(int from, int to) {
        return repository.getFromTo(from, to);
    }

    @Override
    public List<Places> getByCity(String city, int from, int to) {
        return repository.getByCity(city, from, to);
    }

    @Override
    public List<Places> getPlacesByCoords(double lng, double lat, int km) {
        return repository.getPlacesByCoords(lng, lat, km);
    }

    @Override
    public List<Places> getPlacesByFish(String fish, int from, int to) {
        return repository.getPlacesByFish(fish, from, to);
    }
}

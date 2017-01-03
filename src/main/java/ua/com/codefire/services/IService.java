/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.codefire.services;

import java.util.List;
import ua.com.codefire.models.Fishes;
import ua.com.codefire.models.Places;

/**
 *
 * @author user
 */
public interface IService {
    List<Places> getAllPlaces();
    List<Places> getFromTo(int from, int to);
    List<Places> getByCity(String city, int from, int to);
    List<Places> getPlacesByCoords(double lng, double lat, int km);
    List<Places> getPlacesByFish(String fish, int from, int to);
    void addPlace(Places place);
    void addFish(Fishes fish);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.codefire.dao;

import java.util.List;
import ua.com.codefire.models.Places;

/**
 *
 * @author user
 */
public interface IRepository {
    
    List<Places> getFromTo(int from, int to);
    List<Places> getAll();
    List<Places> getByCity(String city ,int from, int to);
    List<Places> getPlacesByCoords(double lng, double lat, int km);
    List<Places> getPlacesByFish(String fish, int from, int to);
}

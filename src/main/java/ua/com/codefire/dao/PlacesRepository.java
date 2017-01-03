/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.codefire.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ua.com.codefire.models.Fishes;
import ua.com.codefire.models.Links;
import ua.com.codefire.models.Places;

/**
 *
 * @author user
 */
public class PlacesRepository implements IRepository {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ua.com.codefire_FishPlaces_jar_0.0.1-SNAPSHOTPU");

    private EntityManager getEntityManger() {
        return emf.createEntityManager();
    }

    @Override
    public List<Places> getFromTo(int from, int to) {
        EntityManager em = getEntityManger();
        return em.createNamedQuery("Places.findAll", Places.class).
                setFirstResult(from).setMaxResults(to).getResultList();
    }

    @Override
    public List<Places> getByCity(String city, int from, int to) {
        EntityManager em = getEntityManger();
        List<Places> cities = em.createNamedQuery("Places.findByPlaceName", Places.class).
                setParameter("placeName", "%" + city.trim().toLowerCase() + "%").
                setFirstResult(from).
                setMaxResults(to).
                getResultList();
        if (!cities.isEmpty()) {
            return cities;
        }
        return null;
    }

    @Override
    public List<Places> getPlacesByCoords(double lng, double lat, int km) {
        EntityManager em = getEntityManger();
        double lngDelta = km * 0.01525693;
        double latDelta = km * 0.009;
        return em.createNamedQuery("Places.findByCoord").
                setParameter("param1", String.valueOf(lng - lngDelta)).
                setParameter("param2", String.valueOf(lng + lngDelta)).
                setParameter("param3", String.valueOf(lat - latDelta)).
                setParameter("param4", String.valueOf(lat + latDelta)).
                getResultList();
    }

    @Override
    public List<Places> getPlacesByFish(String fish, int from, int to) {
        String fishes = "";
        EntityManager em = getEntityManger();
        List<Fishes> check = em.createNamedQuery("Fishes.findByFishName", Fishes.class).
                setParameter("fishName", fish.trim().toLowerCase()).getResultList();
        if (!check.isEmpty()) {
            Fishes fishObj = check.get(0);
            List<Links> links = fishObj.getLinksList();
            if (to > links.size()) {
                links = links.subList(from, links.size());
            } else {
                links = links.subList(from, to);
            }
            List<Places> places = new ArrayList<>();
            for (int i = 0; i < links.size(); i++) {
                for (int j = 0; j < links.get(i).getIdplaces().getLinksList().size(); j++) {
                    fishes = fishes + links.get(i).getIdplaces().getLinksList().get(j).getIdfishes().getFishName() + " ";
                }
                links.get(i).getIdplaces().setPlaceDescript(fishes);
                places.add(links.get(i).getIdplaces());
                fishes = "";
            }
            return places;
        } else {
            return null;
        }
    }

    @Override
    public List<Places> getAll() {
        EntityManager em = getEntityManger();
        return em.createNamedQuery("Places.findAll", Places.class).getResultList();
    }

    @Override
    public void addPlace(Places place) {
        EntityManager em = getEntityManger();
        em.getTransaction().begin();
        em.persist(place);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void addFish(Fishes fish) {
        EntityManager em = getEntityManger();
        em.getTransaction().begin();
        em.persist(fish);
        em.getTransaction().commit();
        em.close();
    }
}

//links
//List<Places> places = em.createNamedQuery("Places.findAll").getResultList();
//        List<Fishes> fishes = em.createNamedQuery("Fishes.findAll").getResultList();
//        for (int i = 0; i < fishes.size(); i++) {
//            for (int j = 0; j < places.size(); j++) {
//                if (places.get(j).getPlaceDescript().contains(fishes.get(i).getFishName())) {
//                    Links link = new Links(places.get(j), fishes.get(i));
//                    em.getTransaction().begin();
//                    em.persist(link);
//                    em.getTransaction().commit();
//                }
//            }
//        }

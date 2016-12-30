package ua.com.codefire;

import com.fasterxml.jackson.annotation.JsonView;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.CharacterEncodingFilter;
import ua.com.codefire.dao.IRepository;
import ua.com.codefire.dao.PlacesRepository;
import ua.com.codefire.models.Places;
import ua.com.codefire.models.View;
import ua.com.codefire.services.IService;
import ua.com.codefire.services.PlacesService;

@Configuration
@RestController
@SpringBootApplication
public class AppRunner {

    @Bean
    public DataSource getDataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("classpath:schema.sql")
                .addScript("classpath:data.sql")
                .build();
    }

    @Bean
    public IRepository getRepository() {
        return new PlacesRepository();
    }

    @Bean
    public IService getService() {
        return new PlacesService();
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setForceEncoding(true);
        characterEncodingFilter.setEncoding("UTF-8");
        registrationBean.setFilter(characterEncodingFilter);
        return registrationBean;
    }

    @Autowired
    private IService service;

    @GetMapping("/")
    public void index(HttpServletResponse response) throws IOException {
        response.sendRedirect("/v1/places?from=1&to=10");
    }

    @JsonView(View.Summary.class)
    @GetMapping("/v1/places")
    public List<Places> showPlaces(
            @RequestParam("from") Integer from,
            @RequestParam("to") Integer to) {
        return service.getFromTo(from - 1, to);
    }

    @JsonView(View.Summary.class)
    @GetMapping("/v1/places/region")
    public List<Places> showPlacesRegion(
            HttpServletResponse response,
            @RequestParam("city") String city,
            @RequestParam(value = "from", required = false) Integer from,
            @RequestParam(value = "to", required = false) Integer to) throws IOException {
        List<Places> result = service.getByCity(city, from-1, to);
        if (null!=result) return result;
        else return Arrays.asList(new Places("Error"));
    }

    @JsonView(View.Summary.class)
    @GetMapping("/v1/places/fish")
    public List<Places> getByFish(
            HttpServletResponse response,
            @RequestParam("name") String fish,
            @RequestParam(value = "from", required = false) Integer from,
            @RequestParam(value = "to", required = false) Integer to) throws IOException {
        List<Places> result = service.getPlacesByFish(fish, from-1, to);
        if (null!=result) return result;
        else return Arrays.asList(new Places("Error"));
    }

    @JsonView(View.Summary.class)
    @GetMapping("/v1/places/around")
    public List<Places> getByCoord(
            @RequestParam("lng") Double longitude,
            @RequestParam("lat") Double latitude,
            @RequestParam("radius") Integer radius) {
        return service.getPlacesByCoords(longitude, latitude, radius);
    }

    public static void main(String[] args) {

        SpringApplication.run(AppRunner.class, args);

    }
}

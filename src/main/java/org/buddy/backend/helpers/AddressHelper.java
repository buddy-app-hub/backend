package org.buddy.backend.helpers;

import org.buddy.backend.models.Coordinates;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import org.buddy.backend.models.Address;

@Service
public class AddressHelper {
    // Recibe una direccion a la que le agrega las coordenadas, obteniendolas de una api externa
    public Address processCoordinatesFromAddress(Address address){
        String apiKey = System.getenv("GEOCODE_MAPS_API_KEY");
        String addressStr = address.getStreetName() + '+' +
                            address.getStreetNumber() + '+' +
                            address.getCity() + '+' +
                            address.getPostalCode() + '+' +
                            address.getCountry();
        String url = "https://geocode.maps.co/search?q=" + addressStr + "&api_key=" + apiKey + "&format=geojson";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(response.getBody());

            JsonNode coordinatesNode = jsonNode.path("features").get(0).path("geometry").path("coordinates");
            double longitude = coordinatesNode.get(0).asDouble();
            double latitude = coordinatesNode.get(1).asDouble();

            Coordinates coordinates = new Coordinates();
            coordinates.setCoordinates(List.of(longitude, latitude));
            address.setCoordinates(coordinates);

            System.out.println("Coordenadas obtenidas: " + coordinates.toString());

            return address;
        } catch (JsonMappingException e) {
            System.out.println("Error obteniendo coordenadas: " + e.getMessage());
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            System.out.println("Error obteniendo coordenadas: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
}

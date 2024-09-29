package org.buddy.backend.repositories;

import java.util.List;

import org.buddy.backend.models.Buddy;
import org.buddy.backend.models.RecommendedBuddy;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BuddyRepository extends MongoRepository<Buddy, String> {
    Buddy findBuddyByFirebaseUID(String firebaseUID);

    /*
     * Pipeline:
     * 1: Busco los buddies que esten en el rango de preferencia del elder, generando un campo distanceBetween con la distancia entre ambos
     * 2: De los obtenidos, me quedo con los buddies que incluyen al elder en su rango de preferencias (el rango de preferencia del buddy es mayor a la distancia entre ambos)
     * 3: devuelvo una estructura que sea compatible con RecommendedBuddy (el buddy junto a la distancia entre él y el elder)
     */
    @Aggregation(pipeline = {
            "{ '$geoNear': { 'near': { 'type': 'Point', 'coordinates': [?0, ?1] }, 'distanceField': 'distanceBetween', 'maxDistance': ?2, 'spherical': true } } }",
            "{ '$match': { '$expr': { '$gte': [ '$buddyProfile.connectionPreferences.maxDistanceKM', { '$divide': [ '$distanceBetween', 1000 ] } ] } } } }",
            "{ '$project': { 'buddy': '$$ROOT', 'distanceToKM': { '$divide': [ '$distanceBetween', 1000 ] } } }" })
    List<RecommendedBuddy> findBuddiesWithinRange(Double longuitude, Double latitude, int maxDistanceMeters);
}

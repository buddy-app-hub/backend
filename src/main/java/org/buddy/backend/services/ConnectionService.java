package org.buddy.backend.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.buddy.backend.models.BuddyProfile;
import org.buddy.backend.models.Connection;
import org.buddy.backend.models.ElderProfile;
import org.buddy.backend.models.Meeting;
import org.buddy.backend.repositories.ConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnectionService {
    @Autowired
    private ConnectionRepository connectionRepository;
    @Autowired
    private BuddyService buddyService;
    @Autowired
    private ElderService elderService;

    public List<Connection> getAllConnections() {
        return connectionRepository.findAll();
    }

    public Connection getConnectionById(String id) {
        return connectionRepository.findById(id).orElse(null);
    }

    public Connection createConnection(Connection connection) {
        return connectionRepository.save(connection);
    }

    public Connection updateConnection(String id, Connection connection) {
        if (connectionRepository.existsById(id)) {
            connection.setId(id);
            return connectionRepository.save(connection);
        }
        return null;
    }

    public void deleteConnection(String id) {
        connectionRepository.deleteById(id);
    }

    public List<Connection> getConnectionsByBuddyID(String buddyID) {
        return connectionRepository.findConnectionsByBuddyID(buddyID);
    }

    public List<Connection> getConnectionsByElderID(String connectionId) {
        return connectionRepository.findConnectionsByElderID(connectionId);
    }

    public Meeting createMeeting(String connectionID, Meeting newMeeting) {
        Optional<Connection> connectionOptional = connectionRepository.findById(connectionID);
        if (connectionOptional.isPresent()) {
            Connection connection = connectionOptional.get();
            Set<String> meetingIds = connection.getMeetings()
                    .stream()
                    .map(Meeting::getMeetingID)
                    .collect(Collectors.toSet());

            String id;
            do {
                id = ObjectId.get().toString();
            } while (meetingIds.contains(id));
            

            newMeeting.setMeetingID(id);
            List<Meeting> connMeetings = connection.getMeetings();
            connMeetings.add(newMeeting);
            connection.setMeetings(connMeetings);
            connectionRepository.save(connection);

            // Si se calificó el encuentro, actualizamos los global ratings promediando los
            // ratings de todos los encuentros, incluyendo al recien calificado
            if (newMeeting.getElderReviewForBuddy() != null) {
                System.out.println("Recalculating buddy global rating...");
                recalculateBuddyGlobalRating(connection.getBuddyID());
            }
            if (newMeeting.getBuddyReviewForElder() != null) {
                System.out.println("Recalculating elder global rating...");
                recalculateElderGlobalRating(connection.getElderID());
            }

            return newMeeting;
        }
        
        return null;
    }

    public Meeting updateMeeting(String connectionID, String meetingID, Meeting updatedMeeting) {
        boolean recalculateBuddyRating = false;
        boolean recalculateElderRating = false;

        Optional<Connection> connectionOptional = connectionRepository.findById(connectionID);
        if (connectionOptional.isPresent()) {
            Connection connection = connectionOptional.get();
            Optional<Meeting> meetingOptional = connection.getMeetings().stream()
                    .filter(m -> m.getMeetingID().equals(meetingID))
                    .findFirst();
            if (meetingOptional.isPresent()) {
                Meeting mToSave = meetingOptional.get();
                mToSave.setSchedule(updatedMeeting.getSchedule());
                mToSave.setLocation(updatedMeeting.getLocation());
                mToSave.setIsCancelled(updatedMeeting.getIsCancelled());
                mToSave.setIsConfirmedByBuddy(updatedMeeting.getIsConfirmedByBuddy());
                mToSave.setIsConfirmedByElder(updatedMeeting.getIsConfirmedByElder());
                mToSave.setIsRescheduled(updatedMeeting.getIsRescheduled());
                mToSave.setActivity(updatedMeeting.getActivity());
                mToSave.setDateLastModification(updatedMeeting.getDateLastModification());
                mToSave.setIsPaymentPending(updatedMeeting.getIsPaymentPending());

                if (mToSave.getElderReviewForBuddy() == null && updatedMeeting.getElderReviewForBuddy() != null) {
                    mToSave.setElderReviewForBuddy(updatedMeeting.getElderReviewForBuddy());
                    recalculateBuddyRating = true;
                }

                if (mToSave.getBuddyReviewForElder() == null && updatedMeeting.getBuddyReviewForElder() != null) {
                    mToSave.setBuddyReviewForElder(updatedMeeting.getBuddyReviewForElder());
                    recalculateElderRating = true;
                }

                connectionRepository.save(connection);

                // Si se calificó el encuentro, actualizamos los global ratings promediando los
                // ratings de todos los encuentros, incluyendo al recien calificado
                if (recalculateBuddyRating) {
                    System.out.println("Recalculating buddy global rating...");
                    recalculateBuddyGlobalRating(connection.getBuddyID());
                }
                if (recalculateElderRating) {
                    System.out.println("Recalculating elder global rating...");
                    recalculateElderGlobalRating(connection.getElderID());
                }

                return mToSave;
            }
        }
        return null;
    }

    // Calcula el promedio de todos los meetings del buddy y lo guarda en el perfil
    // del mismo
    public void recalculateBuddyGlobalRating(String buddyID) {
        List<Connection> buddyConnections = this.getConnectionsByBuddyID(buddyID);

        Double totalRating = 0.0;
        int count = 0;

        for (Connection conn : buddyConnections) {
            for (Meeting m : conn.getMeetings()) {
                if (m.getElderReviewForBuddy() != null) {
                    totalRating += m.getElderReviewForBuddy().getRating();
                    count++;
                }
            }
        }

        Double averageRating = count > 0 ? totalRating / count : null; // null indica que no tiene calificaciones

        // Guardamos el rating promedio
        if (averageRating != null) {
            BuddyProfile buddyProfile = buddyService.getBuddyById(buddyID).getBuddyProfile();
            buddyProfile.setGlobalRating(averageRating);
            buddyService.updateBuddyProfile(buddyID, buddyProfile);
        }
    }

    // Calcula el promedio de todos los meetings del elder y lo guarda en el perfil
    // del mismo
    public void recalculateElderGlobalRating(String elderID) {
        List<Connection> elderConnections = this.getConnectionsByElderID(elderID);

        Double totalRating = 0.0;
        int count = 0;

        for (Connection conn : elderConnections) {
            for (Meeting m : conn.getMeetings()) {
                if (m.getBuddyReviewForElder() != null) {
                    totalRating += m.getBuddyReviewForElder().getRating();
                    count++;
                }
            }
        }

        Double averageRating = count > 0 ? totalRating / count : null; // null indica que no tiene calificaciones

        // Guardamos el rating promedio
        if (averageRating != null) {
            ElderProfile elderProfile = elderService.getElderById(elderID).getElderProfile();
            elderProfile.setGlobalRating(averageRating);
            elderService.updateElderProfile(elderID, elderProfile);
        }
    }
}

package org.buddy.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.cloud.StorageClient;

@Service
public class FirebaseService {

    public void deleteUserFromStorage(String userId) {
        try {
            Bucket bucket = StorageClient.getInstance().bucket();
            String userPath = "users/" + userId + "/";

            System.out.println("Iniciando eliminación de objetos en el prefijo: " + userPath);

            // Listamos y eliminamos todos los objetos con el prefijo userPath
            // Nota: cuando un directorio no tiene mas contenido, firebase tambien lo elimina
            Iterable<Blob> blobs = bucket.list(Storage.BlobListOption.prefix(userPath)).iterateAll();

            for (Blob blob : blobs) {
                System.out.println("Eliminando objeto: " + blob.getName());
                blob.delete(); // Eliminar cada objeto encontrado
            }

            System.out.println("Eliminación de la carpeta del usuario completa: " + userPath);

        } catch (Exception e) {
            System.out.println("Error eliminando la carpeta del usuario: " + e.getMessage());
        }
    }

    public void deleteUserChats(String userId) {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference chatRooms = db.collection("chatRooms");

        try {
            // Buscar los documentos donde participants contiene el userId y eliminamos todo el contenido
            // Nota: cuando un directorio no tiene mas contenido, firebase tambien lo elimina

            ApiFuture<QuerySnapshot> query = chatRooms.whereArrayContains("participants", userId).get();
            List<QueryDocumentSnapshot> chatDocuments = query.get().getDocuments();

            if (chatDocuments.isEmpty()) {
                System.out.println("No se encontraron chats para el usuario: " + userId);
                return;
            }

            System.out.println("Eliminando chats para el usuario: " + userId);

            // Iteramos sobre los documentos (chats)
            for (QueryDocumentSnapshot chat : chatDocuments) {
                String chatId = chat.getId();
                System.out.println("Procesando chat con ID: " + chatId);

                // Eliminamos la subcoleccion de messages
                CollectionReference messages = chat.getReference().collection("messages");
                ApiFuture<QuerySnapshot> messagesQuery = messages.get();
                List<QueryDocumentSnapshot> messageDocuments = messagesQuery.get().getDocuments();

                for (QueryDocumentSnapshot message : messageDocuments) {
                    System.out.println("Eliminando mensaje con ID: " + message.getId());
                    message.getReference().delete().get();
                }

                // Una vez eliminados los mensajes, eliminar el chat principal
                chat.getReference().delete().get();
                System.out.println("Chat eliminado con ID: " + chatId);
            }

            System.out.println("Eliminación de chats completa para el usuario: " + userId);

        } catch (Exception e) {
            System.out.println("Error eliminando chats para el usuario: " + e.getMessage());
        }
    }

    public void deleteUserFromAuth(String userId) {
        try {
            // Eliminamos al usuario de Firebase Authentication
            FirebaseAuth.getInstance().deleteUser(userId);
            System.out.println("Usuario eliminado de Firebase Authentication con UID: " + userId);
        } catch (FirebaseAuthException e) {
            System.out.println("Error eliminando usuario de Firebase Authentication: " + e.getMessage());
        }
    }
}

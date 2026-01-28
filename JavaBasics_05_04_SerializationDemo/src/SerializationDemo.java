/**
 * Chapter 5.4 tests from Java Basics Course:
 * https://stepik.org/course/187/syllabus
 *
 * @author Fil
 * @version 1.0
 * @apiNote Chapter 5.4 tests
 */import fil.tests.Client;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.logging.*;

public class SerializationDemo {
    public static void main(String[] args) throws Exception {

        int nSaved = 0;
        int nRead = 0;
        Client[] clients = {
                new Client(100,"Andrew Filichkin", LocalDate.of(1959, 11, 18)),
                new Client(200,"Nastya Filichkina", LocalDate.of(1973, 4, 9)),
                new Client(300,"Vasyliy Filichkin", LocalDate.of(2013, 4, 23))
        };

        Path path = Paths.get("c:\\temp\\objects.bin");

        try {
            nSaved = saveClients(path, clients);
        }
        catch (IOException exc) {
            System.out.println(exc);
        }
        System.out.println("Saved: " + nSaved);

        try {
            nRead = readClients(path, clients);
        }
        catch (IOException exc) {
            System.out.println(exc);
        }
        System.out.println("Verifyed: " + nRead);

    }

    public static int saveClients(Path path, Client[] clients) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path));
        int nObjects = 0;

        try {
            oos.writeInt((int) clients.length);
        } catch (IOException exc) {
            throw exc;
        };

        for (Client oneClient : clients) {
            try {
                oos.writeObject(oneClient);
            }
            catch (IOException exc) {
                throw exc;
            }
            nObjects++;
        }
        oos.close();
        return nObjects;
    }

    public static int readClients(Path path, Client[] clients) throws Exception {
        final Logger logger = Logger.getLogger(Class.class.getName());
        ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path));
        Client deserializedClient;
        int nClients = 0;
        int nObjects = 0;

        try {
            nClients = ois.readInt();
        } catch (IOException exc) {
            throw exc;
        }

        for (int i = 0; i < nClients; i++ ) {
            try {
                deserializedClient = (Client) ois.readObject();
            } catch (IOException exc) {
                throw exc;
            }
            nObjects++;
            logger.info(deserializedClient.toString());
        }
        ois.close();
        return nObjects;
    }


}
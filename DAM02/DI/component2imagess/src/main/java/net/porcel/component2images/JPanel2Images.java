package net.porcel.component2images;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobItem;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;

public class JPanel2Images extends JPanel implements Serializable, ActionListener {

    private String connectionString;
    private String containerName;
    private boolean isPolling;
    private int requestInterval;
    private List<BlobItem> oldList = new ArrayList();
    private List<BlobItem> newList = new ArrayList();
    private BlobServiceClient blobServiceClient;
    private BlobContainerClient containerClient;
    Timer jPanel2ImagesTimer;
    ArrayList<MyEventListeners> listeners = new ArrayList<>();

    public JPanel2Images() {
    }

    public void inicialize() {

        this.setBorder(BorderFactory.createBevelBorder(1));

        // Rellenamos la lista de items antiguos.
        try {
            blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString).buildClient();
            containerClient = blobServiceClient.getBlobContainerClient(containerName);

            //System.out.println("\nRevisando contenido actual");
            oldList.clear();

            try {
                for (BlobItem blobItem : containerClient.listBlobs()) {
                    oldList.add(blobItem);
                    //System.out.println("- Añadido: " + blobItem.getName());
                }
            } catch (Exception e) {
                System.out.println("Error al intentar añadir objetos:" + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Conexion fallida al intentar rellenar las listas");
        }
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        //System.out.println(oldList);
        if (oldList.isEmpty()) {
            inicialize();
        }
        //System.out.println("Revisando contenido nuevo");
        try {
            blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString).buildClient();
            containerClient = blobServiceClient.getBlobContainerClient(containerName);

            newList.clear();

            // Llenamos newList con los blobs actuales
            for (BlobItem blobItem : containerClient.listBlobs()) {
                //System.out.println("- Añadido: " + blobItem.getName());
                newList.add(blobItem);
            }

            // Comprobar lista antigua con lista actual
            //System.out.println("\nComparando diferencias:");
            if (!areListsEqual(oldList, newList)) { // areListEquals esta adaptado de chatGPT
                //System.out.println("Cambio detectado");

                List<BlobItem> nuevos = new ArrayList<>();
                List<BlobItem> eliminados = new ArrayList<>();

                // Identificar archivos nuevos
                for (BlobItem blob : newList) {
                    boolean found = false;
                    for (BlobItem oldBlob : oldList) {
                        if (blob.getName().equals(oldBlob.getName())) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        nuevos.add(blob);
                    }
                }

                // Identificar archivos eliminados
                for (BlobItem blob : oldList) {
                    boolean found = false;
                    for (BlobItem newBlob : newList) {
                        if (blob.getName().equals(newBlob.getName())) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        eliminados.add(blob);
                    }
                }

                // Crear el mensaje para enviarlo al JFrame
                String mensaje = "Cambios detectados en el contenedor:\n";

                if (!nuevos.isEmpty()) {
                    mensaje += "\nArchivos nuevos:\n";
                    for (BlobItem blob : nuevos) {
                        mensaje += ("- Nombre: " + blob.getName() + "\n"
                                + "-- MIME: " + blob.getProperties().getContentType() + "\n"
                                + "-- Tamaño: " + blob.getProperties().getContentLength() + "\n"
                                + "-- Fecha de subida: " + blob.getProperties().getLastModified() + "\n");
                    }
                }

                if (!eliminados.isEmpty()) {
                    mensaje += "\nArchivos eliminados:\n";
                    for (BlobItem blob : eliminados) {
                        mensaje += ("- Nombre: " + blob.getName() + "\n"
                                + "-- MIME: " + blob.getProperties().getContentType() + "\n"
                                + "-- Tamaño: " + blob.getProperties().getContentLength() + "\n"
                                + "-- Fecha de subida: " + blob.getProperties().getLastModified() + "\n");
                    }
                }

                // Mostrar mensaje
                if (!nuevos.isEmpty() | !eliminados.isEmpty()) {
                    for (MyEventListeners listener : listeners) {
                        listener.onBlobDifference(mensaje);
                    }
                    // Actualizar oldList
                    oldList = new ArrayList<>(newList);
                }
            } else {
                //System.out.println("No se han detectado cambios");
            }
        } catch (Exception e) {
            System.out.println("Conexion fallida");
            System.out.println(e.getMessage());
        }
    }

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public boolean isIsPolling() {
        return isPolling;
    }

    public void setIsPolling(boolean isPolling) {
        this.isPolling = isPolling;

        if (this.isPolling == true) {
            jPanel2ImagesTimer = new Timer(requestInterval, this);
            //System.out.println("Arrancando el servicio...");
            this.setBackground(Color.green);
            jPanel2ImagesTimer.start();
        } else if (this.isPolling == false) {
            //System.out.println("Parando el servicio...");
            this.setBackground(Color.red);
            jPanel2ImagesTimer.stop();
        }
    }

    public int getRequestInterval() {
        return requestInterval;
    }

    public void setRequestInterval(int requestInterval) {
        this.requestInterval = requestInterval * 1000;
    }

    private boolean areListsEqual(List<BlobItem> list1, List<BlobItem> list2) {
        if (list1.size() != list2.size()) {
            return false; // Si las listas tienen tamaños diferentes, no son iguales
        }

        // Extraer los nombres de los blobs y comparar
        Set<String> names1 = list1.stream().map(BlobItem::getName).collect(Collectors.toSet());
        Set<String> names2 = list2.stream().map(BlobItem::getName).collect(Collectors.toSet());
        return names1.equals(names2);
    }

    public void addListener(MyEventListeners listener) {
        listeners.add(listener);
    }

    public void removeListener(MyEventListeners listener) {
        listeners.remove(listener);
    }
}

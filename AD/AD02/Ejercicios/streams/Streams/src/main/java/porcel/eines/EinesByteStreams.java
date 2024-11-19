package porcel.eines;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class EinesByteStreams {

    public static void escriuByte(String desti, byte[] dades) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(desti)) {
            fos.write(dades);
            fos.flush();
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    public static byte[] tornaBytes(String origen){
        List<Byte> bytes = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(origen)) {
            int c;
            while ((c = fis.read()) != -1) {
                bytes.add((byte)c);
            }

            byte[] bytesArray = new byte[bytes.size()];
            for (int i = 0; i < bytesArray.length; i++) {
                bytesArray[i] = bytes.get(i);
            }

            return bytesArray;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void copiaBytes(String origen, String desti){
        List<Byte> bytes = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(origen)) {
            int c;
            while ((c = fis.read()) != -1) {
                bytes.add((byte) c);
            }

            try(FileOutputStream fos = new FileOutputStream(desti)) {
                for (int i = 0; i < bytes.size(); i++) {
                    fos.write(bytes.get(i));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

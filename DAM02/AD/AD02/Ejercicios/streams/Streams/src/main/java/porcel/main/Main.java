package porcel.main;

import porcel.proves.ProvesStreams;

public class Main {
    public static void main(String[] args) {

        try {
            ProvesStreams.provesByte();
            ProvesStreams.provesCharacter();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
package com.porcel.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class dataAccess {

    public static HttpURLConnection getConnection(URL requestURL, String method) {
        HttpURLConnection con = null;

        try {
            con = (HttpURLConnection) requestURL.openConnection();
            con.setRequestMethod(method);
            con.setRequestProperty("Accept", "application/json");

            int responseCode = con.getResponseCode();
            if (responseCode == 200) {
                return con;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static HttpURLConnection postConnection(URL requestURL, String method, String POST_PARAMS) {
        HttpURLConnection con = null;

        try {
            con = (HttpURLConnection) requestURL.openConnection();
            con.setRequestMethod(method);
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = POST_PARAMS.getBytes("UTF-8");
                os.write(input, 0, input.length);
                os.flush();
            }

            int responseCode = con.getResponseCode();
            if (responseCode == 200 || responseCode == 201) {
                return con;
            } else {
                System.out.println("Error en la conexión POST: Código de respuesta " + responseCode);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static HttpURLConnection putConnection(URL requestURL, String method, String PUT_PARAMS) {
        HttpURLConnection con = null;

        try {
            con = (HttpURLConnection) requestURL.openConnection();
            con.setRequestMethod(method);
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setDoOutput(true);

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = PUT_PARAMS.getBytes("UTF-8");
                os.write(input, 0, input.length);
                os.flush();
            }

            int responseCode = con.getResponseCode();
            if (responseCode == 200 || responseCode == 204) { // 204 No Content es común en PUT
                return con;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static HttpURLConnection delConnection(URL requestURL, String method) {
        HttpURLConnection con = null;

        try {
            con = (HttpURLConnection) requestURL.openConnection();
            con.setRequestMethod(method);
            con.setRequestProperty("Accept", "application/json");

            int responseCode = con.getResponseCode();
            if (responseCode == 200) {
                return con;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static void getObras() throws IOException {
        String readLine = null;
        URL requestURL = new URL("http://localhost:8080/PSP04_Porcel_Cifre_Macia_war_exploded/api/obras");

        HttpURLConnection con = getConnection(requestURL, "GET");

        if (con != null) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                StringBuffer response = new StringBuffer();
                while ((readLine = br.readLine()) != null) {
                    response.append(readLine + "\r\n");
                }
                System.out.println("JSON String Result " + response);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                con.disconnect();
            }
        } else {
            System.out.println("No hi ha cap obra amb l'ID indicat");
        }
    }

    public static void getObrasById(String id) throws IOException {
        String readLine = null;
        URL requestURL = new URL("http://localhost:8080/PSP04_Porcel_Cifre_Macia_war_exploded/api/obras/id/" + id);

        HttpURLConnection con = getConnection(requestURL, "GET");

        if (con != null) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                StringBuffer response = new StringBuffer();
                while ((readLine = br.readLine()) != null) {
                    response.append(readLine + "\r\n");
                }
                System.out.println("JSON String Result " + response);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                con.disconnect();
            }
        } else {
            System.out.println("No hi ha cap obra amb l'autor indicat");
        }
    }

    public static void getObrasByAutor(String autor) throws IOException {
        String readLine = null;
        URL requestURL = new URL("http://localhost:8080/PSP04_Porcel_Cifre_Macia_war_exploded/api/obras/autor/" + autor);

        HttpURLConnection con = getConnection(requestURL, "GET");

        if (con != null) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                StringBuffer response = new StringBuffer();
                while ((readLine = br.readLine()) != null) {
                    response.append(readLine + "\r\n");
                }
                System.out.println("JSON String Result " + response);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                con.disconnect();
            }
        } else {
            System.out.println("No connection");
        }
    }

    public static void createObra(int id, String titol, String any, String modalidad, String autor) throws IOException {
        final String POST_PARAMS = "{\n" +
                "\"idObra\": " + id + ",\n" +
                "\"titol\": \"" + titol + "\",\n" +
                "\"any\": \"" + any + "\",\n" +
                "\"modalidad\": \"" + modalidad + "\",\n" +
                "\"autor\": \"" + autor + "\"\n" +
                "}";

        URL requestURL = new URL("http://localhost:8080/PSP04_Porcel_Cifre_Macia_war_exploded/api/obras/createObra");
        HttpURLConnection con = postConnection(requestURL, "POST", POST_PARAMS);

        if (con != null) {
            System.out.println("JSON String Result " + con.getResponseCode());
        }
    }

    public static void updateObra(int id, String titol, String any, String modalidad, String autor) throws IOException {
        final String PUT_PARAMS = "{\n" +
                "\"idObra\": " + id + ",\n" +
                "\"titol\": \"" + titol + "\",\n" +
                "\"any\": \"" + any + "\",\n" +
                "\"modalidad\": \"" + modalidad + "\",\n" +
                "\"autor\": \"" + autor + "\"\n" +
                "}";

        URL requestURL = new URL("http://localhost:8080/PSP04_Porcel_Cifre_Macia_war_exploded/api/obras/updateObra");
        HttpURLConnection con = putConnection(requestURL, "PUT", PUT_PARAMS);

        if (con != null) {
            System.out.println("JSON String Result " + con.getResponseCode());
        }
    }

    public static void deleteObra(String id) throws IOException {
        URL requestURL = new URL("http://localhost:8080/PSP04_Porcel_Cifre_Macia_war_exploded/api/obras/deleteObra/" + id);
        HttpURLConnection con = delConnection(requestURL, "DELETE");

        if (con != null) {
            System.out.println("JSON String Result " + con.getResponseCode());
        }
    }
}

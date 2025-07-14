package com.porcel.ut04_examen_servidorapi;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
public class HelloApplication extends Application {

}

// Si el puerto 8080 esta ocupado..
// netstat -aon | findstr :8080
//  TCP    0.0.0.0:8080           0.0.0.0:0              LISTENING       5808

//tasklist | findstr 5808
//httpd.exe                     5808 Services

//taskkill /PID 5808 /F
//
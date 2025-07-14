# Macia Porcel Cifre
# workout2success

![login_screen](https://github.com/user-attachments/assets/0eea9eec-ea62-43bf-b2bc-b422d4854219)
![jPanelHomeScreen](https://github.com/user-attachments/assets/3323dcf4-680b-48e9-8307-f08e8ae3969a)
![jPanelUsers](https://github.com/user-attachments/assets/d27ce10d-a5dd-4df2-b5b2-658bad65ed4d)
![jDialogAddWorkout](https://github.com/user-attachments/assets/cbdf2430-194f-43ad-a29e-77c09517370c)

![Diagrama02 11](https://github.com/user-attachments/assets/1b2d51e4-0e58-4aba-8660-101bdf40a3ac)
![Esquema de conexiones entre paneles](https://github.com/user-attachments/assets/7e54bb3b-813c-4a99-977d-4d90b926ce17)

# Sources:
# Tutor Miguel Oscar Garcia Jódar (paucasesnovescifp)
# https://fpadistancia.caib.es/
# Youtube - desarrollo de interfaces -  https://www.youtube.com/watch?v=3msU8OXyBB8&list=PLIfP1vJ2qakli4Z_-yVZV-rq_hQeHQzUb&index=3&ab_channel=DesarrollodeInterfaces
# Logo hecho en: https://looka.com/
# Sistema DAO adaptado de Youtube - Java Code Junkie.

# Problemas encontrados: 

# El controlador no pudo establecer una conexión segura con SQL Server con el cifrado de Capa de sockets seguros (SSL). Error: "PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target". ClientConnectionId:8a039a35-fb52-4682-8bc1-d4dadbb0655a
# Solución: Parece que se ha solucionado reiniciando SQL Server.

# No se pudo realizar la conexión TCP/IP al host localhost, puerto 1433. Error: "Connection refused: no further information. Verifique las propiedades de conexión, compruebe que hay una instancia de SQL Server ejecutándose en el host y aceptando las conexiones TCP/IP en el puerto y compruebe que no hay ningún firewall bloqueando las conexiones TCP en el puerto.".
# Solución: Añadir encrypt=false en la conexión a la BBDD

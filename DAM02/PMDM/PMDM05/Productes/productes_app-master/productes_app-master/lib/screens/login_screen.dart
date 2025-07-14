import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:productes_app/providers/login_form_provider.dart';
import 'package:productes_app/ui/input_decorations.dart';
import 'package:productes_app/widgets/widgets.dart';
import 'package:provider/provider.dart';

class LoginScreen extends StatelessWidget {
  const LoginScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: AuthBackground(
        // Permite hacer scroll si el contenido ocupa más espacio que la pantalla
        child: SingleChildScrollView(
          child: Column(
            children: [
              SizedBox(height: 250), // espacio superior
              CardContainer( // Tarjeta blanca para centrar el contenido del formulario
                child: Column(
                  children: [
                    SizedBox(height: 10),
                    Text('Login',
                        style: Theme.of(context).textTheme.headlineMedium),
                    SizedBox(height: 30),
                    // Creamos el LoginFormProvider (con ChangeNotifier) para manejar el estado del formulario
                    ChangeNotifierProvider(
                      create: (_) => LoginFormProvider(),
                      child: _LoginForm(),
                    ),
                  ],
                ),
              ),
              SizedBox(height: 50),
            ],
          ),
        ),
      ),
    );
  }
}

class _LoginForm extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    // Con esto obtenemos acceso al provider llamado 'LoginFormProvider'.
    // Nos permite usar su información o funciones dentro de esta pantalla.
    // Obtenemos el provider que se creó justo arriba (con ChangeNotifierProvider)
    final loginForm = Provider.of<LoginFormProvider>(context);

    return Container(
      child: Form( // Constructor del Form

        // Esta 'key' es como una etiqueta o identificador único para el formulario.
        // Nos permite acceder al formulario desde fuera (por ejemplo, para validar los campos).
        // Sin esta key, no podríamos controlar el formulario de forma segura desde nuestro código.
        // IMPORTANTE para poder usar loginForm.isValidForm() usado en el "OnPress()"
        key: loginForm.formKey,

        // // Esto activa la validación automática en cuanto el usuario empieza a escribir o cambia el foco
        // Cuadno escribimos, lanza validator() del TextFormField, no usa la key en este caso
        autovalidateMode: AutovalidateMode.onUserInteraction,
        child: Column(
          children: [

            // jTextField para el mailAdress
            TextFormField(
              autocorrect: false,
              keyboardType: TextInputType.emailAddress, // Teclado optimizado para mail
              decoration: InputDecorations.authInputDecoration(
                hintText: 'john.doe@gmail.com',
                labelText: 'Correu electrònic',
                prefixIcon: Icons.alternate_email_outlined,
              ),
              // Guardamos el valor directamente en loginForm.email cada vez que el usuario escribe
              // asignamos valor de la casilla a LoginFormProvider.email
              onChanged: (value) => loginForm.email = value, 
              // Validación del email (se usa en OnPressed() por la key y cada vez que alguien escribe, por autovalidate)
              validator: (value) {
                String pattern =
                    r'^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$';
                RegExp regExp = new RegExp(pattern);
                return regExp.hasMatch(value!) ? null : 'No es de tipus correu';
              },
            ),

            SizedBox(height: 30),

            // jTextField para password
            TextFormField(
              autocorrect: false,
              obscureText: true, // Oculta el texto al escribir
              keyboardType: TextInputType.visiblePassword,
              decoration: InputDecorations.authInputDecoration(
                hintText: '*****',
                labelText: 'Contrasenya',
                prefixIcon: Icons.lock_outline,
              ),
              // Guardamos el valor directamente en loginForm.password cada vez que el usuario escribe
              // asignamos valor de la casilla a LoginFormProvider.password
              onChanged: (value) => loginForm.password = value,
              // Validación del email (se usa en OnPressed() por la key y cada vez que alguien escribe, por autovalidate)
              validator: (value) {
                return (value != null && value.length >= 6)
                    ? null
                    : 'La contrasenya ha de ser de 6 caràcters';
              },
            ),

            SizedBox(height: 30),

            // Columna que va a almacenar los botones de login y registrarse
            Column(
              children: [
                // jButton Login
                MaterialButton(
                  shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(10)),
                  disabledColor: Colors.grey, // Color cuando está deshabilitado
                  elevation: 0,
                  color: Colors.deepPurple,
                  child: Container(
                    padding: EdgeInsets.symmetric(horizontal: 80, vertical: 15),
                    child: Text(
                      loginForm.isLoading ? 'Esperi' : 'Iniciar sessió',
                      style: TextStyle(color: Colors.white),
                    ),
                  ),
                  onPressed: loginForm.isLoading
                      ? null
                      : () async {
                          // Deshabilitam el teclat
                          FocusScope.of(context).unfocus(); // Cierra el teclado

                          // Si el formulario es válido, se ejecuta la lógica de login
                          if (loginForm.isValidForm()) {
                            loginForm.isLoading = true; // Activamos el estado de carga

                            //Simulam una petició
                            await handleSubmit(context, true); // Iniciar sesión

                            await Future.delayed(Duration(seconds: 2));
                            loginForm.isLoading = false;
                            //Navigator.pushReplacementNamed(context, 'home');
                          }
                        },
                ),

                SizedBox(height: 10),

                // jButton Register user
                MaterialButton(
                  shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(10)),
                  disabledColor: Colors.grey,
                  elevation: 0,
                  color: Colors.deepPurple,
                  child: Container(
                    padding: EdgeInsets.symmetric(horizontal: 80, vertical: 15),
                    child: Text(
                      loginForm.isLoading ? 'Esperi' : 'Registrar usuari',
                      style: TextStyle(color: Colors.white),
                    ),
                  ),
                  onPressed: loginForm.isLoading
                      ? null
                      : () async {
                          // Deshabilitam el teclat
                          FocusScope.of(context).unfocus();

                          if (loginForm.isValidForm()) {
                            loginForm.isLoading = true;

                            //Simulam una petició
                            handleSubmit(context, false); // Registrar

                            await Future.delayed(Duration(seconds: 2));
                            loginForm.isLoading = false;
                            //Navigator.pushReplacementNamed(context, 'home');
                          }
                        },
                )
              ],
            ),
          ],
        ),
      ),
    );
  }

  handleSubmit(BuildContext context, bool value) async {
    // creamos instancia de loginFormProviders
    // Con esto obtenemos acceso al provider llamado 'LoginFormProvider'.
    // Nos permite usar su información o funciones dentro de esta pantalla.
    // Obtenemos el provider que se creó justo arriba (con ChangeNotifierProvider)
    final loginForm = Provider.of<LoginFormProvider>((context), listen: false);

    // Obtenemos los valores de los campos loginForm.email y password
    final email = loginForm.email;
    final password = loginForm.password;

    try {
      // Verificar si el usuario va a iniciar sesión o registrarse
      if (value) {
        await loginForm.signInWithEmailAndPassword(email, password);
      } else {
        await loginForm.registerWithEmailAndPassword(email, password);
      }
    } on FirebaseAuthException catch (e) {
      final errorMsg = _getFirebaseErrorMessage(e);
      // barrita que sale debajo de la pantalla mostrando el error del login
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(
          content: Text(errorMsg),
          backgroundColor: Colors.redAccent,
        ),
      );
    }
  }

  String _getFirebaseErrorMessage(FirebaseAuthException e) {
    switch (e.code) {
      case 'user-not-found':
        return 'No existe un usuario con ese email.';
      case 'wrong-password':
        return 'La contraseña es incorrecta.';
      case 'email-already-in-use':
        return 'Este email ya está registrado.';
      case 'weak-password':
        return 'La contraseña es demasiado débil.';
      default:
        return 'Error: ${e.message}';
    }
  }
}

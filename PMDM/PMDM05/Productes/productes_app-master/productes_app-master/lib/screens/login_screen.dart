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
        child: SingleChildScrollView(
          child: Column(
            children: [
              SizedBox(height: 250),
              CardContainer(
                child: Column(
                  children: [
                    SizedBox(height: 10),
                    Text('Login',
                        style: Theme.of(context).textTheme.headlineMedium),
                    SizedBox(height: 30),
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
    final loginForm = Provider.of<LoginFormProvider>(context);
    return Container(
      child: Form(
        key: loginForm.formKey,
        //TODO: Mantenir la referencia a la Key
        autovalidateMode: AutovalidateMode.onUserInteraction,
        child: Column(
          children: [
            // jTextField para el mailAdress
            TextFormField(
              autocorrect: false,
              keyboardType: TextInputType.emailAddress,
              decoration: InputDecorations.authInputDecoration(
                hintText: 'john.doe@gmail.com',
                labelText: 'Correu electrònic',
                prefixIcon: Icons.alternate_email_outlined,
              ),
              onChanged: (value) => loginForm.email =
                  value, // asignamos valor de la casilla a LoginFormProvider.email
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
              obscureText: true,
              keyboardType: TextInputType.visiblePassword,
              decoration: InputDecorations.authInputDecoration(
                hintText: '*****',
                labelText: 'Contrasenya',
                prefixIcon: Icons.lock_outline,
              ),
              onChanged: (value) => loginForm.password =
                  value, // asignamos valor de la casilla a LoginFormProvider.password
              validator: (value) {
                return (value != null && value.length >= 6)
                    ? null
                    : 'La contrasenya ha de ser de 6 caràcters';
              },
            ),

            SizedBox(height: 30),

            Column(
              children: [
                // jButton Login
                MaterialButton(
                  shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(10)),
                  disabledColor: Colors.grey,
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
                          FocusScope.of(context).unfocus();

                          if (loginForm.isValidForm()) {
                            loginForm.isLoading = true;

                            //Simulam una petició
                            await handleSubmit(context, true);

                            await Future.delayed(Duration(seconds: 2));
                            loginForm.isLoading = false;
                            //Navigator.pushReplacementNamed(context, 'home');
                          }
                        },
                ),

                SizedBox(height: 10),

                // jButton Create user
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
                            handleSubmit(context, false);

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
    final loginForm = Provider.of<LoginFormProvider>((context), listen: false);

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

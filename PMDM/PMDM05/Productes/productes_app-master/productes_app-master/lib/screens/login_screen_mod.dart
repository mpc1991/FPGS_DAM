import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:productes_app/providers/login_form_provider.dart';
import 'package:provider/provider.dart';

class AuthScreen extends StatefulWidget {
  //const AuthScreen({super.key});

  @override
  State<AuthScreen> createState() => _AuthScreenState();
}

class _AuthScreenState extends State<AuthScreen> {
  // Check si está logueado o registrado
  bool _isLogin = false;
  bool _loading = false;
  String? _authError;

  // Check para validar la entrada del usuario
  final _formKey = GlobalKey<FormState>();

  // Almacenar los inputs del usuario
  final TextEditingController _emailController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();

  handleSubmit(bool value) async {
    // Validate user inputs using formkey
    if (!_formKey.currentState!.validate()) return;

    setState(() {
      _loading =
          true; // Indicamos que estamos esperando la respuesta de Firebase
    });

    // Obtener inputs del controlador
    final email = _emailController.value.text;
    final password = _passwordController.value.text;

    try {
      // Verificar si el usuario va a iniciar sesión o registrarse
      if (value) {
        await LoginFormProvider().signInWithEmailAndPassword(email, password);
        // Si el login es exitoso, navega a la pantalla de inicio
        // TODO: Aunque el login sea erróneo, la pantalla salta a homeScreen
        // Navigator.pushReplacementNamed(context, 'home');
        // Navigator.pushNamed(context, 'home')
      } else {
        await LoginFormProvider().registerWithEmailAndPassword(email, password);
      }
    } on FirebaseAuthException catch (e) {
      setState(() {
        _loading = false;
        _authError = _getFirebaseErrorMessage(e); // 👇 siguiente paso
      });
    } catch (e) {
      setState(() => _loading = false);
      print(e);
    }
  }

  @override
  Widget build(BuildContext context) {
    var loginFormProvider = Provider.of<LoginFormProvider>(context);

    return Scaffold(
      appBar: AppBar(
        title: Text('Login'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Form(
          // Aññadir key al widget de formulario
          key: _formKey,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              // jtextField para el usuario
              TextFormField(
                // Asignar controlador, vincula al att _emailController.
                controller: _emailController,
                // Validar el input
                validator: (value) {
                  // devuelve texto de error si no es correcto, de lo contrario no devuelve nada
                  if (value == null || value.isEmpty) {
                    return 'Please enter your email';
                  }
                  return null;
                },
                decoration: InputDecoration(
                  hintText: 'Email',
                ),
              ),

              // jTextField para la contraseña
              TextFormField(
                  // Asignar controlador, vincula al att _passwordController
                  controller: _passwordController,
                  obscureText: true,
                  // Función para validar el usuario
                  validator: (value) {
                    if (value == null || value.isEmpty) {
                      return 'Please enter your password';
                    }
                    return null;
                  },
                  decoration: InputDecoration(
                    hintText: 'Password',
                  )),

              SizedBox(
                height: 16.0,
              ),

              // Botones para iniciar sesión o registrarse (en horizontal)
              Row(
                mainAxisAlignment: MainAxisAlignment
                    .spaceEvenly, // Espaciado parejo entre botones
                children: [
                  // Botón para iniciar sesión
                  ElevatedButton(
                    // onPressed: handleSubmit(true), ejecuta en tiempo de construcción
                    onPressed: () => handleSubmit(
                        true), // lamba exp, ejecuta al apretar el botón
                    child: Text('Login'),
                  ),

                  // Botón para registrarse
                  ElevatedButton(
                    onPressed: () => handleSubmit(false),
                    child: Text('Register'),
                  ),
                ],
              ),

              // Menajes de error
              if (_authError != null)
                Padding(
                  padding: const EdgeInsets.only(top: 20.0),
                  child: Text(
                    _authError!,
                    style: TextStyle(
                        color: Colors.red, fontWeight: FontWeight.bold),
                  ),
                ),
            ],
          ),
        ),
      ),
    );
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

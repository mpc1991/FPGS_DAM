import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:productes_app/providers/login_form_provider.dart';
import 'package:provider/provider.dart';

// Pantalla principal de autenticación (login/registro).
// Utiliza StatefulWidget para poder actualizar el estado del widget
// cuando haya cambios, como mostrar un error o activar el loading.

class AuthScreen extends StatefulWidget {
  //const AuthScreen({super.key});

  @override
  State<AuthScreen> createState() => _AuthScreenState();
}

class _AuthScreenState extends State<AuthScreen> {
  
  bool _isLogin = false; // saber si el usuario inicia sesión o se registra
  bool _loading = false; // indica si se está esperando respuesta de Firebase.
  String? _authError; // almacena el mensaje de error que se muestra al usuario.

  // Clave global para identificar y validar el formulario.
  // Se usa para llamar a métodos como validate().
  final _formKey = GlobalKey<FormState>();

  // // Controladores para acceder al texto introducido en los campos de email y contraseña.
  final TextEditingController _emailController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();

  handleSubmit(bool value) async {
    // Validate user inputs using formkey
    // Busca todos los widgets FormField (como TextFormField) que estén dentro del Form que tenga esa key.
    // Si alguno devuelve un mensaje de error (tipo return 'campo vacío'), entonces no es válido y 
    // muestra ese error en pantalla debajo del campo.
    // A cada uno le llama a su función validator, si la tiene definida.
    if (!_formKey.currentState!.validate()) return;

    setState(() {
      _loading = true; // Indicamos que estamos esperando la respuesta de Firebase
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
    // Con esto obtenemos acceso al provider llamado 'LoginFormProvider'.
    // Nos permite usar su información o funciones dentro de esta pantalla.
    // Es como pedirle datos a una clase externa que gestiona la lógica del formulario.
    var loginFormProvider = Provider.of<LoginFormProvider>(context);

    return Scaffold(
      appBar: AppBar(
        title: Text('Login'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),

        // Formulario
        child: Form( // Constructor del Form
          // Esta 'key' es como una etiqueta o identificador único para el formulario.
          // Nos permite acceder al formulario desde fuera (por ejemplo, para validar los campos).
          // Más adelante usamos _formKey.currentState!.validate() para comprobar si todo está bien rellenado.
          // Sin esta key, no podríamos controlar el formulario de forma segura desde nuestro código.
          // Interactura en HandleSubmit para validar todos los TextFormField
          key: _formKey, // Aññadir key al widget de formulario
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              // jtextField para el usuario
              TextFormField(
                // Asignar controlador, vincula al att _emailController.
                controller: _emailController,
                // Validar el input
                validator: (value) { // Usa el validador para asegurarse de que no está vacío.
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
                  validator: (value) { // Condición usada para validar desde el handlesubmit
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
                    onPressed: () => handleSubmit(true), // lamba exp, ejecuta al apretar el botón
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
              // Si hay un error (por ejemplo, usuario no encontrado), se muestra aquí en rojo.
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

  // Devuelve un mensaje personalizado según el tipo de error que Firebase lanza.
  // Ayuda a que el usuario entienda mejor lo que ha fallado.
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

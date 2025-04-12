import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';

class LoginFormProvider extends ChangeNotifier {
  GlobalKey<FormState> formKey = new GlobalKey<FormState>();

  // Creando nueva instancia de firebase auth
  final FirebaseAuth _auth = FirebaseAuth.instance;

  // Crear nuevo usuario
  Future<void> registerWithEmailAndPassword(String email, String password) async {
    try {
      // crear usuario en firebase
      await _auth.createUserWithEmailAndPassword(email: email, password: password); // crear usuario
    } on FirebaseAuthException catch (e) {
      if (e.code == 'weak-password') {
        print ('The password provided is too weak.');
      } else if (e.code == 'email-already-in-use') {
        print('The account already exists for that email.');
      }
      notifyListeners();
    } catch (e) {
      print('registerWithEmailAndPassword $e');
    }
  }

  Future<void> signInWithEmailAndPassword(String email, String password) async {
    // try {
    //   // Iniciar sesión con el usuario existente a la BBDD
       await _auth.signInWithEmailAndPassword(email: email, password: password);
    // } on FirebaseAuthException catch(e) {
    //   if (e.code == 'user-not-found') {
    //     print('No user found for that email.');
    //   } else if (e.code == 'wrong-password') {
    //     print('Wrong password provided for that user');
    //   }
    //   notifyListeners();
    // } catch (e) {
    //   print(e);
    // }
  }

  String email = '';
  String password = '';
  bool _isLoading = false;
  bool get isLoading => _isLoading;

  set isLoading(bool value) {
     _isLoading = value;
     //notifyListeners();
   }

  bool isValidForm() {
    print('Valor del formulari: ${formKey.currentState?.validate()}');
    print('$email - $password');
    return formKey.currentState?.validate() ?? false;
  }
}

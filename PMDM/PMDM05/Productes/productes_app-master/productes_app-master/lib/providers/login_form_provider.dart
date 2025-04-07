import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';

class LoginFormProvider extends ChangeNotifier {
  GlobalKey<FormState> formKey = new GlobalKey<FormState>();
  //final FirebaseAuth _auth = FirebaseAuth.instance;
  String email = '';
  String password = '';
  bool _isLoading = false;
  bool get isLoading => _isLoading;

  Future<void> registerWithEmailAndPassword(String email, String password) async{
    final user = await createUserWithEmailAndPassword(email, password);

  }

  Future<void> createUserWithEmailAndPassword (String email, String password) async {
    //final user = await signInWithEmailAndPassword(email, password);
  }


  set isLoading(bool value) {
     _isLoading = value;
     notifyListeners();
   }

  bool isValidForm() {
    print('Valor del formulari: ${formKey.currentState?.validate()}');
    print('$email - $password');
    return formKey.currentState?.validate() ?? false;
  }
}

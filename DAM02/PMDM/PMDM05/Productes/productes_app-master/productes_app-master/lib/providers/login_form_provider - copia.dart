import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';

class LoginFormProvider extends ChangeNotifier {
  GlobalKey<FormState> formKey = new GlobalKey<FormState>();

  final FirebaseAuth _auth = FirebaseAuth.instance;

  User? user;
  UserCredential? userCredential;

  bool isLogin = false;
  bool isRegister = false;
  bool isLoading = false;
  List<bool> selectedEvent = [false, false];

  bool accesGranted = false;
  String errorMessage = '';

  LoginFormProvider();

  loginOrRegister(String email, String password) async {
    isLoading = true;
    notifyListeners();
    try{
      if (isRegister) {
        userCredential = await _auth.createUserWithEmailAndPassword(email: email, password: password);
      } else if (isLogin) {
        userCredential = await _auth.signInWithEmailAndPassword(email: email, password: password);
      }

      user = userCredential!.user;
    } catch (error) {
      print(getMessageFromErrorCode(error));
      notifyListeners();
    }
  }
  
  void logOut(){
    userCredential = null;
    accesGranted = false;
    isLogin = false;
    isRegister = false;
    errorMessage = '';
    selectedEvent = [false, false];
    user = null;

  }

  void opcioMenu(int index) {
    for (int i = 0; i < selectedEvent.length; i++) {
      selectedEvent[i] = i == index;
    }
    if (index == 0) {
      isLogin = true;
      isRegister = false;
    } else {
      isLogin = false;
      isRegister = true;
    }
    notifyListeners();
  }

  String getMessageFromErrorCode(errorCode) {
    switch (errorCode.code) {
      case "ERROR_EMAIL_ALREADY_IN_USE":
      case "account-exists-with-different-credential":
      case "email-already-in-use":
        return "Email already used. Go to login page.";
      case "ERROR_WRONG_PASSWORD":
      case "wrong-password":
        return "Wrong email/password combination.";
      case "ERROR_USER_NOT_FOUND":
      case "user-not-found":
        return "No user found with this email.";
      case "ERROR_USER_DISABLED":
      case "user-disabled":
        return "User disabled.";
      case "ERROR_TOO_MANY_REQUESTS":
      case "operation-not-allowed":
        return "Too many requests to log into this account.";
      case "ERROR_OPERATION_NOT_ALLOWED":
      case "operation-not-allowed":
        return "Server error, please try again later.";
      case "ERROR_INVALID_EMAIL":
      case "invalid-email":
        return "Email address is invalid.";
      case "INVALID_LOGIN_CREDENTIALS":
        return "Invalid credentials.";
      default:
        return "Login failed. Please try again.";
    }
  }
}

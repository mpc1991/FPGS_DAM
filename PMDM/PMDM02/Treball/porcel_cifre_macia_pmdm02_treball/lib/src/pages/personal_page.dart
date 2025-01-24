import 'package:flutter/material.dart';
import 'package:porcel_cifre_macia_pmdm02_treball/src/dto/persona.dart';
import 'package:intl/intl.dart';

class PersonalPage extends StatefulWidget {
  final Persona persona;
  PersonalPage({super.key, required this.persona});

  @override
  State<PersonalPage> createState() => _PersonalPageState();
}

class _PersonalPageState extends State<PersonalPage> {
  final Persona personaNova = new Persona.biuda(); // Objecte temporal que emmagetzema l'informació

  TextEditingController _inputFieldDataNaixamentController =
      TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text('${widget.persona.getLlinatges()}'), // Titol = llinatge
        ),
        body: ListView( // Llista de métodes que es voran per pantalla
          padding: EdgeInsets.symmetric(horizontal: 10, vertical: 20),
          children: [
            _crearNom(),
            Divider(),
            _crearLlinatges(),
            Divider(),
            _crearDataNaixament(),
            Divider(),
            _crearMail(),
            Divider(),
            _crearContrasenya(),
            Divider(),
            _menuActions()
          ],
        ));
  }

  // Metodes que defineixen cada casella:
  _crearNom() {
    return TextField(
        autofocus: true,
        textCapitalization: TextCapitalization.sentences,
        decoration: InputDecoration(
          hintText: '${widget.persona.getNom()}',
          helperText: 'Nom de l\'usuari',
          icon: Icon(Icons.account_circle_outlined),
          suffixIcon: Icon(Icons.accessibility_new),
          border: OutlineInputBorder(borderRadius: BorderRadius.circular(50)),
        ),
        onChanged: (valor) {
          personaNova.setNom(valor); // Asignam el valor al objecte temporal
        });
  }

  _crearLlinatges() {
    return TextField(
        textCapitalization: TextCapitalization.sentences,
        decoration: InputDecoration(
          helperText: 'Llinatges de l\'usuari',
          hintText: '${widget.persona.getLlinatges()}',
          icon: Icon(Icons.account_circle_outlined),
          suffixIcon: Icon(Icons.accessibility_new),
          border: OutlineInputBorder(borderRadius: BorderRadius.circular(50)),
        ),
        onChanged: (valor) {
          personaNova.setLlinatges(valor); // Asignam el valor al objecte temporal
        });
  }

  _crearDataNaixament() {
    return TextField(
        enableInteractiveSelection: true,
        controller: _inputFieldDataNaixamentController,
        decoration: InputDecoration(
          helperText: 'Data de naixament',
          hintText: '${widget.persona.getDataNaixament().toString()}',
          icon: Icon(Icons.perm_contact_cal_outlined),
          suffixIcon: Icon(Icons.calendar_month_outlined),
          border: OutlineInputBorder(borderRadius: BorderRadius.circular(50)),
        ),
        onTap: () {
          FocusScope.of(context).requestFocus(new FocusNode());
          _seleccionaData(context);
        });
  }

  void _seleccionaData(BuildContext context) async {
    DateTime? picked = await showDatePicker(
        context: context,
        initialDate: DateTime.now(),
        firstDate: DateTime(1900),
        lastDate: DateTime.now());
    if (null != picked) {
      setState(() {
        // Formatetjam perque no es veguin les hores, minuts i segons
        String formattedDate = DateFormat('yyyy-MM-dd').format(picked); 
        personaNova.setDataNaixament(formattedDate); // Asignam el valor al objecte temporal
        _inputFieldDataNaixamentController.text =
            personaNova.getDataNaixament(); // Assignam el valor a la casella.
      });
    }
  }

  _crearMail() {
    return TextField(
        keyboardType: TextInputType.emailAddress, // Keyboard que inclou "@""
        decoration: InputDecoration(
          helperText: 'Mail de l\'usuari',
          hintText: '${widget.persona.getCorreu()}',
          icon: Icon(Icons.email),
          suffixIcon: Icon(Icons.alternate_email),
          border: OutlineInputBorder(borderRadius: BorderRadius.circular(50)),
        ),
        onChanged: (valor) {
          personaNova.setCorreu(valor); // Asignam el valor al objecte temporal
        });
  }

  _crearContrasenya() {
    return TextField(
        obscureText: true, // Per a que no es vegui el que s'escriu
        decoration: InputDecoration(
          helperText: 'Nova contrasenya',
          hintText: 'Contrasenya',
          icon: Icon(Icons.lock),
          suffixIcon: Icon(Icons.lock_open),
          border: OutlineInputBorder(borderRadius: BorderRadius.circular(50)),
        ),
        onChanged: (valor) {
          personaNova.setContrasenya(valor); // Asignam el valor al objecte temporal
        });
  }

  _menuActions() { // Botóns "Desa" i "Cancela"
    return Row(mainAxisAlignment: MainAxisAlignment.end, children: [
      TextButton(
        onPressed: () {
          setState(() {
            // Si no es ha fet cap canvi, no s'asigna el nou valor
            if (personaNova.getNom() != '') {
              widget.persona.setNom(personaNova.getNom());
            }
            if (personaNova.getLlinatges() != '') {
              widget.persona.setLlinatges(personaNova.getLlinatges());
            }
            if (personaNova.getDataNaixament() != '') {
              widget.persona.setDataNaixament(personaNova.getDataNaixament());
            }
            if (personaNova.getCorreu() != '') {
              widget.persona.setCorreu(personaNova.getCorreu());
            }
            if (personaNova.getContrasenya() != '') {
              widget.persona.setContrasenya(personaNova.getContrasenya());
            }
          });
          Navigator.pop(context, widget.persona); // retorn de l'objecte a la pagina principal
        },
        style: TextButton.styleFrom(
          backgroundColor: Colors.blueAccent,
          foregroundColor: Colors.white,
        ),
        child: Row(
          mainAxisSize: MainAxisSize.max,
          children: [
            Text('Desa'),
            SizedBox(
              width: 8,
            ),
            Icon(Icons.check),
          ],
        ),
      ),
      TextButton(
        onPressed: () {
          Navigator.pop(context);
        },
        style: TextButton.styleFrom(
          backgroundColor: Colors.redAccent,
          foregroundColor: Colors.white,
          textStyle: TextStyle(),
        ),
        child: Row(
          mainAxisSize: MainAxisSize.max,
          children: [
            Text('Cancela'),
            SizedBox(
              width: 8,
            ),
            Icon(Icons.cancel_rounded),
          ],
        ),
      ),
    ]);
  }
}

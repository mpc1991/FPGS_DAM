import 'package:flutter/material.dart';

class inputPage extends StatefulWidget {
  const inputPage({super.key});

  @override
  State<inputPage> createState() => _inputPageState();
}

class _inputPageState extends State<inputPage> {
  String _nom = '';
  String _email = '';
  String _data = '';
  String _pais = 'Espanya';
  TextEditingController _inputFieldDataController = TextEditingController();
  List<String> _paisos = [
    'Andorra',
    'Anglaterra',
    'Dinamarca',
    'Espanya',
    'França'
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Input Page'),
      ),
      body: ListView(
        padding: EdgeInsets.symmetric(horizontal: 10, vertical: 20),
        children: [
          _crearInput(),
          Divider(),
          _crearMail(),
          Divider(),
          _crearPassword(),
          Divider(),
          _crearData(context),
          Divider(),
          _crearDropdown(),
          Divider(),
          _crearPersona(),
          Divider(),
        ],
      ),
    );
  }

  _crearInput() {
    return TextField(
      autofocus: true,
      textCapitalization: TextCapitalization.sentences,
      decoration: InputDecoration(
        counter: Text('Lletres: ${_nom.length}'),
        hintText: 'Nom de l\'usuari',
        labelText: 'Nom',
        helperText: 'Posi el nom complet',
        suffixIcon: Icon(Icons.accessibility),
        icon: Icon(Icons.account_circle),
        border: OutlineInputBorder(borderRadius: BorderRadius.circular(40)),
      ),
      onChanged: (valor) {
        setState(() {
          _nom = valor;
        });
      },
    );
  }

  _crearPersona() {
    return ListTile(
      title: Text('Nom: $_nom'),
      subtitle: Text('Correu: $_email'),
      trailing: Text(_pais),
    );
  }

  _crearMail() {
    return TextField(
      keyboardType: TextInputType.emailAddress, // El teclado muestra la @
      decoration: InputDecoration(
        //counter: Text('Lletres: ${_nom.length}'),
        hintText: 'Escriu l\'e-mail',
        labelText: 'e-mail',
        helperText: 'Posi l\'e-mail.',
        suffixIcon: Icon(Icons.alternate_email),
        icon: Icon(Icons.email),
        border: OutlineInputBorder(borderRadius: BorderRadius.circular(40)),
      ),
      onChanged: (valor) {
        setState(() {
          _email = valor;
        });
      },
    );
  }

  _crearPassword() {
    return TextField(
      obscureText: true,
      decoration: InputDecoration(
        hintText: 'Pass',
        labelText: 'Password',
        helperText: 'Posi la password',
        suffixIcon: Icon(Icons.lock_open),
        icon: Icon(Icons.lock),
        border: OutlineInputBorder(borderRadius: BorderRadius.circular(40)),
      ),
      onChanged: (valor) {
        setState(() {
          //_password = valor;
        });
      },
    );
  }

  Widget _crearData(BuildContext context) {
    return TextField(
      enableInteractiveSelection: false,
      controller: _inputFieldDataController,
      decoration: InputDecoration(
        hintText: 'Data naixament',
        labelText: 'Data naixament',
        suffixIcon: Icon(Icons.perm_contact_calendar),
        icon: Icon(Icons.calendar_today),
        border: OutlineInputBorder(borderRadius: BorderRadius.circular(40)),
      ),
      onTap: () {
        FocusScope.of(context).requestFocus(new FocusNode());
        _seleccionaData(context);
      },
    );
  }

  void _seleccionaData(BuildContext context) async {
    DateTime? picked = await showDatePicker(
        context: context,
        locale: Locale('es', 'ES'),
        initialDate: DateTime.now(),
        firstDate: DateTime(2020),
        lastDate: DateTime(2025));
    if (picked != null) {
      setState(() {
        _data = picked.toString();
        _inputFieldDataController = _data as TextEditingController;
      });
    }
  }

  Widget _crearDropdown() {
    return Row(
      children: [
        Icon(Icons.language),
        SizedBox(width: 30),
        Expanded(
          child: DropdownButton(
            hint: Text('Selecciona País'),
            //value: _pais,
            items: getOptionDropdown(),
            onChanged: (opcio) {
              setState(() {
                _pais = opcio as String;
              });
            },
          ),
        ),
      ],
    );
  }

  List<DropdownMenuItem<String>> getOptionDropdown() {
    List<DropdownMenuItem<String>> llista = [];
    for (String pais in _paisos) {
      llista.add(DropdownMenuItem(
        value: pais,
        child: Text(pais),
      ));
    }
    ;

    //_paisos.forEach((pais) {
    // llista.add(DropdownMenuItem(
    // ));
    //});

    return llista;
  }
}

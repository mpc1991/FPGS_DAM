import 'package:flutter/material.dart';
import 'package:porcel_cifre_macia_pmdm02_treball/src/dto/persona.dart';
import 'package:porcel_cifre_macia_pmdm02_treball/src/pages/personal_page.dart';
import 'package:porcel_cifre_macia_pmdm02_treball/src/providers/menu_providers.dart';
import 'package:porcel_cifre_macia_pmdm02_treball/src/utils/icono_string_util.dart';

class HomePage extends StatefulWidget {
  final Persona persona;
  const HomePage(this.persona, {super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Column(
          children: [
            Text('SPPMMD'),
            Text(
              widget.persona.getCorreu(),
              style: TextStyle(fontSize: 14),
            ),
          ],
        ),
      ),
      body: _llista(context, widget.persona, setState),
    );
  }
}

_llista(BuildContext context, Persona persona, Function setStateCallback) {
  return FutureBuilder(
      future: menuProvider.carregarDades(),
      initialData: [],
      builder: (context, AsyncSnapshot<List<dynamic>> snapshot) {
        // rep contexte i tipus de dada que retorna el future
        return ListView(
          children: _llistaElements(
              context, snapshot.data, persona, setStateCallback),
        );
      });
}

List<Widget> _llistaElements(BuildContext context, List<dynamic>? data,
    Persona persona, Function setStateCallback) {
  final List<Widget> elements = []; // llista que emmagatzema els botóns

  data?.forEach((element) {
    final widgetTemp = ListTile(
      //https://flutterassets.com/quick-tip-how-do-i-make-bold-text-in-flutter/
      title: Text(
        element['texte'],
        style: TextStyle(fontWeight: FontWeight.bold),
      ),
      leading: getIcon(element['icona']),
      trailing: Icon(Icons.keyboard_arrow_right, color: Colors.blue),
      onTap: () async {
        // Trasferencia de persona a PersonalPage adaptada de chatGPT
        if (element['ruta'] == 'personal') { // Si la ruta es "personal" passam l'objecte persona.
          final _updatedPersona = await Navigator.push( // Esperam el pop
            context,
            MaterialPageRoute(
              builder: (context) => PersonalPage(persona: persona),
            ),
          );
          if (_updatedPersona != null) { // Adaptat de chatGPO per a que el correu s'actualitzi a l'Appbar
            setStateCallback(() {
              // Actualiza la persona directamente
              persona.setCorreu(_updatedPersona.getCorreu());
            });
          }
        } else { // Si la ruta no es "personal", no es passa l'objecte "persona"
          Navigator.pushNamed(context, element['ruta']);
        }
      },
    );

    elements
      ..add(widgetTemp)
      ..add(Divider());
  });
  return elements;
}

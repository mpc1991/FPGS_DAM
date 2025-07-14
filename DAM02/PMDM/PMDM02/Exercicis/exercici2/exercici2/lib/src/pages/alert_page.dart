import 'package:flutter/material.dart';

class AlertPage extends StatelessWidget {
  const AlertPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: ElevatedButton(
          child: Text('Botó alerta!'),
          style: ElevatedButton.styleFrom(
              backgroundColor: Colors.blue,
              foregroundColor: Colors.white,
              shape: StadiumBorder()),
          onPressed: () {
            _mostrarAlert(context);
          },
        ),
      ),
      appBar: AppBar(
        title: Text('Pagina Alert'),
      ),
      floatingActionButton: FloatingActionButton(
          child: Icon(Icons.arrow_back),
          onPressed: () {
            Navigator.pop(context);
          }),
    );
  }

  void _mostrarAlert(BuildContext context) {
    showDialog(
        context: context,
        barrierDismissible: true,
        builder: (context) {
          return AlertDialog(
            title: Text('Titol de l\'alerta'),
            content: Column(
              mainAxisSize: MainAxisSize.min,
              children: [Text('Missatge de l\'alerta'), FlutterLogo(size: 50)],
            ),
            actions: [
              TextButton(
                  onPressed: () => Navigator.of(context).pop(),
                  child: Text('Cancelar')),
              TextButton(
                  onPressed: () {
                    Navigator.of(context).pop();
                  },
                  child: Text('Acceptar')),
            ],
            shape:
                RoundedRectangleBorder(borderRadius: BorderRadius.circular(30)),
          );
        });
  }
}

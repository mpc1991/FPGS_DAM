import 'package:flutter/material.dart';

class WidgetPage extends StatelessWidget {
  const WidgetPage({super.key});

  @override
  Widget build(BuildContext context) {
    List<String> data = getCarBrands();

    return Scaffold(
      appBar: AppBar(
        title: Text('LinearGradient && Autocomplete'),
      ),
      body: Container(
        decoration: const BoxDecoration(
          // LinearGradient nos permite elegir varios colores que iran salteandose donde los implantemos
          gradient: LinearGradient(
            colors: [
              Colors.white,
              Colors.blueAccent,
              Colors.white,
              Colors.blueAccent,
              Colors.white,
              Colors.blueAccent,
            ],
            begin: Alignment.topLeft, // Definimos donde empieza a pintar
            end: Alignment.bottomRight, // Definimos donde termina de pintar
            transform: GradientRotation(10 / 4), // Definimos la rotación
          ),
        ),
        child: Center(
          child: Container(
            child: Column(
              mainAxisSize: MainAxisSize.min,
              children: [
                // ShaderMask nos permite elegir varios colores que iran salteandose donde los implantemos
                ShaderMask(
                  shaderCallback: (bounds) => LinearGradient(
                    colors: [
                      Colors.black45,
                      Colors.teal,
                      Colors.black45,
                      Colors.teal,
                    ],
                    begin: Alignment.topRight, // Definimos donde empieza a pintar
                    end: Alignment.bottomLeft, // Definimos donde termina de pintar
                    transform: GradientRotation(5 / 4), // Definimos la rotación
                  ).createShader(bounds),
                  child: const Text(
                    'Autocomplete text',
                    style: TextStyle(
                      color: Colors.white,
                      fontSize: 48,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                ),
                SizedBox(
                  height: 10,
                ),
                // Widget de Autocompletar
                Autocomplete<String>(
                  fieldViewBuilder:
                      (context, controller, focusNode, onFieldSubmitted) {
                    return TextField(
                      controller:
                          controller, // Usar el controlador proporcionado
                      focusNode: focusNode, // Usar el focusNode proporcionado
                      decoration: InputDecoration(
                        labelText: 'Cerca la marca de cotche',
                        border: OutlineInputBorder(),
                        filled: true, // perque el fons sigui de color
                        fillColor: Colors.white, // elegim el color de fons
                      ),
                    );
                  },
                  optionsBuilder: (TextEditingValue textEditingValue) {
                    return data.where((entry) => entry
                        .toLowerCase()
                        .contains(textEditingValue.text.toLowerCase()));
                  },
                  displayStringForOption: (String option) => option,
                  optionsViewBuilder: (context, onSelected, options) {
                    return Material(
                      child: ListView.builder(
                        padding: const EdgeInsets.all(8),
                        itemCount: options.length,
                        itemBuilder: (context, index) {
                          final option = options.elementAt(index);
                          return ListTile(
                            title: Text(option),
                            onTap: () {
                              onSelected(option);
                            },
                          );
                        },
                      ),
                    );
                  },
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }

  // Llista que conté les marques.
  List<String> getCarBrands() {
    return [
      'Acura',
      'Alfa Romeo',
      'Audi',
      'BMW',
      'Buick',
      'Cadillac',
      'Chevrolet',
      'Chrysler',
      'Dodge',
      'Fiat',
      'Ford',
      'Genesis',
      'GMC',
      'Honda',
      'Hyundai',
      'Infiniti',
      'Jaguar',
      'Jeep',
      'Kia',
      'Lamborghini',
      'Land Rover',
      'Lexus',
      'Lincoln',
      'Maserati',
      'Mazda',
      'McLaren',
      'Mercedes-Benz',
      'MINI',
      'Mitsubishi',
      'Nissan',
      'Pagani',
      'Peugeot',
      'Porsche',
      'Ram',
      'Renault',
      'Rolls-Royce',
      'Saab',
      'Subaru',
      'Suzuki',
      'Tesla',
      'Toyota',
      'Volkswagen',
      'Volvo',
      'Aston Martin',
      'Bentley',
      'Bugatti',
      'Ferrari',
      'Rivian',
      'Zenos',
      'Harley-Davidson',
      'Indian Motorcycle',
      'Yamaha',
      'Kawasaki',
      'Honda Motorcycles',
      'Suzuki Motorcycles',
      'BMW Motorrad',
      'Ducati',
      'Triumph',
      'Moto Guzzi',
      'KTM',
      'Aprilia',
      'Royal Enfield',
      'BSA',
      'Norton',
      'Vincent',
      'MV Agusta',
      'Benelli',
      'CFMOTO'
    ];
  }
}

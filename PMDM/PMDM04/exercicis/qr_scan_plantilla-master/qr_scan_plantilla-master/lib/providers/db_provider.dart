import 'dart:io';

import 'package:path_provider/path_provider.dart';
import 'package:sqflite/sqflite.dart';
import 'package:path/path.dart';

class DBProvider{
    static Database? _database;

    static final DBProvider db = DBProvider._();

    DBProvider._(){}

    Future<Database> get database async {
      if (database == null) {
        _database = await initDB();
      }

      return _database!;
    }

    Future<Database> initDB() async {
      // Obtenir es path
      Directory documentsDirectory = await getApplicationCacheDirectory();
      final path = join(documentsDirectory.path, 'Scans.db');
      print(path);

      // Creació de la BBDD
      return await openDatabase(
        path, 
        version: 1,
        onOpen:(db) {},
        onCreate: (Database db, int version) async {
          await db.execute('''
          CREATE TABLE Scans(
            id INTEGER PRIMARY KEY,
            tipus TEXT
            valor TEXT
          )
        ''');
        }
        );
    }
}
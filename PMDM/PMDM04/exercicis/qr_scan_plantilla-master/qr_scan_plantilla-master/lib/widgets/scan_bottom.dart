import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import 'package:qr_scan/models/scan_models.dart';
import 'package:qr_scan/providers/db_provider.dart';
import 'package:qr_scan/providers/scan_list_provider.dart';

class ScanButton extends StatelessWidget {
  const ScanButton({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return FloatingActionButton(
      elevation: 0,
      child: Icon(
        Icons.filter_center_focus,
      ),
      onPressed: () {
        print('Botó polsat!');
        //DBProvider.db.database;
        String barcodeScanRes = 'geo:123';
        final scanListProvider = Provider.of<ScanListProvider>(context, listen: false);
        scanListProvider.nouScan(barcodeScanRes);

        String barcodeScanRes1 = 'https//asdasd';
        scanListProvider.nouScan(barcodeScanRes1);
      },
    );
  }
}

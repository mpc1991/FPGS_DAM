import 'package:flutter/material.dart';
import 'package:qr_scan/widgets/scan_tiles.dart';

class DireccionsScreen extends StatelessWidget {
  const DireccionsScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    // devuelve el widget ScanTiles con valor "http"
    return ScanTiles(tipus: 'http');
  }
}

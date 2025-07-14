import 'package:exercici2/src/pages/alert_page.dart';
import 'package:exercici2/src/pages/animated_container.dart';
import 'package:exercici2/src/pages/avatar_page.dart';
import 'package:exercici2/src/pages/card_page.dart';
import 'package:exercici2/src/pages/home_page.dart';
import 'package:exercici2/src/pages/input_page.dart';
import 'package:exercici2/src/pages/listView_page.dart';
import 'package:exercici2/src/pages/slider_page.dart';
import 'package:flutter/material.dart';

Map<String, WidgetBuilder> getRoutes() {
  return <String, WidgetBuilder>{
    '/'                 : (BuildContext context) => HomePage(),
    'alert'             : (BuildContext context) => AlertPage(),
    'avatar'            : (BuildContext context) => AvatarPage(),
    'card'              : (BuildContext context) => CardPage(),
    'animatedContainer' : (BuildContext context) => AnimatedContainerPage(),
    'inputs'            : (BuildContext context) => inputPage(),
    'slider'            : (BuildContext context) => SliderPage(),
    'list'              : (BuildContext context) => LlistaPage(),
  };
}

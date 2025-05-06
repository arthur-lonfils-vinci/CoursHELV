import 'package:ex2/Class/propriete.dart';
import 'package:flutter/material.dart';

class PropertyWidget extends StatelessWidget {
  final Propriete propriete;

  const PropertyWidget({super.key, required this.propriete});

  @override
  Widget build(BuildContext context) {
    return Card(
      child: ListTile(
        title: Text('${propriete.state} - ${propriete.type}'),
        subtitle: Text('${propriete.area} - ${propriete.nbrChambre}'),
        trailing: Text('${propriete.price} €'),
      ),
    );
  }
}

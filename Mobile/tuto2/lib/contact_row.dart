import 'package:flutter/material.dart';
import 'package:tuto2/contact.dart';

class ContactRow extends StatelessWidget {
  final Contact contact;

  const ContactRow({
    super.key,
    required this.contact,
  });

  @override
  Widget build(BuildContext context) {
    return ListTile(
      title: Text(contact.name),
      subtitle: Text(contact.phone),
      trailing: Icon(contact.isFavorite ? Icons.star : Icons.star_border),
    );
  }
}
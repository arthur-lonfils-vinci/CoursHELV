class Contact {
  final String name;
  final String phone;
  final bool isFavorite;

  Contact({required this.name, required this.phone, this.isFavorite = false});
}

List<Contact> _createContacts() {
  final contacts1 = {
    "Pierre": "123",
    "Paul": "456",
    "Jean": "789",
  }.entries.map((c) => Contact(name: c.key, phone: c.value));

  final contacts2 = [
    for (var i = 0; i < 1000; i++)
      Contact(name: "Contact ${i + 1}", phone: "$i", isFavorite: true),
  ];

  final contacts3 = List.generate(10, (i) => Contact(name: "C$i", phone: "$i"));

  return [...contacts1, ...contacts2, ...contacts3];
}

final defaultContacts = _createContacts();

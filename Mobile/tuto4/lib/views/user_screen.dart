import 'package:flutter/material.dart';

class UserScreen extends StatelessWidget {
  final String username;

  const UserScreen({super.key, required this.username});

  @override
  Widget build(BuildContext context) {
    final Map<String, String> userData = getUserData(username);

    return Scaffold(
      appBar: AppBar(
        title: const Text('User Details'),
        backgroundColor: Theme.of(context).colorScheme.primaryContainer,
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text('Username: ${userData['username']}'),
            Text('First Name: ${userData['firstname']}'),
            Text('Last Name: ${userData['lastname']}'),
            Text('Email: ${userData['email']}'),
          ],
        ),
      ),
    );
  }
}

Map<String, String> getUserData(String username) {
  final List<Map<String, String>> userList = [
    {
      'username': 'mcCain123',
      'firstname': 'John',
      'lastname': 'McCain',
      'email': 'john.mccain@example.com',
    },
    {
      'username': 'greg123',
      'firstname': 'Greg',
      'lastname': 'Doe',
      'email': 'greg.doe@example.com',
    },
    {
      'username': 'sarah123',
      'firstname': 'Sarah',
      'lastname': 'Johnson',
      'email': 'sarah.johnson@example.com',
    },
  ];

  return userList.firstWhere((user) => user['username'] == username);
}

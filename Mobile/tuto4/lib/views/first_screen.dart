import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:provider/provider.dart';
import 'package:tuto4/view_models/click_view_model.dart';

class FirstScreen extends StatelessWidget {
  const FirstScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("First screen"),
        backgroundColor: Theme.of(context).colorScheme.primaryContainer,
      ),
      body: Center(
        child: Consumer<ClickViewModel>(
          builder:
              (context, viewModel, child) => Column(
                mainAxisSize: MainAxisSize.min,
                children: [
                  const Text("Hello from first screen."),
                  const SizedBox(height: 16),
                  Text("There were ${viewModel.clicks} clicks."),
                  const SizedBox(height: 16),
                  ElevatedButton(
                    onPressed: () => viewModel.increment(),
                    child: const Text("click me"),
                  ),
                  const SizedBox(height: 32),
                  ElevatedButton(
                    onPressed: () => context.go("/second"),
                    child: const Text("go to second screen"),
                  ),
                  const SizedBox(height: 32),
                  const Expanded(child: UserListView()),
                ],
              ),
        ),
      ),
    );
  }
}

class UserListView extends StatelessWidget {
  static const usernames = ['mcCain123', 'greg123', 'sarah123'];

  const UserListView({super.key});

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      itemCount: usernames.length,
      itemBuilder: (context, index) {
        final username = usernames[index];
        return ListTile(
          title: Center(child: Text(username)),
          onTap: () => context.go('/users/$username'),
        );
      },
    );
  }
}

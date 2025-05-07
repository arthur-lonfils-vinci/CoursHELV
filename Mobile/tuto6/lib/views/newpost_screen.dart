import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:tuto6/view_models/post_view_model.dart';
import 'package:tuto6/widgets/nav_bar.dart';

class NewPostScreen extends StatefulWidget {
  final String title;
  const NewPostScreen({super.key, required this.title});

  @override
  State<NewPostScreen> createState() => _NewPostState();
}

class _NewPostState extends State<NewPostScreen> {
  final _formKey = GlobalKey<FormState>();
  final _nameController = TextEditingController();
  final _contentController = TextEditingController();
  String get title => widget.title;

  @override
  void dispose() {
    _nameController.dispose();
    _contentController.dispose();
    super.dispose();
  }

  void _submit() {
    if (_formKey.currentState!.validate()) {
      final name = _nameController.text;
      final content = _contentController.text;
      Provider.of<PostViewModel>(context, listen: false).addPost(name, content);
      Navigator.of(context).pop();
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: navBar(context, title),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Form(
          key: _formKey,
          child: Column(
            children: [
              TextFormField(
                controller: _nameController,
                decoration: const InputDecoration(labelText: 'Name'),
                validator:
                    (value) =>
                        (value == null || value.isEmpty)
                            ? 'Please enter a name'
                            : null,
              ),
              TextFormField(
                controller: _contentController,
                decoration: const InputDecoration(labelText: 'Content'),
                validator:
                    (value) =>
                        (value == null || value.isEmpty)
                            ? 'Please enter a content'
                            : null,
              ),
              const SizedBox(height: 20),
              ElevatedButton(
                onPressed: _submit,
                child: const Text('Create Post'),
              ),
            ],
          ),
        ),
      ),
    );
  }
}

import 'package:flutter/material.dart';

class MyForm extends StatefulWidget {
  final void Function(String) setColor;

  const MyForm(this.setColor, {super.key});

  @override
  State<MyForm> createState() => _MyFormState();
}

class _MyFormState extends State<MyForm> {
  final controller = TextEditingController();
  final formKey = GlobalKey<FormState>();

  @override
  void dispose() {
    controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Form(
      key: formKey,
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.end,
        children: [
          Expanded(
            child: TextFormField(
              controller: controller,
              decoration: const InputDecoration(labelText: "Enter color"),
              validator:
                  (value) =>
                      (value == null || value.isEmpty)
                          ? "Color can't be empty"
                          : null,
            ),
          ),
          const SizedBox(width: 32.0),
          ElevatedButton(
            child: const Text("Change color"),
            onPressed: () {
              if (formKey.currentState!.validate()) {
                widget.setColor(controller.text);
                controller.text = "";
              }
            },
          ),
        ],
      ),
    );
  }
}

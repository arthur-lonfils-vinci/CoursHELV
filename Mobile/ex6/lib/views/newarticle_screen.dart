import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:provider/provider.dart';
import 'package:ex6/view_models/article_view_model.dart';
import 'package:ex6/widgets/nav_bar.dart';

class NewArticleScreen extends StatefulWidget {
  final String title;
  const NewArticleScreen({super.key, required this.title});

  @override
  State<NewArticleScreen> createState() => _NewPostState();
}

class _NewPostState extends State<NewArticleScreen> {
  final _formKey = GlobalKey<FormState>();
  final _nameController = TextEditingController();
  final _priceController = TextEditingController();
  final _quantityController = TextEditingController();
  bool _isSubmitting = false;
  String get title => widget.title;

  @override
  void initState() {
    super.initState();
    _quantityController.text = '1'; // Default quantity
  }

  @override
  void dispose() {
    _nameController.dispose();
    _priceController.dispose();
    _quantityController.dispose();
    super.dispose();
  }

  void _submit() async {
    if (_formKey.currentState!.validate()) {
      setState(() {
        _isSubmitting = true;
      });

      final name = _nameController.text;
      final price =
          double.tryParse(_priceController.text.replaceAll(',', '.')) ?? 0.0;
      final quantity = int.tryParse(_quantityController.text) ?? 0;

      try {
        await Provider.of<ArticleViewModel>(
          context,
          listen: false,
        ).addArticle(name, price, quantity);

        if (!mounted) return;

        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(
            content: Text('Article "$name" successfully created'),
            backgroundColor: Colors.green,
          ),
        );
        Navigator.of(context).pop();
      } catch (e) {
        if (!mounted) return;

        setState(() {
          _isSubmitting = false;
        });

        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(
            content: Text('Failed to create article: ${e.toString()}'),
            backgroundColor: Colors.red,
          ),
        );
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: navBar(context, title),
      body: SingleChildScrollView(
        child: Padding(
          padding: const EdgeInsets.all(16.0),
          child: Form(
            key: _formKey,
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.stretch,
              children: [
                Card(
                  elevation: 2,
                  child: Padding(
                    padding: const EdgeInsets.all(16.0),
                    child: Column(
                      children: [
                        TextFormField(
                          controller: _nameController,
                          decoration: InputDecoration(
                            labelText: 'Article Name',
                            border: OutlineInputBorder(
                              borderRadius: BorderRadius.circular(8.0),
                            ),
                            prefixIcon: const Icon(Icons.shopping_bag_outlined),
                          ),
                          textCapitalization: TextCapitalization.sentences,
                          textInputAction: TextInputAction.next,
                          validator:
                              (value) =>
                                  (value == null || value.isEmpty)
                                      ? 'Please enter a name'
                                      : null,
                        ),
                        const SizedBox(height: 16),
                        TextFormField(
                          controller: _priceController,
                          decoration: InputDecoration(
                            labelText: 'Price',
                            border: OutlineInputBorder(
                              borderRadius: BorderRadius.circular(8.0),
                            ),
                            prefixIcon: const Icon(Icons.attach_money),
                            hintText: 'Enter price (e.g. 9.99)',
                          ),
                          keyboardType: const TextInputType.numberWithOptions(
                            decimal: true,
                          ),
                          inputFormatters: [
                            FilteringTextInputFormatter.allow(
                              RegExp(r'^\d+[,.]?\d{0,2}'),
                            ),
                          ],
                          textInputAction: TextInputAction.next,
                          validator: (value) {
                            if (value == null || value.isEmpty) {
                              return 'Please enter a price';
                            }
                            final price = double.tryParse(
                              value.replaceAll(',', '.'),
                            );
                            if (price == null) {
                              return 'Please enter a valid number';
                            }
                            if (price <= 0) {
                              return 'Price must be greater than 0';
                            }
                            return null;
                          },
                        ),
                        const SizedBox(height: 16),
                        TextFormField(
                          controller: _quantityController,
                          decoration: InputDecoration(
                            labelText: 'Quantity',
                            border: OutlineInputBorder(
                              borderRadius: BorderRadius.circular(8.0),
                            ),
                            prefixIcon: const Icon(Icons.inventory_2_outlined),
                            hintText: 'Enter quantity',
                          ),
                          keyboardType: TextInputType.number,
                          inputFormatters: [
                            FilteringTextInputFormatter.digitsOnly,
                          ],
                          validator: (value) {
                            if (value == null || value.isEmpty) {
                              return 'Please enter a quantity';
                            }
                            final quantity = int.tryParse(value);
                            if (quantity == null) {
                              return 'Please enter a valid number';
                            }
                            if (quantity < 0) {
                              return 'Quantity cannot be negative';
                            }
                            return null;
                          },
                        ),
                      ],
                    ),
                  ),
                ),
                const SizedBox(height: 20),
                ElevatedButton.icon(
                  onPressed: _isSubmitting ? null : _submit,
                  style: ElevatedButton.styleFrom(
                    padding: const EdgeInsets.symmetric(vertical: 12),
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(8),
                    ),
                  ),
                  icon:
                      _isSubmitting
                          ? const SizedBox(
                            width: 20,
                            height: 20,
                            child: CircularProgressIndicator(
                              strokeWidth: 2,
                              color: Colors.white,
                            ),
                          )
                          : const Icon(Icons.save),
                  label: Text(
                    _isSubmitting ? 'Creating...' : 'Create Article',
                    style: const TextStyle(fontSize: 16),
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}

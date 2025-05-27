import 'package:app_train/view_models/app_view_model.dart';
import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:provider/provider.dart';
import '../widgets/cart_item_widget.dart';

class CartScreen extends StatelessWidget {
  const CartScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final appViewModel = Provider.of<AppViewModel>(context);
    final cart = appViewModel.cart;

    final totalPrice = appViewModel.totalPrice;

    return Scaffold(
      appBar: AppBar(
        title: const Text('Cart'),
        leading: IconButton(
          icon: const Icon(Icons.arrow_back),
          onPressed: () => context.go('/'),
        ),
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        actions: [
          IconButton(
            icon: const Icon(Icons.save),
            onPressed: () => appViewModel.saveCart(),
          ),
          IconButton(
            icon: const Icon(Icons.restore),
            onPressed: () => appViewModel.restoreCart(),
          ),
        ],
      ),
      floatingActionButton: FloatingActionButton(
        child: const Icon(Icons.clear_all),
        onPressed: () => appViewModel.removeAllFromCart(),
      ),
      body: Column(
        children: [
          Expanded(
            child: SizedBox(
              width: 512,
              child:
                  cart.isNotEmpty
                      ? ListView.builder(
                        itemCount: cart.length,
                        itemBuilder:
                            (context, index) =>
                                CartItemWidget(item: cart[index]),
                      )
                      : const Center(child: Text("Cart is empty.")),
            ),
          ),
          Center(
            child: Text(
              'Total: $totalPrice €',
              style: const TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
          ),
        ],
      ),
    );
  }
}

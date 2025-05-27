import 'package:app_train/view_models/app_view_model.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../../models/cart_item.dart';

class CartItemWidget extends StatelessWidget {
  final CartItem item;

  const CartItemWidget({super.key, required this.item});

  @override
  Widget build(BuildContext context) {
    final appViewModel = Provider.of<AppViewModel>(context);
    final totalPrice = (item.dish.price * item.count).toStringAsFixed(2);

    return Card(
      child: ListTile(
        leading: CircleAvatar(
          backgroundImage: NetworkImage(item.dish.imagePath),
        ),
        title: Text(item.dish.name),
        subtitle: Text('${item.dish.price} € x ${item.count} = $totalPrice €'),
        // add and remove buttons
        trailing: Row(
          mainAxisSize: MainAxisSize.min,
          children: [
            IconButton(
              icon: const Icon(Icons.remove),
              onPressed: () {
                appViewModel.removeFromCart(item.dish);
              },
            ),
            IconButton(
              icon: const Icon(Icons.add),
              onPressed: () {
                appViewModel.addToCart(item.dish);
              },
            ),
          ],
        ),
      ),
    );
  }
}

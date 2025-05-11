import 'package:exam_blanc/models/cart_item.dart';
import 'package:exam_blanc/models/dish.dart';
import 'package:exam_blanc/services/dish_service.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:sqflite/sqflite.dart';
import 'package:sqflite_common_ffi_web/sqflite_ffi_web.dart';

class CartService {
  final List<CartItem> _cart = [];

  Future<void> addToCart(Dish dish) async {
    if (_cart.any((item) => item.dish.id == dish.id)) {
      // if the dish is already in the cart, just increase the count
      _cart.firstWhere((item) => item.dish.id == dish.id).count++;
      return;
    }
    _cart.add(CartItem(dish: dish, count: 1));
  }

  Future<List<CartItem>> getCart(List<Dish> dishes) async {
    return _cart;
  }

  Future<void> removeFromCart(int dishId, bool? allRemove) async {
    if (allRemove == true) {
      // if allRemove is true, remove all items with the same dishId
      _cart.removeWhere((item) => item.dish.id == dishId);
      return;
    }
    
    // if allRemove is false, just decrease the count
    if (_cart.any((item) => item.dish.id == dishId)) {
      // if the dish is in the cart, just decrease the count
      _cart.firstWhere((item) => item.dish.id == dishId).count--;
      if (_cart.firstWhere((item) => item.dish.id == dishId).count <= 0) {
        _cart.removeWhere((item) => item.dish.id == dishId);
      }
      return;
    }
  }

  Future<void> clearCart() async {
    _cart.clear();
  }

  //The Following methods are using the database for data persistence

  late Database _database;
  Database get database => _database;

  Future<Database> getDatabase() async {
    final databaseFactory = databaseFactoryFfiWeb;
    return await databaseFactory.openDatabase('cart.db');
  }

  Future<void> createTable() async {
    final db = await getDatabase();
    await db.execute(
      'CREATE TABLE IF NOT EXISTS cart (id INTEGER PRIMARY KEY AUTOINCREMENT,count INTEGER NOT NULL, dishId INTEGER NOT NULL)',
    );
  }

  Future<Database> initDatabase() async {
    if (kIsWeb) {
      WidgetsFlutterBinding.ensureInitialized();
      databaseFactory = databaseFactoryFfiWeb;
    }

    _database = await getDatabase();
    await createTable();

    return database;
  }

  Future<void> saveCart(List<CartItem> cart) async {
    final db = await getDatabase();
    // first, delete the existing cart
    await db.delete('cart');
    // then, insert the new cart items
    await db.transaction((txn) async {
      for (var item in cart) {
        await txn.insert('cart', {'count': item.count, 'dishId': item.dish.id});
      }
    });
  }

  Future<List<CartItem>> getCartFromDB(List<Dish> dishes) async {
    final db = await getDatabase();
    final List<Map<String, dynamic>> maps = await db.query('cart');
    return List.generate(maps.length, (i) {
      final cartItem = CartItem(
        dish: dishes.firstWhere((dish) => dish.id == maps[i]['dishId']),
        count: maps[i]['count'],
      );
      _cart.add(cartItem);
      return cartItem;
    });
  }
}

import 'package:exam_blanc/view_models/app_view_model.dart';
import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:provider/provider.dart';
import '../widgets/menu_widget.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  final TextEditingController _searchController = TextEditingController();
  String _searchQuery = '';
  String _selectedCategory = 'All';

  @override
  void dispose() {
    _searchController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final appViewModel = Provider.of<AppViewModel>(context);
    final menu = appViewModel.menu;
    final cart = appViewModel.cart;

    // Get unique categories from the menu
    final List<String> categories = ['All'];
    if (menu.isNotEmpty) {
      final uniqueCategories = menu.map((item) => item.category).toSet();
      categories.addAll(uniqueCategories.cast<String>());
    }

    // Filter menu based on search and category
    final filteredMenu =
        menu.where((item) {
          final matchesSearch = item.name.toLowerCase().contains(
            _searchQuery.toLowerCase(),
          );
          final matchesCategory =
              _selectedCategory == 'All' || item.category == _selectedCategory;
          return matchesSearch && matchesCategory;
        }).toList();

    return Scaffold(
      backgroundColor: Colors.grey[100],
      appBar: AppBar(
        elevation: 0,
        title: Text(
          "Our Menu",
          style: TextStyle(
            fontWeight: FontWeight.bold,
            color: Theme.of(context).primaryColor,
          ),
        ),
        backgroundColor: Colors.white,
        actions: [
          Stack(
            alignment: Alignment.center,
            children: [
              IconButton(
                icon: Icon(
                  Icons.shopping_cart,
                  color: Theme.of(context).primaryColor,
                ),
                onPressed: () => context.go('/cart'),
              ),
              if (cart.isNotEmpty)
                Positioned(
                  right: 3,
                  bottom: 3,
                  child: Container(
                    padding: const EdgeInsets.all(2),
                    decoration: BoxDecoration(
                      color: Colors.red,
                      borderRadius: BorderRadius.circular(10),
                    ),
                    constraints: const BoxConstraints(
                      minWidth: 18,
                      minHeight: 18,
                    ),
                    child: Text(
                      '${cart.length}',
                      style: const TextStyle(color: Colors.white, fontSize: 12),
                      textAlign: TextAlign.center,
                    ),
                  ),
                ),
            ],
          ),
        ],
      ),
      body: Column(
        children: [
          // Search bar
          Container(
            color: Colors.white,
            padding: const EdgeInsets.symmetric(
              horizontal: 16.0,
              vertical: 8.0,
            ),
            child: TextField(
              controller: _searchController,
              decoration: InputDecoration(
                hintText: 'Search menu...',
                prefixIcon: const Icon(Icons.search),
                suffixIcon:
                    _searchQuery.isNotEmpty
                        ? IconButton(
                          icon: const Icon(Icons.clear),
                          onPressed: () {
                            _searchController.clear();
                            setState(() {
                              _searchQuery = '';
                            });
                          },
                        )
                        : null,
                border: OutlineInputBorder(
                  borderRadius: BorderRadius.circular(30),
                  borderSide: BorderSide.none,
                ),
                filled: true,
                fillColor: Colors.grey[200],
                contentPadding: const EdgeInsets.symmetric(vertical: 0),
              ),
              onChanged: (value) {
                setState(() {
                  _searchQuery = value;
                });
              },
            ),
          ),

          // Category filter chips
          if (menu.isNotEmpty)
            Container(
              height: 50,
              color: Colors.white,
              padding: const EdgeInsets.symmetric(horizontal: 8.0),
              child: ListView.builder(
                scrollDirection: Axis.horizontal,
                itemCount: categories.length,
                itemBuilder: (context, index) {
                  return Padding(
                    padding: const EdgeInsets.symmetric(horizontal: 4.0),
                    child: FilterChip(
                      selected: categories[index] == _selectedCategory,
                      label: Text(categories[index]),
                      backgroundColor: Colors.grey[200],
                      selectedColor: Theme.of(
                        context,
                      ).primaryColor.withOpacity(0.2),
                      labelStyle: TextStyle(
                        color:
                            categories[index] == _selectedCategory
                                ? Theme.of(context).primaryColor
                                : Colors.black87,
                        fontWeight:
                            categories[index] == _selectedCategory
                                ? FontWeight.bold
                                : FontWeight.normal,
                      ),
                      onSelected: (selected) {
                        setState(() {
                          _selectedCategory = categories[index];
                        });
                      },
                    ),
                  );
                },
              ),
            ),

          // Content
          Expanded(
            child: RefreshIndicator(
              onRefresh: () async {
                appViewModel.menu;
              },
              child:
                  menu.isNotEmpty
                      ? Center(
                        child:
                            filteredMenu.isEmpty
                                ? Center(
                                  child: Column(
                                    mainAxisAlignment: MainAxisAlignment.center,
                                    children: [
                                      Icon(
                                        Icons.search_off,
                                        size: 80,
                                        color: Colors.grey[400],
                                      ),
                                      const SizedBox(height: 16),
                                      Text(
                                        'No items found',
                                        style: TextStyle(
                                          fontSize: 18,
                                          color: Colors.grey[600],
                                        ),
                                      ),
                                    ],
                                  ),
                                )
                                : SizedBox(
                                  width: 512,
                                  child: MenuWidget(menu: filteredMenu),
                                ),
                      )
                      : Center(
                        child: Column(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            SizedBox(
                              width: 80,
                              height: 80,
                              child: CircularProgressIndicator(
                                valueColor: AlwaysStoppedAnimation<Color>(
                                  Theme.of(context).primaryColor,
                                ),
                                strokeWidth: 6.0,
                              ),
                            ),
                            const SizedBox(height: 24),
                            Text(
                              "Loading our delicious menu...",
                              style: TextStyle(
                                fontSize: 18,
                                color: Colors.grey[700],
                                fontWeight: FontWeight.w500,
                              ),
                            ),
                          ],
                        ),
                      ),
            ),
          ),
        ],
      ),
    );
  }
}

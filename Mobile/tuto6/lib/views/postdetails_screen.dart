import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:tuto6/view_models/post_view_model.dart';
import 'package:tuto6/widgets/nav_bar.dart';

class PostDetails extends StatelessWidget {
  final String postId;

  const PostDetails({super.key, required this.postId});

  @override
  Widget build(BuildContext context) {
    return Consumer<PostViewModel>(
      builder: (context, model, child) {
        final post = model.getPost(postId);
        return Scaffold(
          appBar: navBar(context, 'Details for ${post.name}'),
          body: Padding(
            padding: const EdgeInsets.all(16.0),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(post.name),
                const SizedBox(height: 16.0),
                Text(post.content),
              ],
            ),
          ),
        );
      },
    );
  }
}
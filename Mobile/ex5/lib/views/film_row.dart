import 'package:flutter/material.dart';
import 'package:url_launcher/url_launcher.dart';

import '../models/film.dart';

class FilmRow extends StatelessWidget {
  final Film film;

  const FilmRow({super.key, required this.film});

  @override
  Widget build(BuildContext context) {
    return Card(
      elevation: 3,
      margin: const EdgeInsets.symmetric(vertical: 8, horizontal: 12),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          // Film banner image at the top of the card
          ClipRRect(
            borderRadius: const BorderRadius.vertical(top: Radius.circular(4)),
            child:
                film.movieBanner.isNotEmpty
                    ? Image.network(
                      film.movieBanner,
                      width: double.infinity,
                      height: 120,
                      fit: BoxFit.cover,
                      errorBuilder:
                          (context, error, stackTrace) => Container(
                            height: 120,
                            color: Colors.grey[300],
                            child: const Center(
                              child: Icon(Icons.movie, size: 50),
                            ),
                          ),
                    )
                    : Container(
                      height: 120,
                      color: Colors.grey[300],
                      child: const Center(child: Icon(Icons.movie, size: 50)),
                    ),
          ),
          Padding(
            padding: const EdgeInsets.all(12),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                // Title row with original title in subtitle
                Row(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    // Film poster thumbnail
                    if (film.image.isNotEmpty)
                      ClipRRect(
                        borderRadius: BorderRadius.circular(4),
                        child: Image.network(
                          film.image,
                          width: 70,
                          height: 100,
                          fit: BoxFit.cover,
                          errorBuilder:
                              (context, error, stackTrace) => Container(
                                height: 100,
                                width: 70,
                                color: Colors.grey[200],
                                child: const Icon(Icons.image_not_supported),
                              ),
                        ),
                      ),
                    const SizedBox(width: 12),
                    // Film title and details
                    Expanded(
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text(
                            film.title,
                            style: Theme.of(context).textTheme.titleLarge
                                ?.copyWith(fontWeight: FontWeight.bold),
                          ),
                          if (film.originalTitle.isNotEmpty)
                            Text(
                              film.originalTitle,
                              style: Theme.of(
                                context,
                              ).textTheme.titleSmall?.copyWith(
                                fontStyle: FontStyle.italic,
                                color: Colors.grey[700],
                              ),
                            ),
                          const SizedBox(height: 8),
                          // Director and producer
                          Row(
                            children: [
                              const Icon(Icons.person, size: 16),
                              const SizedBox(width: 4),
                              Expanded(
                                child: Text(
                                  "Director: ${film.director}",
                                  style: Theme.of(context).textTheme.bodyMedium,
                                ),²²²²
                              ),
                            ],
                          ),
                          const SizedBox(height: 4),
                          Row(
                            children: [
                              const Icon(Icons.people, size: 16),
                              const SizedBox(width: 4),
                              Expanded(
                                child: Text(
                                  "Producer: ${film.producer}",
                                  style: Theme.of(context).textTheme.bodyMedium,
                                ),
                              ),
                            ],
                          ),
                          // Runtime and release date
                          const SizedBox(height: 4),
                          Row(
                            children: [
                              const Icon(Icons.timer, size: 16),
                              const SizedBox(width: 4),
                              Text(
                                "${film.runningTime} minutes",
                                style: Theme.of(context).textTheme.bodyMedium,
                              ),
                            ],
                          ),
                          const SizedBox(height: 4),
                          Row(
                            children: [
                              const Icon(Icons.calendar_today, size: 16),
                              const SizedBox(width: 4),
                              Text(
                                "Released: ${film.releaseDate}",
                                style: Theme.of(context).textTheme.bodyMedium,
                              ),
                            ],
                          ),
                          const SizedBox(height: 4),
                          Row(
                            children: [
                              const Icon(
                                Icons.star,
                                size: 16,
                                color: Colors.amber,
                              ),
                              const SizedBox(width: 4),
                              Text(
                                "Score: ${film.rtScore}",
                                style: Theme.of(context).textTheme.bodyMedium
                                    ?.copyWith(fontWeight: FontWeight.bold),
                              ),
                            ],
                          ),
                        ],
                      ),
                    ),
                  ],
                ),
                // Description
                if (film.description.isNotEmpty) ...[
                  const SizedBox(height: 12),
                  Text(
                    film.description,
                    style: Theme.of(context).textTheme.bodyMedium,
                    maxLines: 3,
                    overflow: TextOverflow.ellipsis,
                  ),
                ],
                // URL
                const SizedBox(height: 8),
                InkWell(
                  onTap: () async {
                    final url = Uri.parse(film.url);
                    if (await canLaunchUrl(url)) {
                      await launchUrl(url);
                    } else {
                      // Handle error if URL can't be launched
                      if (context.mounted) {
                        ScaffoldMessenger.of(context).showSnackBar(
                          SnackBar(content: Text('Could not open ${film.url}')),
                        );
                      }
                    }
                  },
                  child: Row(
                    children: [
                      const Icon(Icons.link, size: 16),
                      const SizedBox(width: 4),
                      Expanded(
                        child: Text(
                          "More Info",
                          style: TextStyle(
                            color: Colors.blue,
                            decoration: TextDecoration.underline,
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}

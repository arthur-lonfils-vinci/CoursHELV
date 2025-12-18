# TP : Découverte de Redis avec Docker

## 🎯 Objectifs

- Démarrer un serveur Redis avec Docker
- Utiliser Redis Insight pour explorer et manipuler les données
- Maîtriser les commandes de base (GET, SET, DEL)
- Explorer les structures de données (Strings, Hashes, Lists, Sets, Sorted Sets)
- Comprendre le TTL et l'expiration des clés
- Réaliser des cas d'usage concrets (cache, sessions, compteurs, leaderboard)

---

## 📋 Prérequis

- Docker et Docker Compose installés sur votre machine
- Un navigateur web moderne

---

## 🚀 Partie 1 : Mise en place de l'environnement

### 1.1 Créer le fichier docker-compose.yml

Créez un dossier `tp-redis` et placez-y ce fichier :

```yaml
# docker-compose.yml
version: '3.8'

services:
  redis:
    image: redis:8-alpine
    container_name: redis-db
    ports:
      - "6379:6379"
    volumes:
      - redis-data:/data
    command: redis-server --appendonly yes

  redis-insight:
    image: redis/redisinsight:2.70
    container_name: redis-ui
    ports:
      - "5540:5540"
    depends_on:
      - redis

volumes:
  redis-data:
```

### 1.2 Démarrer les services

```bash
cd tp-redis
docker-compose up -d
```

Vérifiez que les deux containers sont bien lancés :

```bash
docker ps
```

Vous devriez voir `redis-db` et `redis-ui` en état "Up".

### 1.3 Se connecter à Redis Insight

1. Ouvrez votre navigateur et allez sur **http://localhost:5540**

2. Cliquez sur **"Add Redis Database"**

3. Entrez l'URL de connexion suivante :
   ```
   redis://default@redis-db:6379
   ```

4. Cliquez sur **"Add Redis Database"**

> 💡 **Pourquoi `redis-db` dans l'URL ?**
> 
> Docker Compose crée un réseau virtuel interne où chaque container est accessible par son nom (`container_name`). Le container Redis Insight doit contacter le container Redis, et dans ce réseau Docker, `redis-db` est le hostname du serveur Redis.
> 
> - Depuis votre PC : `localhost:6379` (car le port est exposé)
> - Depuis un autre container Docker : `redis-db:6379` (nom du container)

### 1.4 Explorer l'interface Redis Insight

Une fois connecté, vous arrivez sur le dashboard de votre base Redis. Prenez un moment pour explorer :

- **Browser** : visualiser les clés et leurs valeurs
- **Workbench** : exécuter des commandes Redis (c'est ici qu'on travaillera !)
- **Analysis Tools** : analyser la mémoire et les performances

Cliquez sur **Workbench** pour continuer le TP.

---

## 🔤 Partie 2 : Découverte du Workbench et premiers pas

Le Workbench est un éditeur de commandes Redis intégré. Vous pouvez y écrire des commandes, les exécuter, et voir les résultats.

### 2.1 Votre première commande

Dans le Workbench, tapez et exécutez (bouton ▶️ ou Ctrl+Entrée) :

```redis
PING
```

Redis répond `PONG` — la connexion fonctionne !

### 2.2 Opérations de base sur les Strings

Les Strings sont le type de base de Redis. Une clé = une valeur.

```redis
# Stocker une valeur
SET nom "Alice"
```

Après avoir exécuté `SET nom "Alice"`, allez dans l'onglet **Browser** (menu de gauche). Vous devriez voir la clé `nom` apparaître. Cliquez dessus pour voir sa valeur et ses propriétés.

Revenez dans le **Workbench** pour continuer.

```redis
# Récupérer la valeur
GET nom

# Vérifier si une clé existe (retourne 1 si oui, 0 si non)
EXISTS nom

# Supprimer une clé
DEL nom

# Vérifier qu'elle n'existe plus
GET nom
```

**Question** : Que retourne `GET nom` après `DEL nom` ?

### 2.3 Valeurs numériques et incrémentation

```redis
# Initialiser un compteur
SET visiteurs 0

# Incrémenter de 1
INCR visiteurs

# Incrémenter de 10
INCRBY visiteurs 10

# Décrémenter
DECR visiteurs

# Voir la valeur
GET visiteurs
```

**Question** : Si on fait `SET compteur "abc"` puis `INCR compteur`, que se passe-t-il ?

### 2.4 Stocker plusieurs clés

```redis
# SET multiple en une commande
MSET prenom "Bob" age "25" ville "Paris"

# GET multiple
MGET prenom age ville
```

---

## ⏰ Partie 3 : TTL et expiration

Le TTL (Time To Live) permet de faire expirer automatiquement les clés.

### Exercice 3.1 : Créer une clé avec expiration

```redis
# Créer une clé qui expire dans 30 secondes
SETEX session:user123 30 "token_abc123"

# Vérifier le TTL restant
TTL session:user123

# Attendre quelques secondes et revérifier
TTL session:user123

# Après 30 sec, la clé disparaît
GET session:user123
```

### Exercice 3.2 : Ajouter un TTL à une clé existante

```redis
SET config:cache "valeur"

# Ajouter un TTL de 60 secondes
EXPIRE config:cache 60

# Vérifier
TTL config:cache

# Supprimer le TTL (rendre la clé permanente)
PERSIST config:cache

# Vérifier
TTL config:cache
```

**Question** : Quelle est la différence entre TTL retournant `-1` et `-2` ?

---

## 📦 Partie 4 : Les Hashes (objets)

Les hashes permettent de stocker des objets avec plusieurs champs.

### Exercice 4.1 : Créer et manipuler un hash

```redis
# Créer un utilisateur
HSET user:1001 nom "Alice" email "alice@example.com" age "28"

# Récupérer un champ
HGET user:1001 nom

# Récupérer tous les champs
HGETALL user:1001

# Modifier un champ
HSET user:1001 age "29"

# Vérifier si un champ existe
HEXISTS user:1001 telephone

# Ajouter un champ
HSET user:1001 telephone "+33612345678"

# Supprimer un champ
HDEL user:1001 telephone
```

### Exercice 4.2 : Compteurs dans un hash

```redis
# Statistiques d'un article
HSET article:42 titre "Introduction à Redis" vues 0 likes 0

# Incrémenter les vues
HINCRBY article:42 vues 1
HINCRBY article:42 vues 1
HINCRBY article:42 vues 1

# Ajouter un like
HINCRBY article:42 likes 1

# Voir les stats
HGETALL article:42
```

---

## 📝 Partie 5 : Les Lists (files d'attente)

Les lists sont parfaites pour les queues (FIFO) ou les piles (LIFO).

### Exercice 5.1 : Queue de messages

```redis
# Ajouter des tâches à gauche (début de la liste)
LPUSH queue:emails "email1@test.com"
LPUSH queue:emails "email2@test.com"
LPUSH queue:emails "email3@test.com"

# Voir la liste
LRANGE queue:emails 0 -1

# Traiter les tâches (retirer par la droite = FIFO)
RPOP queue:emails
RPOP queue:emails

# Voir ce qu'il reste
LRANGE queue:emails 0 -1

# Longueur de la liste
LLEN queue:emails
```

### Exercice 5.2 : Historique des dernières actions

```redis
# Ajouter des actions (les plus récentes en premier)
LPUSH history:user:1 "login"
LPUSH history:user:1 "view_page:home"
LPUSH history:user:1 "view_page:products"
LPUSH history:user:1 "add_to_cart:item42"
LPUSH history:user:1 "checkout"

# Garder seulement les 3 dernières actions
LTRIM history:user:1 0 2

# Voir l'historique
LRANGE history:user:1 0 -1
```

---

## 🎯 Partie 6 : Les Sets (ensembles)

Les sets stockent des valeurs uniques, sans ordre ni doublons.

### Exercice 6.1 : Tags et catégories

```redis
# Ajouter des tags à un article
SADD article:42:tags "redis" "nosql" "database" "tutorial"

# Essayer d'ajouter un doublon
SADD article:42:tags "redis"

# Voir tous les tags
SMEMBERS article:42:tags

# Nombre de tags
SCARD article:42:tags

# Vérifier si un tag existe
SISMEMBER article:42:tags "redis"
SISMEMBER article:42:tags "python"
```

### Exercice 6.2 : Opérations ensemblistes

```redis
# Utilisateurs qui aiment Redis
SADD fans:redis "alice" "bob" "charlie"

# Utilisateurs qui aiment MongoDB
SADD fans:mongodb "bob" "david" "eve"

# Qui aime les deux ? (intersection)
SINTER fans:redis fans:mongodb

# Qui aime l'un ou l'autre ? (union)
SUNION fans:redis fans:mongodb

# Qui aime Redis mais pas MongoDB ? (différence)
SDIFF fans:redis fans:mongodb
```

---

## 🏆 Partie 7 : Les Sorted Sets (classements)

Les sorted sets associent un score à chaque élément pour le tri.

### Exercice 7.1 : Leaderboard d'un jeu

```redis
# Ajouter des joueurs avec leurs scores
ZADD leaderboard 1500 "alice"
ZADD leaderboard 2300 "bob"
ZADD leaderboard 1800 "charlie"
ZADD leaderboard 3100 "david"
ZADD leaderboard 950 "eve"

# Top 3 (scores décroissants)
ZREVRANGE leaderboard 0 2 WITHSCORES

# Classement d'un joueur (0-indexed, du plus haut au plus bas)
ZREVRANK leaderboard "charlie"

# Score d'un joueur
ZSCORE leaderboard "alice"

# Mettre à jour un score (alice gagne 200 points)
ZINCRBY leaderboard 200 "alice"

# Nouveau classement
ZREVRANGE leaderboard 0 -1 WITHSCORES
```

---

## 🛒 Partie 8 : Cas pratique — Dataset e-commerce

Il est temps de travailler avec des données plus réalistes ! Nous allons importer un dataset e-commerce complet.

### 8.1 Nettoyer la base

Avant d'importer, nettoyons les données de test :

```redis
FLUSHALL
```

### 8.2 Importer le dataset

Copiez-collez le contenu du fichier `ecommerce-dataset.redis` (fourni séparément) dans le Workbench et exécutez-le.

*Les commentaires vont générer des erreurs, c'est normal, vous pouvez les ignorer.*

Ce dataset contient :
- 10 utilisateurs (hashes `user:*`)
- 10 produits (hashes `product:*`)
- 8 commandes (hashes `order:*`)
- Des paniers, wishlists, catégories, tags...
- Des classements (vues, ventes, revenus)

### 8.3 Explorer les données dans le Browser

Allez dans **Browser** et explorez la structure des données :
- Filtrez par `user:*` pour voir les utilisateurs
- Filtrez par `product:*` pour voir les produits
- Cliquez sur une clé pour voir son contenu

### 8.4 Exercices de requêtes

Retournez dans le **Workbench** et répondez aux questions suivantes :

**Utilisateurs :**

```redis
# 1. Récupérer toutes les infos de l'utilisateur alice
# 2. Quel est l'email de bob ?
# 3. Combien d'utilisateurs sont en France ?
# 4. Lister les utilisateurs premium
# 5. Quels utilisateurs sont en France ET premium ? (intersection)
```

**Produits :**

```redis
# 6. Quel est le prix du Laptop Pro 15 ?
# 7. Quels produits sont dans la catégorie "electronics" ?
# 8. Quels tags a le produit 104 (Mechanical Keyboard) ?
# 9. Le produit 105 a-t-il le tag "fitness" ?
```

**Classements :**

```redis
# 10. Top 5 des produits les plus vus
# 11. Top 3 des produits les plus vendus
# 12. Top 3 des produits par revenu
# 13. Combien de vues a le produit 101 ?
# 14. Quel est le rang du produit 105 en termes de ventes ? (0 = premier)
```

**Paniers et commandes :**

```redis
# 15. Que contient le panier de l'utilisateur 2 ?
# 16. Quelles sont les 3 dernières commandes ?
# 17. Détails de la commande 1003
# 18. Dernière activité sur le site
```

**Questions avancées :**

```redis
# 19. Produits dans la wishlist de user:2 mais PAS dans son panier (Indice : comparer les clés)
# 20. Utilisateurs qui ont noté le produit 101
```

---

## 🧪 Partie 9 : Cas pratique — Système de cache

### Scénario

Vous devez implémenter un cache pour une API qui retourne des profils utilisateurs. Le cache doit :
- Stocker les profils pendant 5 minutes
- Permettre d'invalider le cache manuellement

### Exercice 9.1 : Implémentation

Proposez une structure de données simple ainsi que les opérations de base à effectuer pour gérer ce cas d'utilisation.

---

## 🔥 Partie 10 : Cas pratique — Rate Limiting

### Scénario

Limiter chaque IP à 100 requêtes par minute.

### Exercice 10.1 : Implémentation simple

Proposez une structure de données simple ainsi que les opérations de base à effectuer pour gérer ce cas d'utilisation.

---

## 📊 Partie 11 : Commandes utiles et nettoyage

### 11.1 Commandes d'exploration

```redis
# Lister toutes les clés (attention en prod !)
KEYS *

# Lister les clés qui matchent un pattern
KEYS user:*
KEYS product:*

# Compter les clés
DBSIZE

# Informations sur le serveur
INFO

# Statistiques mémoire
INFO memory
```

### 11.2 Arrêter l'environnement

```bash
# Depuis le dossier tp-redis
docker-compose down

# Pour supprimer aussi les données
docker-compose down -v
```

---

## 📚 Pour aller plus loin

- Documentation officielle : https://redis.io/commands
- Redis University (cours gratuits) : https://university.redis.com

---

## 🎁 Bonus : Exercice final

Implémentez un système de "likes" pour des posts :
1. Chaque utilisateur ne peut liker qu'une fois (utiliser un Set)
2. Compter le nombre total de likes (utiliser SCARD)
3. Pouvoir "unliker" (SREM)
4. Afficher les 10 posts les plus likés (Sorted Set)

<details>
<summary>💡 Indice</summary>

```redis
# Pour chaque post, un Set des users qui ont liké
SADD likes:post:42 "user:1" "user:2" "user:3"

# Nombre de likes
SCARD likes:post:42

# Maintenir un sorted set des posts par nombre de likes
ZADD popular:posts [SCARD result] "post:42"
```

</details>
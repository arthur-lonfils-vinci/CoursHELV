# TP : Détection de fraude avec Neo4j

## 🎯 Objectifs

- Démarrer un serveur Neo4j avec Docker
- Utiliser le Neo4j Browser pour explorer et visualiser les données
- Maîtriser les commandes Cypher de base (MATCH, CREATE, WHERE, RETURN)
- Créer des nœuds, des relations et des propriétés

---

## 📋 Prérequis

- Docker et Docker Compose installés sur votre machine

---

## 🚀 Partie 1 : Mise en place de l'environnement

### 1.1 Créer le fichier docker-compose.yml

Créez un dossier `tp-neo4j` et placez-y ce fichier :

```yaml
# docker-compose.yml
version: '3.8'

services:
  neo4j:
    image: neo4j:5-community
    container_name: neo4j-icij
    ports:
      - "7474:7474"   # HTTP (Browser)
      - "7687:7687"   # Bolt (connexion)
    environment:
      - NEO4J_AUTH=neo4j/password123
      - NEO4J_PLUGINS=["apoc"]
      - NEO4J_dbms_memory_heap_initial__size=1G
      - NEO4J_dbms_memory_heap_max__size=2G
    volumes:
      - neo4j-data:/data
      - neo4j-logs:/logs
      - ./import:/import

volumes:
  neo4j-data:
  neo4j-logs:
```

### 1.2 Démarrer le service

```bash
cd tp-neo4j
docker-compose up -d
```

### 1.3 Se connecter au Neo4j Browser

1. Ouvrez votre navigateur et allez sur **http://localhost:7474**

2. Connectez-vous avec :
   - **Username** : `neo4j`
   - **Password** : `password123`

3. Vous arrivez sur l'interface Neo4j Browser avec une zone de saisie Cypher en haut.

---

## 🔵 Partie 2 : Premiers pas avec Cypher

Le Neo4j Browser est un environnement interactif. Tapez vos commandes dans la barre en haut et appuyez sur **Ctrl+Entrée** (ou cliquez sur le bouton ▶️) pour exécuter.

### 2.1 Votre première requête

```cypher
// Vérifier que la base est vide
MATCH (n) RETURN n
```

Résultat attendu : aucun nœud (base vide).

### 2.2 Créer votre premier nœud

```cypher
// Créer une personne
CREATE (alice:Person {name: "Alice", age: 30, country: "France"})
RETURN alice
```

Cliquez sur le nœud qui apparaît pour voir ses propriétés !

### 2.3 Créer plusieurs nœuds

```cypher
// Créer d'autres personnes
CREATE (bob:Person {name: "Bob", age: 35, country: "UK"})
CREATE (charlie:Person {name: "Charlie", age: 28, country: "USA"})
RETURN bob, charlie
```

### 2.4 Créer une société

```cypher
// Créer une société offshore
CREATE (acme:Company {name: "Acme Holdings Ltd", jurisdiction: "Panama", status: "Active"})
RETURN acme
```

### 2.5 Visualiser tous les nœuds

```cypher
MATCH (n) RETURN n
```

Vous devriez voir 4 nœuds. Cliquez, ou passez votre souris au dessus d'un nœud pour voir ses propriétés.

---

## 🔗 Partie 3 : Créer des relations

Les relations sont le cœur des bases de données graphes.

### 3.1 Créer une relation de propriété

```cypher
// Alice possède Acme Holdings
MATCH (alice:Person {name: "Alice"}), (acme:Company {name: "Acme Holdings Ltd"})
CREATE (alice)-[:OWNS {share: 100, since: "2020-01-15"}]->(acme)
RETURN alice, acme
```

Une flèche apparaît entre Alice et Acme !

### 3.2 Créer d'autres relations

```cypher
// Bob connaît Alice
MATCH (bob:Person {name: "Bob"}), (alice:Person {name: "Alice"})
CREATE (bob)-[:KNOWS {since: "2015"}]->(alice)
RETURN bob, alice
```

```cypher
// Charlie travaille pour Acme
MATCH (charlie:Person {name: "Charlie"}), (acme:Company {name: "Acme Holdings Ltd"})
CREATE (charlie)-[:WORKS_FOR {role: "Director", since: "2021-03-01"}]->(acme)
RETURN charlie, acme
```

### 3.3 Visualiser le graphe complet

```cypher
MATCH (n)-[r]->(m) RETURN n, r, m
```

Explorez le graphe ! Faites glisser les nœuds pour mieux voir les connexions.

---

## 🔍 Partie 4 : Requêtes de recherche

### 4.1 Rechercher par label

```cypher
// Toutes les personnes
MATCH (p:Person) RETURN p

// Toutes les sociétés
MATCH (c:Company) RETURN c
```

### 4.2 Rechercher par propriété

```cypher
// Personnes françaises
MATCH (p:Person) WHERE p.country = "France" RETURN p

// Sociétés au Panama
MATCH (c:Company) WHERE c.jurisdiction = "Panama" RETURN c
```

### 4.3 Rechercher par relation

```cypher
// Qui possède quoi ?
MATCH (p:Person)-[:OWNS]->(c:Company)
RETURN p.name AS owner, c.name AS company

// Qui connaît qui ?
MATCH (a:Person)-[:KNOWS]->(b:Person)
RETURN a.name AS person1, b.name AS person2
```

### 4.4 Filtrer les relations

```cypher
// Propriétaires avec plus de 50% des parts
MATCH (p:Person)-[r:OWNS]->(c:Company)
WHERE r.share > 50
RETURN p.name, c.name, r.share
```

---

## 📊 Partie 5 : Commandes utiles et nettoyage

### 5.1 Commandes d'exploration

```cypher
// Schéma de la base
CALL db.schema.visualization()

// Statistiques
MATCH (n) RETURN labels(n), count(*)

// Types de relations
MATCH ()-[r]->() RETURN type(r), count(*)
```

### 5.2 Arrêter l'environnement

```bash
# Depuis le dossier tp-neo4j
docker-compose down

# Pour supprimer aussi les données
docker-compose down -v
```

---

## 📚 Pour aller plus loin

- Documentation Cypher : https://neo4j.com/docs/cypher-manual/
- Neo4j GraphAcademy (cours gratuits) : https://graphacademy.neo4j.com
- Graph Data Science : https://neo4j.com/docs/graph-data-science/
# TP : Exploration des Panama Papers avec Neo4j

## 🎯 Objectifs

- Démarrer un serveur Neo4j avec Docker
- Importer le **dataset officiel ICIJ** (Panama Papers, Paradise Papers, Pandora Papers...)
- Utiliser le Neo4j Browser pour explorer et visualiser les données
- Maîtriser les commandes Cypher de base (MATCH, CREATE, WHERE, RETURN)
- Détecter des patterns de fraude sur de vraies données (800 000+ entités)
- Comprendre la différence entre Neo4j Browser et Bloom pour la visualisation

---

## 📋 Prérequis

- Docker et Docker Compose installés sur votre machine
- ~2 Go d'espace disque (pour le dump ICIJ)
- Connexion internet (pour télécharger le dump)

---

## 📊 Le dataset ICIJ Offshore Leaks

Le dataset que nous allons utiliser est le **vrai dataset** utilisé par les journalistes de l'ICIJ (International Consortium of Investigative Journalists) pour leurs enquêtes :

| Source | Année | Documents | Révélations |
|--------|-------|-----------|-------------|
| **Offshore Leaks** | 2013 | 2.5M | Premiers leaks offshore |
| **Panama Papers** | 2016 | 11.5M | Démission PM Islande |
| **Bahamas Leaks** | 2016 | 1.3M | Politiciens EU |
| **Paradise Papers** | 2017 | 13.4M | Apple, Nike offshore |
| **Pandora Papers** | 2021 | 11.9M | 35 chefs d'état |

**Total** : 810 000+ entités offshore, 200+ pays, 80+ années de données

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

### 1.2 Télécharger le dataset ICIJ

```bash
cd tp-neo4j
mkdir -p import
```
Téléchargez le dump Neo4J 5 officiel de la base de données de l'ICIJ : https://offshoreleaks-data.icij.org/offshoreleaks/neo4j/icij-offshoreleaks-5.13.0.dump et placez le dans le dossier `import`


### 1.3 Importer le dump dans Neo4j

```bash
# Importer le dump ICIJ
docker compose run --rm neo4j sh -c \
  "neo4j-admin database load neo4j --from-stdin --overwrite-destination=true < /import/icij-offshoreleaks-5.13.0.dump"

# Démarrer Neo4j avec les données importées
docker compose up -d
```

### 1.4 Se connecter au Neo4j Browser

1. Ouvrez votre navigateur et allez sur **http://localhost:7474**

2. Connectez-vous avec :
   - **Username** : `neo4j`
   - **Password** : `password123`

3. Vérifiez que les données sont chargées :

```cypher
// Compter les entités
MATCH (n) RETURN labels(n) AS type, count(n) AS count ORDER BY count DESC
```

Vous devriez voir environ :
- ~800 000 Entity
- ~750 000 Officer
- ~25 000 Intermediary
- ~400 000 Address

---

## 📐 Partie 2 : Comprendre le modèle de données ICIJ

### 2.1 Visualiser le schéma

```cypher
CALL db.schema.visualization()
```

### 2.2 Le modèle de données

Le dataset ICIJ utilise 4 types de nœuds principaux :

| Label | Description | Exemple |
|-------|-------------|---------|
| **Entity** | Société offshore | "Acme Holdings Ltd" |
| **Officer** | Personne liée (directeur, actionnaire, bénéficiaire) | "John Smith" |
| **Intermediary** | Cabinet juridique/agent | "Mossack Fonseca" |
| **Address** | Adresse postale | "123 Panama City" |

Et 4 types de relations :

| Relation | Signification |
|----------|--------------|
| `:officer_of` | Personne → Société (directeur, actionnaire...) |
| `:intermediary_of` | Agent → Société (création, gestion) |
| `:registered_address` | Société → Adresse |
| `:similar_name` | Entités au nom similaire |

### 2.3 Explorer un exemple

```cypher
// Voir un exemple d'entité avec ses connexions
MATCH (e:Entity {name: "LAKE STREET INVESTMENTS LTD."})-[r]-(connected)
RETURN e, r, connected
LIMIT 25
```

---

## 🔍 Partie 3 : Premières requêtes d'exploration

### 3.1 Statistiques générales

```cypher
// Distribution par juridiction
MATCH (e:Entity)
RETURN e.jurisdiction AS jurisdiction, count(*) AS total
ORDER BY total DESC
LIMIT 15
```

**Question** : Quelle juridiction a le plus d'entités offshore ?

### 3.2 Les intermédiaires les plus actifs

```cypher
// Top 10 des cabinets juridiques
MATCH (i:Intermediary)-[:intermediary_of]->(e:Entity)
RETURN i.name AS intermediary, count(e) AS entities
ORDER BY entities DESC
LIMIT 10
```

**Question** : Combien d'entités Mossack Fonseca a-t-il créées ?

### 3.3 Rechercher une personne

```cypher
// Chercher toutes les personnes nommées "SMITH"
MATCH (o:Officer)
WHERE o.name CONTAINS "SMITH"
RETURN o.name, o.countries, o.sourceID
LIMIT 20
```

> ⚠️ **Note** : La présence d'une personne ne signifie pas qu'elle est impliquée dans des activités illégales.

### 3.4 Rechercher une société

```cypher
// Chercher les sociétés avec "SHELL" dans le nom
MATCH (e:Entity)
WHERE e.name CONTAINS "SHELL"
RETURN e.name, e.jurisdiction, e.incorporation_date
LIMIT 20
```

---

## 🔗 Partie 4 : Analyse des connexions

### 4.1 Connexions directes d'une personne

```cypher
// Trouver toutes les sociétés liées à un officier
MATCH (o:Officer)-[r:officer_of]->(e:Entity)
WHERE o.name CONTAINS "ANDREW CHARLES WILLIAMS"
RETURN o.name, type(r), r.link AS role, e.name, e.jurisdiction
```

### 4.2 Réseau étendu (2 niveaux)

```cypher
// Explorer le réseau d'un officer (jusqu'à 2 niveaux)
MATCH path = (o:Officer)-[*1..2]-(connected)
WHERE o.name CONTAINS "ANDREW CHARLES WILLIAMS"
RETURN path
LIMIT 50
```

### 4.3 Connexions via un intermédiaire

```cypher
// Qui est connecté via Mossack Fonseca ?
MATCH (o:Officer)-[:officer_of]->(e:Entity)<-[:intermediary_of]-(i:Intermediary)
WHERE i.name CONTAINS "MOSSACK FONSECA"
RETURN o.name AS officer, e.name AS entity
LIMIT 30
```

---

## 🚨 Partie 5 : Détection de patterns suspects

### 5.1 Pattern : Officers multiples sociétés

Une personne dirigeant des dizaines de sociétés est potentiellement un prête-nom.

```cypher
// Personnes avec plus de 50 sociétés
MATCH (o:Officer)-[:officer_of]->(e:Entity)
WITH o, count(e) AS nb_entities
WHERE nb_entities > 50
RETURN o.name, nb_entities
ORDER BY nb_entities DESC
LIMIT 20
```

**Question** : Qui a le plus de sociétés ?

### 5.2 Pattern : Même adresse

Plusieurs sociétés à la même adresse = sociétés écrans.

```cypher
// Adresses avec plus de 100 sociétés
MATCH (e:Entity)-[:registered_address]->(a:Address)
WITH a, collect(e.name) AS entities, count(e) AS nb
WHERE nb > 100
RETURN a.address, nb, entities[0..5] AS sample_entities
ORDER BY nb DESC
LIMIT 10
```

### 5.3 Pattern : Connexions cachées

Trouver le lien entre deux personnes via le réseau offshore.

```cypher
// Plus court chemin entre deux officers
MATCH (o1:Officer), (o2:Officer)
WHERE o1.name CONTAINS "SMITH" AND o2.name CONTAINS "ANDREW CHARLES WILLIAMS"
MATCH path = shortestPath((o1)-[*..6]-(o2))
RETURN path
```

---

## 🧪 Partie 7 : Exercices pratiques

### Exercice 1 : Les sociétés d'un pays

Trouvez toutes les entités offshore dont les officers viennent de France.

<details>
<summary>💡 Solution</summary>

```cypher
MATCH (o:Officer)-[:officer_of]->(e:Entity)
WHERE o.countries CONTAINS "France"
RETURN DISTINCT e.name, e.jurisdiction, o.name
LIMIT 50
```

</details>

### Exercice 2 : Le réseau d'un cabinet

Visualisez le réseau complet d'Appleby (cabinet des Paradise Papers).

<details>
<summary>💡 Solution</summary>

```cypher
MATCH path = (i:Intermediary)-[:intermediary_of]->(e:Entity)<-[:officer_of]-(o:Officer)
WHERE i.name CONTAINS "APPLEBY"
RETURN path
LIMIT 100
```

</details>

### Exercice 3 : Connexion entre deux juridictions

Y a-t-il des sociétés au Panama liées à des officers des îles Cayman ?

<details>
<summary>💡 Solution</summary>

```cypher
MATCH (o:Officer)-[:officer_of]->(e:Entity)
WHERE e.jurisdiction_description = "Panama" AND o.countries CONTAINS "Cayman"
RETURN o.name, e.name
LIMIT 30
```

</details>

### Exercice 4 : Évolution temporelle

Quand les sociétés offshore au Panama ont-elles été créées ? (distribution par année)

<details>
<summary>💡 Solution</summary>

```cypher
MATCH (e:Entity)
WHERE e.jurisdiction_description = "Panama" AND e.incorporation_date IS NOT NULL
RETURN substring(e.incorporation_date, 7, 4) AS year, count(*) AS total
ORDER BY year
```

</details>

---

## 📊 Partie 8 : Requêtes avancées

### 8.1 Centralité : Qui est le plus connecté ?

```cypher
// Top officers par nombre de connexions
MATCH (o:Officer)
WITH o, size((o)--()) AS connections
WHERE connections > 100
RETURN o.name, connections
ORDER BY connections DESC
LIMIT 20
```

### 8.2 Distribution des rôles

```cypher
// Types de rôles officer_of
MATCH (o:Officer)-[r:officer_of]->(e:Entity)
RETURN r.link AS role, count(*) AS total
ORDER BY total DESC
LIMIT 15
```

### 8.3 Réseau géographique

```cypher
// Flux entre pays (officers) et juridictions (entités)
MATCH (o:Officer)-[:officer_of]->(e:Entity)
WHERE o.countries IS NOT NULL AND e.jurisdiction IS NOT NULL
RETURN o.countries AS from_country, e.jurisdiction AS to_jurisdiction, count(*) AS connections
ORDER BY connections DESC
LIMIT 20
```

---

## 🛑 Partie 9 : Nettoyage

### 9.1 Arrêter l'environnement

```bash
# Depuis le dossier tp-neo4j
docker compose down

# Pour supprimer aussi les données (libérer l'espace)
docker compose down -v
```

### 9.2 Espace disque

Le dump ICIJ utilise environ 2 Go. Pour libérer l'espace :

```bash
rm import/icij-offshoreleaks-5.13.0.dump
```

---

## 📚 Pour aller plus loin

- **ICIJ Offshore Leaks Database** : https://offshoreleaks.icij.org
- **GitHub ICIJ** : https://github.com/ICIJ/offshoreleaks-data-packages
- **Documentation Cypher** : https://neo4j.com/docs/cypher-manual/
- **Neo4j GraphAcademy** (cours gratuits) : https://graphacademy.neo4j.com
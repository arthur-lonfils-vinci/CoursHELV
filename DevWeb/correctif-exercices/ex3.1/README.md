# Documentation de mon API

Voici les opérations offertes par mon API.

## API de films

| URI | Méthode HTTP | Auths? | Opération |
|---|---|---|---|
| **`films`** | GET | Non | READ ALL : Lire toutes les ressources de la collection |
| **`films/:id`** | GET | Non | READ ALL : Lire une ressource de la collection |
| **`films`** | POST | Oui | CREATE ONE : Créer une ressource de la collection basée sur un body au format `{title: string; director: string;  duration: number;  budget?: number;  description?: string;  imageUrl?: string;  }` |
| **`films/:id`** | DELETE | Oui | DELETE ONE : Effacer une ressource de la collection |
| **`films/:id`** | PATCH | Oui | UPDATE ONE : Modifier une ressource de la collection basée sur un body au format `{title?: string; director?: string;  duration?: number;  budget?: number;  description?: string;  imageUrl?: string;  }` en devant fournir au moins une propriété valide|
| **`films/:id`** | PUT | Oui | UPDATE ONE or CREATE ONE : Modifier une ressource de la collection si l'id existe, sinon créer une nouvelle ressource. Le format du body d'une requête `{title: string; director: string;  duration: number;  budget?: number;  description?: string;  imageUrl?: string;  }` |



## API d'authentification
| URI | Méthode HTTP | Auths? | Opération |
|---|---|---|---|
| **`auths/login`** | POST | Non | Permettre à un utilisateur de s'authentifier en sur base d'une ressource au format `{username: string; password: string;}`. Une fois l'utilisateur authentifié, celui-ci récupère son token et son username (format `AuthenticatedUser`) |
| **`auths/register`** | POST | Non | Créer un nouvel user et l'authentifier sur base d'une ressource au format `{username: string; password: string;}`. Une fois l'utilisateur créé et authentifié, celui-ci récupère son token et son username (format `AuthenticatedUser`) |

## API de commentaires
| URI | Méthode HTTP | Auths? | Opération |
|---|---|---|---|
| **`comments?film=filmId`** | GET | JWT | READ ALL FILTERED : Lire toutes les ressources de la collection filtrée sur base d'un `film` |
| **`comments`** | POST | JWT | CREATE ONE : Créer une ressource basée sur un body au format `{filmId: number; comment:string; }` ; le `username` est sécurisé via le token. |
| **`comments/films/:filmId`** | DELETE | JWT | DELETE ONE : Effacer le commentaire d'un utilisateur ou d'une utilisatrice sur un film sur base du  `filmId` se trouvant dans l'URL et du `username` sécurisé dans le token. |


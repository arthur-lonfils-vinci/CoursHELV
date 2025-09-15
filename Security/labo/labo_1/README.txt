Les fichiers ipynb peuvent être lu avec jupyter-notebook.

Vous pouvez installer et lancer jupyter avec les commandes suivantes :
Si vous avez les droits root sur la machine :
    $ sudo apt-get update
    $ sudo apt-get install jupyter-notebook

Sinon, vous pouvez utiliser pip, le gestionnaire de paquet python, avec
l'argument --user :
    $ pip3 install --user jupyter

Pour lancer jupyter, dans le dossier où vous comptez travailler :
$ jupyter notebook

Cela ouvrira une nouvelle fenêtre dans votre navigateur vous permettant de
lire et modifier les fichiers .ipynb.

Il est également possible d'utiliser la fonction d'upload de jupyter pour
envoyer un fichier depuis le navigateur. Enfin, il est également possible de
créer un notebook vide via le bouton "+" puis "Python 3 (ipykernel)".

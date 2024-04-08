# Compte Rendu du TP 6 : Patrons de Conceptions

Noms des étudiants du binôme : Pierre Grange - Omar Mattar

## Exercice 1
Ce modèle reflète le patron de conception Composite.
L'interface MobileObject agit comme la composante abstraite dans le patron. Cette interface définit des opérations qui sont communes aux composants simples et aux composites, ici, elle définit les opérations getVelocity et getMass.
La classe Vehicle représente la partie composite qui peut contenir des éléments enfants. La relation 0..* indique qu'un Vehicle peut contenir zéro ou plusieurs éléments de type MobileObject, y compris potentiellement d'autres Vehicle ou des implémentations simples de MobileObject. Elle implémente les opérations définies dans l'interface MobileObject.

Il n'y a pas besoin de réécrire les méthodes getVelocity ou getMass pour la nouvelle classe, car cette dernière hérite de Bike et donc de Vehicle. On peut donc utiliser les méthodes initiales qui fonctionnent de la même manière pour notre TalAlongBike.

## Exercice 2
Le patron de conception itérateur est utilisé la méthode getVelocity() pour parcourir les composants d'un véhicule.
Ce patron permet ici de calculer une propriété qui dépend de tous les éléments de la collection. Il permet également de facilement changer la structure de données des composants sans avoir à changer la logique de parcours dans getVelocity().
En modifiant le Set en List, nous n'avons pas non plus besoin de modifier la méthode getVelocity, car l'utilisation du pattern Itérateur nous permet d'accéder aux composants peu importe leur organisation dans la classe Vehicle.

## Exercice 3
Pour réaliser ce patron de conception Singleton, on a créé un constructeur privé pour s'assurer que les classes extérieures ne puissent pas créer de Clock. Ensuite, nous avons ajouté une instance de Clock dans la classe elle-même, ainsi qu'une méthode get pour y accéder.
La classe Wheel est également modifiée, car elle ne peut plus instancier la Clock désormais. Elle utilise maintenant le getter afin d'utiliser les fonctions de la Clock.

## Exercice 4
Les fichiers Bike.java et Wheel.java sont localisés dans les dossiers cycling et transport respectivement, par conséquent, ils n'appartiennent pas au même paquetage. Il existe une dépendance cycliste entre les deux classes ... Les dépendances cycliques sont généralement à éviter, car elles peuvent rendre le code plus difficile à comprendre et peut entraîner des problèmes de maintenance et de test du code.

La classe Wheel utilise la fonctionnalité getPush() pour récupérer la force exercée sur le Bike afin de calculer son accélération et du coup sa vitesse. Il y a une abstraction de la classe Bike qui est la classe abstraite Vehicle et qui isole la fonction getPush(). Cette abstraction est localisée dans le même paquetage de la classe Wheel.

Pour casser cette dépendance cyclique, on va changer le type de référence de l'attribut drive de Bike a Vehicle dans la classe Wheel et on va enlever la ligne ' import fr.polytech.sim.cycling.Bike; ' de la classe Wheel.

## Exercice 5
On a simplement remonté le code redondant dans la classe mère NamedLogger en ajoutant un attribut public afin d'accéder au message.
Les deux classes ConsoleLogger et FileLogger peuvent maintenant appeler la méthode super pour obtenir un message dans leur attribut hérité.

## Exercice 6

## Exercice 7

## Exercice 8

## Exercice 9


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


## Exercice 4

## Exercice 5

## Exercice 6

## Exercice 7

## Exercice 8

## Exercice 9



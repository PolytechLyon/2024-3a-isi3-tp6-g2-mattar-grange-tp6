# Compte Rendu du TP 6 : Patrons de Conceptions

Noms des étudiants du binôme : Pierre Grange - Omar Mattar

## Exercice 1
Ce modèle reflète le patron de conception Composite.
L'interface MobileObject agit comme la composante abstraite dans le patron. Cette interface définit des opérations qui sont communes aux composants simples et aux composites, ici, elle définit les opérations getVelocity et getMass.
La classe Vehicle représente la partie composite qui peut contenir des éléments enfants. La relation 0..* indique qu'un Vehicle peut contenir zéro ou plusieurs éléments de type MobileObject, y compris potentiellement d'autres Vehicle ou des implémentations simples de MobileObject. Elle implémente les opérations définies dans l'interface MobileObject.

```java
public class TagAlongBike extends Bike {
    public TagAlongBike() {
        components.add(new Wheel(this));
        components.add(new Wheel(this));
        components.add(new SimpleBike());
    }
}
```

Il n'y a pas besoin de réécrire les méthodes getVelocity ou getMass pour la nouvelle classe, car cette dernière hérite de Bike et donc de Vehicle. On peut donc utiliser les méthodes initiales qui fonctionnent de la même manière pour notre TalAlongBike.

## Exercice 2
Le patron de conception itérateur est utilisé la méthode getVelocity() pour parcourir les composants d'un véhicule.
Ce patron permet ici de calculer une propriété qui dépend de tous les éléments de la collection. Il permet également de facilement changer la structure de données des composants sans avoir à changer la logique de parcours dans getVelocity().
```java
protected final List<MobileObject> components = new ArrayList<>();
```
En modifiant le Set en List, nous n'avons pas non plus besoin de modifier la méthode getVelocity, car l'utilisation du pattern Itérateur nous permet d'accéder aux composants peu importe leur organisation dans la classe Vehicle.

## Exercice 3
Pour réaliser ce patron de conception Singleton, on a créé un constructeur privé pour s'assurer que les classes extérieures ne puissent pas créer de Clock. Ensuite, nous avons ajouté une instance de Clock dans la classe elle-même, ainsi qu'une méthode get pour y accéder.
La classe Wheel est également modifiée, car elle ne peut plus instancier la Clock désormais. Elle utilise maintenant le getter afin d'utiliser les fonctions de la Clock.
    
```java
public class Clock {
private Clock() {}

    private static final Clock clock = new Clock();

    public static Clock getClockInstance() {
        return clock;
    }

    private final int time = new Random().nextInt(25);
    
    public int getTime() {
        return this.time;
    }
}
```

## Exercice 4
Les fichiers Bike.java et Wheel.java sont localisés dans les dossiers cycling et transport respectivement, par conséquent, ils n'appartiennent pas au même paquetage. Il existe une dépendance cycliste entre les deux classes ... Les dépendances cycliques sont généralement à éviter, car elles peuvent rendre le code plus difficile à comprendre et peut entraîner des problèmes de maintenance et de test du code.

La classe Wheel utilise la fonctionnalité getPush() pour récupérer la force exercée sur le Bike afin de calculer son accélération et du coup sa vitesse. Il y a une abstraction de la classe Bike qui est la classe abstraite Vehicle et qui isole la fonction getPush(). Cette abstraction est localisée dans le même paquetage de la classe Wheel.

Pour casser cette dépendance cyclique, on va changer le type de référence de l'attribut drive de Bike a Vehicle dans la classe Wheel et on va enlever la ligne ' import fr.polytech.sim.cycling.Bike; ' de la classe Wheel.
    
```java
public class Wheel implements MobileObject {
private static final double DEFAULT_MASSE = 10;

    private final Logger logger = LoggerFactory.CreateLogger("Wheel");
    private final Clock clock = Clock.getClockInstance();
    private final Vehicle drive;
    
    //Reste du code
}
 ```   

## Exercice 5
On a simplement remonté le code redondant dans la classe mère NamedLogger en ajoutant un attribut public afin d'accéder au message.
Les deux classes ConsoleLogger et FileLogger peuvent maintenant appeler la méthode super pour obtenir un message dans leur attribut hérité.

```java
public class NamedLogger implements Logger {
    
    public void log(String format, Object... args) {
        String entry = String.format(format, args);
        this.message = String.format("%s\t%s\n", this.name, entry);
    }
}
```
```java
public class ConsoleLogger extends NamedLogger {
    
    public void log(String format, Object... args) {
        super.log(format, args);
        System.out.print(message);
    }
}
```
```java
public class FileLogger extends NamedLogger {

    synchronized public void log(String format, Object... args) {
        super.log(format, args);
        try (FileWriter fileWriter = new FileWriter(FILE_NAME, true)) {
            fileWriter.write(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```

## Exercice 6
L'interface LoggerFactory permet de définir si l'on utilise un ConsoleLogger ou un FileLogger. Nous n'avons plus le choix et toutes les classes Wheel, BikeSimulator et Vehicle doivent utiliser le logger que nous définissons dans la factory.
Cela centralise efficacement la création des loggers.

```java
public class LoggerFactory {
    private LoggerFactory() {}
    
    public static Logger CreateLogger(String name)    {
        return new ConsoleLogger(name);
    }
}
```
Pour utiliser la factory, on remplace simplement les appels aux constructeurs des loggers par un appel à la méthode CreateLogger() de la factory.
```java
Logger logger = LoggerFactory.CreateLogger("BikeSimulator");
```

La différence avec le patron de conception singleton est que cette methode ne garanti pas qu'il n'y ai qu'une seule instance de la classe Logger.

## Exercice 7
Pour appliquer le patron de conception décorateur à notre code, nous avons créé une classe abstraite LoggerDecorator qui implémente l'interface Logger. Cette classe abstraite contient un attribut logger de type Logger. La classe LoggerDecorator permet aux décorateurs concrets d'appeller simplement le méthode log() de logger, mais ajoutent une fonctionnalité supplémentaire, ici, le decorateur concret TimestampedLoggerDecorator ajoute l'heure au message.
```java
public abstract class LoggerDecorator implements Logger {
    protected Logger logger;
}
```
```java
public class TimestampedLoggerDecorator extends LoggerDecorator {


    public TimestampedLoggerDecorator(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void log(String format, Object... args) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SS"));
        System.out.print(timestamp+"\t");
        logger.log(format, args);
    }
}
```
Pour utiliser le décorateur, on crée un logger de type FileLogger ou ConsoleLogger dans le LoggerFactory, puis on le passe en paramètre au constructeur du décorateur.
```java
public static Logger CreateLogger(String name)    {

        return new TimestampedLoggerDecorator(new ConsoleLogger(name));
    }
```
## Exercice 8
La classe Context suit le patron de conception Façade en fournissant une interface simplifiée pour l'outil ServiceLoader de Java.
En particulier, la classe Context fournit les méthodes inject() et injectAll(), qui simplifient l'utilisation de ServiceLoader pour charger des implémentations de classes spécifiques.

Dans la classe BikeSimulator, on utilise la méthode inject() de la classe Context pour injecter un objet de type Bike dans la simulation, au lieu de l'instancier avec le mot clef new.
```java
public class BikeSimulator implements Simulation {
    private final Logger logger = LoggerFactory.CreateLogger("BikeSimulator");

    public void run() {
        Bike bike = Context.inject(Bike.class);
        //Reste du code
    }
}
```
Pour changer la classe injectée de SimpleBike à TagAlongBike, on modifie simplement le fichier Bike dans le dossier src/main/resources/META-INF/services en remplaçant fr.polytech.sim.cycling.SimpleBike par fr.polytech.sim.cycling.TagAlongBike.

On peut avoir plusieurs lignes dans le fichier fr.polytech.sim.cycling.Bike. Chaque ligne correspond à une implémentation de l'interface Bike qui peut être injectée par le contexte. Chaque ligne correspond à une implémentation de l'interface Bike qui peut être injectée par le contexte.
Cependant, il est important de noter que la méthode Context.inject(Class<T> klass) ne retourne que la première instance trouvée par ServiceLoader. Si on veut utiliser toutes les implémentations de Bike mentionnées dans le fichier, on devra utiliser la méthode Context.injectAll(Class<T> klass) une fois qu'elle sera implémentée.

## Exercice 9
La classe Context suit le patron de conception Itérateur. En effet, la méthode injectAll() de la classe Context permet de parcourir toutes les implémentations de Bike mentionnées dans le fichier src/main/resources/META-INF/services/fr.polytech.sim.cycling.Bike.

On va implenter la méthode injectAll() dans la classe Context pour qu'elle retourne un itérateur sur toutes les implémentations de Bike mentionnées dans le fichier.
```java
public static <T> Iterator<T> injectAll(Class<T> klass) {
        return ServiceLoader.load(klass).iterator();
    }
```
et on va utiliser cette méthode dans la classe BikeSimulator pour parcourir toutes les implémentations de Bike.
```java
public void run() {
       Iterator<Bike> bikes = Context.injectAll(Bike.class);
        Bike bike;

        while (bikes.hasNext()) {
            bike = bikes.next();
            this.logger.log("Bike's speed %.2f Km/h.", bike.getVelocity());
            this.logger.log("Bike's mass %.2f Kg.", bike.getMass());
        }
}
```
Enfin, on va ajouter plusieurs implémentation différents de Bike dans le fichier src/main/resources/META-INF/services/fr.polytech.sim.cycling.Bike pour tester la méthode injectAll().
```text
fr.polytech.sim.cycling.TagAlongBike
fr.polytech.sim.cycling.SimpleBike
```
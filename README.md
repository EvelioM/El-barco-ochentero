# El barco ochentero

La aplicación más ochentera para el barco más ochentero.

## Introducción

El barco ochentero es un crucero tematica diseñado para hacer volver a sus tripulantes a una epoca donde todo era más colorido, los pantalones eran de campana y los peinados voluptuosos.

Por esto vamos a desarrollar una aplicación que complemente esta experiencia ofreciendo funcionalidad util a cualquier marinero ochentero que siga el rumbo de este barco.

## Diseño y desarrollo de la aplicación

La aplicación se desarrolla de forma que una vez se autentifica el usuario accede a un menú, con el cual puede interactuar a través de un *navigation drawer*. En este menú de inicio puede acceder a ciertas funcionalidades como son:

+ **Información** acerca del barco ochentero.
+ **Videos** del barco ochentero.
+ **Un listado de contactos** que se encuentran en el movil.
+ **Un juego** con controles giroscópicos en el que manejas el barco ochentero y esquivas enemigos ochenteros como a naranjito o bolas de discoteca.
+ Funcionalidad para **echar fotos** desde el móvil.

La mayor parte del desarrollo se ha usado en el juego y por tanto es la sección que aglutina más código y más funcionalidad, estando gran parte de las competencias realacionadas con este. Es fácil darse cuenta de la magnitud de esta sección cuando vemos que el paquete **game** tiene otros como **views**, **util** o **data**, mientras que el resto de paquetes del proyecto solamente albergan clases.

En el diseño central del menú hemos usado *fragments* para poder tener siempre el *navigation drawer* y poder seguir moviendonos a través de la aplicación. Esto no ocurre en el juego, donde queremos dedicar todos los recursos a este, tampoco en las preferencias ni en la camara, por supuesto.

## Problemas encontrados y solución a los mismos

A lo largo del desarrollo de la práctica nos hemos ido encontrando con una serie de problemas.

### Control de versiones

Durante el proyecto hemos usado Github apoyado por Android Studio para el control de versiones de forma que se puedan trabajar funcionalidades distintas en ramas sin alterar otras. De este modo creamos la rama **eve/feat/game** para trabajar en el desarrollo del juego. EL problema cuando al hacer un *merge* y cerrar la rama no salimos de esta en Android Studio por lo que cada *commit* la reabría.

Esto es de esperar ya que no tenemos mucha experiencia con el control de versiones y ninguna usandolo a través de Android Studio.

Al final tuvimos que hacer otro *merge* y cerrar la rama y luego otro *merge* de Android Studio con la rama **master** remota. Después de esto abandonamos la idea de usar ramas y continuamos el desarrollo en **master**.

### Gradle

Otro problema recurrente fue el control de dependencias a través de Gradle ya que creando distintos proyectos los gradles nos generaban incompativilidades en las versiones. La solución consistió en añadir el gradle a git. Aún así, este problema nunca desapareció del todo y al incluir librerías tendríamos que trastear un poco el gradle.

### Gestos

En la aplicación añadimos el gesto de arrastrar hacia la derecha para exponer el *navigation drawer*, esto dio problemas a la hora de trabajar con fragmentos ya que perdían el efecto del *onTouchListener* y con esto el gesto. La solución fue enviarlo a través del constructor. 

Esto, sin embargo, fue imposible al intentar usarlo con un *ScrollView* ya que si le añadimos el *onTouchListener* perdemos la funcionalidad principal de *scroll*. Este problema no se ha podido resolver.

### Gestión de memoria y contexto

Otra fuente de fallos en la aplicación fue el manejo de memoria debido al gran tamaño de las imagenes del juego y la reducida memoria del móvil en el que se desarrolla la aplicación. 

La solución consiste en crear una clase con constantes de modo que solamente haya una imagen para el enemigo del juego y todos los enemigos usen la misma. 

Del mismo modo, en clases del juego que no son la actividad pueden necesitar acceder al contexo para acceder a recursos entre otras cosas, para permitir esto se incluye el contexto entre las constantes.

## Puntos fuertes y débiles de mi aplicación

La aplicación es robusta y no falla, además estéticamente no es desagradable. Tiene las funcionalidades que debe tener aunque lo cierto es que la coherencia con el tema, en ese caso los ochenta, podría ser mayor. 

Realmente la única sección estilísticamente apropiada es la del juego, que desde nuestro punto de vista es lo más hortera que hemos podido hacer, y algo que no sabemos muy bien si es un punto fuerte o débil de nuestra aplicación.

Otro punto débil de la aplicación es la falta de modularidad y el alto acoplamiento de las clases que hemos ido haciendo. Las actividades tienen mucha lógica operativa, falta mucha separación de tareas. Esto haría tener que mejorar la aplicación más difícil de lo que debiera. 

## Conclusiones y vías futuras

Tenemos, en definitiva, una aplicación que dado el alcance de la tarea es bastante correcta en contenido.

Se podría ampliar el juego ya que fue desarrollado en poco tiempo y con poco esfuerzo en cuanto a la dificultad y lo interesante que pudiera ser el desafío. Por ejemplo, podríamos poner varios obstaculos a la vez de modo que tengamos que colar el barco por un pasillo estrecho de bolas de discoteca. O hacer que los enemigos vayan cada vez más rápido o tengan más patrones de movimiento.

## Webgrafía

+ [Guía de AndroidAuthority sobre el desarrollo de juegos para Android.](https://www.androidauthority.com/android-game-java-785331/)

+ [Apuntes de Android](https://cv.ucam.edu)


+ [Android Developer Reference](https://developer.android.com/reference)

## ANEXO: Autoevaluación

| Competencia | Uso |
| --- | --- |
|Tipo de layouts utilizados | Se utiliza siempre ConstraintLayout ya que permite tener menos niveles en el *xml* lo que hace sea más fácil cargar los layouts |
|Tipo de controles utilizados | Se utilizan controles como *EditText* o *Button* |
| Actividades utilizadas | Se han utilizado muchas actividades para la aplicación |
| Intenciones (Implícitas) | Se usa para abrir la camara y echar fotos |
| Intenciones (Explícitas) | Se usan para cambiar de una actividad a otra. En el juego tenemos una intención explícita con respuesta |
| Fragments | El navigation drawer cambia los fragments sobre un contenedor |
| Shared preferences | La aplicación cuenta con una sección de preferencias y las usa para elegir si hay música o no en el juego |
| Ficheros | Podemos guardar en un fichero contactos de los que tomamos del Content Provider |
| BBDD | Se ha usado una base de datos SQLite para el control de usuarios en *login* y *registro* permamente |
| Contents Providers | A través de Content Providers podemos obtener telefonos del movil en el menú |
| Uso de pantalla | Se puede desplegar el *navigation drawer* moviendo el dedo hacia la derecha en la pantalla |
| Sensores | Se han usado acelerómetros y magnómetros para obtener la orientación del móvil en el juego del barco |
| Toasts | Se usan *toast* a la hora de registrarse para dar la bienvenida al nuevo usuario |
| Notificaciones para la barra de tareas | Se genera una notificación cuando se gana o pierde en el juego |
| Action bar | La aplicación tiene *action bar* en todo momento salvo en el juego |
| Navigation drawer | Una vez accedes a la aplicación tienes un *navigation drawer* |
| Iconos personalizados | El adaptive icon de la aplicación se ha realizado con imagenes vectoriales |
| Audio | Con la opción de musica activada en preferencias hay música en el juego |
| Video | Hay un fragment con videos de *raw* |
| Sensible a tipos de pantalla | Se usa ConstraintLayout de modo que se ajustará bien a distintos tamaños |
| Sensible a rotaciones de pantalla | En las actividades de *login* y *registro* |
| Sensible a cambios de idioma | Si cambiamos el *locale* entre inglés y español podemos cambiar el idioma en la pantalla de inicio |
| Otras adiciones a la aplicación | Se ha desarrollado un juego con controles giroscópicos donde se maneja el barco para evitar obstáculos y enemigos |

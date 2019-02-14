# MyPrototipe1.2 :computer:

## Práctica de aplicación


## 1. Revisión de los [Documentos de Proyecto](https://moodle.iescierva.net/mod/folder/view.php?id=26072) para conocer las características establecidas para el proyecto "JVida" en su tercera iteración.
- Corresponde al tercer prototipo en el que se va a abordar una mejor implementación del modelo de datos con nuevas clases relacionadas con la parte de <b>"Gestion de Simulacion"</b> y mejoras en la arquitectura de aplicación en la capa de datos y la capa de presentación.

## 2. Preparación del entorno de trabajo
- Importa, si es necesario como se hizo en la práctica anterior,

- Sincroniza el repositorio maestro local del proyecto JV2018 desde el repositorio remoto del proyecto: https://github.com/PyED2018/Proyecto.git

- A partir de la replica maestra local, copia el proyecto con el explorador de paquetes de eclipse.

- Asigna un nombre de trabajo adecuado para el prototipo1.2

## 3. Mejora de características del almacén de datos.

- Modificar la clase <b>Datos</b> para utilizar ArrayList en lugar de los arrays y adaptar todas las llamadas de acceso a datos según el cambio realizado.

- En el almacén de datos se deben establecer mecanismos de almacenamiento y ordenación basados en identificadores únicos que no produzcan problemas de repetición de datos y sean a su vez suficientemente flexibles.

    - El almacenamiento, en todas las estructuras de datos, se resolverá insertando -el correspondiente objeto- en la posición adecuada para que los datos siempre permanezcan ordenados. Para ello se puede seguir la siguiente estrategia en el proceso de alta: 
      - Comprobar que no existe ningún objeto almacenado con el nuevo identificador.
      - Obtener la posición que le correspondería al nuevo objeto dentro de la estructura si estuviese almacenado.
      - Insertar el nuevo objeto en la posición obtenida.
      
    - El reto consiste en la implementación de un algoritmo de  búsqueda binaria. que proporcione la posición que ocupa (valor positivo), o la posición que ocuparía (valor negativo) ; un objeto según un identificador único.
 - Crear una tabla de equivalencias entre <b>nif</b>, <b>correo</b> y el <b>idUsr</b> realmente utilizado en las búsquedas; esto permitirá la utilización del nif o la dirección de correo como  identificación admitida en el inicio de sesión. 
    - Esto se puede resolver con un diccionario o mapa que asocie parejas clave-valor, basado en una tabla hash. 
    
## 4. Implantación de nuevas clases del modelo.
- Incorporar en el paquete .modelo las nuevas clases identificadas en el diagrama de clase UML correspondiente al modelo 1.2. Ver: Documentos del proyecto.La clase Mundo está definida por el espacio y las leyes que se utilizan en una simulación.

- El espacio tiene una doble representación una explicita con las celdas ocupadas (valor 1) y vacias (valor 0). La otra representación, compacta consiste en una lista de celdas ocupadas, referenciadas por objetos de la clase <b>Posicion</b>.

- Las constantes que determinan las leyes de actualización de cada celda están representadas con un mapa de pares <b>tipoConstante, array de valores </b>.  

- Se deben definir los constructores habituales y métodos de acceso con la adecuada validación de datos.

- Se debe redefinir adecuadamente los métodos <b>clone(), hashCode(), equals() y toString()</b>.

## 5. Validación de datos con asertos y tratamiento de errores con excepciones.

Hay que completar los siguientes detalles:

- Añadir excepciones para el tratamiento de errores en los paquetes:  <b>.modelo, .accesoDatos, .accesoUsr</b>

    - <b>ModeloException
    - DatosException
    - AccesoUsrException</b>
- Generalizar el uso de assertos y excepciones en las verificaciones de las clases del modelo.

## 6. Pruebas básicas de las clases del modelo 2.

Hay que completar los siguientes detalles:

- Crear un nuevo sub-paquete del proyecto que se llame test.modelo, donde se ubicarán una clase de prueba asociada a cada una de las clases del modelo según el formato de nombre <b>xxxxxxTest</b>   (xxxxxx es el nombre de la clase probada). 

- Dentro de cada clase <b>xxxxxxTest</b>,preparar métodos especializados para las pruebas de toda la funcionalidad de la correspondiente clase  utilizando JUnit5.:

  - <b>testSet...()
  - testGet...()
  - test...Constructores()
  - testToString()
  - test...()</b>
  
- Dentro del sub-paquete modelo.test, crear las clases <b>...test</b> para cada clase del <b>.modelo</b>, de <b>.accesoDatos</b>, <b>.accesoUsr</b> y <b>.util</b>.

## 7. Entrega del código fuente.

Una vez completados todos los ejercicios y apartados entrega el código fuente del prototipo1.2 y los ejercicios de práctica básica, teniendo en cuenta que:

- Conviene hacer una revisión del código fuente de todas las clases del prototipo1.2 y de los ejercicios de práctica básica realizados, comprobando convenciones de escritura de código fuente Java.

- Hay que comprobar que la documentación interna del código fuente está actualizada y es correcta. 

- Sólo hay que entregar código fuente. No el resto del proyecto de Eclipse.

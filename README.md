# users
Microservicio responsable del manejo de datos de los buddies, familiares y adultos mayores

# Desarrollo

## Local

Asegurarse de tener un archivo .env en la raíz del proyecto, clonado a partir del env.example.

Dar permisos de ejecución al script, con

```chomod +x run_dev.sh```

y luego ejecutarlo

```./run_dev.sh```

El mismo se encargará de limpiar los containers e imágenes del servicio luego de interrumpirlo.

Una vez que estén corriendo los containers, acceder al swagger desde `localhost:8086/docs`

Cada cambio que hagamos en el código, debemos volver a correr `./run_dev.sh`
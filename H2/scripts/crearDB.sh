#!/bin/bash

# Verificar que se proporcionó un nombre de base de datos
if [ -z "$1" ]; then
  echo "Error: Proporcione un nombre para la base de datos."
  exit 1
fi

# Nombre de la base de datos proporcionado por el usuario
DB_NAME="$1"

# Ruta proporcionada por el usuario
#Cambiar ruta a la deseada. Atencion, en esta ruta debe estar el h2.jar
DB_PATH="/home/francopaco/ejemplo_db/"

# Verificar si existe la base de datos en la ruta proporcionada
if [ -d "$DB_PATH" ]; then
  # Verificar si hay archivos .h2.db en el directorio (indicando una base de datos H2)
  if ls "$DB_PATH/$DB_NAME*.h2.db" 1> /dev/null 2>&1; then
    echo "La base de datos H2 '$DB_NAME' ya existe en la ruta proporcionada."
  else
    echo "La ruta proporcionada existe, pero no parece contener una base de datos H2 con el nombre '$DB_NAME'. Creando la base de datos..."

    # Crear la base de datos
    #Ajustar ruta a la deseada.
    java -cp "/home/francopaco/ejemplo_db/h2-2.2.224.jar" org.h2.tools.Server -ifNotExists -baseDir "$DB_PATH"
  fi
else
  echo "La ruta proporcionada no existe. Por favor, proporcione una ruta válida."
fi

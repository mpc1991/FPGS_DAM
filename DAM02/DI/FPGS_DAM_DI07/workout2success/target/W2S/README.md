# workout2success v2.0
#### Macià Porcel Cifre -- DI04
###
> [!NOTE]
> Interaccion
#####  * De por si la aplicación actualmente no presenta lentitud

> [!NOTE]
> Metaforas
#####  * jPanelHome --> Los botones ahora usan iconos además del texto.

> [!NOTE]
> Affordance (un objeto debe verse clickable, un campo de texto debe verse editable)
#####  * jPanelHome --> Los botones ahora cambian de color al pulsarse
#####  * jPanelHomeUsers --> Ahora la lista de usuarios cambia de color al realizar mouseEntered & mouseExited y al pulsarla.
#####  * jPanelHomeUsers --> Ahora la lista de workouts cambia de color al realizar mouseEntered & mouseExited y al pulsarla.
#####  * jPanelHomeUsers --> Ahora la lista de ejercicios cambia de color al realizar mouseEntered & mouseExited y al pulsarla.
#####  * jPanelHomeUsers --> Ahora los botones de "add", "change" y "create" cambian de color al realizar mouseEntered & mouseExited y al pulsarlos.

> [!NOTE]
> Visibility
#####  * jPanelHome --> Ahora usa botones grandes en vez de los pequeños y rectangulares.
#####  * jPanelHome --> jPanelOptions ahora muestra iconos grandes con metáforas para facilitar la interacción visual.
#####  * jPanelHomeUsers --> Se han quitado tablas dejando solo las importantes, haciendolo más ligero visualmente.
#####  * jPanelHomeUsers --> Ahora la lista de texto plano se ha modificado por botones más grandes y aplicado Affordance.

> [!NOTE]
> Feedback
#####  * Se ha añadido feedback visual al pasar por encima o clickar en los elementos destinados a ello.

> [!NOTE]
> Natural Mapping
#####  * Se han quitado elementos y separado funcionalidades.
#####  * Se ha implantado un calendario en una página completamente diferente y facilmente accesible.
#####  * Los botones son facilmente identificables en la interfaz.

> [!NOTE]
> Otros cambios adicionales:
#####  * jPanelHome --> Ahora contiene un splitPane y dentro de él están los paneles jPanelOptions y jPanelShowInfo.
#####  * jPanelHome --> jLabelOptions ahora usa Miglayout para representar los botones.
#####  * jPanelHome --> Eliminados todos los setBounds.
#####  * jPanelHome --> Cambiado look & feel.
#####  * jPanelHome --> Ahora al entrar, por defecto nos muestra la pantalla de usuarios.
#####  * jPanelHome --> Cuando se hace pequeño "jLabelOptions" a través del SplitPane, los iconos se colocan en una única fila y viceversa.
#####
#####  * jPanelHomeUsers --> ahora usa Miglayout para representar todos sus elementos.
#####  * jPanelHomeUsers --> Cambiado look & feel.
#####  * jPanelHomeUsers --> Ahora usa MigLyout para mostrar la lista de usuarios usando botones en vez de una lista plana.
#####  * jPanelHomeUsers --> Ahora los botones cambian de color al realizar mouseEntered & mouseExited.
#####  * jPanelCalendar --> Se ha traspasado el calendario de la UD03, accesible desde jPanelHome --> jPanelOptions --> jButtonCalendar.
#####  * jDialogHomeUsersAdd --> Botón jButtonCreate se ha puesto como predeterminado para que al pulsar enter se envíe el form.
#####  * jDialogHomeUsersWorkoutsAdd --> Botón jButtonCreate se ha puesto como predeterminado para que al pulsar enter se envíe el form.
#####  * jDialogHomeUsersExercicisAdd --> Botón jButtonCreate se ha puesto como predeterminado para que al pulsar enter se envíe el form.
#####  * Tabulacion debe pasar a la siguiente casilla (esta accion ya parece hacerla por defecto).
#####  * jDialogHomeUsersAdd --> Regular expressions --> casilla correo.
#####  * jDialogHomeUsersAdd --> Ahora usa jPasswordField para el campo de contraseña al añadir un usuario.

> [!NOTE]
> Arreglos referentes a la v1
#####  * Movida la estructura DAO a data.
#####  * Modificada la estructura del Readme.
#####  * El jDialog redundante en el inicio de sesión se ha suprimido.
#####  * Añadido mensaje de error de login en jPanelLogin.
#####  * Añadido botón de restablecer contraseña en jPanelLogin para evitar un GAP (sin funcionalidad actualmente).

> [!NOTE]
> Páginas consultadas
##### http://www.miglayout.com/QuickStart.pdf
##### https://doc.formdev.com/miglayout-swing/net/miginfocom/swing/MigLayout.html
##### https://icon-icons.com/es/pack/BigMug-Line-icons/935

> [!WARNING]
> Pendiente implementar/modificar que pueden dar problemas todavía
#####  * Los botones Add, change, delete deben cambiarse a iconos. (Metaforas).
#####  * Los botones "Create" para confirmar la creación debe cambiarse a icono de V y X y de color. (Metafora).
#####  * Los botones "Create" para confirmar la creación debe cambiarse de color. (affordance).
#####  * El icono de refresh debe cambiarse por una flecha giratoria (Metafora).
#####  * El logout debería estar más en medio.
#####  * Revisar los refresh de las listas y tablas para que mantenga el usuario seleccionado.
#####  * Cuando se crea un usuario parece que no se asigna correctamente al instructor que toca y se asigna todo a Otero.
#####  * Poder cambiar los colores desde settings.
#####  * Poder buscar un usuario por nombre --> busqueda, filtros, paginacion.
#####  * Dark Pattern --> botón cancelar menos visible que el de aceptar.

> [!WARNING]
> Criteris de qualificació 
### Como se comentó en la tutoria, debido a la cantidad de trabajo casi infinita que podría suponer este tema se ha centralizado en los paneles que podían causar mayor impresion.
#### Modificar el aspecto (look and feel) de las ventanas para hacerlas más atractivas y acorde con las tendencias actuales (p.ej. flat design, color scheme, etc.). 1p
##### - Se ha modificado la estética de las pantallas:
##### -- JPanelHome
##### -- JPanelHomeUsers
##### -- JPanelCalendar
#####
#### Añadir iconos y/o otros elementos visuales a los componentes para mejorar la usabilidad de la interfaz. 1p
##### - Se han añadido metaforas a los botones de:
##### -- JPanelHome
#####
#### Modificar la fuente y componentes utilizados para mostrar texto de forma que éste resulte legible e inteligible. 1p
##### - Se han modificado las fuentes, tamaños y colores en:
##### -- jPanelHome - Las letras de los botones están en blanco, negrita y tamaño 14
##### -- jPanelHomeUsers - Las letras de los botones están en blanco, negrita y tamaño 14
##### -- jPanelHomeUsers - Las letras de los workouts y exercices pasan a color blanco al seleccionarlos
#####
#### Distribuir y agrupar los componentes de una manera más organizada y atractiva para el usuario. 1p
##### -- Se han eliminado elementos redundantes de jPanelHomeUsers
##### -- Se han agrupado componentes usando botones y celdas más visibles evitando solapamiento de caracteres
#####
#### Redefinir los Layouts de los diferentes contenedores (JFrame, JDialog, …), Anchors (anclajes), Auto Resizing, Min and Max Size, etc. para que la usabilidad de la interfaz no se vea afectada en caso de redimensionamiento de las ventana principal. Utilizar MigLayout principalment. You can also use BorderLayout if needed. No null layout should be present in any container of the project now. 2p
##### - Se han eliminado los Layout null de:
##### -- jPanelHome
##### -- jPanelHomeUsers
##### - Se han organizado usando MigLayout los siguientes componentes:
##### -- jPanelHome -> jPanelOptions
##### -- jPanelHomeUsers
##### -- jPanelHomeUsers -> jPaneUsersList
##### -- jPanelHome -> jPanelOptions
#####
#### - Implementar la gestión de errores y excepciones necesaria, informando y dando feedback al usuario cuando sea necesario (no es necesario utilizar ninguna API de validación). 1p
##### - Añadido aviso indicando que el mail no tiene una estructura correcta
#####
#### - Añadir más cambios a la interfaz que mejoren la usabilidad y documentarlos en el Readme.md del repositorio, justificando la solución adoptada, tanto a nivel de interfaz y componentes de la interfaz como a nivel de código. 3p
##### - Documentados todos los cambios en el Readme
#####
#### - Clean code. Naming things. Project structure. Commits and github. Readme. 1p

> [!NOTE]
> Capturas del antes y el despues
> 
![antes](https://github.com/user-attachments/assets/c33895c7-3e91-4159-807d-f357fd3a89d1)
![despues](https://github.com/user-attachments/assets/e68f2f62-ca53-4e22-a128-08789cc7125b)

> [!NOTE]
> Paleta de colores usada
> Fondo de color blanco
> Bordes y énfasis: #D98888
> Items clickables y mauseOver: #800020
![idea_paleta](https://github.com/user-attachments/assets/3d06be23-61ef-4e78-9171-d8da5f98c337)

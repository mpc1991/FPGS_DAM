# component2image
#### Macià Porcel Cifre
#### DI03
###
### How to use it?
#### The component name inside the .jar is "Workout2Calendar".
####
#### To configure the listener in your jFrame use this code:
###### workout2Calendar.setListeners(new MyCalendarListeners() {
######            @Override
######            public void hasWorkoutListener(hasWorkoutsEventArgs eventArg) {
######                jTextArea.setText(eventArg.toString());
######            }
###### });
####
#### On your showCalendar button use this code:
###### if (workout2Calendar != null) {
######  this.remove(workout2Calendar); // Eliminamos instancias anteriores
###### }
####
###### workout2Calendar = new Workout2Calendar(); // Creamos nueva instancia
###### workout2Calendar.setBounds(10, 250, 500, 300); // Modificar a placer
###### this.add(workout2Calendar); // Añadimos el complemento al jPanel
###### workout2Calendar.setColor(Color.RED); // Elije el color con el que quieras pintar las casillas que tengan Workouts
###### workout2Calendar.initializeCalendar(); // Este paso preparará el calendario
###### workout2Calendar.setVisible(true); // Ponemos el calendario visible
###### this.revalidate();
###### this.repaint();
####
#### On your hideCalendar button use this code:
###### if (workout2Calendar != null) {
######  this.remove(workout2Calendar); // Eliminar el calendario del JFrame
######  workout2Calendar.setVisible(false); // Asegurarse de que el calendario no esté visible
######  this.revalidate(); // Revalidar el layout para actualizar la vista
######  this.repaint();    // Repintar el JFrame para reflejar los cambios
######  jTextArea.setText("");
###### }

package porcel.workout2success.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import porcel.workout2success.Main;
import porcel.workout2success.data.DataAccessCalendar;
import porcel.workout2success.dto.WorkoutCalendar;
import porcel.workout2success.listeners.MyCalendarListeners;
import porcel.workout2success.listeners.hasWorkoutsEventArgs;

/**
 * Clase que mostrará el calendario dentro del panel contenedor
 * 
 * @author Macia Porcel Cifre
 * @version 2.0
 */
public class JPanelCalendar extends JPanel {

    private int year;
    private int month;
    private Color color = Color.decode("#800020");
    private int startY;
    private JComboBox<String> monthComboBox;
    private JSpinner yearSpinner;
    JPanel controlPanel;
    ArrayList<MyCalendarListeners> listeners = new ArrayList<>();

    /**
     * Constructor principal
     *
     * @param jPanelHome Referencia al panel contenedor de esta classe
     * @param main Referenacia al Main
     */
    public JPanelCalendar(JPanelHome jPanelHome, Main main) {
        LocalDate currentDate = LocalDate.now();
        this.year = currentDate.getYear();
        this.month = currentDate.getMonthValue();
        inicialize();
        inicializeMovementDetection();
    }

    /**
     * Inicializa la barra de control superior del calendario
     */
    public void inicialize() {
        this.setBorder(BorderFactory.createEtchedBorder());
        setLayout(new MigLayout("wrap 7, fill", "[grow, fill]", "[grow, fill]"));
        controlPanel = new JPanel();

        // Crear JComboBox para seleccionar mes
        String[] months = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        monthComboBox = new JComboBox<>(months);
        monthComboBox.setSelectedIndex(month - 1);
        monthComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                month = monthComboBox.getSelectedIndex() + 1;
                initializeCalendar();
            }
        });

        // Crear JSpinner para seleccionar el año
        SpinnerModel yearModel = new SpinnerNumberModel(year, 1900, 2100, 1); // rango de años
        yearSpinner = new JSpinner(yearModel);
        yearSpinner.setValue(year);
        yearSpinner.addChangeListener(e -> {
            year = (Integer) yearSpinner.getValue();
            initializeCalendar();
        });

        // Agregar los componentes de control
        controlPanel.add(new JLabel("Mes:"));
        controlPanel.add(monthComboBox, "span");
        controlPanel.add(new JLabel("Año:"));
        controlPanel.add(yearSpinner, "span");
    }

    /**
     * Inicializa el panel del calendario
     */
    public void initializeCalendar() {
        removeAll(); // Limpiar el panel

        add(controlPanel, "span"); // Para visualizar los botones

        // Obtención del calendario y colocacion de botones adaptada de chatGPT
        // Obtener el número de días del mes y el primer día
        YearMonth yearMonth = YearMonth.of(year, month);
        int daysInMonth = yearMonth.lengthOfMonth();
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        int startDayOfWeek = firstDayOfMonth.getDayOfWeek().getValue(); // 1=Lunes, 7=Domingo

        // Obtener las fechas de los workouts desde la base de datos
        List<LocalDate> workoutDates = DataAccessCalendar.getWorkoutDatesForMonth(year, month);

        // Agregar días vacíos hasta el primer día de la semana
        for (int i = 1; i < startDayOfWeek; i++) {
            add(new JPanel()); // Placeholder vacío
        }

        // Agregar botones para cada día del mes
        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate currentDate = LocalDate.of(year, month, day);
            JButton dayButton = new JButton(String.valueOf(day));

            //Si el día tiene un workout, cambiamos el color del botón y le añadimos el tooltip
            if (workoutDates.contains(currentDate)) {
                dayButton.setBackground(color);
                dayButton.setForeground(Color.white);
                dayButton.setFont(dayButton.getFont().deriveFont(Font.BOLD, 14f));

                int workoutCount = Collections.frequency(workoutDates, currentDate);
                dayButton.setToolTipText(workoutCount + " entrenamiento(s) para este día");
            } else {
                dayButton.setBackground(Color.white);
            }

            ActionListener hasWorkoutsListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    List<WorkoutCalendar> workoutsForDay = DataAccessCalendar.getWorkoutsForDay(currentDate);
                    //System.out.println(workoutsForDay.toString());
                    hasWorkoutsEventArgs eventArgs = new hasWorkoutsEventArgs(this, workoutsForDay);

                    // Notificmos a todos los listeners
                    for (MyCalendarListeners listener : listeners) {
                        listener.hasWorkoutListener(eventArgs);
                    }
                }
            };

            dayButton.addActionListener(hasWorkoutsListener); // Añadimos un ActionListener de hasWorkouts al botón que vamos a crear

            add(dayButton); // Añadimos el botón
        }

        // Forzamos actualización visual
        revalidate();
        repaint();
    }

    /**
     * <p>
     * Mause Listener para cambiar de mes al hacer click y arrastrar </p>
     * <p>
     * Si al soltar el click la diferencia en las coordenadas es positiva o
     * negativa pasamos al mes siguiente o anterior.</p>
     */
    private void inicializeMovementDetection() {
        // Añadir los listeners de swipe
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startY = e.getY();  // Almacena la posición Y inicial cuando se presiona el mouse
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int endY = e.getY();  // Obtiene la posición Y final cuando se suelta el mouse
                if (startY - endY > 50) {
                    // Detecta un "swipe down"
                    showNextMonth();
                } else if (endY - startY > 50) {
                    // Detecta un "swipe up"
                    showPreviousMonth();
                }
            }
        });

        inicialize(); // refrescamos el calendario
    }

    /**
     * Método para establecer año y mes
     *
     * @param year referencia el año
     * @param month referencia el mes
     */
    public void setMonth(int year, int month) {
        this.year = year;
        this.month = month;
        initializeCalendar();
    }

    /**
     * Getter para obtener el color
     *
     * @return devuelve el color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Setter para establecer el color
     *
     * @param color recibido
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Método para mostrar el més siguiente al actual
     */
    private void showNextMonth() {
        if (month == 12) {  // Si es diciembre
            month = 1;       // Poner el mes a enero
            year++;          // Aumentar el año
        } else {
            month++;         // Siguiente mes
        }
        // Actualizar los componentes de control
        monthComboBox.setSelectedIndex(month - 1);  // Actualizar JComboBox
        yearSpinner.setValue(year);                  // Actualizar JSpinner
        initializeCalendar();  // Actualizar el calendario
    }

    /**
     * Método para mostrar el més anterior al actual
     */
    private void showPreviousMonth() {
        if (month == 1) {     // Si es enero
            month = 12;        // Poner el mes a diciembre
            year--;            // Reducir el año
        } else {
            month--;           // Mes anterior
        }
        // Actualizar los componentes de control
        monthComboBox.setSelectedIndex(month - 1);  // Actualizar JComboBox
        yearSpinner.setValue(year);
        initializeCalendar();  // Actualizar el calendario
    }

    public void removeListeners(MyCalendarListeners listeners) {
        this.listeners.remove(listeners);
    }

    public void setListeners(MyCalendarListeners listeners) {
        this.listeners.add(listeners);
    }
}


import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import java.sql.Timestamp;
import java.time.Instant;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author acv
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    private String currentSemester;
    private StudentEntry currentStudent; //exclusively used for drop course in student section

    public MainFrame() {
        initComponents();
        rebuildSemesterComboBoxes();
        rebuildCourseStudentComboBoxes(currentSemester);
        rebuildDisplayCourses();
    }

    public void rebuildSemesterComboBoxes() {
        //rebuilds Semester combo boxes
        ArrayList<String> semesters = SemesterQueries.getSemesterList();
        currentSemesterComboBox.setModel(new javax.swing.DefaultComboBoxModel(semesters.toArray()));
        if (semesters.size() > 0) {
            currentSemesterLabel.setText(semesters.get(0));
            currentSemester = currentSemesterLabel.getText();
        } else {
            currentSemesterLabel.setText("None, add a semester.");
            currentSemester = "None";
        }
    }

    private void rebuildCourseStudentComboBoxes(String currentSemester) {
        //rebuilds both Course combo boxes and student combo boxes. Could be split up into separate methods
        ArrayList<String> courseIDs = CourseQueries.getAllCourseCodes(currentSemester);
        schCourse.setModel(new javax.swing.DefaultComboBoxModel(courseIDs.toArray()));
        dropCourseAdmin.setModel(new javax.swing.DefaultComboBoxModel(courseIDs.toArray()));
        selectCourseList.setModel(new javax.swing.DefaultComboBoxModel(courseIDs.toArray()));
        ArrayList<StudentEntry> studentsNames = StudentQueries.getAllStudents();
        schStudent.setModel(new javax.swing.DefaultComboBoxModel(studentsNames.toArray()));
        selectStudentButton.setModel(new javax.swing.DefaultComboBoxModel(studentsNames.toArray()));
        dropCourseStudent.setModel(new javax.swing.DefaultComboBoxModel(studentsNames.toArray()));
        dropStudent.setModel(new javax.swing.DefaultComboBoxModel(studentsNames.toArray()));

        if (courseIDs.isEmpty()) {
            scheduleCoursesLabel.setText("No Courses or Students found, please add some!");
        } else if (studentsNames.isEmpty()) {
            scheduleCoursesLabel.setText("No Courses or Students found, please add some!");
        }
    }

    private void rebuildStudentCourseListComboBox(StudentEntry student) {
        ArrayList<ScheduleEntry> schedules = ScheduleQueries.getScheduleByStudent(currentSemester, student.getstudentID());
        dropCourseCombo.setModel(new javax.swing.DefaultComboBoxModel(schedules.toArray()));

    }

    private void rebuildDisplayCourses() {
        //rebuilds the "Display Courses" table, automatically updated as courses are added
        ArrayList<CourseEntry> courses = CourseQueries.getAllCourses(currentSemester);
        DefaultTableModel displayCoursesTableModel = (DefaultTableModel) displayCoursesTable.getModel();

        displayCoursesTableModel.setNumRows(0);
        Object[] rowData = new Object[3];

        for (CourseEntry course : courses) {
            rowData[0] = course.getCourseID();
            rowData[1] = course.getDescription();
            rowData[2] = course.getSeats();
            displayCoursesTableModel.addRow(rowData);
        }
    }

    private void rebuildDisplaySchedule() {
        //rebuilds the "Display Schedule" table, requires manual clicking of display schedule button
        ArrayList<StudentEntry> studentsNames = StudentQueries.getAllStudents();
        Integer studentPos = selectStudentButton.getSelectedIndex();
        ArrayList<ScheduleEntry> schedules = ScheduleQueries.getScheduleByStudent(currentSemester, studentsNames.get(studentPos).getstudentID());
        DefaultTableModel displayScheduleTableModel = (DefaultTableModel) displayScheduleTable.getModel();

        displayScheduleTableModel.setNumRows(0);
        Object[] rowData = new Object[2];

        for (ScheduleEntry schedule : schedules) {
            rowData[0] = schedule.getCourseID();
            rowData[1] = schedule.getStatus();

            displayScheduleTableModel.addRow(rowData);
        }

    }

    private void rebuildCourseList() {
        ArrayList<ScheduleEntry> scheduled = ScheduleQueries.getScheduledStudentsByCourse(currentSemester, selectCourseList.getSelectedItem().toString());
        ArrayList<ScheduleEntry> waitlisted = ScheduleQueries.getWaitListedStudentsByCourse(currentSemester, selectCourseList.getSelectedItem().toString());
        DefaultTableModel scheduledTableModel = (DefaultTableModel) scheduledStudentsTable.getModel();
        DefaultTableModel waitlistedTableModel = (DefaultTableModel) waitlistedStudentsTable.getModel();

        scheduledTableModel.setNumRows(0);
        waitlistedTableModel.setNumRows(0);
        Object[] scheduleData = new Object[3];
        Object[] waitlistData = new Object[3];
        System.out.print("outside");

        for (ScheduleEntry schedule : scheduled) {
            System.out.print("inside");
            StudentEntry temp = StudentQueries.getStudent(schedule.getStudentID());
            scheduleData[0] = temp.getlast();
            scheduleData[1] = temp.getfirst();
            scheduleData[2] = temp.getstudentID();

            scheduledTableModel.addRow(scheduleData);
        }
        for (ScheduleEntry waiting : waitlisted) {
            StudentEntry temp = StudentQueries.getStudent(waiting.getStudentID());
            scheduleData[0] = temp.getlast();
            scheduleData[1] = temp.getfirst();
            scheduleData[2] = temp.getstudentID();

            waitlistedTableModel.addRow(scheduleData);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        addSemesterTextfield = new javax.swing.JTextField();
        addSemesterSubmitButton = new javax.swing.JButton();
        addSemesterStatusLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        addCourseDescription = new javax.swing.JTextField();
        addCourseButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        addCourseCode = new javax.swing.JTextField();
        addCourseSeats = new javax.swing.JSpinner();
        courseLabel = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        addStudentID = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        addStudentFirst = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        addStudentButton = new javax.swing.JButton();
        addStudentLabel = new javax.swing.JLabel();
        addStudentLast = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        waitlistedStudentsTable = new javax.swing.JTable();
        displayedCourseListButton = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        selectCourseList = new javax.swing.JComboBox<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        scheduledStudentsTable = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        dropStudentText = new javax.swing.JTextArea();
        dropStudentButton = new javax.swing.JButton();
        dropStudent = new javax.swing.JComboBox<>();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        dropCourseAdminText = new javax.swing.JTextArea();
        dropCourseAdmin = new javax.swing.JComboBox<>();
        dropCourseAdminButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        displayCoursesTable = new javax.swing.JTable();
        displayCoursesButton = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        displayScheduleTable = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        selectStudentButton = new javax.swing.JComboBox<>();
        displayScheduleButton = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        schCourse = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        schStudent = new javax.swing.JComboBox<>();
        scheduleCourseButton = new javax.swing.JButton();
        scheduleCoursesLabel = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        dropCourseStudent = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        dropCourseStudentText = new javax.swing.JTextArea();
        selectStudentDropButton = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        dropCourseCombo = new javax.swing.JComboBox<>();
        dropCourseCourse = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        currentSemesterLabel = new javax.swing.JLabel();
        currentSemesterComboBox = new javax.swing.JComboBox<>();
        changeSemesterButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Georgia", 1, 32)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Course Scheduler");

        jLabel3.setText("Semester Name:");

        addSemesterTextfield.setColumns(20);

        addSemesterSubmitButton.setText("Submit");
        addSemesterSubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSemesterSubmitButtonActionPerformed(evt);
            }
        });

        addSemesterStatusLabel.setText("                                                   ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addSemesterTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(addSemesterSubmitButton))
                    .addComponent(addSemesterStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(addSemesterTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addSemesterSubmitButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addSemesterStatusLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Add Semester", jPanel3);

        addCourseDescription.setColumns(20);

        addCourseButton.setText("Submit");
        addCourseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCourseButtonActionPerformed(evt);
            }
        });

        jLabel5.setText("CourseCode:");

        jLabel6.setText("Description:");

        jLabel7.setText("Seats:");

        addCourseCode.setColumns(20);

        addCourseSeats.setModel(new javax.swing.SpinnerNumberModel(1, 1, 5000, 1));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(addCourseSeats, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(addCourseButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(addCourseCode, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
                            .addComponent(addCourseDescription))
                        .addGap(298, 298, 298))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(courseLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addCourseCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addCourseDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addCourseSeats, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addCourseButton)
                .addGap(18, 18, 18)
                .addComponent(courseLabel)
                .addContainerGap(126, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Add Course", jPanel4);

        jLabel9.setText("StudentID:");

        addStudentID.setColumns(20);

        jLabel10.setText("First Name:");

        addStudentFirst.setColumns(20);

        jLabel11.setText("Last Name:");

        addStudentButton.setText("Submit");
        addStudentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStudentButtonActionPerformed(evt);
            }
        });

        addStudentLast.setColumns(20);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(addStudentButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(addStudentFirst, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                    .addComponent(addStudentID)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(addStudentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(addStudentLast))
                .addGap(383, 383, 383))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(addStudentID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(addStudentFirst))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(addStudentLast))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addStudentButton)
                .addGap(30, 30, 30)
                .addComponent(addStudentLabel)
                .addGap(151, 151, 151))
        );

        jTabbedPane2.addTab("Add Student", jPanel5);

        waitlistedStudentsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Last Name", "First Name", "StudentID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(waitlistedStudentsTable);
        if (waitlistedStudentsTable.getColumnModel().getColumnCount() > 0) {
            waitlistedStudentsTable.getColumnModel().getColumn(0).setResizable(false);
            waitlistedStudentsTable.getColumnModel().getColumn(1).setResizable(false);
            waitlistedStudentsTable.getColumnModel().getColumn(2).setResizable(false);
        }

        displayedCourseListButton.setText("Display Schedules");
        displayedCourseListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayedCourseListButtonActionPerformed(evt);
            }
        });

        jLabel15.setText("Select Course:");

        selectCourseList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        scheduledStudentsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Last Name", "First Name", "StudentID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(scheduledStudentsTable);
        if (scheduledStudentsTable.getColumnModel().getColumnCount() > 0) {
            scheduledStudentsTable.getColumnModel().getColumn(0).setResizable(false);
            scheduledStudentsTable.getColumnModel().getColumn(1).setResizable(false);
            scheduledStudentsTable.getColumnModel().getColumn(2).setResizable(false);
        }

        jLabel16.setText("Waitlisted Students:");

        jLabel17.setText("Scheduled Students:");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7))
                .addGap(28, 28, 28)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selectCourseList, 0, 131, Short.MAX_VALUE))
                    .addComponent(displayedCourseListButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(selectCourseList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(displayedCourseListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(349, 349, 349))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(189, 189, 189))))
        );

        jTabbedPane2.addTab("Display Course List of Students", jPanel10);

        dropStudentText.setEditable(false);
        dropStudentText.setColumns(20);
        dropStudentText.setRows(5);
        jScrollPane5.setViewportView(dropStudentText);

        dropStudentButton.setText("Drop Student");
        dropStudentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dropStudentButtonActionPerformed(evt);
            }
        });

        dropStudent.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(dropStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dropStudentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(281, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dropStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dropStudentButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Drop Student", jPanel11);

        dropCourseAdminText.setEditable(false);
        dropCourseAdminText.setColumns(20);
        dropCourseAdminText.setRows(5);
        jScrollPane4.setViewportView(dropCourseAdminText);

        dropCourseAdmin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        dropCourseAdminButton.setText("Drop Course");
        dropCourseAdminButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dropCourseAdminButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(dropCourseAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dropCourseAdminButton, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(281, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dropCourseAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dropCourseAdminButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Drop Course", jPanel12);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Admin", jPanel1);

        displayCoursesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CourseID", "Description", "Seats"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        displayCoursesTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(displayCoursesTable);
        if (displayCoursesTable.getColumnModel().getColumnCount() > 0) {
            displayCoursesTable.getColumnModel().getColumn(2).setResizable(false);
        }

        displayCoursesButton.setText("Display Courses");
        displayCoursesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayCoursesButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 757, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(displayCoursesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(displayCoursesButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Display Courses", jPanel6);

        displayScheduleTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Course Code", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(displayScheduleTable);
        if (displayScheduleTable.getColumnModel().getColumnCount() > 0) {
            displayScheduleTable.getColumnModel().getColumn(0).setResizable(false);
            displayScheduleTable.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel12.setText("Select Student:");

        selectStudentButton.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        displayScheduleButton.setText("Display Schedule");
        displayScheduleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayScheduleButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selectStudentButton, 0, 196, Short.MAX_VALUE))
                    .addComponent(displayScheduleButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(selectStudentButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(displayScheduleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(200, 200, 200))
        );

        jTabbedPane3.addTab("Display Schedule", jPanel7);

        schCourse.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("Select Course");

        jLabel8.setText("Select Student");

        schStudent.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        scheduleCourseButton.setText("Submit");
        scheduleCourseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scheduleCourseButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scheduleCoursesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(scheduleCourseButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(schStudent, 0, 150, Short.MAX_VALUE)))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(schCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 493, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(schCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(schStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(scheduleCourseButton)
                .addGap(18, 18, 18)
                .addComponent(scheduleCoursesLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Schedule Courses", jPanel8);

        dropCourseStudent.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        dropCourseStudentText.setEditable(false);
        dropCourseStudentText.setColumns(20);
        dropCourseStudentText.setRows(5);
        jScrollPane3.setViewportView(dropCourseStudentText);

        selectStudentDropButton.setText("Select Student");
        selectStudentDropButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectStudentDropButtonActionPerformed(evt);
            }
        });

        jLabel13.setText("Student:");

        jLabel14.setText("(A Student Must be Selected for Courses to Display)");

        dropCourseCourse.setText("Drop Course");
        dropCourseCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dropCourseCourseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dropCourseCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dropCourseStudent, 0, 228, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(selectStudentDropButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(dropCourseCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(29, 29, 29))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dropCourseStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectStudentDropButton)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addGap(6, 6, 6)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dropCourseCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dropCourseCourse))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(198, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Drop Course", jPanel9);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );

        jTabbedPane1.addTab("Student", jPanel2);

        jLabel2.setFont(new java.awt.Font("Georgia", 1, 16)); // NOI18N
        jLabel2.setText("Current Semester: ");

        currentSemesterLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 16)); // NOI18N
        currentSemesterLabel.setText("           ");

        currentSemesterComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        changeSemesterButton.setText("Change Semester");
        changeSemesterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeSemesterButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(currentSemesterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(currentSemesterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(changeSemesterButton)
                                .addGap(80, 80, 80)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(currentSemesterLabel)
                    .addComponent(currentSemesterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(changeSemesterButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addSemesterSubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSemesterSubmitButtonActionPerformed
        //adds a semester and rebuilds its combo box
        String semester = addSemesterTextfield.getText();
        if (semester.isBlank()) {
            addSemesterStatusLabel.setText("No semester name entered, semester has not been added");
        } else {
            SemesterQueries.addSemester(semester);
            addSemesterStatusLabel.setText("Semester " + semester + " has been added.");
            rebuildSemesterComboBoxes();
        }
    }//GEN-LAST:event_addSemesterSubmitButtonActionPerformed

    private void addCourseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCourseButtonActionPerformed
        //adds a course to the current semester
        String semester = currentSemester;
        String code = addCourseCode.getText();
        String desc = addCourseDescription.getText();
        Integer seats = (Integer) addCourseSeats.getValue();

        if (semester == "None") {
            courseLabel.setText("No semester selected, class has not been added");
        } else if (semester.isBlank()) {
            courseLabel.setText("No semester selected, class has not been added");
        } else if (code.isBlank()) {
            courseLabel.setText("No class code entered, class has not been added");
        } else if (desc.isBlank()) {
            courseLabel.setText("No class description code entered, class has not been added");
        } else {
            CourseEntry course = new CourseEntry(semester, code, desc, seats);
            CourseQueries.addCourse(course);
            courseLabel.setText("Course " + desc + " has been added.");
            rebuildCourseStudentComboBoxes(currentSemester);
            rebuildDisplayCourses();
        }
    }//GEN-LAST:event_addCourseButtonActionPerformed

    private void changeSemesterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeSemesterButtonActionPerformed
        //changes the current active semester and updates comboboxes/course list
        ArrayList<String> semesters = SemesterQueries.getSemesterList();
        if (semesters.size() > 0) {
            currentSemesterLabel.setText(semesters.get(currentSemesterComboBox.getSelectedIndex()));
            currentSemester = currentSemesterLabel.getText();
            rebuildCourseStudentComboBoxes(currentSemester);
            rebuildDisplayCourses();
        } else {
            currentSemesterLabel.setText("None, add a semester.");
            currentSemester = "None";
        }
    }//GEN-LAST:event_changeSemesterButtonActionPerformed

    private void addStudentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStudentButtonActionPerformed
        //adds a new student
        String studentid = addStudentID.getText();
        String first = addStudentFirst.getText();
        String last = addStudentLast.getText();

        if (studentid.isBlank()) {
            addStudentLabel.setText("No student ID entered, student has not been added");
        } else if (first.isBlank()) {
            addStudentLabel.setText("No first name entered, student has not been added");
        } else if (last.isBlank()) {
            addStudentLabel.setText("No last name entered, student has not been added");
        } else {
            StudentEntry student = new StudentEntry(studentid, first, last);
            StudentQueries.addStudent(student);
            addStudentLabel.setText("Student " + first + " " + last + " has been added.");
            rebuildCourseStudentComboBoxes(currentSemester);
        }
    }//GEN-LAST:event_addStudentButtonActionPerformed

    private void dropCourseCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dropCourseCourseActionPerformed
        dropCourseStudentText.setText("");
        if (ScheduleQueries.isScheduled(currentSemester, currentStudent.getstudentID(), dropCourseCombo.getSelectedItem().toString()) == true) {
            ArrayList<ScheduleEntry> waitlisted = ScheduleQueries.getWaitListedStudentsByCourse(currentSemester, dropCourseCombo.getSelectedItem().toString());
            if (waitlisted.size() > 0) {
                ScheduleEntry i = waitlisted.get(0);
                ScheduleQueries.updateScheduleEntry(currentSemester, i);
                StudentEntry temp = StudentQueries.getStudent(i.getStudentID());
                dropCourseStudentText.append(String.format("Student %s, %s has been scheduled to %s\n", temp.getlast(), temp.getfirst(), dropCourseCombo.getSelectedItem().toString()));
            }
        }

        dropCourseStudentText.append(String.format("Student %s, %s %s has been dropped from %s\n", currentStudent.getlast(), currentStudent.getfirst(), currentStudent.getstudentID(), dropCourseCombo.getSelectedItem().toString()));
        ScheduleQueries.dropStudentScheduleByCourse(currentSemester, currentStudent.getstudentID(), dropCourseCombo.getSelectedItem().toString());
        ArrayList<StudentEntry> studentsNames = StudentQueries.getAllStudents();
        Integer studentPos = dropCourseStudent.getSelectedIndex();
        currentStudent = studentsNames.get(studentPos);
        rebuildStudentCourseListComboBox(currentStudent);

    }//GEN-LAST:event_dropCourseCourseActionPerformed

    private void selectStudentDropButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectStudentDropButtonActionPerformed
        ArrayList<StudentEntry> studentsNames = StudentQueries.getAllStudents();
        Integer studentPos = dropCourseStudent.getSelectedIndex();
        currentStudent = studentsNames.get(studentPos);
        rebuildStudentCourseListComboBox(currentStudent);
    }//GEN-LAST:event_selectStudentDropButtonActionPerformed

    private void scheduleCourseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scheduleCourseButtonActionPerformed
        //Schedules a course for the given semester/student
        ArrayList<String> courseIDs = CourseQueries.getAllCourseCodes(currentSemester);
        ArrayList<StudentEntry> studentsNames = StudentQueries.getAllStudents();
        Integer courseIDPos = schCourse.getSelectedIndex();
        Integer studentPos = schStudent.getSelectedIndex();
        Timestamp instant = Timestamp.from(Instant.now());
        String waitlist = "s";
        Integer courseSeats = CourseQueries.getCourseSeats(currentSemester, courseIDs.get(courseIDPos));
        Integer takenSeats = ScheduleQueries.getCourseSeats(currentSemester, courseIDs.get(courseIDPos));
        if (courseSeats - takenSeats <= 0) {
            waitlist = "w";
            scheduleCoursesLabel.setText(String.format("Waitlisted for the %s", courseIDs.get(courseIDPos)));
        } else {
            scheduleCoursesLabel.setText(String.format("Course %s scheduled!", courseIDs.get(courseIDPos)));
        }

        ScheduleEntry schedule = new ScheduleEntry(currentSemester, studentsNames.get(studentPos).getstudentID(), courseIDs.get(courseIDPos), waitlist, instant);
        ScheduleQueries.addSchedule(schedule);
    }//GEN-LAST:event_scheduleCourseButtonActionPerformed

    private void displayScheduleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayScheduleButtonActionPerformed
        rebuildDisplaySchedule();
    }//GEN-LAST:event_displayScheduleButtonActionPerformed

    private void displayCoursesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayCoursesButtonActionPerformed
        rebuildDisplayCourses();
    }//GEN-LAST:event_displayCoursesButtonActionPerformed

    private void dropCourseAdminButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dropCourseAdminButtonActionPerformed
        dropCourseAdminText.setText("");
        ArrayList<ScheduleEntry> scheduledStudents = ScheduleQueries.getScheduledStudentsByCourse(currentSemester, dropCourseAdmin.getSelectedItem().toString());
        ArrayList<ScheduleEntry> waitlistedStudents = ScheduleQueries.getWaitListedStudentsByCourse(currentSemester, dropCourseAdmin.getSelectedItem().toString());
        ArrayList<StudentEntry> studentsNames = StudentQueries.getAllStudents();
        dropCourseAdminText.append("Scheduled Students dropped:\n");
        for (ScheduleEntry scheduled : scheduledStudents) {
            StudentEntry temp = StudentQueries.getStudent(scheduled.getStudentID());
            dropCourseAdminText.append(String.format("%s, %s %s\n", temp.getlast(), temp.getfirst(),temp.getstudentID()));
        }
        dropCourseAdminText.append("\nWaitlisted Students dropped:\n");
        for (ScheduleEntry waitlisted : waitlistedStudents) {
            StudentEntry temp = StudentQueries.getStudent(waitlisted.getStudentID());
            dropCourseAdminText.append(String.format("%s, %s %s\n", temp.getlast(), temp.getfirst(),temp.getstudentID()));
        }

        CourseQueries.dropCourse(currentSemester, dropCourseAdmin.getSelectedItem().toString());
        ScheduleQueries.dropScheduleByCourse(currentSemester, dropCourseAdmin.getSelectedItem().toString());
        rebuildCourseStudentComboBoxes(currentSemester);
    }//GEN-LAST:event_dropCourseAdminButtonActionPerformed

    private void dropStudentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dropStudentButtonActionPerformed
        dropStudentText.setText("");
        ArrayList<String> semesters = SemesterQueries.getSemesterList();
        ArrayList<StudentEntry> studentsNames = StudentQueries.getAllStudents();
        Integer studentPos = dropStudent.getSelectedIndex();
        StudentEntry student = studentsNames.get(studentPos);
        dropStudentText.append(String.format("Student %s, %s %s has been dropped from the list of students\n", student.getlast(), student.getfirst(), student.getstudentID()));
        
        for (String semester : semesters) {
            dropStudentText.append(String.format("For semester %s:\n", semester));
            ArrayList<ScheduleEntry> courses = ScheduleQueries.getScheduleByStudent(semester, student.getstudentID());

            for (ScheduleEntry entry : courses) {
                Boolean isScheduled = false;
                if (ScheduleQueries.isScheduled(semester, student.getstudentID(), entry.getCourseID()) == true) {
                    ArrayList<ScheduleEntry> waitlisted = ScheduleQueries.getWaitListedStudentsByCourse(semester, entry.getCourseID());
                    isScheduled = true;
                    if (waitlisted.size() > 0) {
                        ScheduleEntry i = waitlisted.get(0);
                        ScheduleQueries.updateScheduleEntry(semester, i);
                        StudentEntry temp = StudentQueries.getStudent(i.getStudentID());
                        dropStudentText.append(String.format("Student %s, %s scheduled to %s\n", temp.getlast(), temp.getfirst(), entry.getCourseID()));
                    }
                }
                if (isScheduled = true) {
                dropStudentText.append(String.format("Student %s, %s %s has dropped from %s\n", student.getlast(), student.getfirst(),student.getstudentID(), entry.getCourseID())); }
                else {
                dropStudentText.append(String.format("Student %s, %s %s has dropped from the waitlist for %s\n", student.getlast(), student.getfirst(),student.getstudentID(), entry.getCourseID()));
                }
                ScheduleQueries.dropStudentScheduleByCourse(semester, student.getstudentID(), entry.getCourseID());
            }
            dropStudentText.append("\n");
        }
        StudentQueries.dropStudent(student.getstudentID());
        rebuildCourseStudentComboBoxes(currentSemester);

    }//GEN-LAST:event_dropStudentButtonActionPerformed

    private void displayedCourseListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayedCourseListButtonActionPerformed
        rebuildCourseList();
    }//GEN-LAST:event_displayedCourseListButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addCourseButton;
    private javax.swing.JTextField addCourseCode;
    private javax.swing.JTextField addCourseDescription;
    private javax.swing.JSpinner addCourseSeats;
    private javax.swing.JLabel addSemesterStatusLabel;
    private javax.swing.JButton addSemesterSubmitButton;
    private javax.swing.JTextField addSemesterTextfield;
    private javax.swing.JButton addStudentButton;
    private javax.swing.JTextField addStudentFirst;
    private javax.swing.JTextField addStudentID;
    private javax.swing.JLabel addStudentLabel;
    private javax.swing.JTextField addStudentLast;
    private javax.swing.JButton changeSemesterButton;
    private javax.swing.JLabel courseLabel;
    private javax.swing.JComboBox<String> currentSemesterComboBox;
    private javax.swing.JLabel currentSemesterLabel;
    private javax.swing.JButton displayCoursesButton;
    private javax.swing.JTable displayCoursesTable;
    private javax.swing.JButton displayScheduleButton;
    private javax.swing.JTable displayScheduleTable;
    private javax.swing.JButton displayedCourseListButton;
    private javax.swing.JComboBox<String> dropCourseAdmin;
    private javax.swing.JButton dropCourseAdminButton;
    private javax.swing.JTextArea dropCourseAdminText;
    private javax.swing.JComboBox<String> dropCourseCombo;
    private javax.swing.JButton dropCourseCourse;
    private javax.swing.JComboBox<String> dropCourseStudent;
    private javax.swing.JTextArea dropCourseStudentText;
    private javax.swing.JComboBox<String> dropStudent;
    private javax.swing.JButton dropStudentButton;
    private javax.swing.JTextArea dropStudentText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JComboBox<String> schCourse;
    private javax.swing.JComboBox<String> schStudent;
    private javax.swing.JButton scheduleCourseButton;
    private javax.swing.JLabel scheduleCoursesLabel;
    private javax.swing.JTable scheduledStudentsTable;
    private javax.swing.JComboBox<String> selectCourseList;
    private javax.swing.JComboBox<String> selectStudentButton;
    private javax.swing.JButton selectStudentDropButton;
    private javax.swing.JTable waitlistedStudentsTable;
    // End of variables declaration//GEN-END:variables
}

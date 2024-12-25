# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-keep class javax.script.** { *; }
-keep class com.sun.script.javascript.** { *; }
-keep class org.mozilla.javascript.** { *; }
-dontwarn java.awt.AWTEvent
-dontwarn java.awt.ActiveEvent
-dontwarn java.awt.BorderLayout
-dontwarn java.awt.Color
-dontwarn java.awt.Component
-dontwarn java.awt.Container
-dontwarn java.awt.Dimension
-dontwarn java.awt.EventQueue
-dontwarn java.awt.Font
-dontwarn java.awt.FontMetrics
-dontwarn java.awt.Frame
-dontwarn java.awt.Graphics
-dontwarn java.awt.GridBagConstraints
-dontwarn java.awt.GridBagLayout
-dontwarn java.awt.GridLayout
-dontwarn java.awt.Insets
-dontwarn java.awt.LayoutManager
-dontwarn java.awt.MenuComponent
-dontwarn java.awt.Point
-dontwarn java.awt.Polygon
-dontwarn java.awt.Rectangle
-dontwarn java.awt.Toolkit
-dontwarn java.awt.event.ActionEvent
-dontwarn java.awt.event.ActionListener
-dontwarn java.awt.event.ComponentEvent
-dontwarn java.awt.event.ComponentListener
-dontwarn java.awt.event.ContainerEvent
-dontwarn java.awt.event.ContainerListener
-dontwarn java.awt.event.KeyAdapter
-dontwarn java.awt.event.KeyEvent
-dontwarn java.awt.event.KeyListener
-dontwarn java.awt.event.MouseAdapter
-dontwarn java.awt.event.MouseEvent
-dontwarn java.awt.event.MouseListener
-dontwarn java.awt.event.WindowAdapter
-dontwarn java.awt.event.WindowEvent
-dontwarn java.awt.event.WindowListener
-dontwarn javax.swing.AbstractButton
-dontwarn javax.swing.BorderFactory
-dontwarn javax.swing.Box
-dontwarn javax.swing.BoxLayout
-dontwarn javax.swing.ButtonGroup
-dontwarn javax.swing.CellEditor
-dontwarn javax.swing.DefaultListModel
-dontwarn javax.swing.DefaultListSelectionModel
-dontwarn javax.swing.DesktopManager
-dontwarn javax.swing.Icon
-dontwarn javax.swing.JButton
-dontwarn javax.swing.JCheckBoxMenuItem
-dontwarn javax.swing.JComboBox
-dontwarn javax.swing.JComponent
-dontwarn javax.swing.JDesktopPane
-dontwarn javax.swing.JDialog
-dontwarn javax.swing.JFileChooser
-dontwarn javax.swing.JFrame
-dontwarn javax.swing.JInternalFrame
-dontwarn javax.swing.JLabel
-dontwarn javax.swing.JList
-dontwarn javax.swing.JMenu
-dontwarn javax.swing.JMenuBar
-dontwarn javax.swing.JMenuItem
-dontwarn javax.swing.JOptionPane
-dontwarn javax.swing.JPanel
-dontwarn javax.swing.JPopupMenu
-dontwarn javax.swing.JRadioButtonMenuItem
-dontwarn javax.swing.JRootPane
-dontwarn javax.swing.JScrollPane
-dontwarn javax.swing.JSplitPane
-dontwarn javax.swing.JTabbedPane
-dontwarn javax.swing.JTable
-dontwarn javax.swing.JTextArea
-dontwarn javax.swing.JToolBar
-dontwarn javax.swing.JTree
-dontwarn javax.swing.JViewport
-dontwarn javax.swing.KeyStroke
-dontwarn javax.swing.ListModel
-dontwarn javax.swing.ListSelectionModel
-dontwarn javax.swing.LookAndFeel
-dontwarn javax.swing.SwingUtilities
-dontwarn javax.swing.UIManager
-dontwarn javax.swing.border.Border
-dontwarn javax.swing.event.CellEditorListener
-dontwarn javax.swing.event.ChangeEvent
-dontwarn javax.swing.event.DocumentEvent
-dontwarn javax.swing.event.DocumentListener
-dontwarn javax.swing.event.EventListenerList
-dontwarn javax.swing.event.InternalFrameAdapter
-dontwarn javax.swing.event.InternalFrameEvent
-dontwarn javax.swing.event.InternalFrameListener
-dontwarn javax.swing.event.ListSelectionEvent
-dontwarn javax.swing.event.ListSelectionListener
-dontwarn javax.swing.event.PopupMenuEvent
-dontwarn javax.swing.event.PopupMenuListener
-dontwarn javax.swing.event.TreeExpansionEvent
-dontwarn javax.swing.event.TreeExpansionListener
-dontwarn javax.swing.event.TreeModelEvent
-dontwarn javax.swing.event.TreeModelListener
-dontwarn javax.swing.filechooser.FileFilter
-dontwarn javax.swing.table.AbstractTableModel
-dontwarn javax.swing.table.TableCellEditor
-dontwarn javax.swing.table.TableCellRenderer
-dontwarn javax.swing.table.TableModel
-dontwarn javax.swing.text.BadLocationException
-dontwarn javax.swing.text.Caret
-dontwarn javax.swing.text.Document
-dontwarn javax.swing.text.Segment
-dontwarn javax.swing.tree.DefaultTreeCellRenderer
-dontwarn javax.swing.tree.DefaultTreeSelectionModel
-dontwarn javax.swing.tree.TreeCellRenderer
-dontwarn javax.swing.tree.TreeModel
-dontwarn javax.swing.tree.TreePath
-dontwarn javax.swing.tree.TreeSelectionModel
-dontwarn sun.reflect.CallerSensitive

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
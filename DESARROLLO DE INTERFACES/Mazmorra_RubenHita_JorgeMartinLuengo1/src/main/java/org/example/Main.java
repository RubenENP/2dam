package org.example;

import org.w3c.dom.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {
    static List<Room> roomList = new ArrayList<>();
    public static String actualRoom = "R0";

    public static JButton north;
    public static JButton south;
    public static JButton east;
    public static JButton west;

    public static JTextArea textArea;
    public static JTextArea comentarios;

    public static Panel leftPanel;
    public static Panel rightPanel;

    public static XMLJTree xmljTree;


    public static void main(String[] args) {
        JFrame frame = new JFrame("Mazmorra");
        frame.setSize(1280, 720);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(true);
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem menuItem = new JMenuItem("Load");
        JMenuItem menuItem2 = new JMenuItem("Start");
        menu.add(menuItem);
        menu.add(menuItem2);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        leftPanel = new Panel();
        leftPanel.setLayout(new CardLayout());

        xmljTree = new XMLJTree(null);
        xmljTree.setPreferredSize(new Dimension(400, 400));
        xmljTree.setVisible(false);
        leftPanel.add(new JScrollPane(xmljTree));
        menuItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("XML File", "xml");
            fileChooser.setFileFilter(filter);
            int returnVal = fileChooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                xmljTree.setPath(fileChooser.getSelectedFile().getAbsolutePath());
                xmljTree.setVisible(true);
            }
        });

        menuItem2.addActionListener(e -> setInicio());

        rightPanel = new Panel();
        rightPanel.setLayout(new CardLayout());
        rightPanel.setVisible(false);

        rightPanel.setBackground(Color.red);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        frame.getContentPane().add(splitPane);

        Panel bottomPanel = new Panel();
        bottomPanel.setLayout(new CardLayout());
        Panel topPanel = new Panel();
        topPanel.setLayout(new BorderLayout());
        north = new JButton("Norte");
        north.setVisible(false);
        south = new JButton("Sur");
        south.setVisible(false);
        east = new JButton("Este");
        east.setVisible(false);
        west = new JButton("Oeste");
        west.setVisible(false);

        textArea = new JTextArea();

        topPanel.add(north, BorderLayout.NORTH);
        topPanel.add(south, BorderLayout.SOUTH);
        topPanel.add(east, BorderLayout.EAST);
        topPanel.add(west, BorderLayout.WEST);
        topPanel.add(textArea, BorderLayout.CENTER);
        topPanel.setPreferredSize(new Dimension(400, 400));
        comentarios = new JTextArea();
        comentarios.setText("Comentarios");
        bottomPanel.add(comentarios);

        JSplitPane splitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPanel, bottomPanel);
        splitPane.setRightComponent(splitPane2);


    }

    public static class XMLJTree extends JTree {
        DefaultTreeModel dtModel = null;

        public XMLJTree(String filePath) {
            if (filePath != null) {
                setPath(filePath);
            }
        }

        public void setPath(String filePath) {
            Node root;
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(filePath);
                root = doc.getDocumentElement();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Can't parse file", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (root != null) {
                dtModel = new DefaultTreeModel(builtTreeNode(root));
                this.setModel(dtModel);
                parseXml(root);
            }
        }

        private DefaultMutableTreeNode builtTreeNode(Node root) {
            DefaultMutableTreeNode dmtNode;
            NamedNodeMap attributes = root.getAttributes();
            if (attributes != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(root.getNodeName());
                for (int i = 0; i < attributes.getLength(); i++) {
                    Node attribute = attributes.item(i);
                    sb.append(" ").append(attribute.getNodeValue());
                }
                if (Objects.equals(root.getNodeName(), "description")) {
                    sb.append(": ").append(root.getTextContent());
                }
                dmtNode = new DefaultMutableTreeNode(sb.toString());
            } else {
                dmtNode = new DefaultMutableTreeNode(root.getNodeValue());
            }

            NodeList children = root.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);
                if (child.getNodeType() == Node.ELEMENT_NODE) {
                    dmtNode.add(builtTreeNode(child));
                }
            }
            return dmtNode;
        }

        private void parseXml(Node root) {
            NodeList rooms = root.getChildNodes();

            for (int i = 1; i < rooms.getLength(); i++) {
                if (rooms.item(i).getNodeName().equals("room")) {
                    List<Door> doorList = new ArrayList<>();
                    String description = "";
                    String id = rooms.item(i).getAttributes().getNamedItem("id").getNodeValue();

                    for (int j = 1; j < rooms.item(i).getChildNodes().getLength(); j++) {
                        if (rooms.item(i).getChildNodes().item(j).getNodeName().equals("door")) {
                            doorList.add(new Door(rooms.item(i).getChildNodes().item(j).getAttributes().getNamedItem("name").getNodeValue(),
                                    rooms.item(i).getChildNodes().item(j).getAttributes().getNamedItem("dest").getNodeValue()));
                        }

                        if (rooms.item(i).getChildNodes().item(j).getNodeName().equals("description")) {
                            description = rooms.item(i).getChildNodes().item(j).getTextContent();
                        }
                    }

                    roomList.add(new Room(id, doorList, description));
                }
            }
        }
    }

    static void setInicio() {
        setRoom(0);
        xmljTree.setVisible(true);
        rightPanel.setVisible(true);

        north.addActionListener(e -> {
            Room room = roomList.stream().filter(room1 -> room1.getId().equals(actualRoom)).findFirst().orElse(null);

            Room roomDestino = roomList.stream().filter(habitacion -> habitacion.getId().equals(room.getDoorList()
                            .stream().filter(door -> door.getName().equals("Norte")).findFirst().orElse(null).getDest()))
                    .findFirst().orElse(null);

            actualRoom = roomDestino.getId();
            setRoom(roomList.indexOf(roomDestino));

            showMessage(actualRoom);
        });

        south.addActionListener(e -> {
            Room room = roomList.stream().filter(room1 -> room1.getId().equals(actualRoom)).findFirst().orElse(null);

            Room roomDestino = roomList.stream().filter(habitacion -> habitacion.getId().equals(room.getDoorList()
                            .stream().filter(door -> door.getName().equals("Sur")).findFirst().orElse(null).getDest()))
                    .findFirst().orElse(null);

            actualRoom = roomDestino.getId();
            setRoom(roomList.indexOf(roomDestino));

            showMessage(actualRoom);
        });

        east.addActionListener(e -> {
            Room room = roomList.stream().filter(room1 -> room1.getId().equals(actualRoom)).findFirst().orElse(null);

            Room roomDestino = roomList.stream().filter(habitacion -> habitacion.getId().equals(room.getDoorList()
                            .stream().filter(door -> door.getName().equals("Este")).findFirst().orElse(null).getDest()))
                    .findFirst().orElse(null);

            actualRoom = roomDestino.getId();
            setRoom(roomList.indexOf(roomDestino));

            showMessage(actualRoom);
        });

        west.addActionListener(e -> {
            Room room = roomList.stream().filter(room1 -> room1.getId().equals(actualRoom)).findFirst().orElse(null);

            Room roomDestino = roomList.stream().filter(habitacion -> habitacion.getId().equals(room.getDoorList()
                            .stream().filter(door -> door.getName().equals("Oeste")).findFirst().orElse(null).getDest()))
                    .findFirst().orElse(null);

            actualRoom = roomDestino.getId();
            setRoom(roomList.indexOf(roomDestino));

            showMessage(actualRoom);
        });
    }

    private static void showMessage(String id) {
        switch (id) {
            case "R0":
                comentarios.append("\nHas vuelto al principiu ho");
                break;
            case "R1":
                comentarios.append("\nAqui hace friu, ¿Que estamus en Andorra o que?");
                break;
            case "R2":
                comentarios.append("\nGondios que calor hiju, no tendrás un agüita ho");
                break;
            case "R3":
                comentarios.append("\nTe has encontrado una nintendo jefe ¿Oiste?");
                break;
            case "R4":
                comentarios.append("\nEsta chuck norris reza pa que no te pegue un tiro , vaya");
                break;
            case "R5":
                comentarios.append("\nTe encontraste a un Darell salvaje WUOOO");
                break;
            case "R6":
                comentarios.append("\nEsta raul, GOL GOL GOL de Raul, se ha colau un caballo, y un pato");
                break;
            case "R7":
                comentarios.append("\nUna llave ho, pa que sera?");
                break;
            case "R8":
                comentarios.append("\nUn audi primo, ¿Que hago lo vendo o quee?");
                break;
            default:
                comentarios.append("\nPor ahi no jefe :(");
        }

    }

    static void setRoom(int index) {
        north.setVisible(false);
        south.setVisible(false);
        east.setVisible(false);
        west.setVisible(false);

        textArea.setText(roomList.get(index).getDescription());
        textArea.setEditable(false);
        comentarios.setText("Bienvenido Jefe");
        comentarios.setEditable(false);
        for (int i = 0; i < roomList.get(index).getDoorList().size(); i++) {
            switch (roomList.get(index).getDoorList().get(i).getName()) {
                case "Norte":
                    north.setVisible(true);
                    break;
                case "Sur":
                    south.setVisible(true);
                    break;
                case "Este":
                    east.setVisible(true);
                    break;
                case "Oeste":
                    west.setVisible(true);
                    break;
            }
        }
    }
}

class Room {
    private List<Door> doorList;
    private String description;
    private String id;

    Room(String id, List<Door> doorList, String description) {
        this.id = id;
        this.doorList = doorList;
        this.description = description;
    }

    public List<Door> getDoorList() {
        return doorList;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }
}

class Door {
    private String name;
    private String dest;

    Door(String name, String dest) {
        this.name = name;
        this.dest = dest;
    }

    public String getName() {
        return name;
    }

    public String getDest() {
        return dest;
    }
}
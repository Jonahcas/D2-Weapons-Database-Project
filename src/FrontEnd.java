import javax.swing.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//--------------------------------------
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class FrontEnd extends JFrame{
    private JPanel panel1;
    private JLabel title;
    private JComboBox wtOptions;
    private JComboBox dtOptions;
    private JComboBox ftOptions;
    private JComboBox ObtFromOptions;
    private JComboBox nameOptions;
    private JComboBox CraftOptions;
    private JComboBox RarityOptions;
    private JLabel wtFilter;
    private JLabel dtFilter;
    private JLabel ftFilter;
    private JLabel nameFilter;
    private JLabel whereFilter;
    private JLabel craftCheck;
    private JLabel rarFilter;
    private JLabel placeholder;
    private JLabel placeholder2;
    private JLabel weaponInfo;
    private JLabel weaponinfo2;
    private JLabel weaponinfo3;
    private JLabel weaponinfo4;
    private JLabel weaponinfo5;
    private JLabel weaponinfo6;
    private JLabel weaponinfo7;
    private JLabel weaponinfo8;
    private JLabel weaponinfo9;
    // private JLabel weaponInfo;

    public FrontEnd() {

        initGUI();

        wtOptions.addActionListener(e -> {
        // If a certain weapon type is selected, disable all irrelevant data (frames,RPMs, etc),
            // then query the SQL database and return all relevant data into the weaponInfo label.
            String selection = (String)wtOptions.getSelectedItem();
            String query = "select * from weapons where Weapon_Type = " + '"' + selection + '"';
            String result = dbQuery(query);
            weaponInfo.setText(result);
        });
        nameOptions.addActionListener(e -> {
            String selection = (String)nameOptions.getSelectedItem();
            String query = "select * from weapons where Name = " + '"' + selection + '"';
            dbQuery(query);
            String result = dbQuery(query);
            weaponInfo.setText(result);
        });
        dtOptions.addActionListener(e -> {
            String selection = (String)dtOptions.getSelectedItem();
            String query = "select * from weapons where Damage_Type = " + "'" + selection + "'";
            dbQuery(query);
            String result = dbQuery(query);
            weaponInfo.setText(result);
        });
        ftOptions.addActionListener(e -> {
            String selection = (String)ftOptions.getSelectedItem();
            String query = "select * from weapons where Frame_Type = " + "'" + selection + "'";
            dbQuery(query);
            String result = dbQuery(query);
            weaponInfo.setText(result);
        });
        ObtFromOptions.addActionListener(e -> {
            String selection = (String)ObtFromOptions.getSelectedItem();
            String query = "select * from weapons where Obtained_From = " + "'" + selection + "'";
            dbQuery(query);
            String result = dbQuery(query);
            weaponInfo.setText(result);
        });
        CraftOptions.addActionListener(e -> {
            String selection = (String)CraftOptions.getSelectedItem();
            String query = "select * from weapons where Craftable = " +  selection;
            dbQuery(query);
            String result = dbQuery(query);
            weaponInfo.setText(result);
        });
        RarityOptions.addActionListener(e -> {
            String selection = (String)RarityOptions.getSelectedItem();
            String query = "select * from weapons where Rarity = " + "'" + selection + "'";
            dbQuery(query);
            String result = dbQuery(query);
            weaponInfo.setText(result);
        });
    }

    public String dbQuery(String query)
    {
        //Spaghetti code goes here
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/weapons_schema", "root", "TrustNo.1");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            LinkedList<String> WeaponList = new LinkedList<>();

            while (resultSet.next()) {
                String wDetails = resultSet.getString("Name") + " is a " +
                                resultSet.getString("RPM") + " RPM " +
                                resultSet.getString("Frame_Type") + " Frame " +
                                resultSet.getString("Weapon_Type") + " obtained from " +
                                resultSet.getString("Obtained_From") + "." + "\n";
                WeaponList.add(wDetails);

                // Various JLabels
                JLabel[] arr = new JLabel[9];
                arr[0] = weaponInfo;
                arr[1] = weaponinfo2;
                arr[2] = weaponinfo3;
                arr[3] = weaponinfo4;
                arr[4] = weaponinfo5;
                arr[5] = weaponinfo6;
                arr[6] = weaponinfo7;
                arr[7] = weaponinfo8;
                arr[8] = weaponinfo9;

                for (int i = 0; i < WeaponList.size(); i++)
                {
                    // Go through each node in the Linked List, placing them in the corresponding JLabel
                    arr[i].setEnabled(true);
                    arr[i].setText(WeaponList.get(i));
                }
            }
            System.out.println(WeaponList);
            return String.valueOf(WeaponList);
        }

        catch (Exception e)
        {
            e.printStackTrace();
            return "Error: Could not find weapon(s)";
        }
    }

    private void initGUI()
    {
        setContentPane(panel1);
        setSize(1000, 250);
        setTitle("Destiny 2 Weapon Viewer - Witch Queen Edition!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        FrontEnd FE = new FrontEnd();
    }


}

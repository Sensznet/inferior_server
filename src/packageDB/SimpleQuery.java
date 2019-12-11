package packageDB;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author trix <(^+^)>
 */
public class SimpleQuery extends Thread{
    private volatile static SimpleQuery sq;
    
    private static final String hostname="localhost";
    private static final String port="3306";
    private static final String dbname="mmog";
    private static final String user="root";
    private static final String password="";
    //#####
    private static String url = "jdbc:mysql://"+hostname+":"+port+"/"+dbname;
    //####
    private static crypt cryption =  new crypt();
    private static String sID;
    private static int ID;
    private static ResultSet rs;
    private static String newIderrorCode;
    private static int xPos=10;                //anfangswerte deklarieren
    private static int yPos=10;
    private static int Richtung=1;
    private static int EXP=0;
    private static int LVL=0;
    private static int AGILITY=1;
    private static int STRENGTH=1;
    private static int INTELLIGENCE=1;
    private static int GOLD=3;
    private static int HPmax=(STRENGTH*20)+20;
    private static int HPmom=HPmax;
    private static int MANAmax=(INTELLIGENCE*15);
    private static int MANAmom=MANAmax;
    private static Connection conn;
    private static Statement stmt;
    /*
     * //##################################//
     * //DATABASE QUERY NOTICE RESULTVALUES//
     * //##################################//
     * aktualisiereAcc --> neuer wert eingeben
     * incXXX          --> wert um den erhoeht werden soll eingeben
     *
     */
    public SimpleQuery()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        }
        catch(Exception e)
        {
             e.printStackTrace();
        }
        try
        {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
        }
        catch(SQLException sqle)
        {

        }
    }
    
    public static SimpleQuery getInstance() {
        if(sq == null) {
            synchronized (SimpleQuery.class) {
                if(sq == null) {
                    sq = new SimpleQuery();
                }
            }
        }
        return sq;
    }
    
    public static int getIDbyNAME(String Name) 
            /*
             *  RESULT = INT || if error int=0 --> name/id nicht vorhanden
             */
    {
        String spruefung="";        
        try
        {                             
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_data WHERE name='"+Name+"';";
            rs = stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                spruefung=rs.getString(1);
            }
            int sID=Integer.parseInt(spruefung);
            if(sID==0)
            {
                ID=0;
                return ID;
            }
            else
            {
                String sqlCommand1=
                        "SELECT id FROM user_data WHERE name='"+Name+"';";
                rs = stmt.executeQuery(sqlCommand1);
                while(rs.next())
                {
                     spruefung=rs.getString(1);
                }
                sID=Integer.parseInt(spruefung);
                return sID;
            }            
        }
        catch(SQLException sqle)
        {          
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
            return ID;
        }       
    }
    public static String getPWbyID(int IDloc)
            /*
             * RESULT = string ||
             * er0 --> ID nicht vorhanden
             * er1 --> SQL error
             * PWs sollten laenger als 3 buchstaben sein
             */
    {
        String ausgabeloc = "";
        int countloc=0;
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_log WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc="er0";
            }
            else
            {
                String sqlCommand1=
                        "SELECT password FROM user_log WHERE ID='"+IDloc+"';";
                rs=stmt.executeQuery(sqlCommand1);
                while(rs.next())
                {
                    ausgabeloc=rs.getString(1);
                }              
            }
        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
            ausgabeloc="er1";
        }
        ausgabeloc=cryption.decrypt(ausgabeloc);
        return ausgabeloc;
    }
    public static ArrayList<String[]> getPlayers() {
        String sqlCommand0 = "SELECT ID, xPos, yPos, name, HPmom, Manamom, LVL, EXP,STRENGTH, AGILITY, INTELLIGENCE, Richtung FROM user_data;";
        ArrayList<String[]> rows = new ArrayList<String[]>();
        try {
            rs=stmt.executeQuery(sqlCommand0);
            ResultSetMetaData meta = rs.getMetaData();
            int colCount = meta.getColumnCount();
            while(rs.next()) {
                String[] row = new String[colCount];
                for (int index=1; index<=colCount; index++) {
                    row[index-1] = rs.getString(index);
                }
                rows.add(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SimpleQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rows;
    }
    
    public static int getxPosbyID(int IDloc)
            /*
             * RESULT = int ||
             * int=0 --> fehler
             */
    {
        int ausgabeloc=0;
        int countloc=0;
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_data WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=0;
            }
            else
            {
                String sqlCommand1=
                        "SELECT xPos from user_data WHERE id='"+IDloc+"';";
                rs=stmt.executeQuery(sqlCommand1);
                while(rs.next())
                {
                    ausgabeloc=rs.getInt(1);
                }
            }
        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
        }
        return ausgabeloc;
    }
    public static int getyPosbyID(int IDloc)
            /*
             * RESULT = int ||
             * int=0 --> fehler
             */
    {
        int ausgabeloc=0;
        int countloc=0;
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_data WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=0;
            }
            else
            {
                String sqlCommand1=
                        "SELECT yPos from user_data WHERE id='"+IDloc+"';";
                rs=stmt.executeQuery(sqlCommand1);
                while(rs.next())
                {
                    ausgabeloc=rs.getInt(1);
                }
            }
        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
        }
        return ausgabeloc;
    }
    public static int getRichtungbyID(int IDloc)
            /*
             * RESULT = int ||
             * int=0 --> fehler
             */
    {
        int ausgabeloc=0;
        int countloc=0;
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_data WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=0;
            }
            else
            {
                String sqlCommand1=
                        "SELECT richtung from user_data WHERE id='"+IDloc+"';";
                rs=stmt.executeQuery(sqlCommand1);
                while(rs.next())
                {
                    ausgabeloc=rs.getInt(1);
                }
            }
        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
        }
        return ausgabeloc;
    }
    public static int getEXPbyID(int IDloc)
            /*
             * RESULT = int ||
             * int=0 --> fehler
             */
    {
        int ausgabeloc=0;
        int countloc=0;
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_data WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=0;
            }
            else
            {
                String sqlCommand1=
                        "SELECT exp from user_data WHERE id='"+IDloc+"';";
                rs=stmt.executeQuery(sqlCommand1);
                while(rs.next())
                {
                    ausgabeloc=rs.getInt(1);
                }
            }
        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
        }
        return ausgabeloc;
    }
    public static int getLVLbyID(int IDloc)
            /*
             * RESULT = int ||
             * int=0 --> fehler
             */
    {
        int ausgabeloc=0;
        int countloc=0;
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_data WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=0;
            }
            else
            {
                String sqlCommand1=
                        "SELECT LVL from user_data WHERE id='"+IDloc+"';";
                rs=stmt.executeQuery(sqlCommand1);
                while(rs.next())
                {
                    ausgabeloc=rs.getInt(1);
                }
            }
        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
        }
        return ausgabeloc;
    }
    public static int getAGIbyID(int IDloc)
            /*
             * RESULT = int ||
             * int=0 --> fehler
             */
    {
        int ausgabeloc=0;
        int countloc=0;
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_data WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=0;
            }
            else
            {
                String sqlCommand1=
                        "SELECT agility from user_data WHERE id='"+IDloc+"';";
                rs=stmt.executeQuery(sqlCommand1);
                while(rs.next())
                {
                    ausgabeloc=rs.getInt(1);
                }
            }
        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
        }
        return ausgabeloc;
    }
    public static int getSTRbyID(int IDloc)
            /*
             * RESULT = int ||
             * int=0 --> fehler
             */
    {
        int ausgabeloc=0;
        int countloc=0;
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_data WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=0;
            }
            else
            {
                String sqlCommand1=
                        "SELECT strength from user_data WHERE id='"+IDloc+"';";
                rs=stmt.executeQuery(sqlCommand1);
                while(rs.next())
                {
                    ausgabeloc=rs.getInt(1);
                }
            }
        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
        }
        return ausgabeloc;
    }
    public static int getINTbyID(int IDloc)
            /*
             * RESULT = int ||
             * int=0 --> fehler
             */
    {
        int ausgabeloc=0;
        int countloc=0;
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_data WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=0;
            }
            else
            {
                String sqlCommand1=
                        "SELECT intelligence from user_data WHERE id='"+IDloc+"';";
                rs=stmt.executeQuery(sqlCommand1);
                while(rs.next())
                {
                    ausgabeloc=rs.getInt(1);
                }
            }
        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
        }
        return ausgabeloc;
    }
    public static int getHPmombyID(int IDloc)
            /*
             * RESULT = int ||
             * int=0 --> fehler
             */
    {
        int ausgabeloc=0;
        int countloc=0;
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_data WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=0;
            }
            else
            {
                String sqlCommand1=
                        "SELECT HPmom from user_data WHERE id='"+IDloc+"';";
                rs=stmt.executeQuery(sqlCommand1);
                while(rs.next())
                {
                    ausgabeloc=rs.getInt(1);
                }
            }
        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
        }
        return ausgabeloc;
    }
    public static int getHPmaxbyID(int IDloc)
            /*
             * RESULT = int ||
             * int=0 --> fehler
             */
    {
        int ausgabeloc=0;
        int countloc=0;
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_data WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=0;
            }
            else
            {
                String sqlCommand1=
                        "SELECT HPmax from user_data WHERE id='"+IDloc+"';";
                rs=stmt.executeQuery(sqlCommand1);
                while(rs.next())
                {
                    ausgabeloc=rs.getInt(1);
                }
            }
        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
        }
        return ausgabeloc;
    }
    public static int getMANAmombyID(int IDloc)
            /*
             * RESULT = int ||
             * int=0 --> fehler
             */
    {
        int ausgabeloc=0;
        int countloc=0;
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_data WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=0;
            }
            else
            {
                String sqlCommand1=
                        "SELECT MANAmom from user_data WHERE id='"+IDloc+"';";
                rs=stmt.executeQuery(sqlCommand1);
                while(rs.next())
                {
                    ausgabeloc=rs.getInt(1);
                }
            }
        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
        }
        return ausgabeloc;
    }
    public static int getMANAmaxbyID(int IDloc)
            /*
             * RESULT = int ||
             * int=0 --> fehler
             */
    {
        int ausgabeloc=0;
        int countloc=0;
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_data WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=0;
            }
            else
            {
                String sqlCommand1=
                        "SELECT MANAmax from user_data WHERE id='"+IDloc+"';";
                rs=stmt.executeQuery(sqlCommand1);
                while(rs.next())
                {
                    ausgabeloc=rs.getInt(1);
                }
            }
        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
        }
        return ausgabeloc;
    }
    public static String createnewAcc(String Nameloc, String Passwordloc, String Emailloc) //rdy
            /*
             * Result = String || ID des neuen accounts bzw errorcode
             * errorcode    er0 --> name schon vorhanden
             *              er1 --> fehler in mySQL
             */
    {
        int hundert=100;    //bestimmt ab welcher nummer die IDs beginnen
        int AnzahlUser;
        String spruefung="";
        int ipruefung;
        String returnwert;
        String itemCode=("noitem");
        int idnewacc = 0;
        Passwordloc=cryption.encrypt(Passwordloc);
        try
        {
            //####
            //pruefe ob name schon vorhanden
            //####
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_log WHERE name='"+Nameloc+"';";
            rs = stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                spruefung=rs.getString(1);
            }

            ipruefung=Integer.parseInt(spruefung);
            if(ipruefung==1)                 //name schon vorhanden
            {                                //
                returnwert=("er0");            //
                return returnwert;           //ID(0) wird zurueckgegeben
            }                                //

            String sqlCommand1=
                    "SELECT COUNT(*) FROM user_log";
            rs = stmt.executeQuery(sqlCommand1);
            while (rs.next())
            {
                sID = rs.getString(1);
            }
            AnzahlUser = Integer.parseInt(sID)+hundert; //die IDs beginnen somit ab 100                       

            String sqlCommand=
                    "INSERT INTO `mmog`.`user_log` ("+
                    "`ID`,"+
                    "`name`,"+
                    "`password`,"+
                    "`email`,"+
                    "`aktiv`,"+
                    "`Rang`,"+
                    "`AnmeldeDatum`) "+
                    "VALUES ("+
                    "'"+AnzahlUser+"',"+
                    "'"+Nameloc+"',"+ 
                    "'"+Passwordloc+"',"+
                    "'"+Emailloc+"',"+
                    "'1',"+
                    "'1',"+
                    "now());";
            stmt.executeUpdate(sqlCommand);
            
            String sqlCommand2=
                                "INSERT INTO `mmog`.`user_data` ("+
                                "`ID`," +
                                "`name`,"+                                                               
                                "`xPos`,"+
                                "`yPos`,"+
                                "`Richtung`,"+
                                "`EXP`,"+
                                "`LVL`,"+
                                "`AGILITY`,"+
                                "`STRENGTH`,"+
                                "`INTELLIGENCE`,"+
                                "`GOLD`,"+
                                "`HPmom`,"+
                                "`HPmax`,"+
                                "`MANAmom`,"+
                                "`MANAmax`)"+
                                "VALUES ("+
                                "'"+AnzahlUser+"',"+
                                "'"+Nameloc+"',"+
                                "'"+xPos+"',"+
                                "'"+yPos+"',"+
                                "'"+Richtung+"',"+
                                "'"+EXP+"',"+
                                "'"+LVL+"',"+
                                "'"+AGILITY+"',"+
                                "'"+STRENGTH+"',"+
                                "'"+INTELLIGENCE+"',"+
                                "'"+GOLD+"',"+
                                "'"+HPmom+"',"+
                                "'"+HPmax+"',"+
                                "'"+MANAmom+"',"+
                                "'"+MANAmax+"');";
            stmt.executeUpdate(sqlCommand2);    //account wird in user_data erstellt
            
            String sqlCommand3=
                                "INSERT INTO `mmog`.`user_inventar` ("+
                                "`ID` ," +
                                "`name`,"+
                                "`SLOT01`,"+       
                                "`SLOT02`,"+
                                "`SLOT03`,"+
                                "`SLOT04`,"+
                                "`SLOT05`,"+
                                "`SLOT06`,"+
                                "`SLOT07`,"+
                                "`SLOT08`,"+
                                "`SLOT09`,"+
                                "`SLOT10`)"+
                                "VALUES ("+
                                "'"+AnzahlUser+"',"+
                                "'"+Nameloc+"',"+
                                "'"+itemCode+"',"+      //item01
                                "'"+itemCode+"',"+      //item02
                                "'"+itemCode+"',"+      //item03
                                "'"+itemCode+"',"+      //item04
                                "'"+itemCode+"',"+      //item05
                                "'"+itemCode+"',"+      //item06
                                "'"+itemCode+"',"+      //item07
                                "'"+itemCode+"',"+      //item08
                                "'"+itemCode+"',"+      //item09
                                "'"+itemCode+"');";     //item10
            stmt.executeUpdate(sqlCommand3);
            idnewacc=AnzahlUser;
            returnwert=String.valueOf(idnewacc);
            return returnwert;                      //id des neuen accs wird zurueckgegeben
        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
            sqle.printStackTrace();
            returnwert=("er1");
            return returnwert;                      //fehler in mySQL
        }
    }
    public static boolean aktualisiereAcc(int IDloc, int xPosloc, int yPosloc, int HPmomloc, int MANAmomloc)
            /*
             *Result = Boolean ||
             * true-->aktualisiert
             * false->fehler 
             */
    {
        boolean ausgabeloc=false;
        int countloc=0;
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_data WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=false;
            }
            else
            {
                String sqlCommand1=
                        "UPDATE user_data SET xPos='"+xPosloc+"', yPos='"+yPosloc+"', HPmom='"+HPmomloc+"', MANAmom='"+MANAmomloc+"' WHERE id='"+IDloc+"';";
                stmt.executeUpdate(sqlCommand1);
                ausgabeloc=true;
            }
                                      
        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
            ausgabeloc=false;
        }
        return ausgabeloc;
    }
    public static boolean aktualisiereAccExp(int IDloc, int Exploc)
            /*
             *Result = Boolean ||
             * true-->aktualisiert
             * false->fehler
             */
    {
        boolean ausgabeloc=false;
        int countloc=0;
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_data WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }            
            if(countloc==0)
            {
                ausgabeloc=false;
            }
            else
            {
                String sqlCommand1=
                        "UPDATE user_data SET exp='"+Exploc+"' WHERE id='"+IDloc+"';";
                stmt.executeUpdate(sqlCommand1);
                ausgabeloc=true;

            }

        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
            ausgabeloc=false;
        }
        return ausgabeloc;
    }
    public static boolean aktualisiereAccLVL(int IDloc, int LVLloc)
            /*
             *Result = Boolean ||
             * true-->aktualisiert
             * false->fehler
             */
    {
        boolean ausgabeloc=false;
        int countloc=0;
        try
        {
            conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_data WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=false;
            }
            else
            {
                String sqlCommand1=
                        "UPDATE user_data SET LVL='"+LVLloc+"' WHERE id='"+IDloc+"';";
                stmt.executeUpdate(sqlCommand1);
                ausgabeloc=true;
            }

        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
            ausgabeloc=false;
        }
        return ausgabeloc;
    }
    public static boolean aktualisiereAccGold(int IDloc, int Goldloc)
            /*
             *Result = Boolean ||
             * true-->aktualisiert
             * false->fehler
             */
     {
        boolean ausgabeloc=false;
        int countloc=0;
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_data WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=false;
            }
            else
            {
                String sqlCommand1=
                        "UPDATE user_data SET gold='"+Goldloc+"' WHERE id='"+IDloc+"';";
                stmt.executeUpdate(sqlCommand1);
                ausgabeloc=true;
            }

        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
            ausgabeloc=false;
        }
        return ausgabeloc;
    }
    public static boolean aktualisiereAccAgi(int IDloc, int Agiloc)
            /*
             *Result = Boolean ||
             * true-->aktualisiert
             * false->fehler
             */
     {
        boolean ausgabeloc=false;
        int countloc=0;
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_data WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=false;
            }
            else
            {
                String sqlCommand1=
                        "UPDATE user_data SET Agility='"+Agiloc+"' WHERE id='"+IDloc+"';";
                stmt.executeUpdate(sqlCommand1);
                ausgabeloc=true;
            }

        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
            ausgabeloc=false;
        }
        return ausgabeloc;
    }
    public static boolean aktualisiereAccStr(int IDloc, int Strloc)
            /*
             *Result = Boolean ||
             * true-->aktualisiert
             * false->fehler
             */
     {
        boolean ausgabeloc=false;
        int countloc=0;
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_data WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=false;
            }
            else
            {
                String sqlCommand1=
                        "UPDATE user_data SET Strength='"+Strloc+"' WHERE id='"+IDloc+"';";
                stmt.executeUpdate(sqlCommand1);
                ausgabeloc=true;
            }

        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
            ausgabeloc=false;
        }
        return ausgabeloc;
    }
    public static boolean aktualisiereAccInt(int IDloc, int Intloc)
            /*
             *Result = Boolean ||
             * true-->aktualisiert
             * false->fehler
             */
     {
        boolean ausgabeloc=false;
        int countloc=0;
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_data WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=false;
            }
            else
            {
                String sqlCommand1=
                        "UPDATE user_data SET Intelligence='"+Intloc+"' WHERE id='"+IDloc+"';";
                stmt.executeUpdate(sqlCommand1);
                ausgabeloc=true;
            }

        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
            ausgabeloc=false;
        }
        return ausgabeloc;
    }
    public static boolean aktualisiereAccHPmax(int IDloc, int HPmaxloc)
            /*
             *Result = Boolean ||
             * true-->aktualisiert
             * false->fehler
             */
     {
        boolean ausgabeloc=false;
        int countloc=0;        
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_data WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=false;
            }
            else
            {
                String sqlCommand1=
                        "UPDATE user_data SET HPmax='"+HPmaxloc+"' WHERE id='"+IDloc+"';";
                stmt.executeUpdate(sqlCommand1);
                ausgabeloc=true;
            }

        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
            ausgabeloc=false;
        }
        return ausgabeloc;
    }
    public static boolean aktualisiereAccMANAmax(int IDloc, int MANAmaxloc)
            /*
             *Result = Boolean ||
             * true-->aktualisiert
             * false->fehler
             */
     {
        boolean ausgabeloc=false;
        int countloc=0;        
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_data WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=false;
            }
            else
            {
                String sqlCommand1=
                        "UPDATE user_data SET MANAmax='"+MANAmaxloc+"' WHERE id='"+IDloc+"';";
                stmt.executeUpdate(sqlCommand1);
                ausgabeloc=true;
            }

        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
            ausgabeloc=false;
        }
        return ausgabeloc;
    }

    public static boolean incxPos(int IDloc, int xPosincloc)
            /*
             *Result = Boolean ||
             * true-->aktualisiert
             * false->fehler
             */
     {
        boolean ausgabeloc=false;
        int countloc=0;     
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_data WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=false;
            }
            else
            {
                String sqlCommand1=
                        "UPDATE user_data SET xPos=xPos+'"+xPosincloc+"' WHERE id='"+IDloc+"';";
                stmt.executeUpdate(sqlCommand1);
                ausgabeloc=true;
            }

        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
            ausgabeloc=false;
        }
        return ausgabeloc;
    }
    public static boolean incyPos(int IDloc, int yPosincloc)
            /*
             *Result = Boolean ||
             * true-->aktualisiert
             * false->fehler
             */
     {
        boolean ausgabeloc=false;
        int countloc=0;
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_data WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=false;
            }
            else
            {
                String sqlCommand1=
                        "UPDATE user_data SET yPos=yPos+'"+yPosincloc+"' WHERE id='"+IDloc+"';";
                stmt.executeUpdate(sqlCommand1);
                ausgabeloc=true;
            }

        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
            ausgabeloc=false;
        }
        return ausgabeloc;
    }
    public static boolean incEXP(int IDloc, int EXPincloc)
            /*
             *Result = Boolean ||
             * true-->aktualisiert
             * false->fehler
             */
     {
        boolean ausgabeloc=false;
        int countloc=0;        
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_data WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=false;
            }
            else
            {
                String sqlCommand1=
                        "UPDATE user_data SET exp=exp+'"+EXPincloc+"' WHERE id='"+IDloc+"';";
                stmt.executeUpdate(sqlCommand1);
                ausgabeloc=true;
            }

        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
            ausgabeloc=false;
        }
        return ausgabeloc;
    }
    public static boolean incLVL(int IDloc, int LVLincloc)
            /*
             *Result = Boolean ||
             * true-->aktualisiert
             * false->fehler
             */
     {
        boolean ausgabeloc=false;
        int countloc=0;        
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_data WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=false;
            }
            else
            {
                String sqlCommand1=
                        "UPDATE user_data SET lvl=lvl+'"+LVLincloc+"' WHERE id='"+IDloc+"';";
                stmt.executeUpdate(sqlCommand1);
                ausgabeloc=true;
            }

        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
            ausgabeloc=false;
        }
        return ausgabeloc;
    }
public static boolean incAGI(int IDloc, int AGIincloc)
            /*
             *Result = Boolean ||
             * true-->aktualisiert
             * false->fehler
             */
     {
        boolean ausgabeloc=false;
        int countloc=0;
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_data WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=false;
            }
            else
            {
                String sqlCommand1=
                        "UPDATE user_data SET agility=agility+'"+AGIincloc+"' WHERE id='"+IDloc+"';";
                stmt.executeUpdate(sqlCommand1);
                ausgabeloc=true;
            }
        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
            ausgabeloc=false;
        }
        return ausgabeloc;
    }
    public static boolean incSTR(int IDloc, int STRincloc)
            /*
             *Result = Boolean ||
             * true-->aktualisiert
             * false->fehler
             */
     {
        boolean ausgabeloc=false;
        int countloc=0;       
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_data WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=false;
            }
            else
            {
                String sqlCommand1=
                        "UPDATE user_data SET strength=strength+'"+STRincloc+"' WHERE id='"+IDloc+"';";
                stmt.executeUpdate(sqlCommand1);
                ausgabeloc=true;
            }

        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
            ausgabeloc=false;
        }
        return ausgabeloc;
    }
    public static boolean incINT(int IDloc, int INTincloc)
            /*
             *Result = Boolean ||
             * true-->aktualisiert
             * false->fehler
             */
     {
        boolean ausgabeloc=false;
        int countloc=0;      
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_data WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=false;
            }
            else
            {
                String sqlCommand1=
                        "UPDATE user_data SET intelligence=intelligence+'"+INTincloc+"' WHERE id='"+IDloc+"';";
                stmt.executeUpdate(sqlCommand1);
                ausgabeloc=true;
            }

        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
            ausgabeloc=false;
        }
        return ausgabeloc;
    }
    public static boolean incGold(int IDloc, int Goldincloc)
            /*
             *Result = Boolean ||
             * true-->aktualisiert
             * false->fehler
             */
     {
        boolean ausgabeloc=false;
        int countloc=0;      
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_data WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=false;
            }
            else
            {
                String sqlCommand1=
                        "UPDATE user_data SET gold=gold+'"+Goldincloc+"' WHERE id='"+IDloc+"';";
                stmt.executeUpdate(sqlCommand1);
                ausgabeloc=true;
            }

        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
            ausgabeloc=false;
        }
        return ausgabeloc;
    }
    public static boolean incHPmom(int IDloc, int HPmomincloc)
            /*
             *Result = Boolean ||
             * true-->aktualisiert
             * false->fehler
             */
     {
        boolean ausgabeloc=false;
        int countloc=0;
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_data WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=false;
            }
            else
            {
                String sqlCommand1=
                        "UPDATE user_data SET HPmom=HPmom+'"+HPmomincloc+"' WHERE id='"+IDloc+"';";
                stmt.executeUpdate(sqlCommand1);
                ausgabeloc=true;
            }
        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
            ausgabeloc=false;
        }
        return ausgabeloc;
    }
    public static boolean incHPmaxi(int IDloc, int HPmaxincloc)
            /*
             *Result = Boolean ||
             * true-->aktualisiert
             * false->fehler
             */
     {
        boolean ausgabeloc=false;
        int countloc=0;        
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_data WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=false;
            }
            else
            {
                String sqlCommand1=
                        "UPDATE user_data SET HPmax=HPmax+'"+HPmaxincloc+"' WHERE id='"+IDloc+"';";
                stmt.executeUpdate(sqlCommand1);
                ausgabeloc=true;
            }

        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
            ausgabeloc=false;
        }
        return ausgabeloc;
    }
    public static boolean incMANAmom(int IDloc, int MANAmomincloc)
            /*
             *Result = Boolean ||
             * true-->aktualisiert
             * false->fehler
             */
     {
        boolean ausgabeloc=false;
        int countloc=0;
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_data WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=false;
            }
            else
            {
                String sqlCommand1=
                        "UPDATE user_data SET MANAmom=MANAmom+'"+MANAmomincloc+"' WHERE id='"+IDloc+"';";
                stmt.executeUpdate(sqlCommand1);
                ausgabeloc=true;
            }

        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
            ausgabeloc=false;
        }
        return ausgabeloc;
    }
    public static boolean incMANAmax(int IDloc, int MANAmaxincloc)
            /*
             *Result = Boolean ||
             * true-->aktualisiert
             * false->fehler
             */
     {
        boolean ausgabeloc=false;
        int countloc=0;
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_data WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=false;
            }
            else
            {
                String sqlCommand1=
                        "UPDATE user_data SET MANAmax=MANAmax+'"+MANAmaxincloc+"' WHERE id='"+IDloc+"';";
                stmt.executeUpdate(sqlCommand1);
                ausgabeloc=true;
            }

        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
            ausgabeloc=false;
        }
        return ausgabeloc;
    }
    public static boolean inventar_01_set(int IDloc, String ItemCodeloc)
        /*
         * Result = Boolean ||
         * true-->aktualisiert
         * false-->fehler
         */
{   
        boolean ausgabeloc=false;
        int countloc=0;
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_inventar WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=false;
            }
            else
            {
                String sqlCommand1=
                        "UPDATE user_data SET SLOT01 ='"+ItemCodeloc+"WHERE ID='"+IDloc+"';";
                stmt.executeUpdate(sqlCommand1);
                ausgabeloc=true;
            }

        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
            ausgabeloc=false;
        }
        return ausgabeloc;   
    }
    public static boolean inventar_02_set(int IDloc, String ItemCodeloc)
        /*
         * Result = Boolean ||
         * true-->aktualisiert
         * false-->fehler
         */
{   
        boolean ausgabeloc=false;
        int countloc=0;
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_inventar WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=false;
            }
            else
            {
                String sqlCommand1=
                        "UPDATE user_data SET SLOT02 ='"+ItemCodeloc+"WHERE ID='"+IDloc+"';";
                stmt.executeUpdate(sqlCommand1);
                ausgabeloc=true;
            }

        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
            ausgabeloc=false;
        }
        return ausgabeloc;   
    }
    public static boolean inventar_03_set(int IDloc, String ItemCodeloc)
        /*
         * Result = Boolean ||
         * true-->aktualisiert
         * false-->fehler
         */
{
        boolean ausgabeloc=false;
        int countloc=0;
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_inventar WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=false;
            }
            else
            {
                String sqlCommand1=
                        "UPDATE user_data SET SLOT03 ='"+ItemCodeloc+"WHERE ID='"+IDloc+"';";
                stmt.executeUpdate(sqlCommand1);
                ausgabeloc=true;
            }

        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
            ausgabeloc=false;
        }
        return ausgabeloc;
    }
    public static boolean inventar_04_set(int IDloc, String ItemCodeloc)
        /*
         * Result = Boolean ||
         * true-->aktualisiert
         * false-->fehler
         */
{
        boolean ausgabeloc=false;
        int countloc=0;
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_inventar WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=false;
            }
            else
            {
                String sqlCommand1=
                        "UPDATE user_data SET SLOT04 ='"+ItemCodeloc+"WHERE ID='"+IDloc+"';";
                stmt.executeUpdate(sqlCommand1);
                ausgabeloc=true;
            }

        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
            ausgabeloc=false;
        }
        return ausgabeloc;
    }
    public static boolean inventar_05_set(int IDloc, String ItemCodeloc)
        /*
         * Result = Boolean ||
         * true-->aktualisiert
         * false-->fehler
         */
{
        boolean ausgabeloc=false;
        int countloc=0;
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_inventar WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=false;
            }
            else
            {
                String sqlCommand1=
                        "UPDATE user_data SET SLOT05 ='"+ItemCodeloc+"WHERE ID='"+IDloc+"';";
                stmt.executeUpdate(sqlCommand1);
                ausgabeloc=true;
            }

        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
            ausgabeloc=false;
        }
        return ausgabeloc;
    }
    public static boolean inventar_06_set(int IDloc, String ItemCodeloc)
        /*
         * Result = Boolean ||
         * true-->aktualisiert
         * false-->fehler
         */
{
        boolean ausgabeloc=false;
        int countloc=0;
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_inventar WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=false;
            }
            else
            {
                String sqlCommand1=
                        "UPDATE user_data SET SLOT06 ='"+ItemCodeloc+"WHERE ID='"+IDloc+"';";
                stmt.executeUpdate(sqlCommand1);
                ausgabeloc=true;
            }

        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
            ausgabeloc=false;
        }
        return ausgabeloc;
    }
    public static boolean inventar_07_set(int IDloc, String ItemCodeloc)
        /*
         * Result = Boolean ||
         * true-->aktualisiert
         * false-->fehler
         */
{
        boolean ausgabeloc=false;
        int countloc=0;
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_inventar WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=false;
            }
            else
            {
                String sqlCommand1=
                        "UPDATE user_data SET SLOT07 ='"+ItemCodeloc+"WHERE ID='"+IDloc+"';";
                stmt.executeUpdate(sqlCommand1);
                ausgabeloc=true;
            }

        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
            ausgabeloc=false;
        }
        return ausgabeloc;
    }
    public static boolean inventar_08_set(int IDloc, String ItemCodeloc)
        /*
         * Result = Boolean ||
         * true-->aktualisiert
         * false-->fehler
         */
{
        boolean ausgabeloc=false;
        int countloc=0;
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_inventar WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=false;
            }
            else
            {
                String sqlCommand1=
                        "UPDATE user_data SET SLOT08 ='"+ItemCodeloc+"WHERE ID='"+IDloc+"';";
                stmt.executeUpdate(sqlCommand1);
                ausgabeloc=true;
            }

        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
            ausgabeloc=false;
        }
        return ausgabeloc;
    }
    public static boolean inventar_09_set(int IDloc, String ItemCodeloc)
        /*
         * Result = Boolean ||
         * true-->aktualisiert
         * false-->fehler
         */
{
        boolean ausgabeloc=false;
        int countloc=0;
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_inventar WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=false;
            }
            else
            {
                String sqlCommand1=
                        "UPDATE user_data SET SLOT09 ='"+ItemCodeloc+"WHERE ID='"+IDloc+"';";
                stmt.executeUpdate(sqlCommand1);
                ausgabeloc=true;
            }

        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
            ausgabeloc=false;
        }
        return ausgabeloc;
    }
    public static boolean inventar_10_set(int IDloc, String ItemCodeloc)
        /*
         * Result = Boolean ||
         * true-->aktualisiert
         * false-->fehler
         */
{
        boolean ausgabeloc=false;
        int countloc=0;
        try
        {
            String sqlCommand0=
                    "SELECT COUNT(*) FROM user_inventar WHERE ID='"+IDloc+"';";
            rs=stmt.executeQuery(sqlCommand0);
            while(rs.next())
            {
                countloc=rs.getInt(1);
            }
            if(countloc==0)
            {
                ausgabeloc=false;
            }
            else
            {
                String sqlCommand1=
                        "UPDATE user_data SET SLOT10 ='"+ItemCodeloc+"WHERE ID='"+IDloc+"';";
                stmt.executeUpdate(sqlCommand1);
                ausgabeloc=true;
            }

        }
        catch(SQLException sqle)
        {
            System.out.println("SQL Exception: "+sqle.getMessage());
            System.out.println("SQL State: "+sqle.getSQLState());
            System.out.println("VendorError: "+sqle.getErrorCode());
            ausgabeloc=false;
        }
        return ausgabeloc;
    }
}

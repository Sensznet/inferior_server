/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package packageDB;
import java.sql.*;

/**
 *
 * @author extserv_sc
 */
public class Query {

    private static String Name="deiOmaimSchritt";
    private static String PW="password2";
    private static String Email="oli.kleemann@gmx.de";
    private static ResultSet rs;
    private static int ID=100;
    private static int xPos=5;
    private static int yPos=5;
    private static int Exp=11;
    private static int HPmom=40;
    private static int MANAmom=15;
    private static int name;
    private static SimpleQuery SQ = new SimpleQuery();
    public static void main(String[] args)
    {
       new Query();
       System.out.println(SQ.createnewAcc(Name, PW, Email));
       
     
    }
}



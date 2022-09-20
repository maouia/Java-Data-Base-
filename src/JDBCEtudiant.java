import java.sql.*;


public class JDBCEtudiant {
    private static String pilote;
    private static String url;
    private static String utilisateur;
    private static String motdepasse;
    private static Connection cx;

    public JDBCEtudiant() {
        this.pilote="";
        this.url="jdbc:mysql://localhost:3306/java";
        this.utilisateur="root";
        this.motdepasse="";
    }

    public  boolean connexion(){
        try {
            cx=DriverManager.getConnection(url,utilisateur,motdepasse);
            System.out.println("DB connected");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public  void deconnexion(){
        try{
            cx.close();
            System.out.println("\nDB disconnected");
        }catch (Exception e){

        }
    }


    public int MiseaAjour(String sql){
        try{
            if(connexion()) {
                Statement statement = cx.createStatement();
                 statement.executeUpdate(sql);
                 deconnexion();
                return 1;
            }
        }catch(Exception e){
            return -1;
        }
       return 0;

    }


    public  void affiche(){
        try{

            if(connexion()){
                Statement statement  = cx.createStatement();
                ResultSet resultset = statement.executeQuery("select * from etudiant ORDER BY nom ");
                while(resultset.next()){
                    System.out.println("cin : "+ resultset.getString("cin")+
                            " | nom : "+ resultset.getString("nom")+
                            " | pren : "+ resultset.getString("prenom"));
                }
                statement.close();
                deconnexion();
            }
            else {
                System.out.println("Connection problem");
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }


    public  void Rechercher(long cin){
        try{

            if(connexion()){
                Statement statement  = cx.createStatement();
                ResultSet resultset = statement.executeQuery("select * from etudiant WHERE cin="+cin );
                if(resultset.next()) {
                        System.out.println("Etudiant existe");
                        System.out.println("cin : " + resultset.getString("cin") +
                                " | nom : " + resultset.getString("nom") +
                                " | pren : " + resultset.getString("prenom"));


                }else {
                    System.out.println("Etudiant n`existe pas");
                }


                statement.close();
                deconnexion();
            }
            else {
                System.out.println("Connection problem");
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }




}

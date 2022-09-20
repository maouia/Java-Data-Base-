import java.util.Scanner;

public class scolarite {
   static JDBCEtudiant db  = new JDBCEtudiant();




   static void AjouterEtudiant(Etudiant e){
       if (db.MiseaAjour("INSERT INTO etudiant (`cin`, `nom`, `prenom`)"
               +"VALUES ("+e.getCin()+", '"+e.getNom()+"', '"+e.getPrenom()+"');")==1){
           System.out.println("ajout avec seccess");
       }else {
           System.out.println("ajout problem");
       }
    }


    static void UpdateEtudiant(Long cin){
        if (db.MiseaAjour("UPDATE `etudiant` SET " +
                "`nom`='"+GetNom()+"'," +
                "`prenom`='"+GetPren()+"' " +
                "WHERE cin ="+cin)==1){
            System.out.println("mise a jour avec seccess");
        }else {
            System.out.println("problem de mise a jour ");
        }
    }


    static void SupprimerEtudiant(long cin){
        if (db.MiseaAjour("DELETE FROM `etudiant` WHERE cin="+cin)==1){
            System.out.println("delete avec seccess");
        }else {
            System.out.println("delete problem");
        }
    }

    static void RechercherEtudiant(long cin){
       db.Rechercher(cin);
    }


    static long GetCin(){
       while (true){
           Scanner s = new Scanner(System.in);
           try {
               System.out.print("donner le cin : ");
               long cin = s.nextLong();
               return cin;
           }catch (Exception e){

           }
       }
}

static String GetNom(){
    while (true){
        Scanner s = new Scanner(System.in);
        try {
            System.out.print("donner le nom : ");
            String nom = s.next();
            return nom;
        }catch (Exception e){

        }
    }
}

    static String GetPren(){
        while (true){
            Scanner s = new Scanner(System.in);
            try {
                System.out.print("donner le prenom : ");
                String pren = s.next();
                return pren;
            }catch (Exception e){

            }
        }
    }


    public static void main(String[] args) {

        while (true){

            System.out.println("1) Ajouter étudiant");
            System.out.println("2) Rechercher étudiant");
            System.out.println("3) Afficher les étudiants");
            System.out.println("4) Supprimer étudiant");
            System.out.println("5) Mise a jour étudiant");
            System.out.println("6) Quitter");

            Scanner s = new Scanner(System.in);
            String chose = s.next();
            switch(chose) {
                case "1":
                    System.out.println("-- Ajouter étudiant --");
                    Etudiant e = new Etudiant(GetCin(),GetNom(),GetPren());
                    AjouterEtudiant(e);
                    break;
                case "2":
                    System.out.println("-- Rechercher étudiant --");
                    RechercherEtudiant(GetCin());
                    break;
                case "3":
                    db.affiche();
                    break;
                case "4":
                    System.out.println("-- Supprimer étudiant --");
                    SupprimerEtudiant(GetCin());
                    break;
                case "5" :
                    System.out.println("-- Update étudiant --");
                    UpdateEtudiant(GetCin());
                    break;
                case "6":
                    System.out.println("By BY");
                    return;

                default:
                    System.out.println("choix invalide");
            }
        }
    }
}

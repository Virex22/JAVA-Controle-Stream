import models.Etudiant;
import models.Evaluation;
import models.Note;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Application {

    public static List<Etudiant> getData(){
        Etudiant etudiant1 = new Etudiant("bansept", "franck");
        etudiant1.getListeNote().add(new Note("JAVA", 15));
        etudiant1.getListeNote().add(new Note("PHP", 10));
        etudiant1.getListeNote().add(new Note("UML", 5));

        Etudiant etudiant2 = new Etudiant("doe", "simon");
        etudiant2.getListeNote().add(new Note("JAVA", 17));
        etudiant2.getListeNote().add(new Note("PHP", 18));

        Etudiant etudiant3 = new Etudiant("stark", "sansa");
        etudiant3.getListeNote().add(new Note("C#", 1));
        etudiant3.getListeNote().add(new Note("AVA", 19));


        List<Etudiant> listeEtudiant = new ArrayList<>();
        listeEtudiant.add(etudiant1);
        listeEtudiant.add(etudiant2);
        listeEtudiant.add(etudiant3);
        return listeEtudiant;
    }

    public static void main(String[] args) {
        System.out.println("Exercice 1 :");
        exercice1();
        System.out.println("Exercice 2 :");
        exercice2();
        System.out.println("Exercice 3 :");
        exercice3();
        System.out.println("Exercice 4 :");
        exercice4();
    }

    public static void exercice1() {
        List<Etudiant> data = getData();

        System.out.println(data.stream()
                .map( etudiant -> etudiant.getNom().toLowerCase() + "." + etudiant.getPrenom().toLowerCase() + "@cesi.com")
                .collect(Collectors.joining(" - ")));
    }

    public static void exercice2(){
        List<Etudiant> data = getData();

        System.out.println(
                data.stream()
                        .filter(etudiant -> etudiant.getPrenom().toLowerCase().startsWith("s"))
                        .sorted(Comparator.comparing(etudiant -> -etudiant.getListeNote().stream().mapToInt(note -> note.getNote()).max().getAsInt()))
                        .map(etudiant -> etudiant.getNom() + " " + etudiant.getPrenom())
                        .findFirst().get());
    }

    public static  void exercice3(){
        List<Etudiant> data = getData();

        List<Evaluation> listNote = data.stream()
                .map(etudiant -> etudiant.getListeNote().stream()
                        .map(note -> new Evaluation(etudiant,note.getNote())).collect(Collectors.toList()))
                .flatMap(List::stream)
                .collect(Collectors.toList());

        System.out.println("tableau bien crée avec stream !");
        System.out.println("contenu du tableau :");
        listNote.forEach(note -> System.out.println(note.getPersonne().getPrenom() + " " + note.getNote()));
    }

    public static  void exercice4(){
        List<Etudiant> data = getData();

        System.out.println(
                data.stream()
                        .sorted(Comparator.comparing(personne -> personne.getListeNote().stream()
                                .mapToInt(Note::getNote).average().getAsDouble()))
                        .map(etudiant ->
                                etudiant.getNom().toUpperCase().charAt(0) + "." +
                                        etudiant.getPrenom().toUpperCase().charAt(0) + " (" +
                                        etudiant.getListeNote().stream()
                                                .mapToInt(Note::getNote).max().getAsInt() + ")"
                        )
                        .collect(Collectors.joining(" > ")));
    }


}

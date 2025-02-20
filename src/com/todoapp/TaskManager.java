package com.todoapp;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.io.File;

public class TaskManager {
    private ArrayList<Task> tasks;
    private Scanner scanner;

    public TaskManager() {
        tasks = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    // Afficher les taches
    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Aucune tâche à afficher.");
        } else {
            System.out.println("\n=== Liste des tâches ===");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                System.out.println((i + 1) + ". [" + task.getTitle() + "] - " + task.getDescription());
            }
        }
    }

    // Ajouter une tache
    public void addTask() {
        System.out.print("Entrez le titre de la tâche : ");
        String title = scanner.nextLine();
        System.out.print("Entrez la description de la tâche : ");
        String description = scanner.nextLine();
        tasks.add(new Task(title, description));
        System.out.println("✅ Tâche ajoutée !");
    }

    // Editer une tache
    public void editTask() {
        displayTasks(); // Afficher les tâches disponibles
        if (!tasks.isEmpty()) {
            System.out.print("Entrez le numéro de la tâche à modifier : ");
            int index = scanner.nextInt();
            scanner.nextLine(); // Consommer le retour à la ligne

            if (index > 0 && index <= tasks.size()) {
                Task task = tasks.get(index - 1);

                System.out.println("Tâche actuelle : [" + task.getTitle() + "] - " + task.getDescription());

                System.out.print("Nouveau titre (laisser vide pour ne pas changer) : ");
                String newTitle = scanner.nextLine();
                if (!newTitle.isEmpty()) {
                    task.setTitle(newTitle);
                }

                System.out.print("Nouvelle description (laisser vide pour ne pas changer) : ");
                String newDescription = scanner.nextLine();
                if (!newDescription.isEmpty()) {
                    task.setDescription(newDescription);
                }

                System.out.println("✅ Tâche mise à jour !");
            } else {
                System.out.println("❌ Numéro de tâche invalide.");
            }
        }
    }

    // Supprimer une tache
    public void removeTask() {
        displayTasks();
        if (!tasks.isEmpty()) {
            System.out.print("Entrez le numéro de la tâche à supprimer : ");
            int index = scanner.nextInt();
            scanner.nextLine(); // Consommer le retour à la ligne
            if (index > 0 && index <= tasks.size()) {
                tasks.remove(index - 1);
                System.out.println("✅ Tâche supprimée !");
            } else {
                System.out.println("❌ Numéro de tâche invalide.");
            }
        }
    }

    // Trier les taches par titre
    public void sortTasksByTitle() {
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                return t1.getTitle().compareTo(t2.getTitle());
            }
        });
        System.out.println("✅ Tâches triées par titre !");
    }

    // Trier les taches par date
    public void sortTasksByDateAdded() {
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                return t1.getDateAdded().compareTo(t2.getDateAdded());
            }
        });
        System.out.println("✅ Tâches triées par date d'ajout !");
    }


    // Sauvegarder les taches dans un fichier
    public void saveTasksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("tasks.txt"))) {
            for (Task task : tasks) {
                writer.write(task.getTitle() + "|" + task.getDescription() + "|" + task.getDateAdded().getTime());
                writer.newLine();
            }
            System.out.println("✅ Tâches sauvegardées dans le fichier.");
        } catch (IOException e) {
            System.out.println("❌ Erreur lors de la sauvegarde des tâches.");
            e.printStackTrace();
        }
    }

    // Charger les taches depuis le fichier 
    public void loadTasksFromFile() {
        File file = new File("tasks.txt");
        if (!file.exists()) {
            System.out.println("Aucun fichier de tâches trouvé. Un nouveau fichier sera créé lors de la sauvegarde.");
            return; // Rien à charger
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] taskData = line.split("\\|");
                if (taskData.length >= 3) {  // Vérifier qu'on a toutes les données
                    String title = taskData[0];
                    String description = taskData[1];
                    long dateAddedMillis = Long.parseLong(taskData[2]);
                    Task task = new Task(title, description);
                    task.setDateAdded(new Date(dateAddedMillis)); // Assigner la date
                    tasks.add(task);
                }
            }
            System.out.println("✅ Tâches chargées depuis le fichier.");
        } catch (IOException e) {
            System.out.println("❌ Erreur lors du chargement des tâches.");
            e.printStackTrace();
        }
    }
}

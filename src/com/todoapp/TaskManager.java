package com.todoapp;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {
    private ArrayList<Task> tasks;
    private Scanner scanner;

    public TaskManager() {
        tasks = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void addTask() {
        System.out.print("Entrez le titre de la tâche : ");
        String title = scanner.nextLine();
        System.out.print("Entrez la description de la tâche : ");
        String description = scanner.nextLine();
        tasks.add(new Task(title, description));
        System.out.println("✅ Tâche ajoutée !");
    }

    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Aucune tâche à afficher.");
        } else {
            System.out.println("\n=== Liste des tâches ===");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i).getTitle());
            }
        }
    }

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
}

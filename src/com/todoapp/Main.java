package com.todoapp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        taskManager.loadTasksFromFile(); // Charger les tâches au démarrage

        while (true) {
            System.out.println("\n=== Gestionnaire de tâches ===");
            System.out.println("1. Ajouter une tâche");
            System.out.println("2. Afficher les tâches");
            System.out.println("3. Modifier une tâche");
            System.out.println("4. Supprimer une tâche");
            System.out.println("5. Trier les tâches");
            System.out.println("6. Quitter");
            System.out.print("Choisissez une option : ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consommer le retour à la ligne

            switch (choice) {
                case 1:
                    taskManager.addTask();
                    taskManager.saveTasksToFile(); // Sauvegarder après ajout
                    break;
                case 2:
                    taskManager.displayTasks();
                    break;
                case 3:
                    taskManager.editTask();
                    taskManager.saveTasksToFile(); // Sauvegarder après modification
                    break;
                case 4:
                    taskManager.removeTask();
                    taskManager.saveTasksToFile(); // Sauvegarder après suppression
                    break;
                case 5:
                    System.out.println("Choisissez un critère de tri :");
                    System.out.println("1. Par titre (ordre alphabétique)");
                    System.out.println("2. Par date d'ajout");
                    int sortChoice = scanner.nextInt();
                    scanner.nextLine(); // Consommer le retour à la ligne
                    if (sortChoice == 1) {
                        taskManager.sortTasksByTitle();
                    } else if (sortChoice == 2) {
                        taskManager.sortTasksByDateAdded();
                    } else {
                        System.out.println("❌ Option de tri invalide.");
                    }
                    break;
                case 6:
                    System.out.println("Au revoir !");
                    taskManager.saveTasksToFile(); // Sauvegarder avant de quitter
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("❌ Option invalide, veuillez réessayer.");
            }
        }
    }
}

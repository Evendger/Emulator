package service;

import java.util.Scanner;

public class UI {


    /**
     * Главное меню
     * Реализовано через switch - case
     */
    public void menu(){
        boolean programDo = true;
        EmulatorMethods emulatorMethods = new EmulatorMethods();
        Scanner scanner = new Scanner(System.in);
        while (programDo){
            try {
                displayChoseList();
                System.out.print("Выберите пункт меню: ");
                int index = scanner.nextInt();
                switch (index){
                    case 1:emulatorMethods.addDocument();
                        break;
                    case 2:emulatorMethods.cancel();
                        break;
                    case 3:emulatorMethods.print();
                        break;
                    case 4:emulatorMethods.stop();
                        break;
                    case 5:emulatorMethods.timeOfPrint();
                        break;
                    case 6:getSortredListOfPrintedDocuments();
                        break;
                    case 7:programDo = false;
                        emulatorMethods.stop();
                        break;
                }
                System.out.println();
            }catch (IllegalArgumentException e) {
                System.out.println("Выбран не верный пункт меню.");
                return;
            }
            catch (NullPointerException e) {
                System.out.println("Программа завершена.");
            }
        }
    }

    /**
     * Меню получения сортированного списка
     * распечатанных документов
     */
    private void getSortredListOfPrintedDocuments(){
        boolean programDo = true;
        EmulatorMethods emulatorMethods = new EmulatorMethods();
        Scanner scanner = new Scanner(System.in);
        while (programDo){
            try {
                displaySortredLists();
                System.out.print("Выберите пункт меню: ");
                int index = scanner.nextInt();
                switch (index){
                    case 1:emulatorMethods.sortredListOfDocumentsByID();
                        break;
                    case 2:emulatorMethods.sortredListOfDocumentsByName();
                        break;
                    case 3:emulatorMethods.sortredListOfDocumentsBySize();
                        break;
                    case 4:emulatorMethods.sortredListOfDocumentsByTime();
                        break;
                    case 5:programDo = false;
                        break;
                }
                System.out.println();
            }catch (IllegalArgumentException e) {
                System.out.println("Выбран не верный пункт меню.");
                return;
            }
        }
    }

    /**
     * Вызов происходит в методе menu()
     */
    private void displayChoseList(){
        System.out.println("1. Добавить документ");
        System.out.println("2. Отменить печать последнего добавленного документа.");
        System.out.println("3. Печать документов.");
        System.out.println("4. Остановить печать документов.");
        System.out.println("5. Среднее время затраченное на печать документов.");
        System.out.println("6. Получить сортированный список напечатанных докуметов");
        System.out.println("7. Выход");
    }

    /**
     * Вызов происходит в методе getSortredListOfPrintedDocuments()
     */
    private void displaySortredLists(){
        System.out.println("1. По порядку печати");
        System.out.println("2. По имени");
        System.out.println("3. По размеру бумаги");
        System.out.println("4. По времени печати");
        System.out.println("5. Назад");
    }
}

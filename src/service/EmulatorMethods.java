package service;

import entity.Document;

import java.util.*;

class EmulatorMethods {
    private static ArrayDeque<Document> documentList = new ArrayDeque<>();
    private static List<Document> printedList = new ArrayList<>();
    private static List<Document> sortredList = new ArrayList<>();
    private Iterator iterator;
    private Scanner scanner = new Scanner(System.in);

    /**
     * Добавление документа
     */
    void addDocument() {
        try {
            boolean programDo = true;
            displayChoiseDocument();
            System.out.print("Выберите документ: ");
            int index = scanner.nextInt();
            switch (index) {
                case 1:
                    documentList.add(new Document("Отчёт", 5, "А4"));
                    System.out.println("Документ добавлен в очередь.");
                    break;
                case 2:
                    documentList.add(new Document("Доклад", 4, "А3"));
                    System.out.println("Документ добавлен в очередь.");
                    break;
                case 3:
                    documentList.add(new Document("Памятка", 3, "А5"));
                    System.out.println("Документ добавлен в очередь.");
                    break;
                case 4:
                    documentList.add(new Document("Плакат", 8, "А1"));
                    System.out.println("Документ добавлен в очередь.");
                    break;
                case 5:
                    programDo = false;
                    break;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Выбран не верный пункт меню.");
        }
    }

    /**
     * Печать документа
     */
    void print() {
        PrintDocument printDocument = new PrintDocument();
        new Thread(printDocument).start();
    }

    /**
     * Остановка печати
     */
    void stop() {
        PrintDocument printDocument = new PrintDocument();
        printDocument.disabled();
        iterator = documentList.iterator();
        System.out.println("Печать остановлена, идёт завершение печати текущего документа.");
        System.out.println("Следующие документы не были напечатаны:");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     * Среднее время печати напечатанных документов
     */
    void timeOfPrint() {
        long time = 0;
        double k = 0;
        iterator = printedList.iterator();
        for (Document document: printedList) {
            time = time + document.getTimeOfPrint();
            k++;
        }
        System.out.println("Среднее время печати документов: " + time / k + " секунд");
    }

    /**
     * Отмена печати последнего добавленного документа
     */
    void cancel() {
        try {
            documentList.removeLast();
            System.out.println("Печать принятого документа отменена.");
        } catch (NoSuchElementException e) {
            System.out.println("Удаление невозможно, идёт печать документа.");
        }
    }

    /**
     * Список напечатанных документов по порядку печати
     */
    void sortredListOfDocumentsByID() {
        iterator = printedList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     * Сортировка по названию
     */
    void sortredListOfDocumentsByName() {
        Comparator<Document> comparator = new Comparator<Document>() {
            @Override
            public int compare(Document document1, Document document2) {
                return document1.getName().compareTo(document2.getName());
            }
        };
        sortredList.sort(comparator);
        iterator = sortredList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     * Сортировка по времени печати
     */
    void sortredListOfDocumentsByTime() {
        sortredList.sort(Comparator.comparingLong(Document::getTimeOfPrint));
        iterator = sortredList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     * Сортировка по размеру бумаги
     */
    void sortredListOfDocumentsBySize() {
        Comparator<Document> comparator = new Comparator<Document>() {
            @Override
            public int compare(Document document1, Document document2) {
                return document1.getPaperSize().compareTo(document2.getPaperSize());
            }
        };
        sortredList.sort(comparator);
        iterator = sortredList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     * Вызов происходит в методе addDocument()
     */
    private void displayChoiseDocument() {
        System.out.println("1. Отчёт");
        System.out.println("2. Доклад.");
        System.out.println("3. Памятка.");
        System.out.println("4. Плакат.");
        System.out.println("5. Назад.");
    }

    /**
     * Внутренний клас
     * Реализована печать документа в отдельном потоке
     *
     */
    class PrintDocument implements Runnable {

        void disabled() {
            Thread.currentThread().interrupt();
        }

        public void run() {
            iterator = documentList.iterator();
            long timeOfPrint;
            while (!Thread.currentThread().isInterrupted()) {
                while (iterator.hasNext()) {
                    try {
                        timeOfPrint = documentList.getFirst().getTimeOfPrint();
                        System.out.println("Время печати документа: " + timeOfPrint + " секунд.");
                        printedList.add(documentList.getFirst());
                        sortredList.add(documentList.getFirst());
                        iterator.next();
                        documentList.removeFirst();
                        Thread.sleep(timeOfPrint * 1000);
                        System.out.println("Печать завершена");
                    } catch (InterruptedException | NoSuchElementException | ConcurrentModificationException e) {
                        break;
                    } catch (NullPointerException e) {
                        System.out.println("В очереди печати нет докуметов");
                        break;
                    }
                }
                Thread.currentThread().interrupt();
            }
        }
    }
}

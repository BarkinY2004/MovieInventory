import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DoublyLinkedList movieList = new DoublyLinkedList();

        // in this part do not forget if you want to include movies that are in txt file , first you need to pick one as a choice

        int choice = 0;
        while (choice != 8) {
            printMenu();
            choice = getUserChoice();
            // here is the switch case
            switch (choice) {
                case 1:
                    movieList.loadFromFile("bilgiler.txt");
                    break;
                case 2:
                    Movie movie = getMovieFromUser();
                    movieList.insert(movie);
                    break;
                case 3:
                    String title = getTitleFromUser();
                    printMovieDetails(movieList, title);
                    break;
                case 4:
                    title = getTitleFromUser();
                    movieList.delete(title);
                    break;
                case 5:
                    movieList.printFromHead();
                    break;
                case 6:
                    movieList.printFromTail();
                    break;
                case 7:
                    int year = getYearFromUser();
                    movieList.printMoviesBeforeYear(year);
                    break;
                case 8:
                    movieList.saveToFile("bilgiler.txt");
                    System.out.println("Exiting the application...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("===== Movie Inventory Application =====");
        System.out.println("1) Load movie list from file(PLEASE DO NOT FORGET TO LOAD THE FILE IF YOU WANT TO ADD A MOVIE IN IT");
        System.out.println("2) Add a movie to the list");
        System.out.println("3) Search movie by title");
        System.out.println("4) Delete a movie from the list");
        System.out.println("5) Print movies from head to tail");
        System.out.println("6) Print movies from tail to head");
        System.out.println("7) Print movies before a specific year");
        System.out.println("8) Exit");
        System.out.print("Enter your choice: ");
    }
    // takes a choice between 1-8
    private static int getUserChoice() {
        boolean input = false;
        int returnValue = 0;
        while (!input){
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                returnValue = Integer.parseInt(reader.readLine());
                input = true ;
            } catch (Exception e) {
                System.out.println("Enter an integer between 1-8!");
                System.out.print("Enter your choice: ");
            }
        }
        return returnValue;
    }
    // gets the inputted movie
    private static Movie getMovieFromUser() {
        boolean input= false;
        Movie returnValue = null;
        while (!input){
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

                System.out.print("Enter the year: ");
                int year = Integer.parseInt(reader.readLine());

                System.out.print("Enter the title: ");
                String title = reader.readLine();

                System.out.print("Enter the genre: ");
                String genre = reader.readLine();

                System.out.print("Enter the director: ");
                String director = reader.readLine();

                System.out.print("Enter the number of actors: ");
                int numActors = Integer.parseInt(reader.readLine());

                List<Actor> actors = new ArrayList<>();
                for (int i = 0; i < numActors; i++) {
                    System.out.print("Enter the name of actor " + (i + 1) + ": ");
                    String name = reader.readLine();

                    System.out.print("Enter the gender of actor " + (i + 1) + ": ");
                    String gender = reader.readLine();

                    System.out.print("Enter the nationality of actor " + (i + 1) + ": ");
                    String nationality = reader.readLine();

                    actors.add(new Actor(name, gender, nationality));
                }

                returnValue = new Movie(year, title, genre, director, actors);
                input = true;
            } catch (Exception e) {
                System.out.println("year and number of actors must be integer !!");
                System.out.println("others must be string !!");

            }
        }
        return returnValue;
    }
    // gets the title when user wants to delete or find the infos about it
    private static String getTitleFromUser() {
        boolean input= false;
        String returnValue = null;
        while (!input){
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Enter the title: ");
                returnValue= reader.readLine();
                input = true;
            } catch (Exception e) {
                System.out.println("title must be string !!");

            }
        }
        return returnValue;
    }
    // when user wants to find movies under a year , this method helps us
    private static int getYearFromUser() {
        boolean input= false;
        int returnValue = 0;
        while (!input){
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Enter the year: ");
                returnValue = Integer.parseInt(reader.readLine());
                input = true;
            } catch (Exception e) {
                System.out.println("year must be int !!");
            }
        }
        return returnValue ;
    }
    // and this one prints the details about the movie
    private static void printMovieDetails(DoublyLinkedList movieList, String title) {

        Node current = movieList.getHead();
        while (current != null) {
            if (current.getMovie().getTitle().equalsIgnoreCase(title)) {
                System.out.println(current.getMovie());
                return;
            }
            current = current.getNext();
        }
        System.out.println("Movie '" + title + "' not found in the list.");
    }

}
import java.io.*;
import java.util.ArrayList;
import java.util.List;
// this class is really important for the app to work good enough
public class DoublyLinkedList {
    // we create two nodes here: head and tail (front node and end node)
    private Node head;
    private Node tail;

    public Node getHead() {
        return head;
    }
    // here we have a constructor without any parameter
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }
    // this one looks if movie list is empty or not
    public boolean isEmpty() {
        return head == null;
    }
    // here we have a method for inserting a movie
    public void insert(Movie movie) {
        Node newNode = new Node(movie);
        // we insert the movie for the year but if years are same then we look the name of movies

        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else if (movie.getYear() < head.getMovie().getYear()) {
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
        } else if (movie.getYear() > tail.getMovie().getYear()) {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        } else {

            Node current = head;
            while (current != null && current.getMovie().getYear() <= movie.getYear() ) {

                if (current.getMovie().getYear() == movie.getYear() &&
                        (current.getMovie().getTitle().toUpperCase()).compareTo(movie.getTitle().toUpperCase()) > 0) {

                    break;
                }
                current = current.getNext();

            }

            if (current == null) {
                // we insert new movie after(end of them) other movies
                newNode.setNext(null);
                newNode.setPrev(tail);
                tail.setNext(newNode);
                tail = newNode;
            } else if (current == head) {
                //  we insert new movie before other movies
                newNode.setNext(head);
                newNode.setPrev(null);
                head.setPrev(newNode);
                head = newNode;
            } else {
                // we insert new movie between other movies
                newNode.setNext(current);
                newNode.setPrev(current.getPrev());
                current.getPrev().setNext(newNode);
                current.setPrev(newNode);
            }
        }
    }
    // this method deletes the movie that you asked for. if it is not in the list than we say that.
    // if list is already empty we say that to.
    public void delete(String title) {
        if (isEmpty()) {
            System.out.println("List is empty. Can not delete from an empty list.");
            return;
        }

        Node current = head;
        while (current != null) {
            if (current.getMovie().getTitle().equalsIgnoreCase(title)) {
                if (current == head && current == tail) {
                    head = null;
                    tail = null;
                } else if (current == head) {
                    head = current.getNext();
                    head.setPrev(null);
                } else if (current == tail) {
                    tail = current.getPrev();
                    tail.setNext(null);
                } else {
                    current.getPrev().setNext(current.getNext());
                    current.getNext().setPrev(current.getPrev());
                }
                System.out.println("Movie '" + title + "' deleted from the list.");
                return;
            }
            current = current.getNext();
        }

        System.out.println("Movie '" + title + "' not found in the list.");
    }
    // this is for printing from the head
    public void printFromHead() {
        if (isEmpty()) {
            System.out.println("List is empty.");
            return;
        }

        Node current = head;
        while (current != null) {
            System.out.println(current.getMovie());
            current = current.getNext();
        }
    }
    // this is for printing from the tail
    public void printFromTail() {
        if (isEmpty()) {
            System.out.println("List is empty.");
            return;
        }

        Node current = tail;
        while (current != null) {
            System.out.println(current.getMovie());
            current = current.getPrev();
        }
    }
    // here you pick a specific year ,and we print the movies before that year
    public void printMoviesBeforeYear(int year) {
        if (isEmpty()) {
            System.out.println("List is empty.");
            return;
        }

        Node current = head;
        while (current != null) {
            if (current.getMovie().getYear() < year) {
                System.out.println(current.getMovie());
            }
            current = current.getNext();
        }
    }
    // this one helps us to save it to the txt file
    public void saveToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            Node current = head;
            while (current != null) {
                writer.write(current.getMovie().toString());
                writer.newLine();
                current = current.getNext();
            }
            System.out.println("List saved to file '" + fileName + "'.");
        } catch (IOException e) {
            System.out.println("Error happened while saving the list to file: " + e.getMessage());
        }
    }
    // and this one helps us to load the movies from txt file
    public void loadFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {


                String[] parts_of_line = line.split(",",5);
                int year = Integer.parseInt(parts_of_line[0]);
                String title = parts_of_line[1].trim();
                String genre = parts_of_line[2].trim();
                String director = parts_of_line[3].trim();
                List<Actor> actors = parseActors(parts_of_line[4].trim());

                Movie movie = new Movie(year, title, genre, director, actors);
                insert(movie);
            }
            System.out.println("List loaded from file '" + fileName + "'.");
        } catch (IOException e) {
            System.out.println("Error happened while loading the list from file: " + e.getMessage());
        }
    }
    // and this one helps us to parting actors line
    private List<Actor> parseActors(String actorsString) {

        List<Actor> actors = new ArrayList<>();
        String[] actorParts = actorsString.substring(1, actorsString.length() - 1).split("\\)");
        for (String actorPart : actorParts) {
            String[] parts_for_actors = actorPart.trim().substring(1).split(",");
            if (parts_for_actors.length >= 3) {
                String name = parts_for_actors[0].trim();
                String gender = parts_for_actors[1].trim();
                String nationality = parts_for_actors[2].trim();
                actors.add(new Actor(name, gender, nationality));
            }
        }
        return actors;
    }
}

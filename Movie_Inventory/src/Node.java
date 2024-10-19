public class Node {
    private Movie movie;
    private Node prev;
    private Node next;
    // a constructor with only one parameter
    public Node(Movie movie) {
        this.movie = movie;
        this.prev = null;
        this.next = null;
    }
    // here are getters and setters
    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}

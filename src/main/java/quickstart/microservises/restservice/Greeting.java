package quickstart.microservises.restservice;


public class Greeting {

    private final long id;
    private final String connect;

    public Greeting(long id, String connect) {
        this.id = id;
        this.connect = connect;
    }

    public long getId() {
        return id;
    }

    public String getConnect() {
        return connect;
    }
}

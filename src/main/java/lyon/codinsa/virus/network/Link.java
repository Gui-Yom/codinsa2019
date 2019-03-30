package lyon.codinsa.virus.network;

public class Link {

    public int id;
    public int debit;

    public Link(int id, int debit) {

        this.id = id;
        this.debit = debit;
    }

    @Override
    public String toString() {

        return "Link{" +
               "id=" + id +
               ", debit=" + debit +
               '}';
    }
}

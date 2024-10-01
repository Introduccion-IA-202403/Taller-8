public class Hecho {
    private Boolean NOT;
    private String id;

    public Hecho() {
        this.NOT = false;
        this.id = "";
    }

    public Hecho(Boolean NOT, String id) {
        this.NOT = NOT;
        this.id = id;
    }

    public Boolean getNOT() {
        return NOT;
    }

    public void setNOT(Boolean NOT) {
        this.NOT = NOT;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.NOT) sb.append("¬");
        sb.append(id);
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Hecho h) {
            return (this.NOT == h.NOT && this.id.equals(h.id));
        }
        else return false;
    }
}

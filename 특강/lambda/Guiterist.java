public class Guiterist {
    private int no;
    private String name;
    private String teamName;
    public String guiter;

    public Guiterist(Builder builder) {
        this.no = builder.no;
        this.name = builder.name;
        this.teamName = builder.teamName;
        this.guiter = builder.guiter;
    }

    public String toString() {
        return this.no + " , " + this.name + " , " + this.teamName + " , " + this.guiter;
    }

    public static class Builder {
        private int no;
        private String name;
        private String teamName;
        private String guiter;

        public Builder no(int no) {
            this.no = no;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder teamName(String teamName) {
            this.teamName = teamName;
            return this;
        }

        public Builder guiter(String guiter) {
            this.guiter = guiter;
            return this;
        }

        public Guiterist build() {
            return new Guiterist(this);
        }
    }

    // -----------------------------------------
    // public int getNo() {
    // return this.no;
    // }

    // public void setNo(int value) {
    // this.no = value;
    // }
    // ------------------------------------------
    // public void setInfo (int no) {
    // this.no = no;
    // }

    // public void setInfo (int no, int team) {

    // }
    // -----------------------------------------
    // public Guiterist(int no) {
    // this.no = no;
    // }

    // public Guiterist(int no, String name) {
    // this(no);
    // this.name = name;
    // }

    // public Guiterist(int no, String name, String teamName) {
    // this(no, name);
    // this.teamName = teamName;
    // }
}

class Test {
    public static void main(String[] args) {
        Guiterist guiterist = new Guiterist.Builder().no(1).name("Randy").teamName("Quite").guiter("Les paul").build();
        System.out.println(guiterist);
    }
}
public enum BodyPart {
    HEAD("Голова"),
    LARM("Левая рука"),
    RARM("Правая рука"),
    LLEG("Левая нога"),
    RLEG("Правая нога");

    private final String name;

    BodyPart(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

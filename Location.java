public enum Location {

    ANYWHERE("Где-то", false),
    TRAP("Западня", true),
    PYATACHOKHOUSE("Дом Пяточка", false),
    SIXPINES("Шесть Сосен", false),
    WAYTOSIXPINES("Путь к Шести Соснам", false);

    private final String name;
    public final boolean hardToGetOut;

    Location(String name, boolean hardness){
        this.name = name;
        this.hardToGetOut = hardness;
    }


    @Override
    public String toString(){
        return name;
    }

}

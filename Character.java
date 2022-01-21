import java.util.Arrays;
import java.util.Objects;

public abstract class Character implements AbleToSpeak {
    private final String name;
    private Location characterLocation;
    private boolean[] stucked = new boolean[5];
    private Container[] stuckedIn = new Container[5];
    private int[] stuckness = new int[5];
    private Plan currentPlan = new Plan();


    public Character(){
        this("Unnamed");
    }

    public Character(String name){
        this(name, Location.ANYWHERE);
    }

    public Character(String name, Location location){
        if(name == "" || name == null)
            throw new NullOrEmptyNameException("Name can't be null or empty");
        this.name = name;
        this.characterLocation = location;
    }

    public abstract void goToLocation(Location goal);

    public abstract void tryUnstuck(BodyPart bodyPart);

    public class Plan{
        private Location placeToGo;
        private Character lookForCharacter;
        private Location lookForLocation;
        private Plan nextPlan;
        private boolean success;

        public Plan(){
            placeToGo = characterLocation;
        }

        public Plan(Location goal){
            placeToGo = goal;
        }

        public Plan(Location goal, Character lookForCharacter, Location lookForLocation){
            placeToGo = goal;
            this.lookForCharacter = lookForCharacter;
            this.lookForLocation = lookForLocation;
        }

        public void setNextPlan(Plan nextPlan) {
            System.out.print("Если " + Character.this.toString() + " найдёт " + currentPlan.lookForCharacter.toString()
                    + " в " + currentPlan.lookForLocation.toString() + " то он пойдёт в " + nextPlan.placeToGo + ". ");
            this.nextPlan = nextPlan;
        }

        private boolean checkGoal(){
            return(lookForLocation == lookForCharacter.getCharacterLocation());
        }

        private boolean checkLocation(){ return(placeToGo == characterLocation); }

        public void checkPlan(){
            if (!checkLocation()){
                System.out.print(Character.this.toString() + " сначала думал что " + lookForCharacter.toString() +
                        " в " + lookForLocation.toString() + ", потом думал что " + lookForCharacter.toString() + " не в "
                        + lookForLocation.toString() + ". ");
            }
            else if (checkGoal()){
                System.out.print(Character.this.toString() + " был уверен что " + lookForCharacter.toString() + " в " +
                        lookForLocation.toString() + " так как услышал его там. ");
            }
            else{
                System.out.print(Character.this.toString() + " был уверен что " + lookForCharacter.toString() + " не в "
                        + lookForLocation.toString() + ". ");
            }
        }

        public boolean checkCondition(){
            return(checkGoal() && checkLocation());
        }

        public void commitPlan(){
            success = true;
        }

        public void endPlan(){
            if(checkCondition() && success)
                currentPlan = nextPlan;
            else
                currentPlan = new Plan();
        }

        public Character getLookForCharacter(){
            return lookForCharacter;
        }

        public Location getLookForLocation() {
            return lookForLocation;
        }

        public Location getPlaceToGo() {
            return placeToGo;
        }

        public boolean checkSuccess(){
            return success;
        }

    }

    public void setCurrentPlan(Plan plan){
        currentPlan = plan;
        System.out.print("У " + this.toString() + " появился план. ");
        System.out.print(this.toString() + " планирует пойти в " + currentPlan.placeToGo + ". ");
        if (currentPlan.lookForLocation != null){
            System.out.print(this.toString() + " планирует посмотреть есть ли " + currentPlan.lookForCharacter
                    + " в " + currentPlan.lookForLocation + ". ");
        }
    }

    public void lookIn(Location location){
        System.out.print(this.toString() + " заглянул в " + location.toString() + ". ");
    }

    public Plan getCurrentPlan(){return (currentPlan);}

    public void unstuck(BodyPart part){
        stuckedIn[part.ordinal()].setEmpty(true);
        stucked[part.ordinal()] = false;
        stuckedIn[part.ordinal()] = null;
    }

    public void stuck(BodyPart part, Container cont, int stuckness) throws BodyPartNotFoundException{
        if(part.ordinal() > 5){
            throw new BodyPartNotFoundException(this.toString() + " has no BodyPart " + part.toString());
        }
        if(!stucked[part.ordinal()] && cont.isEmpty()){
            System.out.print(part.toString() + " персонажа " + name + " застряла в " + cont + ". ");
            stucked[part.ordinal()] = true;
            stuckedIn[part.ordinal()] = cont;
            cont.setEmpty(false);
            this.setStuckness(part, stuckness);
        }
        else if(!cont.isEmpty()){
            System.out.print(cont + " уже занят. ");
        }
        else{
            System.out.print(part.name() + "уже застряла. ");
        }
    }

    public void stuck(BodyPart part, Container cont) throws BodyPartNotFoundException{
        stuck(part, cont, 1);
    }

    public boolean isStucked(BodyPart part){ return stucked[part.ordinal()]; }

    public Container getStuckedContainer(BodyPart part) {
        return stuckedIn[part.ordinal()];
    }

    public Location getCharacterLocation() { return characterLocation; }

    public void setCharacterLocation(Location location) {
        this.characterLocation = location;
        System.out.print(this.toString() + " пришёл в " + location.toString() + ". ");
    }

    public boolean isBlinded() { return stucked[BodyPart.HEAD.ordinal()]; }

    public int getStuckness(BodyPart part) {
        return stuckness[part.ordinal()];
    }

    public void setStuckness(BodyPart part, int stuckness){
        if (stuckness > this.stuckness[part.ordinal()])
            System.out.print( getStuckedContainer(part) + " стал сидеть крепче. ");
        else
            System.out.print( getStuckedContainer(part) + " сидит не так крепко. ");
        this.stuckness[part.ordinal()] = stuckness;
    }

    public void raiseBodyPart(BodyPart part){
        System.out.print(this.toString() + " поднял " + part.toString());
        if(stucked[part.ordinal()]){
            System.out.print(" (вместе с " + stuckedIn[part.ordinal()] + ")");
        }
        System.out.print(". ");
    }

    @Override
    public String toString(){
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Character)) return false;
        Character character = (Character) o;
        return Objects.equals(name, character.name) && Arrays.equals(stucked, character.stucked);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name);
        result = 31 * result + Arrays.hashCode(stucked);
        return result;
    }
}
